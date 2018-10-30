package com.wudi.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 
* @ClassName: NavsModel
* @Description: TODO navs表模型
* @author xiao
* @date 2018年10月29日下午2:45:13
*
 */
public class NavsModel extends Model<NavsModel> {
	private static final long serialVersionUID = -5985703092375449851L;
	public static final String tableName = "navs";
	public static final NavsModel dao = new NavsModel();
	public List<NavsModel> children;
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public String getNo() {
		return get("title");
	}
	public void setNo(String title) {
		set("title", title);
	}
	public int getnode() {
		return get("node");
	}
	public void setnode(int node) {
		set("node", node);
	}
	public boolean getspread() {
		int s=get("spread");
		if(s==0) {
			return false;
		}else {
			return true;
		}
	}
	public void setspread(int spread) {
		set("spread", spread);
	}
	public String gethref() {
		return get("href");
	}
	public void sethref(String href) {
		set("href", href);
	}
	public String geticon() {
		return get("icon");
	}
	public void seticon(String icon) {
		set("icon", icon);
	}
	public String getFid() {
		return get("fid");
	}
	public void setFid(String fid) {
		set("fid", fid);
	}
	/**
	 * 
	* @Title:  获取子节点的数据
	* @Description: TODO 获取子节点的数据
	* @param @return    参数
	* @return List<NavsModel>    返回类型
	* @throws
	 */
	public List<NavsModel> getChildren(){
		String sql="select * from "+tableName+" where fid="+getId();
		return dao.find(sql);
	}
/**
 * 查询列表
* @Title: getList
* @Description: TODO(这里用一句话描述这个方法的作用)
* @param @return    参数
* @return List<NavsModel>    返回类型
* @throws
 */
	public static List<NavsModel> getList() {
		String sql="select * from "+tableName+" where node=0";
		List<NavsModel> list=dao.find(sql);
		for(NavsModel a:list) {
			a.put("children", a.getChildren());
		}
		return list;
	}
	
}
