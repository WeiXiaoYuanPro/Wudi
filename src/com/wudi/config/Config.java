package com.wudi.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.wudi.controller.AdminController;
import com.wudi.controller.FileController;
import com.wudi.controller.WeixinController;
import com.wudi.model.NavsModel;
import com.wudi.model.StudentModel;
import com.wudi.model.admin.BuildingModel;
import com.wudi.model.admin.ClassroomModel;
import com.wudi.model.admin.CmsUserModel;
import com.wudi.model.admin.CmsloginLogModel;
import com.wudi.model.admin.DTModel;
import com.wudi.model.admin.DepmanModel;
import com.wudi.model.admin.DormitoryModel;
import com.wudi.model.admin.Role_infoModel;
import com.wudi.model.admin.SchoolModel;
import com.wudi.model.admin.SchoolZoneModel;
import com.wudi.model.admin.Stu_contatcModel;
import com.wudi.model.admin.Stu_familyModel;
import com.wudi.model.admin.StuinfoModel;
import com.wudi.model.admin.TaskModel;
import com.wudi.model.admin.UserInfoModel;
import com.wudi.model.admin.UserLoginLogModel;
/**
 * 	系统配置类
 *   @author XIAO
 *   2018年10月26日10:57:14
 */
public class Config extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// 加载配置文件，注意：配置文件必须放在src目录下,要不然找不到
		loadPropertyFile("config.properties");
		// 配置一些系统变量
		me.setDevMode(getPropertyToBoolean("DevMode", true));//设置为开发模式，方便查看日志
		
	}

	@Override
	public void configRoute(Routes me) {
		// 设置路由，客户端访问就是在这里设置的路径地址
		me.add("/admin", AdminController.class,"WEB-INF/admin");//后台数据管理访问路径：localhost:8080/admin
		me.add("/wudi", WeixinController.class);//微信小程序访问路径：localhost:8080/wudi
		me.add("/file", FileController.class,"upload");//文件上传下载访问路径：localhost:8080/file
		
	}

	@Override
	public void configEngine(Engine me) {
		
	}

	@Override
	public void configPlugin(Plugins me) {
		// 插入其他插件，比如，连接数据库的mysql插件和连接多数据库插件
		DruidPlugin dsMysql = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"),
				getProperty("password").trim());
		{
			dsMysql.setTestOnBorrow(true);
			dsMysql.setTestOnReturn(true);
			dsMysql.setMaxWait(20000);
		}
		//mysql插件
		ActiveRecordPlugin arpMysql = new ActiveRecordPlugin("mysql", dsMysql);
		//从配置文件里查找出来，是否显示sql语句
		boolean showSql = getPropertyToBoolean("showSql", true);
		//系统设置是否显示sql
		arpMysql.setShowSql(showSql);
		{
			//将数据库表，绑定到这来来，注意，表名和类要相对应
			arpMysql.addMapping("navs", NavsModel.class);//主页面左侧菜单显示的表
			arpMysql.addMapping("student", StudentModel.class);//学生表
			arpMysql.addMapping("dormitory", DormitoryModel.class);//宿舍表
			arpMysql.addMapping("stu_family_info", Stu_familyModel.class);//学生家庭信息表
			arpMysql.addMapping("building", BuildingModel.class);//学校楼房信息表
			arpMysql.addMapping("classroom", ClassroomModel.class);//教室信息表
			arpMysql.addMapping("stuinfo", StuinfoModel.class);//学生表		
			arpMysql.addMapping("cms_user", CmsUserModel.class);//用户信息表		
			arpMysql.addMapping("cmslogin_log", CmsloginLogModel.class);//登陆日志信息表
			arpMysql.addMapping("user_info", UserInfoModel.class);//用户信息表

			arpMysql.addMapping("school_zone", SchoolZoneModel.class);//学校楼房信息表


			arpMysql.addMapping("stuinfo", StuinfoModel.class);//学生表
			
			arpMysql.addMapping("role_info", Role_infoModel.class);//学生表
			
			arpMysql.addMapping("school", SchoolModel.class);//学生表
			
			arpMysql.addMapping("userloginlog", UserLoginLogModel.class);//学生联系信息
			
			arpMysql.addMapping("stucontatc", Stu_contatcModel.class);//学生联系信息
			
			arpMysql.addMapping("task", TaskModel.class);//任务
			
			arpMysql.addMapping("depman", DepmanModel.class);//开发成员
			arpMysql.addMapping("dt", DTModel.class);//开发成员


		}
		//添加插件
		me.add(dsMysql);
		me.add(arpMysql);
	
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
	}

	@Override
	public void configHandler(Handlers me) {
		
	}

}
