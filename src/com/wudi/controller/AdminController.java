package com.wudi.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.model.NavsModel;
import com.wudi.model.admin.SchoolModel;
import com.wudi.model.StudentModel;
import com.wudi.model.admin.StuContatcModel;
import com.wudi.model.admin.BuildingModel;
import com.wudi.model.admin.ClassroomModel;
import com.wudi.model.admin.DormitoryModel;
import com.wudi.model.admin.Role_infoModel;
import com.wudi.model.admin.StuinfoModel;
import com.wudi.model.admin.UserInfoModel;
import com.wudi.model.admin.UserLoginLogModel;

/**
 * 
* @ClassName: AdminController
* @Description: TODO 后台管理页面跳转管理类
* @author xiao
* @date 2018年10月29日下午4:08:09
*
 */
public class AdminController extends Controller{

	/**
	 * 
	* @Title: index
	* @Description:后台管理默认到达页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void index() {
		render("index.html");
	}
	/**
	 * 
	* @Title: getnavs
	* @Description: 获取主页面左侧菜单数据
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getnavs() {
		Object object=NavsModel.getListForLeft();
		renderJson(object);
	}
	/**
	 * 显示菜单列表
	 */
	public void navsinfo() {
		render("sys/navsinfo.html");
	}
	/**
	 * 
	* @Title: getnavs
	* @Description: 获取侧菜单数据列表
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getNavsList() {
		//获取页面查询的关键字
		String key=getPara("key");
		Page<NavsModel> list=NavsModel.getList(1,100,key);
		setAttr("infos", list);
		renderJson();
	}
	/**
	 * 打开菜单添加页面
	 */
	public void openNavsAdd() {
		render("sys/navsAdd.html");
	}
	/**
	 * 添加保存菜单信息
	 */
	public void saveNavs() {
		String title=getPara("title");
		String href=getPara("href");
		String icon=getPara("icon");
		String fid=getPara("fid");
		boolean result=NavsModel.saveModel(title, href, icon, fid);
		setAttr("result", result);
		renderJson();
		
	}
	/**
	 * 打开菜单修改页面
	 */
	public void openNavsEdit() {
		//接收页面数据
		String id=getPara("id");
		setAttr("id", id);
		renderFreeMarker("sys/navsEdit.html");
	}
	/**
	 * 更新保存菜单信息
	 */
	public void updatenavs() {
		String id=getPara("id");
		String title=getPara("title");
		String href=getPara("href");
		String icon=getPara("icon");
		String fid=getPara("fid");
		boolean result=NavsModel.updateModel(id,title, href, icon, fid);
		setAttr("result", result);
		renderJson();
		
	}
	/**
	* @Title: students
	* @Description: 打开学生信息列表页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void students() {
		render("stu/studentinfo.html");
	}
	
	/**
	* @Title: queryStudents
	* @Description: 获取学生信息列表信息（查询），在这里，我们是用异步加载方式，
	* 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void queryStudents() {
		//获取页面查询的关键字
		String key=getPara("key");
		//开始查询
		Page<StudentModel> students=StudentModel.getList(1, 10,key);
		//将查到的学生信息列表放到infos，给页面
		setAttr("infos", students);
		//返回格式是json
		renderJson();
	}
	/**
	* @Title: openStudentAdd
	* @Description:打开添加信息页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openStudentAdd() {
		render("stu/studentAdd.html");
	}
	
	/**
	* @Title: getstudent
	* @Description:获取需要修改的学生信息
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getstudent() {
		//接收页面数据
		String no=getPara("no");
		//根据条件查询数据库的数据
		StudentModel student=StudentModel.getByNo(no);
		//放到编辑页面上去
		setAttr("m", student);
		//返回格式是json
		renderJson();
	}
	/**
	* @Title: openStudentEdit
	* @Description:打开修改信息页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openStudentEdit() {
		//接收页面数据
		String no=getPara("no");
		setAttr("no", no);
		renderFreeMarker("stu/studentEdit.html");
	}
	/**
	* @Title: saveStudent
	* @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void saveStudent() {
		String no=getPara("no");
		String name=getPara("name");
		String cls=getPara("cls");
		int sex=getParaToInt("sex");
		//保存数据
		boolean result=StudentModel.save(no,name,cls,sex);
		
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 
	* @Title: updateStudent
	* @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void updateStudent() {
		String no=getPara("no");
		String name=getPara("name");
		String cls=getPara("cls");
		int sex=getParaToInt("sex");
		//更新数据
		boolean result=StudentModel.update(no,name,sex,cls);
		
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 
	* @Title: delStudent
	* @Description:删除信息，这个我们是根据唯一主键id来删除的。
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void delStudent() {
		String no=getPara("id");
		//删除
		boolean result=StudentModel.delStudentByNO(no);
		//返回结果
		setAttr("result", result);
		renderJson();
	}
	
	
	

	
	
	
	
	
	
	
	/**
	 * 在AdminController类中添加拉学生联系方式表 和  客户端登陆日志记录表
	 * @author 王驰
	 * 2018年11月18日 12:21:34
	 *
	 */
	
	
	/**
	* @Title: stucontatc
	* @Description: 打开学生联系信息列表页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void stucontatc() {
		render("stucontatc/stucontatcinfo.html");
	}
	/**
	* @Title: queryStucontatc
	* @Description: 获取学生信息列表信息（查询），在这里，我们是用异步加载方式，
	* 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void queryStuContatc() {
		//获取页面查询的关键字
		String key=getPara("key");
		//开始查询
		Page<StuContatcModel> stucontatc=StuContatcModel.getList(1, 10,key);
		//将查到的学生信息列表放到infos，给页面
		setAttr("infos", stucontatc);
		//返回格式是json
		renderJson();
	}
	/**
	* @Title: openstucontatc
	* @Description:打开添加学生联系方式页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openStuContatcAdd() {
		render("stucontatc/stucontatcAdd.html");
	}
	
	/**
	* @Title: getStuContatc
	* @Description:获取需要修改的学生信息
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getStuContatc() {
		//接收页面数据
		String id=getPara("id");
		//根据条件查询数据库的数据
		StuContatcModel stucontatc=StuContatcModel.getById(id);
		//放到编辑页面上去
		setAttr("m", stucontatc);
		//返回格式是json
		renderJson();
	}
	/**
	* @Title: openstucontatcEdit
	* @Description:打开修改信息页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openStuContatcEdit() {
		//接收页面数据
		String id=getPara("id");
		setAttr("id", id);
		renderFreeMarker("stucontatc/stucontatcEdit.html");
	}
	/**
	* @Title: saveStuContatc
	* @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void saveStuContatc() {
		String id=getPara("id");
		String tel=getPara("tel");
		String qq=getPara("qq");
		String weixin=getPara("weixin");
		String other=getPara("other");
		String stu_no=getPara("stu_no");
		//保存数据
		boolean result=StuContatcModel.save(id,tel,qq,weixin,other,stu_no);
		
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 
	* @Title: updateStuContatc
	* @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void updateStuContatc() {
		String id=getPara("id");
		String tel=getPara("tel");
		String qq=getPara("qq");
		String weixin=getPara("weixin");
		String other=getPara("other");
		String stu_no=getPara("stu_no");
		//更新数据
		boolean result=StuContatcModel.update(id,tel,qq,weixin,other,stu_no);
		
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 
	* @Title: delStuContatc
	* @Description:删除信息，这个我们是根据唯一主键id来删除的。
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void delStuContatc() {
		String id=getPara("id");
		//删除
		boolean result=StuContatcModel.delStuContatcByID(id);
		//返回结果
		setAttr("result", result);
		renderJson();
	}
	
	

	/**
	* @Title: dormitory
	* @Description: 打开学生宿舍信息列表页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void UserLoginLog() {
		render("userloginlog/userloginloginfo.html");
	}
	/**
	 * 打开客户端登陆日志记录添加页面
	 */
	public void openUserLoginLogAdd() {
		render("userloginlog/userloginlogAdd.html");
	}
	/**
	* @Title: queryUserLoginLog
	* @Description: 获取客户端登陆日志记录表信息（查询），在这里，我们是用异步加载方式，
	* 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void queryUserLoginLog() {
		//获取页面查询的关键字
		String key=getPara("key");
		//开始查询
		Page<UserLoginLogModel> Dormitory=UserLoginLogModel.getList(1, 10,key);
		//将查到的学生信息列表放到infos，给页面
		setAttr("infos", Dormitory);
		//返回格式是json
		renderJson();
	}
	/**
	 * 
	* @Title: userloginlog
	* @Description: 获取侧客户端登陆日志记录表
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getUserLoginLogList() {
		//获取页面查询的关键字
		String key=getPara("key");
		Page<UserLoginLogModel> list=UserLoginLogModel.getList(1,100,key);
		setAttr("infos", list);
		renderJson();
	}
	/**
	 * 添加保存客户端登陆日志记录表信息
	 */
	public void saveUserLoginLog() {
		String id=getPara("id");
		String username=getPara("username");
		String login_time=getPara("login_time");
		String ip=getPara("ip");
		String addr=getPara("addr");
		String remark=getPara("remark");
		int status=getParaToInt("status");
		boolean result=UserLoginLogModel.saveModel(id, username, login_time, ip, addr, remark, status);
		setAttr("result", result);
		renderJson();
		
	}
	/**
	 * 打开客户端登陆日志记录表修改页面
	 */
	public void openUserLoginLogEdit() {
		//接收页面数据
		String id=getPara("id");
		setAttr("id", id);
		renderFreeMarker("sys/userloginlogEdit.html");
	}
	/**
	 * 更新保存客户端登陆日志记录表信息
	 */
	public void updateuserloginlog() {
		String id=getPara("id");
		String username=getPara("username");
		String login_time=getPara("login_time");
		String ip=getPara("ip");
		String addr=getPara("addr");
		String remark=getPara("remark");
		int status=getParaToInt("status");
		boolean result=UserLoginLogModel.update(id, username, login_time, ip, addr, remark, status);
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 
	* @Title: delUserLoginLog
	* @Description:删除信息，这个我们是根据唯一主键id来删除的。
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void delUserLoginLog() {
		String id=getPara("id");
		//删除
		boolean result=UserLoginLogModel.delUserLoginLogByID(id);
		//返回结果
		setAttr("result", result);
		renderJson();
	}
	
	
	
	
	
	
	
	

	/**
	* @Title: dormitory
	* @Description: 打开学生宿舍信息列表页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void dormitory() {
		render("dor/dormitoryinfo.html");
	}
	/**
	* @Title: queryDormitory
	* @Description: 获取学生宿舍信息列表信息（查询），在这里，我们是用异步加载方式，
	* 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void queryDormitory() {
		//获取页面查询的关键字
		String key=getPara("key");
		//开始查询
		Page<DormitoryModel> Dormitory=DormitoryModel.getList(1, 10,key);
		//将查到的学生信息列表放到infos，给页面
		setAttr("infos", Dormitory);
		//返回格式是json
		renderJson();
	}
	/**
	* @Title: openDormitoryAdd
	* @Description:打开添加信息页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openDormitoryAdd() {
		render("dor/dormitoryAdd.html");
	}
	
	/**
	* @Title: getdormitory
	* @Description:获取需要修改的学生宿舍信息
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getdormitory() {
		//接收页面数据
		String id=getPara("id");
		//根据条件查询数据库的数据
		DormitoryModel Dormitory=DormitoryModel.getById(id);
		//放到编辑页面上去
		setAttr("m", Dormitory);
		//返回格式是json
		renderJson();
	}
	/**
	* @Title: openDormitoryEdit
	* @Description:打开修改信息页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openDormitoryEdit() {
		//接收页面数据
		String id=getPara("id");
		setAttr("id", id);
		renderFreeMarker("dor/dormitoryEdit.html");
	}
	/**
	* @Title: saveDormitory
	* @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void saveDormitory() {
		String id=getPara("id");
		String name=getPara("name");
		String building_id=getPara("building_id");
		int capacity=getParaToInt("capacity");
		int type=getParaToInt("type");
		int status=getParaToInt("status");
		String latitud=getPara("latitude");
		BigDecimal latitude=new BigDecimal(latitud); 
		latitude=latitude.setScale(2, BigDecimal.ROUND_HALF_UP);
		String longitud=getPara("longitude");
		BigDecimal longitude=new BigDecimal(longitud); 
		longitude=longitude.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//保存数据
		boolean result=DormitoryModel.save(id,name,building_id,capacity,type,status,latitude,longitude);
		
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 
	* @Title: updateDormitory
	* @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void updateDormitory() {
		String id=getPara("id");
		String name=getPara("name");
		String building_id=getPara("building_id");
		int capacity=getParaToInt("capacity");
		int type=getParaToInt("type");
		int status=getParaToInt("status");
		String latitud=getPara("latitude");
		BigDecimal latitude=new BigDecimal(latitud); 
		latitude=latitude.setScale(2, BigDecimal.ROUND_HALF_UP);
		String longitud=getPara("longitude");
		BigDecimal longitude=new BigDecimal(longitud); 
		longitude=longitude.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		boolean result=DormitoryModel.update(id,name,building_id,capacity,type,status,latitude,longitude);
		
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 
	* @Title: delDormitory
	* @Description:删除信息，这个我们是根据唯一主键id来删除的。
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void delDormitory() {
		String id=getPara("id");
		//删除
		boolean result=DormitoryModel.delDormitoryByID(id);
		//返回结果
		setAttr("result", result);
		renderJson();
	}
	
	
	
	/**
	* @Title: building
	* @Description: 打开学校楼房信息列表页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void building() {
		render("bui/buildinginfo.html");
	}
	/**
	* @Title: queryBuilding
	* @Description: 获取学校楼房信息列表信息（查询），在这里，我们是用异步加载方式，
	* 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void queryBuilding() {
		//获取页面查询的关键字
		String key=getPara("key");
		//开始查询
		Page<BuildingModel> Building=BuildingModel.getList(1, 10,key);
		//将查到的学生信息列表放到infos，给页面
		setAttr("infos", Building);
		//返回格式是json
		renderJson();
	}
	/**
	* @Title: openBuilding
	* @Description:打开添加信息页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openBuildingAdd() {
		render("bui/buildingAdd.html");
	}
	
	/**
	* @Title: getbuilding
	* @Description:获取需要修改的学校楼房信息
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getbuilding() {
		//接收页面数据
		String id=getPara("id");
		//根据条件查询数据库的数据
		BuildingModel Building=BuildingModel.getById(id);
		//放到编辑页面上去
		setAttr("m", Building);
		//返回格式是json
		renderJson();
	}
	/**
	* @Title: opeBuildingEdit
	* @Description:打开修改信息页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openBuildingEdit() {
		//接收页面数据
		String id=getPara("id");
		setAttr("id", id);
		renderFreeMarker("bui/buildingEdit.html");
	}
	/**
	* @Title: saveBuilding
	* @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void saveBuilding() {
		String id=getPara("id");
		String name=getPara("name");
		String school_id=getPara("school_id");
		String addr=getPara("addr");
		String remark=getPara("remark");
		
		//保存数据
		boolean result=BuildingModel.save(id,name,school_id,addr,remark);
		
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 
	* @Title: updateBuildingt
	* @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void updateBuilding() {
		String id=getPara("id");
		String name=getPara("name");
		String school_id=getPara("school_id");
		String addr=getPara("addr");
		String remark=getPara("remark");
		
		boolean result=BuildingModel.update(id,name,school_id,addr,remark);
		
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 
	* @Title: delBuilding
	* @Description:删除信息，这个我们是根据唯一主键id来删除的。
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void delBuilding() {
		String id=getPara("id");
		//删除
		boolean result=BuildingModel.delBuildingByID(id);
		//返回结果
		setAttr("result", result);
		renderJson();
	}
	
	
	/**
	 * 
	 * Stu_info
	 * 
	 */
		public void stuinfos() {
			render("stu_info/stuinfoinfo.html");
		}
		public void queryStuinfos() {
			//获取页面查询的关键字
			String key=getPara("key");
			//开始查询
			Page<StuinfoModel> s=StuinfoModel.getList(1, 10,key);
			//将查到的学生信息列表放到infos，给页面
			setAttr("infos", s);
			//返回格式是json
			renderJson();
		}
		public void openStuinfoAdd() {
			render("stu_info/stuinfoAdd.html");
		}
		public void getstuinfo() {
			//接收页面数据
			String no=getPara("no");
			//根据条件查询数据库的数据
			StuinfoModel student=StuinfoModel.getByNo(no);
			//放到编辑页面上去
			setAttr("m", student);
			//返回格式是json
			renderJson();
		}
		public void openStuinfoEdit() {
			//接收页面数据
			String no=getPara("no");
			setAttr("no", no);
			renderFreeMarker("stu_info/stuinfoEdit.html");
		}
		public void saveStuinfo() {
			String no=getPara("no");
			String name=getPara("name");
			int sex=getParaToInt("sex");
			String birth=getPara("birth");
			String img=getPara("img");
			//保存数据
			boolean result=StuinfoModel.save(no,name,sex,birth,img);
			setAttr("result", result);
			renderJson();
		}
		public void updateStuinfo() {
			String no=getPara("no");
			String name=getPara("name");
			int sex=getParaToInt("sex");
			String birth=getPara("birth");
			String img=getPara("img");
			//更新数据
			boolean result=StuinfoModel.update(no,name,sex,birth,img);
			setAttr("result", result);
			renderJson();
		}
		public void delStuinfo() {
			String no=getPara("no");
			//删除
			boolean result=StuinfoModel.delStuinfoByNO(no);
			//返回结果
			setAttr("result", result);
			renderJson();
		}

		/**
		* @Title: classroom
		* @Description: 打开教室信息列表页面
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void classroom() {
			render("cla/classroominfo.html");
		}
		/**
		* @Title: queryClass
		* @Description: 获取学生宿舍信息列表信息（查询），在这里，我们是用异步加载方式，
		* 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void queryClassroom() {
			//获取页面查询的关键字
			String key=getPara("key");
			//开始查询
			Page<ClassroomModel> Classroom=ClassroomModel.getList(1, 10,key);
			//将查到的学生信息列表放到infos，给页面
			setAttr("infos", Classroom);
			//返回格式是json
			renderJson();
		}
		/**
		* @Title: openDormitoryAdd
		* @Description:打开添加信息页面
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void openClassroomAdd() {
			render("cla/classroomAdd.html");
		}
		
		/**
		* @Title: getdormitory
		* @Description:获取需要修改的学生宿舍信息
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void getclassroom() {
			//接收页面数据
			String id=getPara("id");
			//根据条件查询数据库的数据
			ClassroomModel Classroom=ClassroomModel.getById(id);
			//放到编辑页面上去
			setAttr("m", Classroom);
			//返回格式是json
			renderJson();
		}
		/**
		* @Title: openDormitoryEdit
		* @Description:打开修改信息页面
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void openClassroomEdit() {
			//接收页面数据
			String id=getPara("id");
			setAttr("id", id);
			renderFreeMarker("cla/classroomEdit.html");
		}
		/**
		* @Title: saveDormitory
		* @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void saveClassroom() {
			String id=getPara("id");
			String name=getPara("name");
			String building_id=getPara("building_id");
			int capacity=getParaToInt("capacity");
			int type=getParaToInt("type");
			int status=getParaToInt("status");
			String latitud=getPara("latitude");
			BigDecimal latitude=new BigDecimal(latitud); 
			latitude=latitude.setScale(2, BigDecimal.ROUND_HALF_UP);
			String longitud=getPara("longitude");
			BigDecimal longitude=new BigDecimal(longitud); 
			longitude=longitude.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			//保存数据
			boolean result=ClassroomModel.save(id,name,building_id,capacity,type,status,latitude,longitude);
			
			setAttr("result", result);
			renderJson();
		}
		/**
		 * 
		* @Title: updateDormitory
		* @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void updateClassroom() {
			String id=getPara("id");
			String name=getPara("name");
			String building_id=getPara("building_id");
			int capacity=getParaToInt("capacity");
			int type=getParaToInt("type");
			int status=getParaToInt("status");
			String latitud=getPara("latitude");
			BigDecimal latitude=new BigDecimal(latitud); 
			latitude=latitude.setScale(2, BigDecimal.ROUND_HALF_UP);
			String longitud=getPara("longitude");
			BigDecimal longitude=new BigDecimal(longitud); 
			longitude=longitude.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			boolean result=ClassroomModel.update(id,name,building_id,capacity,type,status,latitude,longitude);
			
			setAttr("result", result);
			renderJson();
		}
		/**
		 * 
		* @Title: delDormitory
		* @Description:删除信息，这个我们是根据唯一主键id来删除的。
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void delClassroom() {
			String id=getPara("id");
			//删除
			boolean result=ClassroomModel.delClassroomByID(id);
			//返回结果
			setAttr("result", result);
			renderJson();
		}


		/**
		 * @Title: userInfo @Description: 打开学生信息列表页面 @param 参数 @return void 返回类型 @throws
		 */
		public void userInfo() {
			render("userinfo/userinfoInfo.html");
		}

		/**
		 * @Title: openUserInfoAdd @Description:打开添加信息页面 @param 参数 @return void
		 *         返回类型 @throws
		 */
		public void openUserInfoAdd() {
			render("userinfo/userinfoAdd.html");
		}

		/**
		 * @Title: openUserInfoEdit @Description:打开修改信息页面 @param 参数 @return void
		 *         返回类型 @throws
		 */
		public void openUserInfoEdit() {
			// 接收页面数据
			String id = getPara("id");

			setAttr("id", id);
			renderFreeMarker("userinfo/userinfoEdit.html");
		}

		/**
		 * @Title: queryUserInfo @Description: 获取学生信息列表信息（查询），在这里，我们是用异步加载方式，
		 *         就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
		 */
		public void queryUserInfo() {
			// 获取页面查询的关键字
			String key = getPara("key");
			// 开始查询
			Page<UserInfoModel> date = UserInfoModel.getList(1, 10, key);
			// 将查到的学生信息列表放到infos，给页面
			setAttr("infos", date);
			// 返回格式是json
			renderJson();
		}

		/**
		 * @Title: getUserInfo @Description:获取需要修改的学生信息 @param 参数 @return void
		 *         返回类型 @throws
		 */
		public void getUserInfo() {
			// 接收页面数据
			String id = getPara("id");
			// 根据条件查询数据库的数据
			UserInfoModel date = UserInfoModel.getById(id);
			// *放到编辑页面上去*
			setAttr("d", date);
			// 返回格式是json
			renderJson();
		}

		/**
		 * @Title: saveUserInfo @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
		 *         参数 @return void 返回类型 @throws
		 */
		public void saveUserInfo() {
			// 获取系统时间
			Date time = new Date(0);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String id = UUID.randomUUID().toString();
			String username = getPara("username");
			String password = getPara("password");
			String create_time = df.format(time.getTime());
			String img = getPara("img");
			String status = getPara("status");
			// 保存数据
			boolean result = UserInfoModel.save(id, username, password, create_time, img, Integer.parseInt(status));

			setAttr("result", result);
			renderJson();
		}

		/**
		 * 
		 * @Title: updateUserInfo @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param
		 *         参数 @return void 返回类型 @throws
		 */
		public void updateUserInfo() {
			String id = getPara("id");
			String username = getPara("username");
			String password = getPara("password");
			String create_time = getPara("create_time");
			String img = getPara("img");
			String status = getPara("status");

			// 更新数据
			boolean result = UserInfoModel.update(id, username, password, create_time, img, Integer.parseInt(status));

			setAttr("result", result);
			renderJson();
		}

		/**
		 * 
		 * @Title: delUserInfo @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return
		 *         void 返回类型 @throws
		 */
		public void delUserInfo() {
			String id = getPara("id");
			// 删除
			boolean result = UserInfoModel.delUserInfoById(id);
			// 返回结果
			setAttr("result", result);
			renderJson();
		}
		/**
		* @Title: role
		* @Description: 打开学生信息列表页面
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void role() {
			render("role/roleinfo.html");
		}
		
		/**
		* @Title: queryRole
		* @Description: 获取学生信息列表信息（查询），在这里，我们是用异步加载方式，
		* 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void queryRole() {
			//获取页面查询的关键字
			String key=getPara("key");
			//开始查询
			Page<Role_infoModel> role=Role_infoModel.getList(1, 10,key);
			//将查到的学生信息列表放到infos，给页面
			setAttr("infos", role);
			//返回格式是json
			renderJson();
		}
		/**
		* @Title: openRoleAdd
		* @Description:打开添加信息页面
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void openRoleAdd() {
			render("role/roleAdd.html");
		}
		/**
		* @Title: getrole
		* @Description:获取需要修改的学生信息
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void getrole() {
			//接收页面数据
			String id=getPara("id");
			//根据条件查询数据库的数据
			Role_infoModel role=Role_infoModel.getByNo(id);
			//放到编辑页面上去
			setAttr("m", role);
			//返回格式是json
			renderJson();
		}
		/**
		* @Title: openRoleEdit
		* @Description:打开修改信息页面
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void openRoleEdit() {
			//接收页面数据
			String id=getPara("id");
			setAttr("id", id);
			renderFreeMarker("role/roleEdit.html");
		}
		/**
		* @Title: saveRole
		* @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void saveRole() {
			String id=getPara("id");
			String rolename=getPara("rolename");
			int level=getParaToInt("level");
			String control=getPara("control");
			String remark=getPara("remark");
			String user_id=getPara("user_id");
			//保存数据
			boolean result=Role_infoModel.save(id,rolename,level,control,remark,user_id);
			
			setAttr("result", result);
			renderJson();
		}
		/**
		 * 
		* @Title: updateRole
		* @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void updateRole() {
			String id=getPara("id");
			String rolename=getPara("rolename");
			int level=getParaToInt("level");
			String control=getPara("control");
			String remark=getPara("remark");
			String user_id=getPara("user_id");
			//更新数据
			boolean result=Role_infoModel.update(id,rolename,level,control,remark,user_id);
			
			setAttr("result", result);
			renderJson();
		}
		/**
		 * 
		* @Title: delRole
		* @Description:删除信息，这个我们是根据唯一主键id来删除的。
		* @param     参数
		* @return void    返回类型
		* @throws
		 */
		public void delRole() {
			String id=getPara("id");
			//删除
			boolean result=Role_infoModel.delRoleByID(id);
			//返回结果

			setAttr("result", result);
			renderJson();
		}
		/**
		 * @Title: schools @Description: 打开学生信息列表页面 @param 参数 @return void 返回类型 @throws
		 */
	    public void schools() {
	    	render("school/schoolinfo.html");
	    }
	    /**
		 * @Title: querySchools @Description: 获取学校信息列表信息（查询），在这里，我们是用异步加载方式，
		 * 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
		 */
	    public void querySchools() {
	    	//获取查询页面的关键字key
	    	String key=getPara("key");
	    	//开始查询
	    	Page<SchoolModel> schools=SchoolModel.getList(1, 10, key);
	    	//将查到的信息给infos，放到页面
	    	setAttr("infos", schools);
	    	renderJson();
	    }
	    /**
		 * @Title: openSchoolAdd @Description:打开添加信息页面 @param 参数 @return void
		 * 返回类型 @throws
		 */
	    public void openSchoolAdd() {
			render("school/schoolAdd.html");
		}
	    /**
		 * @Title: getSchool @Description:获取需要修改的学校信息 @param 参数 @return void
		 * 返回类型 @throws
		 */
	    public void getSchool() {
	    	//获取需要修改的学校信息
	    	String id=getPara("id");
	    	// 根据条件查询数据库的数据
	    	SchoolModel school=SchoolModel.getById(id);
	    	//放到编辑页面上
	    	setAttr("m", school);
	    	//以Json格式返回
	    	renderJson();
	    }
	    /**
		 * @Title: openSchoolEdit @Description:打开修改信息页面 @param 参数 @return void
		 * 返回类型 @throws
		 */
	    public void openSchoolEdit() {
	    	//获取页面
	    	String id=getPara("id");
	    	setAttr("id", id);
	    	renderFreeMarker("school/schoolEdit.html");
	    }
	    /**
		 * @Title: saveSchool @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
		 * 参数 @return void 返回类型 @throws
		 */
	    public void saveSchool() {
	    	String id=getPara("id");
	    	String schoolname=getPara("schoolname");
	    	String no=getPara("no");
	    	String addr=getPara("addr");
	    	String img=getPara("img");
	    	String remark=getPara("remark");
	    	boolean result=SchoolModel.save(id, schoolname, no, addr, img, remark);
	    	setAttr("result", result);
	    	renderJson();
	    }
	    /**
		 * @Title: updateSchool @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param
		 * 参数 @return void 返回类型 @throws
		 */
	    public void updateSchool() {
	    	String id=getPara("id");
	    	String schoolname=getPara("schoolname");
	    	String no=getPara("no");
	    	String addr=getPara("addr");
	    	String img=getPara("img");
	    	String remark=getPara("remark");
	    	boolean result=SchoolModel.update(id, schoolname, no, addr, img, remark);
	    	setAttr("result", result);
	    	renderJson();
	    }
	    /**
	     * @Title:delSchool  @Description:删除信息，根据主键id进行删除
	     */
	    public void delSchool() {
	    	String id=getPara("id");
	    	boolean result=SchoolModel.delSchoolById(id);
	    	setAttr("result", result);
	    	renderJson();
	    }

}
