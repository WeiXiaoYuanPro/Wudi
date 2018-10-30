package com.wudi.controller;

import com.jfinal.core.Controller;
/**
 * 微信小程序数据访问
 * @author 橙色
 * 2018年10月26日11:10:01
 */
public class WeixinController extends Controller {
	/**
	 * 默认获取数据的地方
	 */
	public void index() {
		setAttr("result", "你的微信小程序访问路径确定是对的吗？");
		renderJson();
	}

}
