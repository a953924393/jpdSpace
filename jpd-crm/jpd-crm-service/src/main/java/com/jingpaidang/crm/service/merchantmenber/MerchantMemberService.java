package com.jingpaidang.crm.service.merchantmenber;

import com.jingpaidang.crm.domain.merchantmember.MerchantMember;

/**
 * Created with IntelliJ IDEA.
 * User: Robert
 * Date: 13-8-8
 * Time: 上午11:33
 * To change this template use File | Settings | File Templates.
 */
public interface MerchantMemberService {
    /**
     * 通过会员的id
     * 保存会员与商家的关联
     *  @param merchantMember
     *  @author Robert
     */
    public void saveMerchantMember(MerchantMember merchantMember);
    /**
     * 
     * @Title:isMerchantMemberExist 
     * @Description:TODO(判断桥表是否已存在该信息) 
     * @param:@param merchantMember
     * @param:@return 
     * @return:boolean 
     * @throws 
     * @Create: 2013-8-9 下午4:10:58
     * @Author : Alex
     */
    public boolean isMerchantMemberExist(MerchantMember merchantMember) ;
}
