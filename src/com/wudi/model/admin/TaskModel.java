package com.wudi.model.admin;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;

/**
 * 
* @ClassName: TaskModel
* @Description: TODO navs表模型
* @author xiao
* @date 2018年11月29日下午2:45:13
*
 */
public class TaskModel extends Model<TaskModel> {
	private static final long serialVersionUID = 1L;
	public static final String tableName = "task";
	public static final TaskModel dao = new TaskModel();
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
	public int getStatus() {
		return get("status");
	}
	public void setStatus(int status) {
		set("status", status);
	}
	public Date getCreate_time() {
		return get("create_time");
	}
	public void setCreate_time(Date create_time) {
		set("create_time", create_time);
	}
	public Date getFinish_time() {
		return get("finish_time");
	}
	public void setFinish_time(Date finish_time) {
		set("finish_time", finish_time);
	}
	public Date getDeadline() {
		return get("deadline");
	}
	public void setDeadline(Date deadline) {
		set("deadline", deadline);
	}
	public String getContent() {
		return get("content");
	}
	public void setContent(String content) {
		set("content", content);
	}
	public String getExecutor() {
		return get("executor");
	}
	public void setExecutor(String executor) {
		set("executor", executor);
	}

	/**
	 * TODO:xiao 根据id查找信息
	 * @return list
	 */
	public static TaskModel getModeById(String id){
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
		public static List<TaskModel> getList() {
			String sql="select * from "+tableName+"";
			List<TaskModel> list=dao.find(sql);
			return list;
		}
		/**
		 * 分页查询显示，就是查找
		 * @param pageNumber
		 * @param pageSize
		 * @param key
		 * @return
		 */
		public static Page<TaskModel> getList(int pageNumber, int pageSize,String key) {
			String sele_sql="select a.*, b.depname ";
			StringBuffer from_sql=new StringBuffer();
			from_sql.append("from ").append(tableName).append(" AS a left join ").append(DepmanModel.tableName).append(" AS b ON a.executor = b.id ");
			if(!StringUtil.isBlankOrEmpty(key)) {
				from_sql.append(" where a.title like '%"+key+"%'");
			}
			return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
		} 
		/**
		 * 保存
		* @Title: saveModel
		* @Description:???
		* @param @param title
		* @param @param deadline
		* @param @param content
		* @param @param executor
		* @param @return    参数
		* @return boolean    返回类型
		* @throws
		 */
	public static boolean saveModel(String title,Date deadline, String content,String executor) {
		TaskModel m=new TaskModel();
		m.setTitle(title);
		m.setId(StringUtil.getId());
		m.setDeadline(deadline);
		m.setCreate_time(new Date());
		m.setContent(content);
		m.setExecutor(executor);
		return m.save();
	}
	/**
	 * 更新z状态
	 * @return
	 */
	public static boolean updateModel(String id) {
		TaskModel m=dao.findById(id);
		if(m==null) {
			return false;
		}else {
			m.setStatus(1);//0未完成，1完成
			m.setFinish_time(new Date());
		}
		return m.update();
	}
	/**
	 * TODO:xiao 根据id查找信息
	 * @return list
	 */
	public static TaskModel getModeShowById(String id){
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("select a.*, b.depname  from ").append(tableName).append(" AS a left join ").append(DepmanModel.tableName).append(" AS b ON a.executor = b.id ");
		from_sql.append(" where a.id=?");
		return dao.findById(id);
	}
	/**
	 * TODO:xiao 根据executor查找信息
	 * @return list
	 */
	public static  List<TaskModel> getModeByexecutor(String executor){
		String sql="select * from "+tableName+" where executor=?";
		List<TaskModel> list=dao.find(sql,executor);
		return list;
	}
}
