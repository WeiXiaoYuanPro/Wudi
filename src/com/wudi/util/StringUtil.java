package com.wudi.util;

public class StringUtil{

	public static boolean isBlankOrEmpty(String string){
		return string==null || string.trim().length() == 0;
	}
}
