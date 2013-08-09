package com.jingpaidang.cshop.action.wares;

import javax.annotation.Resource;

import com.taobao.api.ApiException;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.jingpaidang.cshop.action.admin.BaseAction;
import com.jingpaidang.cshop.service.wares.WaresService;

import java.io.IOException;

/**
 * 
 * @ClassName:	WaresAction 
 * @Description:TODO(商品Action) 
 * @author:	Alex
 * @date:	2013-7-3 下午3:39:31 
 *
 */
@ParentPackage("wares")
public class WaresAction extends BaseAction {
	
	@Resource
	private WaresService waresService ;
	
	private int accountId ;
	
	/**
	 * 
	 * @Title:downloadWares 
	 * @Description:TODO(下载商品) 
	 * @param:@return 
	 * @return:String 
	 * @throws 
	 * @Create: 2013-7-3 下午4:06:42
	 * @Author : Alex
	 */
	public String downloadWares(){
		int count = 0 ;
        try {
            count = waresService.downloadWares(accountId);
        } catch (IOException e) {

            return this.ajax("-1");

        } catch (ApiException e) {

            return this.ajax("-1");
        }

        return this.ajax(""+count) ;
	}
	
	public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
