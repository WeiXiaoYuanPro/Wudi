package com.wudi.model.admin;

import java.sql.SQLException;
import java.util.UUID;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.mysql.fabric.xmlrpc.base.Data;
import com.wudi.util.StringUtil;
/**
 * 学生信息
 * @author xiao
 *
 */
public class CmsloginLogModel extends Model<CmsloginLogModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "cmslogin_log";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		UUID.randomUUID().toString();
		set("id", id);
	}
	public String getUserName() {
		return get("username");
	}
	public void setName(String username) {
		set("username" , username);
	}
	public String getLogin_Time() {
		return get("login_time");
	}
	public void setLogin_Time(String login_time){
		set("login_time" , login_time);
	}
	
	public String getIp() {
		return get("ip");
	}
	public void setIp(String ip){
		set("ip" , ip);
	}
	public String getAddr() {
		return get("addr");
	}
	public void setAddr(String addr){
		set("addr" , addr);
	}
	public String getRemark() {
		return get("remark");
	}
	public void setRemark(String remark){
		set("remark" , remark);
	}
	public int getstatus(){
		return get("status");
	}
	public String getStatusStr(){
		int status=getstatus();
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
	public static final CmsloginLogModel dao = new CmsloginLogModel();
	
	/**
	 * 分页查询显示，就是查找
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<CmsloginLogModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		if(!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append(" where username like '%"+key+"%'");
		}
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}  
	/**
	 * 根据username查找
	 * @param usernames
	 * @return
	 */
	public static CmsloginLogModel getByNo(String username){
		return dao.findFirst("select * from " + tableName + " where username = ? " , username);
	}
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static CmsloginLogModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	/**
	 * 根据学号删除数据
	 * @param no
	 * @return
	 */
	public static boolean delCmslogin_LogByID(String id) {
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
	
	public static boolean save(String username,String ip,String addr,String login_time) {
		CmsloginLogModel cl = new CmsloginLogModel();
		cl.setId(StringUtil.getId());
		cl.setName(username);
		cl.setLogin_Time(login_time);
		cl.setIp(ip);
		cl.setAddr(addr);
		cl.setRemark("0");
		cl.setStatus(0);
		return cl.save();
	}

}
