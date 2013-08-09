package com.jingpaidang.crm.service.member;

import com.jingpaidang.crm.domain.member.Member;

/**
 * Created with IntelliJ IDEA.
 * User: Robert
 * Date: 13-8-7
 * Time: 下午1:57
 * To change this template use File | Settings | File Templates.
 */
public interface MemberService {
    /**
     * 保存会员
     * 新增一个会员信息
     * @param member
     *  @author Robert
     */
    public int saveMember(Member member);
    /**
     * 
     * @Title:isMemberExist 
     * @Description:TODO(根据会员京东Id判断会员是否已经存在) 
     * @param:@param jdId
     * @param:@return 
     * @return:boolean 
     * @throws 
     * @Create: 2013-8-8 下午2:55:20
     * @Author : Alex
     */
    public boolean isMemberExist(String jdId);
    /**
     * 
     * @Title:updateLastConsumTime 
     * @Description:TODO(修改会员最后消费时间) 
     * @param:@param member 
     * @return:void 
     * @throws 
     * @Create: 2013-8-8 下午4:18:45
     * @Author : Alex
     */
    public void updateLastConsumTime(Member member) ;
    /**
     * 
     * @Title:getMemberByJdId 
     * @Description:TODO(根据京东ID查询信息) 
     * @param:@param jdId
     * @param:@return 
     * @return:Member 
     * @throws 
     * @Create: 2013-8-9 下午4:42:05
     * @Author : Alex
     */
    public Member getMemberByJdId(String jdId);
    
}
