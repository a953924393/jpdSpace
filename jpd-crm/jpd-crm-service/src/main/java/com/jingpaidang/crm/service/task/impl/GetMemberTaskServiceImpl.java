package com.jingpaidang.crm.service.task.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.order.OrderResult;
import com.jd.open.api.sdk.domain.order.OrderSearchInfo;
import com.jd.open.api.sdk.domain.order.UserInfo;
import com.jd.open.api.sdk.response.order.OrderSearchResponse;
import com.jingpaidang.crm.common.constant.BasicConstant;
import com.jingpaidang.crm.common.utils.DateUtil;
import com.jingpaidang.crm.dao.merchant.MerchantMapper;
import com.jingpaidang.crm.domain.member.Member;
import com.jingpaidang.crm.domain.merchant.Merchant;
import com.jingpaidang.crm.domain.merchantmember.MerchantMember;
import com.jingpaidang.crm.domain.order.MemberOrder;
import com.jingpaidang.crm.rpc.jos.JdService;
import com.jingpaidang.crm.service.member.MemberService;
import com.jingpaidang.crm.service.merchantmenber.MerchantMemberService;
import com.jingpaidang.crm.service.order.MemberOrderService;
import com.jingpaidang.crm.service.task.GetMemberTaskService;

public class GetMemberTaskServiceImpl implements GetMemberTaskService {
	Logger logger = Logger.getLogger("tasklog") ;
	/**
	 * 每页条数最大值100
	 */
	private final static String PAGESIZE = "100" ;
	@Resource
	private MerchantMapper merchantMapper ;
	@Resource
	private JdService jdService ;
	@Resource
	private MemberOrderService memberOrderService ;
	@Resource
	private MemberService memberService ;
	@Resource
	private MerchantMemberService merchantMemberService ;
	
	public void getMember(){
		logger.info("========定时任务开始========");
		//商家列表
		List<Merchant> merchantList = getMerchantList() ;
		if(merchantList!=null && merchantList.size()>0){
			for(Merchant merchant:merchantList){
				if(merchant.getMerchantAccessToken()==null || "".equals(merchant.getMerchantAccessToken())){
					continue ;
				}else{
					List<OrderSearchInfo> orderSearchInfoList = null ;
					orderSearchInfoList = getOrderSearchInfoList(merchant.getMerchantAccessToken(),merchant.getMerchantType());
					addData(orderSearchInfoList,merchant);
				}
			}
		}
		logger.info("========定时任务结束========");
	}
	/**
	 * 
	 * @Title:addData 
	 * @Description:TODO(将数据添加到数据库) 
	 * @param:@param list
	 * @param:@param merchant 
	 * @return:void 
	 * @throws 
	 * @Create: 2013-8-8 下午2:12:26
	 * @Author : Alex
	 */
	private void addData(List<OrderSearchInfo> list,Merchant merchant){
		if(list!=null && list.size()>0 && merchant!=null){
			logger.info("merchantId=" + merchant.getMerchantNum() + "订单数：" + list.size());
			int memberCount = 0 ;
			for(OrderSearchInfo orderInfo:list){
				addMemberOrder(orderInfo,merchant);
				boolean flag = addMember(orderInfo,merchant);
				if(flag){
					memberCount+=1 ;
				}
			}
			logger.info("merchantId=" + merchant.getMerchantNum() + "添加的会员数：" + memberCount);
		}
	}
	/**
	 * 
	 * @Title:addMember 
	 * @Description:TODO(添加会员) 
	 * @param:@param orderInfo
	 * @param:@param merchant 
	 * @return:void 
	 * @throws 
	 * @Create: 2013-8-8 下午4:23:52
	 * @Author : Alex
	 */
	private boolean addMember(OrderSearchInfo orderInfo,Merchant merchant){
		if(orderInfo==null || merchant==null){
			return false;
		}
		//会员的京东账号信息
		String jdId = orderInfo.getPin() ;
		//判断会员是否已经存在
		boolean flag = memberService.isMemberExist(jdId) ;
		Member member = new Member();
		member.setMemberJdId(orderInfo.getPin());
		member.setMemberLastConsumTime(DateUtil.StrToDateTime(orderInfo.getOrderStartTime()));
		if(flag){
			//获取已存在的会员信息
			Member existMember = memberService.getMemberByJdId(jdId) ;
			MerchantMember mm = new MerchantMember();
			mm.setMemberId(existMember.getId());
			mm.setMerchantId((int)merchant.getId());
			mm.setCreated(new Date()) ;
			boolean flag1 = merchantMemberService.isMerchantMemberExist(mm);
			if(!flag1){
				merchantMemberService.saveMerchantMember(mm) ;
			}
			memberService.updateLastConsumTime(member);
			return false;
		}
		member.setMemberCreated(new Date());
		member.setMemberModified(new Date());
		member.setMemberOperator("task") ;
		int memberId = memberService.saveMember(member) ;
		//添加桥表
		MerchantMember merchantMember = new MerchantMember();
		merchantMember.setMemberId(memberId);
		merchantMember.setMerchantId((int)merchant.getId());
		merchantMember.setCreated(new Date()) ;
		merchantMemberService.saveMerchantMember(merchantMember);
		return true ;
	}
	/**
	 * 
	 * @Title:addMemberOrder 
	 * @Description:TODO(添加订单信息) 
	 * @param:@param orderInfo
	 * @param:@param merchant 
	 * @return:void 
	 * @throws 
	 * @Create: 2013-8-8 下午2:41:56
	 * @Author : Alex
	 */
	private void addMemberOrder(OrderSearchInfo orderInfo,Merchant merchant){
		if(orderInfo!=null && merchant!=null){
			String orderId = orderInfo.getOrderId() ;
			//判断是否已经存在该订单
			boolean flag = memberOrderService.isOrderExist(orderId) ;
			if(flag){
				return ;
			}
			UserInfo userInfo = orderInfo.getConsigneeInfo() ;
			MemberOrder memberOrder = new MemberOrder();
			memberOrder.setMerchantNum(merchant.getMerchantNum()) ;
			memberOrder.setMemberJdId(orderInfo.getPin()) ;
			memberOrder.setOrderId(orderId) ;
			if(userInfo!=null){
				memberOrder.setConsigneeName(userInfo.getFullname()) ;
				memberOrder.setConsigneeAddress(userInfo.getFullAddress()) ;
				memberOrder.setConsigneeTelephone(userInfo.getMobile()) ;
			}
			memberOrder.setOrderTotalPrice(orderInfo.getOrderTotalPrice());
			memberOrder.setCreated(new Date()) ;
			memberOrder.setModified(new Date());
			memberOrder.setOperator("task") ;
			memberOrderService.insert(memberOrder) ;
		}
	}
	/**
	 * 
	 * @Title:getOrderSearchInfoList 
	 * @Description:TODO(获取订单列表) 
	 * @param:@param accessToken
	 * @param:@return 
	 * @return:List<OrderSearchInfo> 
	 * @throws 
	 * @Create: 2013-8-8 上午11:00:32
	 * @Author : Alex
	 */
	private List<OrderSearchInfo> getOrderSearchInfoList(String accessToken,String merchantType){
		List<OrderSearchInfo> list = new ArrayList<OrderSearchInfo>() ;
		Calendar calendar = Calendar.getInstance() ;
		calendar.add(calendar.DAY_OF_MONTH, -1) ;
		calendar.set(calendar.HOUR_OF_DAY, 0);
		calendar.set(calendar.MINUTE, 0);
		calendar.set(calendar.SECOND, 0);
		String startDate = DateUtil.date2str(calendar.getTime()) ;
		calendar.set(calendar.HOUR_OF_DAY, 23);
		calendar.set(calendar.MINUTE, 59);
		calendar.set(calendar.SECOND, 59);
		String endDate = DateUtil.date2str(calendar.getTime());
		String orderState = "";
		if(merchantType!=null && ("LBP".equals(merchantType.toUpperCase()) || "SOPL".equals(merchantType.toUpperCase()))){
			orderState = "WAIT_SELLER_STOCK_OUT,SEND_TO_DISTRIBUTION_CENER,DISTRIBUTION_CENTER_RECEIVED,WAIT_GOODS_RECEIVE_CONFIRM,RECEIPTS_CONFIRM,FINISHED_L,TRADE_CANCELED" ;
		}else{
			orderState = "WAIT_SELLER_STOCK_OUT,WAIT_GOODS_RECEIVE_CONFIRM,FINISHED_L,TRADE_CANCELED" ;
		}
		
		// 获取50页的订单
		logger.info("startDate===" + startDate + ",endDate====" + endDate);
		for(int i=1;i<50;i++){
			try {
				OrderSearchResponse response= jdService.getOrderSearchResponse(accessToken, startDate, endDate, orderState, i+"", PAGESIZE) ;
				if(response==null){
					break ;
				}else{
					OrderResult orderResult = response.getOrderInfoResult();
					if(orderResult!=null){
						list.addAll(orderResult.getOrderInfoList()) ;
					}else{
						break ;
					}
				}
			} catch (JdException e) {
				break ;
			}
		}
		return list ;
	}
	/**
	 * 
	 * @Title:getMerchantList 
	 * @Description:TODO(获取所有商家列表) 
	 * @param:@return 
	 * @return:List<Merchant> 
	 * @throws 
	 * @Create: 2013-8-8 上午8:53:32
	 * @Author : Alex
	 */
	
	private List<Merchant> getMerchantList(){
		List<Merchant> list = merchantMapper.findAllMerchant(BasicConstant.MERCHANT_STATUS_NORMAL) ;
		return list ;
	}
}
