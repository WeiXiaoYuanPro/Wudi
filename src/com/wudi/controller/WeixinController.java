package com.wudi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.core.Controller;
import com.wudi.model.admin.LogModel;
import com.wudi.model.admin.RoleInfoModel;
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
	
	public void login(){
		String username=getPara("user_id");
		String password=getPara("password");
		int code=-1;//0代表登陆成功，-1代表登陆失败，2用户名不存在，1,密码错误
		//到数据库查找username
		UserInfoModel m=UserInfoModel.getById(username);
		if(m!=null) {
			if(m.getPassword().equals(password)) {
				//告诉 前端密码正确
				code=0;
			}else {
				//告诉他密码不正确
				code=1;
			}
		}else {
			//用户不存在
			code=2;
		}
		setAttr("code", code);
		setAttr("data", m);
		renderJson();
	}
    /**
     * DOTO:用户登出
     */
    public void outLogin() {
    	//获取前端数据
    	String username=getPara("user_id");
    	//到数据库查询是否有这个用户名
    	UserInfoModel e=UserInfoModel.findModelbyUsername(username);
    	int status=-1;
    	if(e!=null) {
    		//备注登出状态，登陆日志得以区分
    		LogModel.saveLog(e.getUsername(), status, "退出登陆", getRequest());
    		status=0;
    	}
		setAttr("code", status);
    	renderJson();
    }
	
}
