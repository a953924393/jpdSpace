package com.jingpaidang.crm.service.merchant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingpaidang.crm.common.constant.BasicConstant;
import com.jingpaidang.crm.dao.merchant.MerchantMapper;
import com.jingpaidang.crm.domain.merchant.Merchant;
import com.jingpaidang.crm.rpc.jos.JdService;
/** 
 * @ClassName:	MerchantService 
 * @Description:TODO 
 * @author:	Tom
 * @date:	2013-8-8 上午10:44:57 
 */
@Service
public class MerchantService {
		
	@Resource
	private MerchantMapper merchantMapper;
		
    /**
     * 通过accessToken查找到refreshToken
     * @param access
     * @return
     */
        public String findRefreshByAccess(String access) {
            //TODO

            new JdService();
            return null;
        }
        
      /**
       * @Title:insertMerchant 
       * @Description:TODO(这里用一句话描述这个方法的作用) 
       * @param:@param merchant 
       * @return:void
       */
        public void insertMerchant(Merchant merchant)
		{
			
			if(merchantMapper.findMerchantByMerchantNum(merchant.getMerchantNum()) == null )
			{
				if(merchant.getStatus()!=BasicConstant.MERCHANT_STATUS_NORMAL&&merchant.getStatus()!=BasicConstant.MERCHANT_STATUS_REMOVE)
					merchant.setStatus(BasicConstant.MERCHANT_STATUS_NORMAL);
			     merchantMapper.insertMerchant(merchant);
			}
			
				
		}
		/**
		 * @Title:deleteMerchantById 
		 * @Description:TODO(这里用一句话描述这个方法的作用) 
		 * @param:@param id 
		 * @return:void
		 */
		
		public void deleteMerchantById(long id)
		{
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("status",BasicConstant.MERCHANT_STATUS_REMOVE);
			map.put("id", id);
			merchantMapper.deleteMerchantById(map);
		}
		/**
		 * @Title:updateMerchant 
		 * @Description:TODO(这里用一句话描述这个方法的作用) 
		 * @param:@param merchant 
		 * @return:void
		 */
		public void updateMerchant(Merchant merchant,long id)
		{
			if(merchant.getStatus()!=BasicConstant.MERCHANT_STATUS_NORMAL&&merchant.getStatus()!=BasicConstant.MERCHANT_STATUS_REMOVE)
				merchant.setStatus(BasicConstant.MERCHANT_STATUS_NORMAL);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("merchant", merchant);
			map.put("id", id);
			merchantMapper.updateMerchant(map);
		}
		/**	 
		 * @Title:findAllMerchant 
		 * @Description:TODO(这里用一句话描述这个方法的作用) 
		 * @param:@param status
		 * @param:@return 
		 * @return:List<Merchant>
		 */
		public List<Merchant> findAllMerchant(String status)
		{
			List<Merchant> merchants = merchantMapper.findAllMerchant(status);
			
			return merchants;
		}
		/**
		 * @Title:findMerchantById 
		 * @Description:TODO(这里用一句话描述这个方法的作用) 
		 * @param:@param id
		 * @param:@return 
		 * @return:Merchant
		 */
		public Merchant findMerchantById(long id)
		{
			return merchantMapper.findMerchantById(id);
		}
		
}
