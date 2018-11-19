package com.wudi.model.admin;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

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
public class CmsUserModel extends Model<CmsUserModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "cms_user";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		UUID.randomUUID().toString();
		set("id", id);
	}
	public String getUsername() {
		return get("username");
	}
	public void setUsername(String username) {
		set("username", username);
	}
	public String getPassword() {
		return get("password");
	}
	public void setPassword(String password) {
		set("password" , password);
	}
	public Date getCreate_Time() {
		return get("create_time");
	}
	public void setCreate_Time(Date create_time) {
		set("create_time",create_time);
	}
	public String getImg() {
		return get("img");
	}
	public void setImg(String img) {
		set("img" , img);
	}
	public int getType(){
		return get("type");
	}
	public String getTypeStr(){
		int type=getType();
		if(type==1){
			return "普通管理员";
		}
		else{
			return "超级管理员";
		}
	}
	public void setType(int type)
	{
		set("type",type);
	}
	public int getStatus(){
		return get("status");
	}
	public String getStatusStr(){
		int status=getStatus();
		if(status==1){
			return "异常";
		}
		else{
			return "正常";
		}
	}
	public void setStatus(int status)
	{
		set("status",status);
	}


	//因为经常用他，所以干脆给他一个静态的，让他一直存在，免得我们每次new
	public static final CmsUserModel dao = new CmsUserModel();
	
	/**
	 * 分页查询显示，就是查找
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<CmsUserModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		if(!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append(" where name like '%"+key+"%'");
		}
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}  
	/**
	 * 根据username查找
	 * @param username
	 * @return
	 */
	public static CmsUserModel getByUsername(String username){
		return dao.findFirst("select * from " + tableName + " where username = ? " , username);
	}
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static CmsUserModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
/**
 * 
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
	public static boolean save(String username,String password,String img, int type,int status) {
		CmsUserModel s=new CmsUserModel();
		s.setId (UUID.randomUUID().toString());
		s.setPassword(password);
		s.setCreate_Time(new Date());
		s.setImg(img);
		s.setType(type);
		s.setStatus(status);
		s.setUsername(username);
		return s.save();
	}
	
	/**
	 * 保存，这个保存时事务的保存，关于支付，关于钱的保存，我们一般都用这样的保存方法，现在我们暂时不用这个，因为不好调试
	 * @param name
	 * @param sex
	 * @return
	 */
	@Before(Tx.class)
	public static boolean save(final CmsUserModel cms_user){
		boolean succeed = Db.tx(new IAtom() {
					
					@Override
					public boolean run() throws SQLException {
						cms_user.save();
						return true;
					}
					});
				return succeed;
	}
	/**
	 * 更新
	 */
	public static boolean update(String id,String username,String password,String img,int type,int status){
		CmsUserModel model=CmsUserModel.getById(id);
		if(model==null){
			return false;
		}
		model.setId(id);
		model.setUsername(username);
		model.setPassword(password);
		//model.setCreate_Time(create_time);
		model.setImg(img);
		model.setType(type);
		model.setStatus(status);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 根据学号删除数据
	 * @param no
	 * @return
	 */
	public static boolean delCmsUserByID(String id) {
		try {
			String delsql="DELETE FROM "+tableName+" WHERE id=?";
			int iRet=Db.update(delsql, id);
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

}
