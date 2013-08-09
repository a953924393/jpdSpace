package com.jingpaidang.crm.service.merchantmenber.impl;

import java.util.List;

import com.jingpaidang.crm.dao.merchantmember.MerchantMemberMapper;
import com.jingpaidang.crm.domain.merchantmember.MerchantMember;
import com.jingpaidang.crm.service.merchantmenber.MerchantMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Robert
 * Date: 13-8-8
 * Time: 上午11:36
 * To change this template use File | Settings | File Templates.
 */
@Service("merchantMemberService")
public class MerchantMemberServiceImpl implements MerchantMemberService {
    private static final Logger logger = LoggerFactory.getLogger(MerchantMemberServiceImpl.class);

    @Resource
    private MerchantMemberMapper merchantMemberMapper;

    /**
     * 通过会员的id关联商家
     *保存会员与商家的关联
     * @author Robert
     * @param merchantMember
     */
    @Override
    public void saveMerchantMember(MerchantMember merchantMember) {
        //To change body of implemented methods use File | Settings | File Templates.
        logger.info("=================");
        logger.info("开始建立会员与商家的关联");
        merchantMemberMapper.insert(merchantMember);
        logger.info("会员与商家的关联完成");

    }

	@Override
	public boolean isMerchantMemberExist(MerchantMember merchantMember) {
		List<MerchantMember> list = merchantMemberMapper.getMerchantMemberByMerchantIdAndMemberId(merchantMember) ;
		if(list==null){
			return false ;
		}
		return list.size()>0?true:false;
	}
}
