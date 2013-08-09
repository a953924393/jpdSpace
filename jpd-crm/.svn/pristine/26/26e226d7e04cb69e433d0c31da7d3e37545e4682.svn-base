package com.jingpaidang.crm.controller.merchantmember;

import com.jingpaidang.crm.domain.merchantmember.MerchantMember;
import com.jingpaidang.crm.service.merchantmenber.MerchantMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Robert
 * Date: 13-8-8
 * Time: 下午1:16
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("merchantMember")
public class MerchantMemberController{
    @Resource(name = "merchantMemberService")
    private MerchantMemberService merchantMemberService;

    /**
     * @Title:addMerchantMember
     * @Description:TODO(添加会员与商家关联)
     * @param model
     * @param merchanMember
     * @throws
     * @Create: 2013-8-8 下午1:30:19
     * @Author : Robert
     */
    @RequestMapping("addMerchantMember")
    public void addMerchanMember(ModelMap model,MerchantMember merchanMember){
        merchantMemberService.saveMerchantMember(merchanMember);
    }

}
