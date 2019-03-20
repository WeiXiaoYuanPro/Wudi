package com.wudi.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.bean.TDvo;
import com.wudi.bean.TDvoTwo;
import com.wudi.interceptor.AdminInterceptor;
import com.wudi.model.admin.BuildingModel;
import com.wudi.model.admin.DTModel;
import com.wudi.model.admin.DepmanModel;
import com.wudi.model.admin.DormitoryModel;
import com.wudi.model.admin.LogModel;
import com.wudi.model.admin.RoleInfoModel;
import com.wudi.model.admin.RoomModel;
import com.wudi.model.admin.SchoolModel;
import com.wudi.model.admin.StuContatcModel;
import com.wudi.model.admin.StuFamilyModel;
import com.wudi.model.admin.TaskModel;
import com.wudi.model.admin.UserInfoModel;
import com.wudi.util.StringUtil;

/**
 * 
 * @ClassName: AdminController
 * @Description: TODO 后台管理页面跳转管理类
 * @author xiao
 * @date 2018年10月29日下午4:08:09
 *
 */
@Before(AdminInterceptor.class)
public class AdminController extends Controller {
	/**
	 *  功能：登录
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	@Clear(AdminInterceptor.class)
	public void login() {
		String username = getPara("username");
		String password = getPara("password");
		// 如果不正确，就提示什么不正确？
		// 如果正确，就正常显示系统页面
		UserInfoModel m = UserInfoModel.getByUsername(username);
		// 判断用户名和密码是否正确
		if (m != null) {
			if (m.getPassword().equals(password)) {
				setAttr("result", 0);// 可以登录
				setCookie("cname", username, 36000);
				setSessionAttr("user", m);
			} else {
				setAttr("result", 1);// 密码错误
			}
		} else {
			setAttr("result", 2);// 用户名不存在
		}
		renderJson();
	}

	/**
	 *  功能：退出系统
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	@Clear(AdminInterceptor.class)
	public void outLogin() {
		removeCookie("username");
		removeSessionAttr("user");
		redirect("/admin");
	}

	/**
	 *  功能：主页
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public void index() {
		render("index.html");
	}

	/**
	 *  功能：首页
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public void main() {
		render("main.html");
	}

	
	
	/**
	 * @Title: stu_contatc @Description: 打开学生家庭信息列表页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void stu_contatc() {
		render("stu_contatc/stu_contatcinfo.html");
	}

	/**
	 * @Title: queryStu_family @Description: 获取学生家庭信息列表信息（查询），在这里，我们是用异步加载方式，
	 *         就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void queryStu_contatc() {
		// 获取页面查询的关键字
		String key = getPara("key");
		int limit = getParaToInt("limit");
		int page = getParaToInt("page");
		Page<StuContatcModel> list = StuContatcModel.getList(page, limit, key);
		setAttr("code", 0);
		setAttr("msg", "你好！");
		setAttr("count", list.getTotalRow());
		setAttr("data", list.getList());
		renderJson();

	}

	/**
	 * @Title: openStu_contatcAdd @Description:打开添加信息页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void openStu_contatcAdd() {
		render("stu_contatc/stu_contatcAdd.html");
	}

	/**
	 * @Title: getstu_contatc @Description:获取需要修改的学生家庭信息 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void getStu_contatc() {
		// 接收页面数据
		String id = getPara("id");
		// 根据条件查询数据库的数据
		StuContatcModel stu = StuContatcModel.getById(id);
		// 放到编辑页面上去
		setAttr("m", stu);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: openStu_familyEdit @Description:打开修改信息页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void openStu_contatcEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("stu_contatc/stu_contatcEdit.html");
	}

	/**
	 * @Title: saveStu_contatc @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
	 *         参数 @return void 返回类型 @throws
	 */
	public void saveStu_contatc() {
		String id = getPara("id");
		String tel = getPara("tel");
		String qq = getPara("qq");
		String weixin = getPara("weixin");
		String other = getPara("other");
		String stu_no = getPara("stu_no");
		// 保存数据
		boolean result = StuContatcModel.save(id, tel, qq, weixin, other, stu_no);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * @Title: saveStu_contatc @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param
	 *         参数 @return void 返回类型 @throws
	 */
	public void updateStu_contatc() {
		String id = getPara("id");
		String tel = getPara("tel");
		String qq = getPara("qq");
		String weixin = getPara("weixin");
		String other = getPara("other");
		String stu_no = getPara("stu_no");
		boolean result = StuContatcModel.update(id, tel, qq, weixin, other, stu_no);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: delStu_contatc @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	public void delStu_contatc() {
		String id = getPara("id");
		boolean result = StuContatcModel.delStu_contatcById(id);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * @Title: students @Description: 打开学生信息列表页面 @param 参数 @return void 返回类型 @throws
	 */
	public void students() {
		render("stu/studentinfo.html");
	}

	/**
	 * @Title: dormitory @Description: 打开学生宿舍信息列表页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void dormitory() {
		render("dor/dormitoryinfo.html");
	}

	/**
	 * @Title: queryDormitory @Description: 获取学生宿舍信息列表信息（查询），在这里，我们是用异步加载方式，
	 *         就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void queryDormitory() {
		// 获取页面查询的关键字
		String key = getPara("key");
		// 开始查询
		Page<DormitoryModel> Dormitory = DormitoryModel.getList(1, 10, key);
		// 将查到的学生信息列表放到infos，给页面
		setAttr("infos", Dormitory);
		// 返回格式是json
		setAttr("code", 0);
		setAttr("msg", "你好！");
		setAttr("count", Dormitory.getTotalRow());
		setAttr("data", Dormitory.getList());
		renderJson();

	}

	/**
	 * @Title: openDormitoryAdd @Description:打开添加信息页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void openDormitoryAdd() {
		render("dor/dormitoryAdd.html");
	}

	/**
	 * @Title: getdormitory @Description:获取需要修改的学生宿舍信息 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void getdormitory() {
		// 接收页面数据
		String id = getPara("id");
		// 根据条件查询数据库的数据
		DormitoryModel Dormitory = DormitoryModel.getById(id);
		// 放到编辑页面上去
		setAttr("m", Dormitory);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: openDormitoryEdit @Description:打开修改信息页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void openDormitoryEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("dor/dormitoryEdit.html");
	}

	/**
	 * @Title: saveDormitory @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
	 *         参数 @return void 返回类型 @throws
	 */
	public void saveDormitory() {
		String id = getPara("id");
		String name = getPara("name");
		String building_id = getPara("building_id");
		int capacity = getParaToInt("capacity");
		int type = getParaToInt("type");
		int status = getParaToInt("status");
		String latitud = getPara("latitude");
		BigDecimal latitude = new BigDecimal(latitud);
		latitude = latitude.setScale(2, BigDecimal.ROUND_HALF_UP);
		String longitud = getPara("longitude");
		BigDecimal longitude = new BigDecimal(longitud);
		longitude = longitude.setScale(2, BigDecimal.ROUND_HALF_UP);

		// 保存数据
		boolean result = DormitoryModel.save(id, name, building_id, capacity, type, status, latitude, longitude);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: updateDormitory @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param
	 *         参数 @return void 返回类型 @throws
	 */
	public void updateDormitory() {
		String id = getPara("id");
		String name = getPara("name");
		String building_id = getPara("building_id");
		int capacity = getParaToInt("capacity");
		int type = getParaToInt("type");
		int status = getParaToInt("status");
		String latitud = getPara("latitude");
		BigDecimal latitude = new BigDecimal(latitud);
		latitude = latitude.setScale(2, BigDecimal.ROUND_HALF_UP);
		String longitud = getPara("longitude");
		BigDecimal longitude = new BigDecimal(longitud);
		longitude = longitude.setScale(2, BigDecimal.ROUND_HALF_UP);

		boolean result = DormitoryModel.update(id, name, building_id, capacity, type, status, latitude, longitude);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: delDormitory @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	public void delDormitory() {
		String id = getPara("id");
		// 删除
		boolean result = DormitoryModel.delDormitoryByID(id);
		// 返回结果
		setAttr("result", result);
		renderJson();
	}

	/**
	 * @Title: building @Description: 打开学校楼房信息列表页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void building() {
		render("bui/buildinginfo.html");
	}

	/**
	 * @Title: queryBuilding @Description: 获取学校楼房信息列表信息（查询），在这里，我们是用异步加载方式，
	 *         就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void queryBuilding() {
		// 获取页面查询的关键字
		String key = getPara("key");
		int limit = getParaToInt("limit");
		int page = getParaToInt("page");
		Page<BuildingModel> list = BuildingModel.getList(page, limit, key);
		setAttr("code", 0);
		setAttr("msg", "你好！");
		setAttr("count", list.getTotalRow());
		setAttr("data", list.getList());
		renderJson();
	}

	/**
	 * 返回类型 @throws
	 */
	public void openBuildingAdd() {
		render("bui/buildingAdd.html");
	}

	/**
	 * 获取 @Title: getSchoolModels @Description:??? @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void getSchoolModels() {
		List<SchoolModel> list = SchoolModel.getListAll();
		setAttr("ml", list);
		renderJson();
	}
	public void getbuilding() {
		// 接收页面数据
		String id = getPara("id");
		// 根据条件查询数据库的数据
		BuildingModel Building = BuildingModel.getById(id);
		// 放到编辑页面上去
		setAttr("m", Building);
		// 联动下拉框学校
		List<SchoolModel> school_list = SchoolModel.getListAll();
		setAttr("sl", school_list);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: opeBuildingEdit @Description:打开修改信息页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void openBuildingEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("bui/buildingEdit.html");
	}

	/**
	 * @Title: saveBuilding @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
	 *         参数 @return void 返回类型 @throws
	 */
	public void saveBuilding() {
		String name = getPara("name");
		String schoolzone_id = getPara("schoolzone_id");
		String addr = getPara("addr");
		String remark = getPara("remark");
		// 保存数据
		boolean result = BuildingModel.save(name, addr, remark, schoolzone_id);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: updateBuildingt @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param
	 *         参数 @return void 返回类型 @throws
	 */
	public void updateBuilding() {
		String id = getPara("id");
		String name = getPara("name");
		String schoolzone_id = getPara("schoolzone_id");
		String addr = getPara("addr");
		String remark = getPara("remark");

		boolean result = BuildingModel.update(id, name, addr, remark, schoolzone_id);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: delBuilding @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	public void delBuilding() {
		String id = getPara("id");
		// 删除
		boolean result = BuildingModel.delBuildingByID(id);
		// 返回结果
		setAttr("result", result);
		renderJson();
	}
	/**
	 * @Title: classroom @Description: 打开教室信息列表页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void classroom() {
		render("cla/classroominfo.html");
	}

	/**
	 * @Title: queryClass @Description: 获取学生宿舍信息列表信息（查询），在这里，我们是用异步加载方式，
	 *         就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void queryClassroom() {
		// 获取页面查询的关键字
		String key = getPara("key");
		int limit = getParaToInt("limit");
		int page = getParaToInt("page");
		Page<RoomModel> list = RoomModel.getList(page, limit, key);
		setAttr("code", 0);
		setAttr("msg", "你好！");
		setAttr("count", list.getTotalRow());
		setAttr("data", list.getList());
		renderJson();
	}

	/**
	 * @Title: openDormitoryAdd @Description:打开添加信息页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void openClassroomAdd() {
		render("cla/classroomAdd.html");
	}

	/**
	 * @Title: getdormitory @Description:获取需要修改的学生宿舍信息 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void getclassroom() {
		// 接收页面数据
		String id = getPara("id");
		// 根据条件查询数据库的数据
		RoomModel Classroom = RoomModel.getById(id);
		// 放到编辑页面上去
		setAttr("m", Classroom);
		List<BuildingModel> list = BuildingModel.getListAll();
		setAttr("cl", list);
		// 返回格式是json
		renderJson();

	}

	public void getClassroom() {
		List<BuildingModel> list = BuildingModel.getListAll();
		setAttr("cl", list);
		renderJson();
	}

	/**
	 * @Title: openDormitoryEdit @Description:打开修改信息页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void openClassroomEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("cla/classroomEdit.html");
	}

	/**
	 * @Title: saveDormitory @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
	 *         参数 @return void 返回类型 @throws
	 */
	public void saveClassroom() {
		String id = getPara("id");
		String name = getPara("name");
		String building_id = getPara("building_id");
		int capacity = getParaToInt("capacity");
		int type = getParaToInt("type");
		int status = getParaToInt("status");
		String latitud = getPara("latitude");
		BigDecimal latitude = new BigDecimal(latitud);
		latitude = latitude.setScale(2, BigDecimal.ROUND_HALF_UP);
		String longitud = getPara("longitude");
		BigDecimal longitude = new BigDecimal(longitud);
		longitude = longitude.setScale(2, BigDecimal.ROUND_HALF_UP);

		// 保存数据
		boolean result = RoomModel.save(id, name, building_id, capacity, type, status, latitude, longitude);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: updateDormitory @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param
	 *         参数 @return void 返回类型 @throws
	 */
	public void updateClassroom() {
		String id = getPara("id");
		String name = getPara("name");
		String building_id = getPara("building_id");
		int capacity = getParaToInt("capacity");
		int type = getParaToInt("type");
		int status = getParaToInt("status");
		String latitud = getPara("latitude");
		BigDecimal latitude = new BigDecimal(latitud);
		latitude = latitude.setScale(2, BigDecimal.ROUND_HALF_UP);
		String longitud = getPara("longitude");
		BigDecimal longitude = new BigDecimal(longitud);
		longitude = longitude.setScale(2, BigDecimal.ROUND_HALF_UP);

		boolean result = RoomModel.update(id, name, building_id, capacity, type, status, latitude, longitude);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: delDormitory @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	public void delClassroom() {
		String id = getPara("id");
		// 删除
		boolean result = RoomModel.delClassroomByID(id);
		// 返回结果
		setAttr("result", result);
		renderJson();
	}

	/**
	 * @Title: stu_families @Description: 打开学生家庭信息列表页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void stu_families() {
		render("stu_family/stu_family_info.html");
	}

	/**
	 * @Title: queryStu_family @Description: 获取学生家庭信息列表信息（查询），在这里，我们是用异步加载方式，
	 *         就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void queryStu_family() {
		// 获取页面查询的关键字
		String key = getPara("key");
		int limit = getParaToInt("limit");
		int page = getParaToInt("page");
		Page<StuFamilyModel> list = StuFamilyModel.getList(page, limit, key);
		setAttr("code", 0);
		setAttr("msg", "你好！");
		setAttr("count", list.getTotalRow());
		setAttr("data", list.getList());
		renderJson();
	}

	/**
	 * @Title: openStu_familyAdd @Description:打开添加信息页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void openStu_familyAdd() {
		render("stu_family/stu_familyAdd.html");
	}

	/**
	 * @Title:getstu_family @Description:获取需要修改的学生家庭信息 @param 参数 @return void
	 *                      返回类型 @throws
	 */
	public void getstu_family() {
		// 接收页面数据
		String id = getPara("id");
		// 根据条件查询数据库的数据
		StuFamilyModel stu = StuFamilyModel.getById(id);
		// 放到编辑页面上去
		setAttr("m", stu);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: openStu_familyEdit @Description:打开修改信息页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void openStu_familyEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("stu_family/stu_familyEdit.html");
	}

	/**
	 * @Title: saveStu_family @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
	 *         参数 @return void 返回类型 @throws
	 */
	public void saveStu_family() {
		String addr = getPara("addr");
		String tel = getPara("tel");
		String remark = getPara("remark");
		String stu_no = getPara("stu_no");
		boolean result = StuFamilyModel.save(addr, tel, remark, stu_no);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * @Title: updateStu_family @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param
	 *         参数 @return void 返回类型 @throws
	 */
	public void updateStu_family() {
		String id = getPara("id");
		String addr = getPara("addr");
		String tel = getPara("tel");
		String remark = getPara("remark");
		String stu_no = getPara("stu_no");
		boolean result = StuFamilyModel.update(id, addr, tel, remark, stu_no);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: delStu_family @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	public void delStu_family() {
		String id = getPara("id");
		boolean result = StuFamilyModel.delStu_familyById(id);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * @Title: students @Description: 打开学生信息列表页面 @param 参数 @return void 返回类型 @throws
	 */
	public void cmslogin_log() {
		render("cms1/cmslogin_loginfo.html");
	}

	/**
	 * @Title: queryCms_User @Description: 获取学生信息列表信息（查询），在这里，我们是用异步加载方式，
	 *         就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void queryCmslogin_Log() {
		String key = getPara("key");
		int limit = getParaToInt("limit");
		int page = getParaToInt("page");
		Page<LogModel> list = LogModel.getList(page, limit, key);
		setAttr("code", 0);
		setAttr("msg", "你好！");
		setAttr("count", list.getTotalRow());
		setAttr("data", list.getList());
		renderJson();
	}

	/**
	 * 
	 * @Title: delDormitory @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	public void delCmslogin_Log() {
		String id = getPara("id");
		// 删除
		boolean result = LogModel.delCmslogin_LogByID(id);
		// 返回结果
		setAttr("result", result);
		renderJson();
	}
/*******************用户信息操作访问地址：admin/userInfo *******************************
	/**
	 *  功能：打开用户信息列表
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public void userInfo() {
		render("userinfo/userinfoInfo.html");
	}

	/**
	 *  功能：获取用户列表数据
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public void queryUserInfo() {
		// 获取页面查询的关键字
		String key = getPara("key");
		int limit = getParaToInt("limit");
		int page = getParaToInt("page");
		Page<UserInfoModel> list = UserInfoModel.getList(page, limit, key);
		setAttr("code", 0);
		setAttr("msg", "你好！");
		setAttr("count", list.getTotalRow());
		setAttr("data", list.getList());
		renderJson();
	}

	/**
	 *  功能：打开添加用户信息页面
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public void openUserInfoAdd() {
		render("userinfo/userinfoAdd.html");
	}

	/**
	 *  功能：打开更新用户信息页面
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public void openUserInfoEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("userinfo/userinfoEdit.html");
	}

	/**
	 *  功能：根据用户id获取用户信息，拿去显示，再更新
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
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
	 *  功能：保存用户信息
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public void saveUserInfo() {
		// 获取系统时间
		Date time = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String id = StringUtil.getId();
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
	 *  功能：更新用户信息
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
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
	 *  功能：删除用户信息
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public void delUserInfo() {
		String id = getPara("id");
		// 删除
		boolean result = UserInfoModel.delUserInfoById(id);
		// 返回结果
		setAttr("result", result);
		renderJson();
	}
/****************************************************************
	/**
	 * @Title: role @Description: 打开学生信息列表页面 @param 参数 @return void 返回类型 @throws
	 */
	public void role() {
		render("role/roleinfo.html");
	}

	/**
	 * @Title: queryRole @Description: 获取学生信息列表信息（查询），在这里，我们是用异步加载方式，
	 *         就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void queryRole() {
		// 获取页面查询的关键字
		String key = getPara("key");
		// 开始查询
		Page<RoleInfoModel> role = RoleInfoModel.getList(1, 10, key);
		// 将查到的学生信息列表放到infos，给页面
		setAttr("infos", role);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: openRoleAdd @Description:打开添加信息页面 @param 参数 @return void 返回类型 @throws
	 */
	public void openRoleAdd() {
		render("role/roleAdd.html");
	}

	/**
	 * @Title: getrole @Description:获取需要修改的学生信息 @param 参数 @return void 返回类型 @throws
	 */
	public void getrole() {
		// 接收页面数据
		String id = getPara("id");
		// 根据条件查询数据库的数据
		RoleInfoModel role = RoleInfoModel.getByNo(id);
		// 放到编辑页面上去
		setAttr("m", role);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: openRoleEdit @Description:打开修改信息页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void openRoleEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("role/roleEdit.html");
	}

	/**
	 * @Title: saveRole @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	public void saveRole() {
		String id = getPara("id");
		String rolename = getPara("rolename");
		int level = getParaToInt("level");
		String control = getPara("control");
		String remark = getPara("remark");
		String user_id = getPara("user_id");
		// 保存数据
		boolean result = RoleInfoModel.save(id, rolename, level, control, remark, user_id);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: updateRole @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	public void updateRole() {
		String id = getPara("id");
		String rolename = getPara("rolename");
		int level = getParaToInt("level");
		String control = getPara("control");
		String remark = getPara("remark");
		String user_id = getPara("user_id");
		// 更新数据
		boolean result = RoleInfoModel.update(id, rolename, level, control, remark, user_id);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: delRole @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void delRole() {
		String id = getPara("id");
		// 删除
		boolean result = RoleInfoModel.delRoleByID(id);
		// 返回结果

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
	 *         就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void querySchools() {
		// 获取页面查询的关键字
		String key = getPara("key");
		int limit = getParaToInt("limit");
		int page = getParaToInt("page");
		Page<SchoolModel> list = SchoolModel.getList(page, limit, key);
		setAttr("code", 0);
		setAttr("msg", "你好！");
		setAttr("count", list.getTotalRow());
		setAttr("data", list.getList());
		renderJson();
	}

	/**
	 * @Title: openSchoolAdd @Description:打开添加信息页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void openSchoolAdd() {
		render("school/schoolAdd.html");
	}

	/**
	 * @Title: getSchool @Description:获取需要修改的学校信息 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void getSchool() {
		// 获取需要修改的学校信息
		String id = getPara("id");
		// 根据条件查询数据库的数据
		SchoolModel school = SchoolModel.getById(id);
		// 放到编辑页面上
		setAttr("m", school);
		// 以Json格式返回
		renderJson();
	}

	/**
	 * @Title: openSchoolEdit @Description:打开修改信息页面 @param 参数 @return void
	 *         返回类型 @throws
	 */
	public void openSchoolEdit() {
		// 获取页面
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("school/schoolEdit.html");
	}

	/**
	 * @Title: saveSchool @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	public void saveSchool() {
		String id = getPara("id");
		String schoolname = getPara("schoolname");
		String no = getPara("no");
		String addr = getPara("addr");
		String img = getPara("img");
		String remark = getPara("remark");
		boolean result = SchoolModel.save(id, schoolname, no, addr, img, remark);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * @Title: updateSchool @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param
	 *         参数 @return void 返回类型 @throws
	 */
	public void updateSchool() {
		String id = getPara("id");
		String schoolname = getPara("schoolname");
		String no = getPara("no");
		String addr = getPara("addr");
		String img = getPara("img");
		String remark = getPara("remark");
		boolean result = SchoolModel.update(id, schoolname, no, addr, img, remark);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * @Title:delSchool @Description:删除信息，根据主键id进行删除
	 */
	public void delSchool() {
		String id = getPara("id");
		boolean result = SchoolModel.delSchoolById(id);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * 打开任务列表 @Title: openTaskList @Description:??? @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void openTaskList() {
		render("task/taskinfo.html");
	}

	/**
	 * 任务列表 @Title: getTaskList @Description:??? @param 参数 @return void 返回类型 @throws
	 */
	public void getTaskList() {
		// 获取页面查询的关键字
		String key = getPara("key");
		int limit = getParaToInt("limit");
		int page = getParaToInt("page");
		Page<TaskModel> list = TaskModel.getList(page, limit, key);
		setAttr("code", 0);
		setAttr("msg", "你好！");
		setAttr("count", list.getTotalRow());
		setAttr("data", list.getList());
		renderJson();
	}

	/**
	 * @Title: openTaskAdd @Description:??? @param 参数 @return void 返回类型 @throws
	 */
	public void openTaskAdd() {
		render("task/taskAdd.html");
	}

	/**
	 * 获取执行者列表下拉框 @Title: getexecutors @Description:??? @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void getexecutors() {
		List<DepmanModel> list = DepmanModel.getList();
		setAttr("sl", list);
		renderJson();
	}

	/**
	 * 保存 @Title: savaTask @Description:??? @param 参数 @return void 返回类型 @throws
	 */
	public void savaTask() {
		String title = getPara("title");
		String det = getPara("deadline");
		Date deadline = new Date();
		try {
			deadline = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(det.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String content = getPara("content");
		String executor = getPara("executor");
		// 保存数据
		boolean result = TaskModel.saveModel(title, deadline, content, executor);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * 打开任务详情 @Title: openTaskShow @Description:??? @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void openTaskShow() {
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("task/taskEdit.html");
	}

	/**
	 * 查看任务列表 @Title: openTaskShow @Description:??? @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void getTaskShow() {
		String id = getPara("id");
		TaskModel m = TaskModel.getModeShowById(id);
		setAttr("m", m);
		renderJson();
	}

	public void updateTask() {
		String id = getPara("id");
		// 保存数据
		boolean result = TaskModel.updateModel(id);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * 显示到图表
	 */
	public void getTaskTubiaoinfo() {
		// 获取开发者名单
		List<DTModel> list = DTModel.getList();
		// 解析数据，放到图表中
		List<TDvo> tdList = new ArrayList<TDvo>();
		List<TDvoTwo> ncList = new ArrayList<TDvoTwo>();// 未完成
		List<TDvoTwo> cList = new ArrayList<TDvoTwo>();// 完成
		List<TDvoTwo> allList = new ArrayList<TDvoTwo>();// 所有（）含完成和未完成

		int notcTask = 0;// 未完成的任务
		int ctask = 0;// 已经完成的任务
		for (DTModel a : list) {
			TDvo t = new TDvo();
			t.setName(a.getDepname());
			t.setId(a.getId());
			boolean r = true;
			for (TDvo b : tdList) {
				if (b.getId().equals(a.getId())) {
					r = false;
				}
			}
			if (r) {
				tdList.add(t);
			}
			if (a.getTaskstatus() == 1) {
				ctask++;
			} else if (a.getTaskstatus() == 0) {
				notcTask++;
			}
		}

		for (TDvo b : tdList) {
			int nc = 0;// 记录未完成任务
			int c = 0;// 记录完成完成任务
			for (DTModel a : list) {
				if (b.getId().equals(a.getId())) {
					if (a.getTaskstatus() == 1) {
						c++;
					} else if (a.getTaskstatus() == 0) {
						nc++;
					}
				}
			}
			b.setTotalTask(c + nc);
			b.setNotCompleted(nc);
			b.setCompleted(c);

			TDvoTwo wei = new TDvoTwo();
			TDvoTwo wan = new TDvoTwo();
			wei.setName(b.getId());
			wei.setValue(nc);
			wan.setName(b.getId());
			wan.setValue(c);
			ncList.add(wei);
			cList.add(wan);
		}

		TDvoTwo weiwanczong = new TDvoTwo();
		weiwanczong.setName("未完成");
		weiwanczong.setValue(notcTask);
		TDvoTwo wanczong = new TDvoTwo();
		wanczong.setName("已完成");
		wanczong.setValue(ctask);
		allList.add(weiwanczong);
		allList.add(wanczong);
		///////////////////////////////

		setAttr("allList", allList);
		setAttr("names", tdList);
		setAttr("ncList", ncList);
		setAttr("cList", cList);
		renderJson();
	}

	/**
	 * 打开开发者任务 @Title: openStuTask @Description:??? @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void openStuTask() {
		String stuId = getPara("stuid");
		setAttr("stuid", stuId);
		renderFreeMarker("task/taskinfoforstu.html");
	}

	public void getStuTask() {
		String stuId = getPara("stuid");
		// 获取页面查询的关键字
		String key = getPara("key");
		int limit = getParaToInt("limit");
		int page = getParaToInt("page");
		Page<TaskModel> list = TaskModel.getListForStu(page, limit, key, stuId);
		setAttr("code", 0);
		setAttr("msg", "你好！");
		setAttr("count", list.getTotalRow());
		setAttr("data", list.getList());
		renderJson();
	}

	public void openStuTasksee() {
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("task/tasksee.html");
	}
}
