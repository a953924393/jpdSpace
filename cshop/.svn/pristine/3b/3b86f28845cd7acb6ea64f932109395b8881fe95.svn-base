package com.jingpaidang.cshop.action.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jingpaidang.cshop.common.plugins.Pagination;
import org.apache.struts2.ServletActionContext;

import com.jingpaidang.cshop.common.utils.JSONUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Value;

/**
 * Action基类
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 5/28/13
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private static final String HEADER_ENCODING = "UTF-8";
    private static final String HEADER_TEXT_CONTENT_TYPE = "text/plain";
    private static final String HEADER_JSON_CONTENT_TYPE = "text/plain";
    private static final boolean HEADER_NO_CACHE = true;

    public static final String VIEW = "view";
    public static final String LIST = "list";
    public static final String REDIRECT = "redirect";
    public static final String SUCCESS = "../common/success";
    public static final String ERROR = "../common/error";

    public static final String STATUS_PARAMETER_NAME = "status";
	public static final String MESSAGE_PARAMETER_NAME = "message";
	public static final String OBJ_PARAMETER_NAME = "var";


    @Value("${taobao.redirect.uri}")
    private String tbRedirectUri;
    @Value("${taobao.app.key}")
    private String tbAppKey;
    @Value("${taobao.app.secret}")
    private String tbAppSecret;


    @Value("${jd.redirect.uri}")
    private String jdRedirectUri;
    @Value("${jd.app.key}")
    private String jdAppKey;
    @Value("${jd.app.secret}")
    private String jdAppSecret;



    protected String rootUrl; // the root path
    protected String redirectUrl; //the redirect url

    //paginate
    protected Pagination page = new Pagination();
    protected boolean displayPageBar = false; // display pageBar or not

    //操作状态（警告，错误，成功）
    public enum Status {
        warn, error, success
    }

    // 获取Request
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	// 获取Response
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	// 获取ServletContext
	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	// 获取Attribute
	protected Object getAttribute(String name) {
		return ServletActionContext.getRequest().getAttribute(name);
	}

	// 设置Attribute
	protected void setAttribute(String name, Object value) {
		ServletActionContext.getRequest().setAttribute(name, value);
	}

	// 获取Parameter
	protected String getParameter(String name) {
		return ServletActionContext.getRequest().getParameter(name);
	}

	// 获取Parameter数组
	protected String[] getParameterValues(String name) {
		return ServletActionContext.getRequest().getParameterValues(name);
	}

	// 获取Session
	protected Object getSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session.get(name);
	}

	// 设置Session
	protected void setSession(String name, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put(name, value);
	}

	// 移除Session
	protected void removeSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.remove(name);
	}

	// 获取Cookie
	protected String getCookie(String name) {
		Cookie cookies[] = ServletActionContext.getRequest().getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) { return cookie.getValue(); }
			}
		}
		return null;
	}

	// 设置Cookie
	protected void setCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(ServletActionContext.getRequest().getContextPath() + "/");
		ServletActionContext.getResponse().addCookie(cookie);
	}

	// 设置Cookie
	protected void setCookie(String name, String value, Integer maxAge) {
		Cookie cookie = new Cookie(name, value);
		if (maxAge != null) {
			cookie.setMaxAge(maxAge);
		}
		cookie.setPath(ServletActionContext.getRequest().getContextPath() + "/");
		ServletActionContext.getResponse().addCookie(cookie);
	}

	// 移除Cookie
	protected void removeCookie(String name) {
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0);
		cookie.setPath(ServletActionContext.getRequest().getContextPath() + "/");
		ServletActionContext.getResponse().addCookie(cookie);
	}

	// 获取真实路径
	protected String getRealPath(String path) {
		return ServletActionContext.getServletContext().getRealPath(path);
	}

	// 获取ContextPath
	protected String getContextPath() {
		return ServletActionContext.getRequest().getContextPath();
	}

	// AJAX输出
	protected String ajax(String content, String contentType) {
		try {
			HttpServletResponse response = initResponse(contentType);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	// 根据文本内容输出AJAX
	protected String ajax(String text) {
		return ajax(text, HEADER_TEXT_CONTENT_TYPE);
	}

	// 根据操作状态输出AJAX
	protected String ajax(Status status) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS_PARAMETER_NAME, status.toString());
		JSONUtils.toJson(response, jsonMap);
		return NONE;
	}

	// 根据操作状态、消息内容输出AJAX
	protected String ajax(Status status, String message) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS_PARAMETER_NAME, status.toString());
		jsonMap.put(MESSAGE_PARAMETER_NAME, message);
		JSONUtils.toJson(response, jsonMap);
		return NONE;
	}

	// 根据操作状态、消息内容输出AJAX
	protected String ajax(Status status, Object object) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(STATUS_PARAMETER_NAME, status.toString());
		jsonMap.put(OBJ_PARAMETER_NAME, object);
		JSONUtils.toJson(response, jsonMap);
		return NONE;
	}

	// 根据Object输出AJAX
	protected String ajax(Object object) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		JSONUtils.toJson(response, object);
		return NONE;
	}

	// 根据boolean状态输出AJAX
	protected String ajax(boolean booleanStatus) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(STATUS_PARAMETER_NAME, booleanStatus);
		JSONUtils.toJson(response, jsonMap);
		return NONE;
	}

	private HttpServletResponse initResponse(String contentType) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(contentType + ";charset=" + HEADER_ENCODING);
		if (HEADER_NO_CACHE) {
			response.setDateHeader("Expires", 1L);
			response.addHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
		}
		return response;
	}

    public String getRootUrl() {
        rootUrl = getContextPath();
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public boolean isDisplayPageBar() {
        return displayPageBar;
    }

    public void setDisplayPageBar(boolean displayPageBar) {
        this.displayPageBar = displayPageBar;
    }

    public String getTbRedirectUri() {
        return tbRedirectUri;
    }

    public void setTbRedirectUri(String tbRedirectUri) {
        this.tbRedirectUri = tbRedirectUri;
    }

    public String getTbAppKey() {
        return tbAppKey;
    }

    public void setTbAppKey(String tbAppKey) {
        this.tbAppKey = tbAppKey;
    }

    public String getTbAppSecret() {
        return tbAppSecret;
    }

    public void setTbAppSecret(String tbAppSecret) {
        this.tbAppSecret = tbAppSecret;
    }

    public String getJdRedirectUri() {
        return jdRedirectUri;
    }

    public void setJdRedirectUri(String jdRedirectUri) {
        this.jdRedirectUri = jdRedirectUri;
    }

    public String getJdAppKey() {
        return jdAppKey;
    }

    public void setJdAppKey(String jdAppKey) {
        this.jdAppKey = jdAppKey;
    }

    public String getJdAppSecret() {
        return jdAppSecret;
    }

    public void setJdAppSecret(String jdAppSecret) {
        this.jdAppSecret = jdAppSecret;
    }
}
