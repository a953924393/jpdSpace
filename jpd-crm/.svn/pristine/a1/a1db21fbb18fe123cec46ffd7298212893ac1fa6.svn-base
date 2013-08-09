package com.jingpaidang.crm.dao.merchant;

import java.util.List;
import java.util.Map;

import com.jingpaidang.crm.domain.merchant.Merchant;

/**
 * Created with IntelliJ IDEA.
 * User: Tom
 * Date: 8/8/13
 * Time: 10:27 AM
 */
public interface MerchantMapper {

	/**
	 * Insert merchant
	 * @param merchant
	 */
	public void insertMerchant(Merchant merchant);
	/**
	 * Delete merchant by id
	 * @param map 
	 */
	public void deleteMerchantById(Map map);
	/**
	 * Update Merchant
	 * @param merchant
	 */
	public void updateMerchant(Map map);
	/**
	 * Select merchant by id
	 * @param id
	 */
	public Merchant findMerchantById(long id);
	/**
	 * Select all merchant
	 * @param status
	 */
	public List<Merchant> findAllMerchant(String status);
	/**
	 * Select merchant by merchantNum
	 * @param merchantNum
	 */
	public Merchant findMerchantByMerchantNum(long merchantNum);

    /**
     * 通过access查询refresh
     * @param access
     * @return
     */
    public Merchant findByAccess(String merchantAccessToken);

    /**
     * 刷新accessToken
     * @param merchant
     */
    public void updateAccessToken(Merchant merchant);
}
