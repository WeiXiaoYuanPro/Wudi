package com.wudi.controller;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.wudi.model.admin.UserInfoModel;
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
	
	public void userLogin(){
		String username=getPara("user_id");
		String password=getPara("password");
		Map<String, Object> map=new HashMap<>();
		int code=0;//0代表密码不正确 1,2
		//到数据库查找username
		UserInfoModel m=UserInfoModel.getById(username);
		if(m!=null) {
			if(m.getPassword().equals(password)) {
				//告诉 前端密码正确
				code=1;
				//到数据库找出user_name，user_type
				
				map.put("user_name", 1);
				map.put("user_type", 2);
			}else {
				//告诉他密码不正确
				code=0;
			}
		}else {
			//用户不存在
			code=2;
			map.put("user_name", 1);
			map.put("user_type", 2);
		}
		setAttr("code", code);
		setAttr("data", map);
		renderJson();
	}
	public void test() {
		Map<String, Object> map=new HashMap<>();
		map.put("usename", "xiao");
		map.put("leve", 11);
		map.put("code", 2);
		setAttr("data", map);
		renderJson();
	}
	
}
