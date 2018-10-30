package com.wudi.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 后台数据管理拦截器
 * @author xiao
 * 2018年10月22日 09:38:34
 *
 */
	public class AdminInterceptor implements Interceptor {
	    public void intercept(Invocation inv) {
	       //访问前信息拦截
	       inv.invoke();
	       //访问后信息拦截
	    }
	}
