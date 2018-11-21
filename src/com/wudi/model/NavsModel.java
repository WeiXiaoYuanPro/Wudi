package com.wudi.model;

import java.util.List;
import java.util.UUID;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;

/**
 * 
* @ClassName: NavsModel
* @Description: TODO navs表模型
* @author xiao
* @date 2018年11月15日下午2:45:13
*
 */
public class NavsModel extends Model<NavsModel> {
	private static final long serialVersionUID = 1L;
	public static final String tableName = "navs";
	public static final NavsModel dao = new NavsModel();
	public List<NavsModel> children;
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public String getTitle() {
		return get("title");
	}
	public void setTitle(String title) {
		set("title", title);
	}
	public String getHref() {
		return get("href");
	}
	public void setHref(String href) {
		set("href", href);
	}
	public String getIcon() {
		return get("icon");
	}
	public void setIcon(String icon) {
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
		String sql="select * from "+tableName+" where fid=?";
		return dao.find(sql,getId());
	}
	/**
	 * TODO:xiao 根据id查找信息
	 * @return list
	 */
	public static List<NavsModel> getModeListByFid(String fid){
		String sql="select * from "+tableName+" where fid=?";
		return dao.find(sql,fid);
	}
	/**
	 * TODO:xiao 根据id查找信息
	 * @return list
	 */
	public static NavsModel getModeById(String id){
		return dao.findById(id);
	}
/**
 * 查询列表
* @Title: getListForLeft
* @Description: TODO(这里用一句话描述这个方法的作用)
* @param @return    参数
* @return List<NavsModel>    返回类型
* @throws
 */
	public static List<NavsModel> getListForLeft() {
		String sql="select * from "+tableName+" where fid=-1";//-1默认是父节点
		List<NavsModel> list=dao.find(sql);
		for(NavsModel a:list) {
			a.put("children", a.getChildren());
		}
		return list;
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
			String sql="select * from "+tableName+"";
			List<NavsModel> list=dao.find(sql);
			return list;
		}
		/**
		 * 分页查询显示，就是查找
		 * @param pageNumber
		 * @param pageSize
		 * @param key
		 * @return
		 */
		public static Page<NavsModel> getList(int pageNumber, int pageSize,String key) {
			String sele_sql="select * ";
			StringBuffer from_sql=new StringBuffer();
			from_sql.append("from ").append(tableName);
			if(!StringUtil.isBlankOrEmpty(key)) {
				from_sql.append(" where title like '%"+key+"%'");
			}
			return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
		} 
	/**
	 * 添加
	 * @param title
	 * @param href
	 * @param icon
	 * @param fid
	 * @return
	 */
	public static boolean saveModel(String title,String href, String icon,String fid) {
		NavsModel m=new NavsModel();
		m.setTitle(title);
		m.setHref(href);
		m.setIcon(icon);
		m.setId(UUID.randomUUID().toString());
		m.setFid(fid);
		return m.save();
	}
	/**
	 * 更新
	 * @param id
	 * @param title
	 * @param href
	 * @param icon
	 * @param fid
	 * @return
	 */
	public static boolean updateModel(String id,String title,String href, String icon,String fid) {
		if(fid.equals("-1")) {
			return false;
		}
		NavsModel m=dao.findById(id);
		if(m==null) {
			return false;
		}else {
			m.setTitle(title);
			m.setHref(href);
			m.setIcon(icon);
			m.setFid(fid);
		}
		return m.update();
	}
	//
	
}
