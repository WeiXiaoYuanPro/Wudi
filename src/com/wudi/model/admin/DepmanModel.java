package com.wudi.model.admin;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;

/**
 * 
* @ClassName: DepmanModel
* @Description: TODO depman 表模型
* @author xiao
* @date 2018年11月15日下午2:45:13
*
 */
public class DepmanModel extends Model<DepmanModel> {
	private static final long serialVersionUID = 1L;
	public static final String tableName = "depman";
	public static final DepmanModel dao = new DepmanModel();
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public String getDepname() {
		return get("depname");
	}
	public void setDepname(String depname) {
		set("depname", depname);
	}
	/**
	 * 0正常，1，异常
	 * @return
	 */
	public int getStatus() {
		return get("status");
	}
	public void setStatus(int status) {
		set("status", status);
	}
	public void setCreatetime(Date create_time) {
		set("create_time", create_time);
	}

	public Date getCreatetime() {
		return get("create_time");
	}
	/**
	 * TODO:xiao 根据id查找信息
	 * @return list
	 */
	public static DepmanModel getModeById(String id){
		return dao.findById(id);
	}
	/**
	 * 查询列表
	* @Title: getList
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    参数
	* @return List<NavsModel>    返回类型
	* @throws
	 */
		public static List<DepmanModel> getList() {
			String sql="select * from "+tableName+"";
			List<DepmanModel> list=dao.find(sql);
			return list;
		}
		/**
		 * 分页查询显示，就是查找
		 * @param pageNumber
		 * @param pageSize
		 * @param key
		 * @return
		 */
		public static Page<DepmanModel> getList(int pageNumber, int pageSize,String key) {
			String sele_sql="select * ";
			StringBuffer from_sql=new StringBuffer();
			from_sql.append("from ").append(tableName);
			if(!StringUtil.isBlankOrEmpty(key)) {
				from_sql.append(" where depname like '%"+key+"%'");
			}
			return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
		} 
		/**
		 *  功能：保存开发者信息
		 *  修改时间：2019年3月21日22:47:23
		 *  作者： xiao
		*/
		public static boolean save(String depname) {
			DepmanModel m=new DepmanModel();
			m.setId(StringUtil.getId());
			m.setDepname(depname);
			m.setCreatetime(new Date());
			m.setStatus(0);
			return m.save();
		}
		/**
		 *  功能：修改开发者信息
		 *  修改时间：2019年3月21日22:47:23
		 *  作者： xiao
		*/
		public static boolean updata(String id,String depname) {
			DepmanModel m=getModeById(id);
			if(m==null) {
				return false;
			}else {
				m.setDepname(depname);
				return m.update();
			}
		}
			/**
			 *  功能：修改开发者信息
			 *  修改时间：2019年3月21日22:47:23
			 *  作者： xiao
			*/
			public static boolean del(String id) {
				DepmanModel m=getModeById(id);
				if(m==null) {
					return false;
				}else {
					m.setStatus(1);
					return m.update();
				}
			
		}
}
