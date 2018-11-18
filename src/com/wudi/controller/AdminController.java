package com.wudi.controller;

import java.math.BigDecimal;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.model.NavsModel;
import com.wudi.model.admin.Stu_familyModel;
import com.wudi.model.StudentModel;
import com.wudi.model.admin.BuildingModel;
import com.wudi.model.admin.ClassroomModel;
import com.wudi.model.admin.DormitoryModel;
import com.wudi.model.admin.StuContatcModel;
import com.wudi.model.admin.StuinfoModel;

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
	 * TODO:根据id查找信息数据
	 */
	public void getModeListById() {
		String id=getPara("id");
		NavsModel m=NavsModel.getModeById(id);
		List<NavsModel> ml=NavsModel.getModeListById(id);
		setAttr("m", m);//找数据去更新
		setAttr("ml", ml);//父节点列表
		renderJson();
	}
	/**
	 * TODO:根据fid查找信息数据
	 */
	public void getModeByFId() {
		List<NavsModel> ml=NavsModel.getModeListById("-1");
		setAttr("ml", ml);//找数据去更新
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
	 * 显示菜单列表
	 */
	public void stucontactinfo() {
		render("stucontact/stucontactinfo.html");
	}
	/**
	 * 
	* @Title: getstucontact
	* @Description: 获取侧菜单数据列表
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getStucontact() {
		//获取页面查询的关键字
		String key=getPara("key");
		Page<StuContatcModel> list=StuContatcModel.getList(1,100,key);
		setAttr("infos", list);
		renderJson();
	}
	/**
	 * 打开菜单添加页面
	 */
	public void openStucontactAdd() {
		render("stucontact/stucontactAdd.html");
	}
	/**
	 * 添加保存菜单信息
	 */
	public void saveStucontact() {
		String id=getPara("id");
		String tel=getPara("tel");
		String qq=getPara("qq");
		String weixin=getPara("weixin");
		String other=getPara("other");
		String stu_no=getPara("stu_no");
		boolean result=StuContatcModel.save(id, tel, qq, weixin, other, stu_no);
		setAttr("result", result);
		renderJson();
		
	}
	/**
	 * 打开菜单修改页面
	 */
	public void openStucontactEdit() {
		//接收页面数据
		String id=getPara("id");
		setAttr("id", id);
		renderFreeMarker("stucontact/stucontactEdit.html");
	}
	
	/**
	* @Title: saveStucontac
	* @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void saveStucontac() {
		String id=getPara("id");
		String tel=getPara("tel");
		String qq=getPara("qq");
		String weixin=getPara("weixin");
		String other=getPara("other");
		String stu_no=getPara("stu_no");
		//保存数据
		boolean result=StuContatcModel.save(id,tel, qq, weixin, other, stu_no);
		setAttr("result", result);
		renderJson();
	}

	/**
	 * 
	* @Title: updateStucontact
	* @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情
	* @param     参数
	* @return void    返回类型
	* @throws
	 * 更新保存菜单信息
	 */
	public void updateStucontact() {
		String id=getPara("id");
		String tel=getPara("tel");
		String qq=getPara("qq");
		String weixin=getPara("weixin");	
		String other=getPara("other");
		String stu_no=getPara("stu_no");
		boolean result=StuContatcModel.update(id,tel, qq, weixin, other, stu_no);
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
		 * @Title: stu_families @Description: 打开学生家庭信息列表页面 @param 参数 @return void 返回类型 @throws
		 */
		public void stu_families() {
			render("stu_family/stu_family_info.html");
		}
		/**
		 * @Title: queryStu_family @Description: 获取学生家庭信息列表信息（查询），在这里，我们是用异步加载方式，
		 * 就是说，页面先打开了，然后在用js向后台获取数据，这个就是。 @param 参数 @return void 返回类型 @throws
		 */
		public void queryStu_family() {
			// 获取页面查询的关键字
			String key = getPara("key");
			// 开始查询
			Page<Stu_familyModel> stu = Stu_familyModel.getList(1, 10, key);
			// 将查到的学生家庭信息列表放到stu_family_infos，给页面
			setAttr("infos", stu);
			// 返回格式是json
			renderJson();
		}
	/**
	 @Title: openStu_familyAdd @Description:打开添加信息页面 @param 参数 @return void
	 * 返回类型 @throws
	 */
		public void openStu_familyAdd() {
			render("stu_family/stu_familyAdd.html");
		}
	/**
	 * @Title:getstu_family @Description:获取需要修改的学生家庭信息 @param 参数 @return void
		 * 返回类型 @throws
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
		 * 返回类型 @throws
		 */
		public void openStu_familyEdit() {
			// 接收页面数据
			String id = getPara("id");
			setAttr("id", id);
			renderFreeMarker("stu_family/stu_familyEdit.html");
		}
		/**
		 * @Title: saveStu_family @Description:数据保存，在添加信息页面上，点击保存的那个按键做的事情 @param
		 * 参数 @return void 返回类型 @throws
		 */
		public void saveStu_family() {
			String id = getPara("id");
			String addr = getPara("addr");
			String tel = getPara("tel");
			String remark = getPara("remark");
			String stu_no = getPara("stu_no");
			boolean result = Stu_familyModel.save(id, addr, tel, remark, stu_no);
			setAttr("result", result);
			renderJson();
		}
		/**
		 * @Title: updateStu_family @Description:更新信息，就是修改信息页面，点击保存的那个按钮做的事情 @param
		 * 参数 @return void 返回类型 @throws
		 */
		public void updateStu_family() {
			String id=getPara("id");
			String addr = getPara("addr");
			String tel = getPara("tel");
			String remark = getPara("remark");
			String stu_no = getPara("stu_no");
			boolean result = Stu_familyModel.update(id,addr, tel, remark, stu_no);
			setAttr("result", result);
			renderJson();
		}
		/**
		 * 
		 * @Title: delStu_family @Description:删除信息，这个我们是根据唯一主键id来删除的。 @param 参数 @return
		 * void 返回类型 @throws
		 */
		public void delStu_family() {
			String id = getPara("id");
			boolean result = Stu_familyModel.delStu_familyById(id);
			setAttr("result", result);
			renderJson();
		}
}
