package com.wudi.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
/**
 * 微信小程序拦截器
 * @author xiao
 *2018年10月25日18:36:48
 */
public class WeixinIntercepter implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		  //访问前信息拦截
	       inv.invoke();
	       //访问后信息拦截
		
	}

}
