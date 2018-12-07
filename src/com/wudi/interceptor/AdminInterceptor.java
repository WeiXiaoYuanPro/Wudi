package com.wudi.interceptor;


import javax.servlet.http.HttpSession;

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
		inv.invoke(); 
                   
	}
	       
}
	
