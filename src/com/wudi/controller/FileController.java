package com.wudi.controller;
import com.jfinal.core.Controller;
/**
* TODO:肖，文件上传下载控制类
* @Description: ???
* @author xiao
* @date 2018年11月21日下午5:52:58
 */
public class FileController extends Controller {
	public void index() {
		setAttr("info", "这是文件上传下载控制器，你确定要找这个？？");
		renderJson();
	}
	/**
	 *  TODO:文件上传
	* @Title: upload
	* @Description:???
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void uploadImg() {
		renderJson();
	}

}
