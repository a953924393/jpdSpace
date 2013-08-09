package com.jingpaidang.cshop.service.move;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.taobao.api.ApiException;
import org.springframework.stereotype.Service;

import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.request.ware.WareAddRequest;
import com.jd.open.api.sdk.response.ware.WareAddResponse;
import com.jingpaidang.cshop.common.constant.BasicConstant;
import com.jingpaidang.cshop.common.utils.JSONUtils;
import com.jingpaidang.cshop.dao.convert.ConvertRuleMapper;
import com.jingpaidang.cshop.dao.move.MoveHistoryMapper;
import com.jingpaidang.cshop.dao.user.AccountMapper;
import com.jingpaidang.cshop.domain.convert.PlatformConvertRule;
import com.jingpaidang.cshop.domain.move.MoveHistory;
import com.jingpaidang.cshop.domain.user.Account;
import com.jingpaidang.cshop.rpc.jd.JdService;
import com.jingpaidang.cshop.rpc.tb.TbService;
import com.taobao.api.domain.Item;
import com.taobao.api.response.ItemsListGetResponse;

/**
 * 
 * @ClassName:	MoveHistoryService 
 * @Description:TODO(搬家历史业务) 
 * @author:	Alex
 * @date:	2013-7-23 下午1:11:51 
 *
 */
@Service("moveHistoryService")
public class MoveHistoryService {
	
	@Resource
	private MoveHistoryMapper moveHistoryMapper;
	@Resource
	private ConvertRuleMapper convertRuleMapper;
	@Resource
    private AccountMapper accountMapper;
	@Resource
    private TbService tbService;
    @Resource
    private JdService jdService;
    @Resource
    private MoveService moveService ;
	/**
	 * 
	 * @Title:getMoveHistoryList 
	 * @Description:TODO(查询搬家历史列表) 
	 * @param:@param paras
	 * @param:@return 
	 * @return:List<MoveHistory> 
	 * @throws 
	 * @Create: 2013-7-23 下午1:14:05
	 * @Author : Alex
	 */
	public List<MoveHistory> getMoveHistoryList(Map<String,Object> paras){
		List<MoveHistory> list = moveHistoryMapper.getMoveHistoryList(paras) ;
		return list ;
	}
	/**
	 * 
	 * @Title:reMoveTb2Jd 
	 * @Description:TODO(搬家历史重新搬家) 
	 * @param:@param historyIds
	 * @param:@return
	 * @param:@throws Exception 
	 * @return:Map<WareAddRequest,WareAddResponse> 
	 * @throws 
	 * @Create: 2013-7-24 下午3:43:36
	 * @Author : Alex
	 */
	public Map<WareAddRequest, WareAddResponse> reMoveTb2Jd(String historyIds) throws Exception{
		Map<WareAddRequest, WareAddResponse> responseMap = new HashMap<WareAddRequest, WareAddResponse>();
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("ids", historyIds.split(","));
		List<MoveHistory> list = moveHistoryMapper.getMoveHistoryListByIds(paras);
		if(list!=null && list.size()>0){
			for(MoveHistory history:list){
				Long ruleId = history.getRuleId();
				String shopCategoryId = history.getShopCategoryId();
				String itemId = "" + history.getItemId() ;
				moveTb2Jd(history.getId(),responseMap,itemId,ruleId,shopCategoryId);
			}
		}
		return null ;
	}
	/**
	 * 
	 * @Title:moveTb2Jd 
	 * @Description:TODO(从淘宝搬家到京东) 
	 * @param:@param historyId
	 * @param:@param responseMap
	 * @param:@param itemId
	 * @param:@param ruleId
	 * @param:@param shopCategoryId
	 * @param:@throws JdException 
	 * @return:void 
	 * @throws 
	 * @Create: 2013-7-24 下午5:12:20
	 * @Author : Alex
	 */
	public void moveTb2Jd(Long historyId,Map<WareAddRequest, WareAddResponse> responseMap,String itemId, Long ruleId, String shopCategoryId) throws JdException, IOException, ApiException {
        PlatformConvertRule platformConvertRule = convertRuleMapper.findPlatformConvertRuleById(ruleId);

        long srcAccountId = platformConvertRule.getSrcAccountId();

        long targetAccountId = platformConvertRule.getTargetAccountId();

        Account srcAccount = accountMapper.findAccountById((int) srcAccountId);

        Account targetAccount = accountMapper.findAccountById((int) targetAccountId);

        ItemsListGetResponse tbItemsListResponse = tbService.getItemsByNumiid(srcAccount.getPlatformAccessToken(), itemId);

        List<Item> tbItemsListResponseItems = tbItemsListResponse.getItems();

        for(Item item : tbItemsListResponseItems) {
            WareAddRequest wareAddRequest = moveService.wareTb2Jd(item, platformConvertRule, shopCategoryId);
            WareAddResponse wareAddResponse = jdService.addWare(wareAddRequest, targetAccount.getPlatformAccessToken());
            responseMap.put(wareAddRequest, wareAddResponse);
            Map<String,Object> paras = new HashMap<String,Object>();
            paras.put("id", historyId);
            if("0".equals(wareAddResponse.getCode())){
        		paras.put("status", BasicConstant.MOVEHISTORY_STATUS_SUCCESS);
        		paras.put("statusReason", "");
        	}else{
        		paras.put("status", BasicConstant.MOVEHISTORY_STATUS_FAIL);
        		Map<String, Map> jsonMap = JSONUtils.toObject(wareAddResponse.getMsg(), Map.class);
        		paras.put("statusReason", ""+jsonMap.get("error_response").get("zh_desc"));
        	}
            paras.put("modified", new Date());
            moveHistoryMapper.updateStatusAndReasonById(paras);
        }
    }
	public void deleteHistory(Map<String,Object> paras){
		moveHistoryMapper.deleteHistory(paras);
	}
}
