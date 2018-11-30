package com.wudi.model.admin;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 
* @ClassName: DTModel
* @Description: TODO dt 视图 表模型
* @author xiao
* @date 2018年11月15日下午2:45:13
*
 */
public class DTModel extends Model<DTModel> {
	private static final long serialVersionUID = 1L;
	public static final String tableName = "dt";
	public static final DTModel dao = new DTModel();
	public String getId() {
		return get("id");
	}
	public String getDepname() {
		return get("depname");
	}
	public int getStatus() {
		return get("status",0);
	}
	public int getTaskstatus() {
		return get("taskstatus",-1);
	}
	/**
	 * TODO:xiao 根据id查找信息
	 * @return list
	 */
	public static DTModel getModeById(String id){
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
		public static List<DTModel> getList() {
			String sql="select * from "+tableName+"";
			List<DTModel> list=dao.find(sql);
			return list;
		}
		/**
		 * 根据executor查找任务
		* @Title: getTasksById
		* @Description:???
		* @param @param id
		* @param @return    参数
		* @return List<TaskModel>    返回类型
		* @throws
		 */
		public static List<TaskModel> getTasksById(String id){
			return TaskModel.getModeByexecutor(id);
		}
}
