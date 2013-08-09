package com.jingpaidang.crm.service.member.impl;

import java.util.List;

import com.jingpaidang.crm.dao.member.MemberMapper;
import com.jingpaidang.crm.domain.member.Member;


import com.jingpaidang.crm.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;


/**
 * Created with IntelliJ IDEA.
 * User: Robert
 * Date: 13-8-7
 * Time: 上午11:06
 * To change this template use File | Settings | File Templates.
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {
    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Resource
    private MemberMapper memberMapper;

    /**
     * 保存会员
     * 新增一个会员信息
     * @param member
     *  @author Robert
     */
    public int saveMember(Member member){
        logger.info("=================");
        logger.info("开始插入会员");
         memberMapper.insert(member);
        logger.info("新增会员完成");
        return member.getId();
    }

	@Override
	public boolean isMemberExist(String jdId) {
        logger.info("正在查询会员...");
		List<Member> list = memberMapper.getMemberByMemberJdId(jdId);
		if(list==null){
			return false ;
		}
        logger.info("查询会员完成");
        return list.size()>0?true:false;
	}

	@Override
	public void updateLastConsumTime(Member member) {
		memberMapper.updateLastConsumTime(member);
	}

	@Override
	public Member getMemberByJdId(String jdId) {
		List<Member> list = memberMapper.getMemberByMemberJdId(jdId);
		if(list!=null && list.size()>0){
			return list.get(0) ;
		}
		return null;
	}
}
