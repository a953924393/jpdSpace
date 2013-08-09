package com.jingpaidang.crm.controller.merchant;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jingpaidang.crm.controller.base.BaseController;
import com.jingpaidang.crm.domain.merchant.Merchant;
import com.jingpaidang.crm.service.merchant.MerchantService;

/**
 * @ClassName: MerchantController
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: Tom
 * @date: 2013-8-8 上午10:59:06
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

	@Resource
	private MerchantService merchantService;
	/**
	 * @Title:toAdd 
	 * @Description:TODO(这里用一句话描述这个方法的作用) 
	 * @param:@return 
	 * @return:String
	 */
	@RequestMapping("toAdd")
	public String toAdd() {
		return "merchant/add";
	}
	/**
	 * @Title:add 
	 * @Description:TODO(这里用一句话描述这个方法的作用) 
	 * @param:@param model
	 * @param:@param merchant
	 * @param:@return 
	 * @return:String
	 */
	@RequestMapping("add")
	public String add(ModelMap model,Merchant merchant) {
		merchantService.insertMerchant(merchant);
		return "redirect:/merchant/list";

	}
	/**
	 * @Title:toEdit 
	 * @Description:TODO(这里用一句话描述这个方法的作用) 
	 * @param:@param model
	 * @param:@param id
	 * @param:@return 
	 * @return:String
	 */
	@RequestMapping("toEdit/{id}")
	public String toEdit(ModelMap model,@PathVariable long id)
	{
		Merchant merchant = merchantService.findMerchantById(id);
		model.addAttribute("merchant", merchant);
		model.addAttribute("id", id);
		return "/merchant/edit";
	}
	/**
	 * @Title:delete 
	 * @Description:TODO(这里用一句话描述这个方法的作用) 
	 * @param:@param model
	 * @param:@param id
	 * @param:@return 
	 * @return:String
	 */
	@RequestMapping("delete/{id}")
	public String delete(ModelMap model,@PathVariable long id) {
		merchantService.deleteMerchantById(id);
		return "redirect:/merchant/list";
	}
	/**
	 * @Title:update 
	 * @Description:TODO(这里用一句话描述这个方法的作用) 
	 * @param:@param merchant
	 * @param:@return 
	 * @return:String
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	public String update(ModelMap model,Merchant merchant,@PathVariable long id) {
		merchantService.updateMerchant(merchant,id);
		return "redirect:/merchant/list";
	}
	/**
	 * @Title:list 
	 * @Description:TODO(这里用一句话描述这个方法的作用) 
	 * @param:@param model
	 * @param:@return 
	 * @return:String
	 */
	@RequestMapping("list")
	public String list(ModelMap model)
	{
		/**
		 * 通过normal来改变查询的值得状态
		 */
		String status="normal";  
		List<Merchant> list = merchantService.findAllMerchant(status);
		model.addAttribute("lists",list);
		return "merchant/list";
	}

}
