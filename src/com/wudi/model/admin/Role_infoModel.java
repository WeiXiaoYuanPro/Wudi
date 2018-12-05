package com.wudi.model.admin;

import java.sql.SQLException;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wudi.util.StringUtil;
/**
 * 学生信息
 * @author xiao
 *
 */
public class Role_infoModel extends Model<Role_infoModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "role_info";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public String getRolename() {
		return get("rolename");
	}
	public void setRolename(String rolename) {
		set("rolename", rolename);
	}
	public int getLevel() {
		return get("level");
	}
	public void setLevel(int level) {
		set("level" , level);
	}
	public String getControl() {
		return get("control");
	}
	public void setControl(String control) {
		set("control" , control);
	}
	public String getRemark(){
		return get("remark");
	}
	public void setRemark(String remark) {
		set("remark" , remark);
	}
	public String getUser_id(){
		return get("user_id");
	}
	public void setUser_id(String user_id) {
		set("user_id" , user_id);
	}
	//因为经常用他，所以干脆给他一个静态的，让他一直存在，免得我们每次new
	public static final Role_infoModel dao = new Role_infoModel();
	
	/**
	 * 分页查询显示，就是查找
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<Role_infoModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		if(!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append(" where name like '%"+key+"%'");
		}
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}  
	/**
	 * 根据no查找
	 * @param no
	 * @return
	 */
	public static Role_infoModel getByNo(String no){
		return dao.findFirst("select * from " + tableName + " where no = ? " , no);
	}
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static Role_infoModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
/**
 * 
* @Title: save
* @Description:保存，这里是以分别参数传下来的，你们还可以用对象的信息传下来，喜欢这么写就怎么写
* @param @param no
* @param @param name
* @param @param cls
* @param @param sex
* @param @return    参数
* @return boolean    返回类型
* @throws
 */
	public static boolean save(String id,String rolename,int level,String control,String remark,String user_id) {
		Role_infoModel s=new Role_infoModel();
		s.setId(id);
		s.setRolename(rolename);
		s.setLevel(level);
		s.setControl(control);
		s.setRemark(remark);
		s.setUser_id(user_id);
		return s.save();
	}
	
	/**
	 * 保存，这个保存时事务的保存，关于支付，关于钱的保存，我们一般都用这样的保存方法，现在我们暂时不用这个，因为不好调试
	 * @param name
	 * @param sex
	 * @return
	 */
	@Before(Tx.class)
	public static boolean save(final Role_infoModel role){
		boolean succeed = Db.tx(new IAtom() {
					
					@Override
					public boolean run() throws SQLException {
						role.save();
						return true;
					}
					});
				return succeed;
	}
	/**
	 * 更新
	 */
	public static boolean update(String id,String rolename,int level,String control,String remark,String user_id){
		Role_infoModel model=Role_infoModel.getById(id);
		if(model==null){
			return false;
		}
		model.setId(id);
		model.setRolename(rolename);
		model.setLevel(level);
		model.setControl(control);
		model.setRemark(remark);
		model.setUser_id(user_id);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 根据id删除数据
	 * @param no
	 * @return
	 */
	public static boolean delRoleByID(String id) {
		try {
			String delsql="DELETE FROM "+tableName+" WHERE id=?";
			int iRet=Db.update(delsql,id);
			if(iRet > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Role_infoModel findModelbyUserid(String user_id) {
		return dao.findFirst("select *  from " + tableName + " where user_id = ? " , user_id);
	}
}
