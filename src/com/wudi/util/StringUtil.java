package com.wudi.util;

import java.util.Date;

public class StringUtil{

	public static boolean isBlankOrEmpty(String string){
		return string==null || string.trim().length() == 0;
	}
	public static String getId() {
		Long t=new Date().getTime();
		return t.toString();
	}
	  /**
     * 获取文件后缀
     * 
     * @param @param fileName
     * @param @return 设定文件
     * @return String 返回类型
     */
    public static String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'), fileName.length());
    }
}
