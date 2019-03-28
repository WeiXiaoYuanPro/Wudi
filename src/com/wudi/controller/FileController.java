package com.wudi.controller;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.wudi.util.StringUtil;
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
		UploadFile upFile = getFile();//单个上传文件一句搞定  默认路径是 upload
		File file = upFile.getFile();
        String extName = StringUtil.getFileExt(file.getName());
        String filePath = upFile.getUploadPath();
        String fileName = System.currentTimeMillis() + extName;
        file.renameTo(new File(filePath+"\\"+fileName));
        setAttr("src", fileName);
		setAttr("code", 0);
		renderJson();
	}

}
