package com.wudi.interceptor;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 后台数据管理拦截器
 * @author xiao
 * 2018年10月22日 09:38:34
 *
 */
public class AdminInterceptor implements Interceptor {
	public void intercept(Invocation inv) {
		Controller c = inv.getController();
		String cookiename = c.getCookie("cname");
		Object session =c.getSessionAttr("user");
		if (cookiename != null&&session!=null) {
		// 用户登录账号
			inv.invoke();
		} else {
		// cookie 登录未成功，证明该 cookie 已经没有用处，删之
		c.removeCookie("cname");
		c.removeSessionAttr("user");
		c.render("login.html");
        
	}
}
	       
}
	
