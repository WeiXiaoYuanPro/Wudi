package com.wudi.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.model.NavsModel;
import com.wudi.model.StudentModel;
import com.wudi.model.admin.BuildingModel;
import com.wudi.model.admin.ClassroomModel;
import com.wudi.model.admin.CmsUserModel;
import com.wudi.model.admin.CmsloginLogModel;
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
import com.wudi.util.StringUtil;

/**
 * 
 * @ClassName: AdminController
 * @Description: TODO 后台管理页面跳转管理类
 * @author xiao
 * @date 2018年10月29日下午4:08:09
 *
 */
public class AdminController extends Controller {

	/**
	 * 
	 * @Title: index @Description:后台管理默认到达页面 @param 参数 @return void 返回类型 @throws
	 */
	public void index() {
		render("index.html");
	}

	/**
	 * 
	 * @Title: getnavs @Description: 获取主页面左侧菜单数据 @param 参数 @return void 返回类型 @throws
	 */
	public void getnavs() {
		Object object = NavsModel.getListForLeft();
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
	 * @Title: getnavs @Description: 获取侧菜单数据列表 @param 参数 @return void 返回类型 @throws
	 */
	public void getNavsList() {
		// 获取页面查询的关键字
		String key = getPara("key");
		int limit=getParaToInt("limit");
		int page=getParaToInt("page");
		Page<NavsModel> list = NavsModel.getList(page, limit, key);
		setAttr("code", 0);
		setAttr("msg", "你好！");
		setAttr("count", list.getTotalRow());
		setAttr("data", list.getList());
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
		String title = getPara("title");
		String href = getPara("href");
		String icon = "&#xe630;";
		String fid = getPara("fid");
		boolean result = NavsModel.saveModel(title, href, icon, fid);
		setAttr("result", result);
		renderJson();

	}

	/**
	 * TODO:根据id查找信息数据
	 */
	public void getModeListById() {
		String id = getPara("id");
		NavsModel m = NavsModel.getModeById(id);
		List<NavsModel> ml = NavsModel.getModeListByFid("-1");
		setAttr("m", m);// 找数据去更新
		setAttr("ml", ml);// 父节点列表
		renderJson();
	}

	/**
	 * TODO:根据fid查找信息数据
	 */
	public void getModeByFId() {
		List<NavsModel> ml = NavsModel.getModeListByFid("-1");
		setAttr("ml", ml);// 找数据去更新
		renderJson();
	}

	/**
	 * 打开菜单修改页面
	 */
	public void openNavsEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("sys/navsEdit.html");
	}

	/**
	 * 更新保存菜单信息
	 */
	public void updatenavs() {
		String id = getPara("id");
		String title = getPara("title");
		String href = getPara("href");
		String icon = "&#xe630;";
		String fid = getPara("fid");
		boolean result = NavsModel.updateModel(id, title, href, icon, fid);
		setAttr("result", result);
		renderJson();

	}

	/**
	 * @Title: stu_contatc @Description: 打开学生家庭信息列表页面 @param 参数 @return void
	 * 返回类型 @throws
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
        int limit=getParaToInt("limit");
        int page=getParaToInt("page");
        Page<Stu_contatcModel> list = Stu_contatcModel.getList(page, limit, key);
        setAttr("code", 0);
        setAttr("msg", "你好！");
        setAttr("count", list.getTotalRow());
        setAttr("data", list.getList());
        renderJson();
	
	}

	/**
	 * @Title: openStu_contatcAdd
	 * @Description:打开添加信息页面
	 * @param 参数
	 * @return void 返回类型
	 * @throws
	 */
	public void openStu_contatcAdd() {
		render("stu_contatc/stu_contatcAdd.html");
	}

	/**
	 * @Title: getstu_contatc @Description:获取需要修改的学生家庭信息 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void getStu_contatc() {
		// 接收页面数据
		String id = getPara("id");
		// 根据条件查询数据库的数据
		Stu_contatcModel stu = Stu_contatcModel.getById(id);
		// 放到编辑页面上去
		setAttr("m", stu);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: openStu_familyEdit @Description:打开修改信息页面 @param 参数 @return void
	 * 返回类型 @throws
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
		boolean result = Stu_contatcModel.save(id, tel, qq, weixin, other, stu_no);
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
		boolean result = Stu_contatcModel.update(id, tel, qq, weixin, other, stu_no);
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
		boolean result = Stu_contatcModel.delStu_contatcById(id);
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
	 * @Title: queryStudents @Description: 获取学生信息列表信息（查询），在这里，我们是用异步加载方式，
	 * 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void queryStudents() {
		// 获取页面查询的关键字
		String key = getPara("key");
		// 开始查询
		Page<StudentModel> students = StudentModel.getList(1, 10, key);
		// 将查到的学生信息列表放到infos，给页面
		setAttr("infos", students);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: openStudentAdd @Description:打开添加信息页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void openStudentAdd() {
		render("stu/studentAdd.html");
	}

	/**
	 * @Title: getstudent @Description:获取需要修改的学生信息 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void getstudent() {
		// 接收页面数据
		String no = getPara("no");
		// 根据条件查询数据库的数据
		StudentModel student = StudentModel.getByNo(no);
		// 放到编辑页面上去
		setAttr("m", student);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: openStudentEdit @Description:打开修改信息页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void openStudentEdit() {
		// 接收页面数据
		String no = getPara("no");
		setAttr("no", no);
		renderFreeMarker("stu/studentEdit.html");
	}

	/**
	 * @Title: saveStudent @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
	 * 参数 @return void 返回类型 @throws
	 */
	public void saveStudent() {
		String no = getPara("no");
		String name = getPara("name");
		String cls = getPara("cls");
		int sex = getParaToInt("sex");
		// 保存数据
		boolean result = StudentModel.save(no, name, cls, sex);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: updateStudent @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param
	 * 参数 @return void 返回类型 @throws
	 */
	public void updateStudent() {
		String no = getPara("no");
		String name = getPara("name");
		String cls = getPara("cls");
		int sex = getParaToInt("sex");
		// 更新数据
		boolean result = StudentModel.update(no, name, sex, cls);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: delStudent @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return
	 * void 返回类型 @throws
	 */
	public void delStudent() {
		String no = getPara("id");
		// 删除
		boolean result = StudentModel.delStudentByNO(no);
		// 返回结果
		setAttr("result", result);
		renderJson();
	}

	/**
	 * @Title: dormitory @Description: 打开学生宿舍信息列表页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void dormitory() {
		render("dor/dormitoryinfo.html");
	}

	/**
	 * @Title: queryDormitory @Description: 获取学生宿舍信息列表信息（查询），在这里，我们是用异步加载方式，
	 * 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
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
	 * 返回类型 @throws
	 */
	public void openDormitoryAdd() {
		render("dor/dormitoryAdd.html");
	}

	/**
	 * @Title: getdormitory @Description:获取需要修改的学生宿舍信息 @param 参数 @return void
	 * 返回类型 @throws
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
	 * 返回类型 @throws
	 */
	public void openDormitoryEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("dor/dormitoryEdit.html");
	}

	/**
	 * @Title: saveDormitory @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
	 * 参数 @return void 返回类型 @throws
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
	 * 参数 @return void 返回类型 @throws
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
	 * void 返回类型 @throws
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
	 * 返回类型 @throws
	 */
	public void building() {
		render("bui/buildinginfo.html");
	}

	/**
	 * @Title: queryBuilding @Description: 获取学校楼房信息列表信息（查询），在这里，我们是用异步加载方式，
	 * 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void queryBuilding() {
		// 获取页面查询的关键字
        String key = getPara("key");
        int limit=getParaToInt("limit");
        int page=getParaToInt("page");
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
	 * 获取
	* @Title: getSchoolModels
	* @Description:???
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getSchoolModels() {
		List<SchoolModel> list=SchoolModel.getListAll();
		setAttr("ml", list);
		renderJson();
	}
	/**
	 * 获取
	* @Title: getSchoolModels
	* @Description:???
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getSchoolzoneModels() {
		String school_id = getPara("school_id");
		List<SchoolZoneModel> list=SchoolZoneModel.getListBySchoolId(school_id);
		setAttr("ml", list);
		renderJson();
	}
	/**
	 * @Title: getbuilding @Description:获取需要修改的学校楼房信息 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void getbuilding() {
		// 接收页面数据
		String id = getPara("id");
		// 根据条件查询数据库的数据
		BuildingModel Building = BuildingModel.getById(id);
		// 放到编辑页面上去
		setAttr("m", Building);
		//联动下拉框学校
		List<SchoolModel> school_list=SchoolModel.getListAll();
		setAttr("sl", school_list);
		//联动下拉框学校分校
		List<SchoolZoneModel> schoolzone_list=SchoolZoneModel.getListById(Building.getSchoolzone_id());
		setAttr("szl", schoolzone_list);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: opeBuildingEdit @Description:打开修改信息页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void openBuildingEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("bui/buildingEdit.html");
	}

	/**
	 * @Title: saveBuilding @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
	 * 参数 @return void 返回类型 @throws
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
	 * 参数 @return void 返回类型 @throws
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
	 * void 返回类型 @throws
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
	 * 
	 * 
	 * Stu_info
	 * 
	 * 
	 */
	public void stuinfos() {
		render("stuinfo/stuinfoinfo.html");
	}

	public void queryStuinfos() {
		// 获取页面查询的关键字
        String key = getPara("key");
        int limit=getParaToInt("limit");
        int page=getParaToInt("page");
        Page<StuinfoModel> list = StuinfoModel.getList(page, limit, key);
        setAttr("code", 0);
        setAttr("msg", "你好！");
        setAttr("count", list.getTotalRow());
        setAttr("data", list.getList());
        renderJson();
	}

	public void openStuinfoAdd() {
		render("stuinfo/stuinfoAdd.html");
	}

	public void getstuinfo() {
		// 接收页面数据
		String no = getPara("no");
		// 根据条件查询数据库的数据
		StuinfoModel student = StuinfoModel.getByNo(no);
		// 放到编辑页面上去
		setAttr("m", student);
		// 返回格式是json
		renderJson();
	}

	public void openStuinfoEdit() {
		// 接收页面数据
		String no = getPara("no");
		setAttr("no", no);
		renderFreeMarker("stuinfo/stuinfoEdit.html");
	}

	public void saveStuinfo() {
		String no = getPara("no");
		String name = getPara("name");
		int sex = getParaToInt("sex");
		String birth = getPara("birth");
		String img = getPara("img");
		// 保存数据
		boolean result = StuinfoModel.save(no, name, sex, birth, img);
		setAttr("result", result);
		renderJson();
	}

	public void updateStuinfo() {
		String no = getPara("no");
		String name = getPara("name");
		int sex = getParaToInt("sex");
		String birth = getPara("birth");
		String img = getPara("img");
		// 更新数据
		boolean result = StuinfoModel.update(no, name, sex, birth, img);
		setAttr("result", result);
		renderJson();
	}

	public void delStuinfo() {
		String no = getPara("no");
		// 删除
		boolean result = StuinfoModel.delStuinfoByNO(no);
		// 返回结果
		setAttr("result", result);
		renderJson();
	}

	/**
	 * @Title: classroom @Description: 打开教室信息列表页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void classroom() {
		render("cla/classroominfo.html");
	}

	/**
	 * @Title: queryClass @Description: 获取学生宿舍信息列表信息（查询），在这里，我们是用异步加载方式，
	 * 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void queryClassroom() {
		// 获取页面查询的关键字
		String key = getPara("key");
		int limit=getParaToInt("limit");
        int page=getParaToInt("page");
        Page<ClassroomModel> list = ClassroomModel.getList(page, limit, key);
        setAttr("code", 0);
        setAttr("msg", "你好！");
        setAttr("count", list.getTotalRow());
        setAttr("data", list.getList());
        renderJson();
	}

	/**
	 * @Title: openDormitoryAdd @Description:打开添加信息页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void openClassroomAdd() {
		render("cla/classroomAdd.html");
	}

	/**
	 * @Title: getdormitory @Description:获取需要修改的学生宿舍信息 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void getclassroom() {
		// 接收页面数据
		String id = getPara("id");
		// 根据条件查询数据库的数据
		ClassroomModel Classroom = ClassroomModel.getById(id);
		// 放到编辑页面上去
		setAttr("m", Classroom);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: openDormitoryEdit @Description:打开修改信息页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void openClassroomEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("cla/classroomEdit.html");
	}

	/**
	 * @Title: saveDormitory @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
	 * 参数 @return void 返回类型 @throws
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
		boolean result = ClassroomModel.save(id, name, building_id, capacity, type, status, latitude, longitude);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: updateDormitory @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param
	 * 参数 @return void 返回类型 @throws
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

		boolean result = ClassroomModel.update(id, name, building_id, capacity, type, status, latitude, longitude);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: delDormitory @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return
	 * void 返回类型 @throws
	 */
	public void delClassroom() {
		String id = getPara("id");
		// 删除
		boolean result = ClassroomModel.delClassroomByID(id);
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
		int limit=getParaToInt("limit");
        int page=getParaToInt("page");
        Page<Stu_familyModel> list = Stu_familyModel.getList(page, limit, key);
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
		Stu_familyModel stu = Stu_familyModel.getById(id);
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
		boolean result = Stu_familyModel.save(addr, tel, remark, stu_no);
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
		boolean result = Stu_familyModel.update(id, addr, tel, remark, stu_no);
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
		boolean result = Stu_familyModel.delStu_familyById(id);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * @Title: cms_user @Description: 打开用户列表页面 @param 参数 @return void 返回类型 @throws
	 */
	public void cms_user() {
		render("cms/cms_userinfo.html");
	}

	/**
	 * @Title: queryCms_User @Description: 获取学生信息列表信息（查询），在这里，我们是用异步加载方式，
	 * 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void queryCms_User() {
		// 获取页面查询的关键字
		String key = getPara("key");
		// 开始查询
		Page<CmsUserModel> Cms_User = CmsUserModel.getList(1, 10, key);
		// 将查到的学生信息列表放到infos，给页面
		setAttr("infos", Cms_User);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: openCms_UserAdd @Description:打开添加信息页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void openCms_UserAdd() {
		render("cms/cms_userAdd.html");
	}

	/**
	 * @Title: getCms_User @Description:获取需要修改的学生信息 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void getcms_user() {
		// 接收页面数据
		String id = getPara("id");
		// 根据条件查询数据库的数据
		CmsUserModel cms_user = CmsUserModel.getById(id);
		// 放到编辑页面上去
		setAttr("m", cms_user);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: openCms_UserEdit @Description:打开修改信息页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void opencms_userEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("cms/cms_userEdit.html");
	}

	/**
	 * @Title: saveCms_User @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
	 * 参数 @return void 返回类型 @throws
	 */
	public void saveCms_User() {
		String username = getPara("username");
		String password = getPara("password");
		String img = getPara("img");
		int type = getParaToInt("type");
		// 保存数据
		boolean result = CmsUserModel.save(username, password, img, type);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: updateDormitory @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param
	 * 参数 @return void 返回类型 @throws
	 */
	public void updateCms_User() {
		String id = getPara("id");
		String username = getPara("username");
		String password = getPara("password");
		// Date create_time=getPara("create_time");
		String img = getPara("img");
		int type = getParaToInt("type");
		int status = getParaToInt("status");

		boolean result = CmsUserModel.update(id, username, password, img, type, status);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: delDormitory @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return
	 * void 返回类型 @throws
	 */
	public void delCms_User() {
		String id = getPara("id");
		// 删除
		boolean result = CmsUserModel.delCmsUserByID(id);
		// 返回结果
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
	 * 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void queryCmslogin_Log() {
		// 获取页面查询的关键字
		String key = getPara("key");
		// 开始查询
		Page<CmsloginLogModel> Cmslogin_Log = CmsloginLogModel.getList(1, 10, key);
		// 将查到的学生信息列表放到infos，给页面
		setAttr("infos", Cmslogin_Log);
		// 返回格式是json
		renderJson();
	}

	/**
	 * 
	 * @Title: delDormitory @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return
	 * void 返回类型 @throws
	 */
	public void delCmslogin_Log() {
		String id = getPara("id");
		// 删除
		boolean result = CmsloginLogModel.delCmslogin_LogByID(id);
		// 返回结果
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
	 * @Title: role @Description: 打开学生信息列表页面 @param 参数 @return void 返回类型 @throws
	 */
	public void role() {
		render("role/roleinfo.html");
	}

	/**
	 * @Title: queryRole @Description: 获取学生信息列表信息（查询），在这里，我们是用异步加载方式，
	 * 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void queryRole() {
		// 获取页面查询的关键字
		String key = getPara("key");
		// 开始查询
		Page<Role_infoModel> role = Role_infoModel.getList(1, 10, key);
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
		Role_infoModel role = Role_infoModel.getByNo(id);
		// 放到编辑页面上去
		setAttr("m", role);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: openRoleEdit @Description:打开修改信息页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void openRoleEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("role/roleEdit.html");
	}

	/**
	 * @Title: saveRole @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param 参数 @return
	 * void 返回类型 @throws
	 */
	public void saveRole() {
		String id = getPara("id");
		String rolename = getPara("rolename");
		int level = getParaToInt("level");
		String control = getPara("control");
		String remark = getPara("remark");
		String user_id = getPara("user_id");
		// 保存数据
		boolean result = Role_infoModel.save(id, rolename, level, control, remark, user_id);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: updateRole @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param 参数 @return
	 * void 返回类型 @throws
	 */
	public void updateRole() {
		String id = getPara("id");
		String rolename = getPara("rolename");
		int level = getParaToInt("level");
		String control = getPara("control");
		String remark = getPara("remark");
		String user_id = getPara("user_id");
		// 更新数据
		boolean result = Role_infoModel.update(id, rolename, level, control, remark, user_id);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: delRole @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void delRole() {
		String id = getPara("id");
		// 删除
		boolean result = Role_infoModel.delRoleByID(id);
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
        int limit=getParaToInt("limit");
        int page=getParaToInt("page");
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
	 * @Title: schoolzone @Description: 打开学校分校信息列表页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void schoolzone() {
		render("sch/schoolzoneinfo.html");
	}

	/**
	 * @Title: querySchoolZone @Description: 获取学校分校信息列表信息（查询），在这里，我们是用异步加载方式，
	 * 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
	 */
	public void querySchoolZone() {
        // 获取页面查询的关键字
        String key = getPara("key");
        int limit=getParaToInt("limit");
        int page=getParaToInt("page");
        Page<SchoolZoneModel> list = SchoolZoneModel.getList(page, limit, key);
        setAttr("code", 0);
        setAttr("msg", "你好！");
        setAttr("count", list.getTotalRow());
        setAttr("data", list.getList());
        renderJson();
	}

	/**
	 * @Title: openBuilding @Description:打开添加信息页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void openSchoolZoneAdd() {
		List<SchoolModel> list=SchoolModel.getListAll();
		setAttr("ml", list);
		render("sch/schoolzoneAdd.html");
	}

	/**
	 * @Title: getschoolzone @Description:获取需要修改的学校楼房信息 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void getschoolzone() {
		// 接收页面数据
		String id = getPara("id");
		// 根据条件查询数据库的数据
		SchoolZoneModel schoolzone = SchoolZoneModel.getById(id);
		// 放到编辑页面上去
		setAttr("m", schoolzone);
		List<SchoolModel> list=SchoolModel.getListAll();
		setAttr("sl", list);
		// 返回格式是json
		renderJson();
	}

	/**
	 * @Title: opeSchoolZoneEdit @Description:打开修改信息页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
	public void openSchoolZoneEdit() {
		// 接收页面数据
		String id = getPara("id");
		setAttr("id", id);
		renderFreeMarker("sch/schoolzoneEdit.html");
	}

	/**
	 * @Title: saveSchoolZone @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
	 * 参数 @return void 返回类型 @throws
	 */
	public void saveSchoolZone() {
		String id = getPara("id");
		String name = getPara("name");
		String addr = getPara("addr");
		String remark = getPara("remark");
		String school_id = getPara("school_id");
		// 保存数据
		boolean result = SchoolZoneModel.save(id, name, addr, remark, school_id);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: updateSchoolZone @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param
	 * 参数 @return void 返回类型 @throws
	 */
	public void updateSchoolZone() {
		String id = getPara("id");
		String name = getPara("name");
		String addr = getPara("addr");
		String remark = getPara("remark");
		String school_id = getPara("school_id");

		boolean result = SchoolZoneModel.update(id, name, addr, remark, school_id);

		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	 * @Title: delSchoolZone @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return
	 * void 返回类型 @throws
	 */
	public void delSchoolZone() {
		String id = getPara("id");
		// 删除
		boolean result = SchoolZoneModel.delSchoolZoneModelByID(id);
		// 返回结果
		setAttr("result", result);
		renderJson();
	}
	
	/**
	 * 登录判断
	 */
	public void Login() {
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String login_time=(dateFormat.format(date));
		String ip = getRequest().getRemoteHost();
		String addr = getRequest().getRemoteAddr();
		String username = getPara("username");
		String password = getPara("password");
		CmsUserModel n = CmsUserModel.test(username);
		int result ;
		if(n==null) {
			result = 0;
		}
		else {
			if(n.getPassword().equals(password)) {
				boolean results = CmsloginLogModel.save(username, ip, addr, login_time);
				setAttr("results", results);
				result = 1;
			}
			else {
				result = 2;
			}	
		}
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 打开任务列表
	* @Title: openTaskList
	* @Description:???
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openTaskList() {
		render("task/taskinfo.html");
	}
	/**
	 * 任务列表
	* @Title: getTaskList
	* @Description:???
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getTaskList() {
		// 获取页面查询的关键字
		String key = getPara("key");
		int limit=getParaToInt("limit");
		int page=getParaToInt("page");
		Page<TaskModel> list = TaskModel.getList(page, limit, key);
		setAttr("code", 0);
		setAttr("msg", "你好！");
		setAttr("count", list.getTotalRow());
		setAttr("data", list.getList());
		renderJson();
	}
	/**
	* @Title: openTaskAdd
	* @Description:???
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openTaskAdd() {
		render("task/taskAdd.html");
	}
	/**
	 * 获取执行者列表下拉框
	* @Title: getexecutors
	* @Description:???
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getexecutors() {
		List<DepmanModel> list=DepmanModel.getList();
		setAttr("sl", list);
		renderJson();
	}
	/**
	 * 保存
	* @Title: savaTask
	* @Description:???
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void savaTask() {
		String title = getPara("title");
		String det = getPara("deadline");
		Date deadline=new Date();
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
	 * 打开任务详情
	* @Title: openTaskShow
	* @Description:???
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openTaskShow(){
		String id=getPara("id");
		setAttr("id", id);
		renderFreeMarker("task/taskEdit.html");
	}
	/**
	 * 查看任务列表
	* @Title: openTaskShow
	* @Description:???
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getTaskShow() {
		String id=getPara("id");
		TaskModel m=TaskModel.getModeShowById(id);
		setAttr("m", m);
		renderJson();
	}
	public void updateTask() {
		String id=getPara("id");
		// 保存数据
		boolean result = TaskModel.updateModel(id);
		setAttr("result", result);
		renderJson();
	}
	
}
