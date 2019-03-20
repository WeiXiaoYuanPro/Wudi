package com.wudi.bean;
/**
 * 
* @Description: 图表分析vo类
* @author xiao
* @date 2018年11月30日上午11:04:54
 */
public class TDvo {
	private String id;
	private String name;
	private int completed;
	private int notCompleted;
	private int totalTask;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCompleted() {
		return completed;
	}
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	public int getNotCompleted() {
		return notCompleted;
	}
	public void setNotCompleted(int notCompleted) {
		this.notCompleted = notCompleted;
	}
	public int getTotalTask() {
		return totalTask;
	}
	public void setTotalTask(int totalTask) {
		this.totalTask = totalTask;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
