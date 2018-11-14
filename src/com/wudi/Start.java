package com.wudi;
/**
 * 测试启动类
 * @author xiao
 *
 */

import com.jfinal.core.JFinal;

public class Start {
	public static void main(String[] args) {
		JFinal.start("WebContent", 8086, "/", 5);
			//李颖鹏 18:51	
	}
}
