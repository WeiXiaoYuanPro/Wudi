package com.wudi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
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
	
	public void userLoginxiao(){
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

    
	/**
	 * DOTO:用户登录接口
	 * 
	 */
    public void userLogin() {
    	//获取前端数据
    	String username= getPara("user_id");
    	String password= getPara("password");
    	//到数据库查找账号信息
    	UserInfoModel m=UserInfoModel.findModelbyUsername(username);
    	//登陆日志所需的内容给上
    	
    	String code="1";//0：成功，1：密码错误，2：没有用户名，3：其他错误（数据库问题）
    	int status=-1;
    	String user_name="00";
    	String remark="已登陆";
    	//获取地址
    	String addr = getRequest().getRemoteAddr();
    	//获取访问IP
    	String ip = getRequest().getRemoteHost();
    	//获取访问时间
		SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String login_time=now.format(new Date());
    	//
    	if(m==null) {
    		code="2";
    	}else {
    		//判断密码是否正确
	    	if(m.getPassword().equals(password)) {
	    		//如果正确
	    		//匹配username，根据另外一张表匹配到的相同user_id，进入表中查找需要相应的数据
	    		RoleInfoModel n=RoleInfoModel.findModelbyUserid(m.getUsername());
	    		user_name=m.getUsername();
	    		username=m.getUsername();
	    		status=n.getLevel();
	    		boolean result=false;//LogModel.saveModel(username, login_time, ip, addr, remark, status);
	    		setAttr("result", result);
	    		renderJson();
	    		code="0";
	    	}else {
	    		code="1";
	    	}
	    	
    	}
    	Map<String,Object> map=new HashMap<>();
    	map.put("user_name",user_name);
    	map.put("user_type", status);
     	setAttr("code", code);
    	setAttr("data", map);
    	renderJson();
		
    }
    
    /**
     * DOTO:用户登出
     */
    
    
    public void userLogout() {
    	//获取前端数据
    	String username=getPara("user_id");
    	//先定义个变量，等下以便于返回一个值
    	String logout_status="-1";
    	//到数据库查询是否有这个用户名
    	UserInfoModel e=UserInfoModel.findModelbyUsername(username);
    	int status=-1;
    	//备注登出状态，登陆日志得以区分
    	String remark="已退出";
    	String addr = getRequest().getRemoteAddr();
    	String ip = getRequest().getRemoteHost();
		SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String login_time=now.format(new Date());
    	//判断过程，退出正常就返回一个0值（正常退出）
    	if(e==null) {
    		//异常错误，暂时想不到任何错误
    		logout_status="1";
    	}else if(e.getUsername().equals(username)){
    		//传值匹配用户名，查找数据
    		RoleInfoModel ex=RoleInfoModel.findModelbyUserid(e.getUsername());
    		status=ex.getLevel();
    		boolean result=false;//LogModel.saveModel(username, login_time, ip, addr, remark, status);
    		setAttr("result", result);
    		renderJson();
    		logout_status="0";
    	}
    	Map<String,Object> map=new HashMap<>();
    	map.put("logout_status", logout_status);
    	setAttr("data", map);
    	renderJson();
    }
	
}
