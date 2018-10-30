package com.wudi.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.model.NavsModel;
import com.wudi.model.StudentModel;
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
		Object object=NavsModel.getList();
		renderJson(object);
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
	
}
