package com.jingpaidang.crm.controller.member;

import com.jingpaidang.crm.controller.base.BaseController;
import com.jingpaidang.crm.domain.member.Member;
import com.jingpaidang.crm.domain.merchantmember.MerchantMember;
import com.jingpaidang.crm.service.member.MemberService;
import com.jingpaidang.crm.service.merchantmenber.MerchantMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-7
 * Time: 下午1:43
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("member")
public class MemberController extends BaseController {
    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "merchantMemberService")
    private MerchantMemberService merchantMemberService;


    /**

     *
     * @Title:toadd
     * @Description:TODO(跳转到会员添加页面)
     * @param:@return
     * @return:String
     * @throws
     * @Create: 2013-8-7 下午2:29:19
     * @Author :Robert
     */
    @RequestMapping("toadd")
    public String toadd(){
        return "/member/addMember" ;
    }
    /**
     *
     * @Title:addMember
     * @Description:TODO(添加会员)
     * @param:@param model
     * @param:@param member
     * @param:@return
     * @return:String
     * @throws
     * @Create: 2013-8-8 下午2:48:19
     * @Author : Robert
     */
    @RequestMapping("addMember")
    public String addMember(ModelMap model,Member member){

        String jdId=member.getMemberJdId();
        boolean result= memberService.isMemberExist(jdId);
      if(result){
         return "redirect:/member/addSuccess";
      }else {
        memberService.saveMember(member);
        MerchantMember merchantMember=new MerchantMember();
        merchantMember.setMemberId(member.getId());
        merchantMember.setMerchantId(5);
        merchantMember.setCreated(new Date());
        merchantMemberService.saveMerchantMember(merchantMember);
        return "redirect:/index.jsp" ;  }
    }

    /**
     *
     * 判断会员是否已经存在
     * @return  boolean
     *  @Create: 2013-8-9 下午2:29:19
     * @Author :Robert
     */
     @RequestMapping("isMemberExist")
    public boolean isMemberExist(HttpServletRequest request,HttpServletResponse response){
         boolean result =memberService.isMemberExist(request.getParameter("memberjdid"));
         PrintWriter out = null;
         try {
             out = response.getWriter();
         } catch (IOException e) {
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
         out.print(result);
         out.close();
         return result;
     }


}
