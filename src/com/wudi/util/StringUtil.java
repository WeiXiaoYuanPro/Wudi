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
}
