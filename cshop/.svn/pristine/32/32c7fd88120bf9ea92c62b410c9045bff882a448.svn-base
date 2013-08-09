package com.jingpaidang.cshop.web;

import com.jingpaidang.cshop.common.utils.SpringUtil;
import com.jingpaidang.cshop.web.directive.CheckboxDirective;
//import com.jingpaidang.cshop.web.directive.PaginationDirective;
import com.jingpaidang.cshop.web.directive.SubstringMethod;
import freemarker.template.TemplateException;

import javax.servlet.ServletContext;

/**
 * View manager for freemarker.
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 5/29/13
 */
public class FreemarkerManager extends org.apache.struts2.views.freemarker.FreemarkerManager {
    public synchronized freemarker.template.Configuration getConfiguration(ServletContext servletContext) throws TemplateException {
        freemarker.template.Configuration config = (freemarker.template.Configuration) servletContext.getAttribute(CONFIG_SERVLET_CONTEXT_KEY);
        if (config == null) {
            config = createConfiguration(servletContext);

            SubstringMethod substringMethod = (SubstringMethod) SpringUtil.getBean("substringMethod");
            CheckboxDirective checkboxDirective = (CheckboxDirective) SpringUtil.getBean("checkboxDirective");
            //PaginationDirective paginationDirective = (PaginationDirective) SpringUtil.getBean("paginationDirective");

            config.setSharedVariable(SubstringMethod.TAG_NAME, substringMethod);
            config.setSharedVariable(CheckboxDirective.TAG_NAME, checkboxDirective);
            //config.setSharedVariable(PaginationDirective.TAG_NAME, paginationDirective);

            servletContext.setAttribute(CONFIG_SERVLET_CONTEXT_KEY, config);
        }
        config.setWhitespaceStripping(true);
        return config;
    }
}
