package com.wudi.model.admin;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wudi.util.StringUtil;

public class UserInfoModel extends Model<UserInfoModel> {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "user_info";
	public static final UserInfoModel dao = new UserInfoModel();
//	//本方法是将自定义属性添加到json中
//	@Override
//	protected Map<String, Object> _getAttrs() {
//		Map<String, Object> attrs=super._getAttrs();
//		Object id=attrs.get("id");
//		if(id!=null) {
//			if(id.toString().contains("001")) {
//				attrs.put("role","教师");//自定义属性
//			}else {
//				attrs.put("role","学生");
//			}
//		}
//		
//		return attrs;
//	}
	public String getId() {
		return get("id");
	}
	public String getRole() {
		return get("role");
	}
	public void setId(String id) {
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
		set("password", password);
	}

	public void setCreatetime(Date create_time) {
		set("create_time", create_time);
	}

	public Date getCreatetime() {
		return get("create_time");
	}
	public void setBirth(Date birth) {
		set("birth", birth);
	}

	public Date getBirth() {
		return get("birth");
	}
	public void setImg(String img) {
		set("img", img);
	}

	public String getImg() {
		return get("img");
	}

	public void setStatus(int status) {
		set("status", status);
	}

	public int getStatus() {
		return get("status");
	}
	public void setSex(int sex) {
		set("sex", sex);
	}

	public int getSex() {
		return get("sex");
	}
	public void setUser_type(int user_type) {
		set("user_type", user_type);
	}

	public int getUser_type() {
		return get("user_type");
	}
	public String getremark() {
		return get("remark");
	}

	public void setremark(String remark) {
		set("remark", remark);
	}
	

	/**
	 * 根据username查找正常用户
	 * @param username
	 * 190320
	 * @return
	 */
	public static UserInfoModel getByUsername(String username){
		return dao.findFirst("select * from " + tableName + " where username = ? and status=0" , username);
	}
	
	/**
	 *  功能：登录
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public static UserInfoModel getByID(String id){
		return dao.findFirst("select * from " + tableName + " where id = ? and status=0" , id);
	}
	/**
	 *  功能：修改密码
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public static boolean updatePassword(String id,String password){
		UserInfoModel m=getById(id);
		m.setPassword(password);
		return m.update();
	}
	
	/**
	 * 分页查询显示，就是查找
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<UserInfoModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName);
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append(" where username like '%" + key + "%'");
		}
		from_sql.append("  ORDER BY id ");
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}

	/**
	 * 根据id查找
	 * 
	 * @param no
	 * @return
	 */
	public static UserInfoModel getById(String id) {
		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	/**
	 *  功能：保存用户信息
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public static boolean save(String id, String username, int user_type,int sex) {
		UserInfoModel model = new UserInfoModel();
		model.setId(id);
		model.setUsername(username);
		model.setUser_type(user_type);
		model.setSex(sex);
		model.setCreatetime(new Date());
		model.setStatus(0);//默认开启	0:正常，1异常
		return model.save();
	}

	/**
	 * 保存，这个保存时事务的保存，关于支付，关于钱的保存，我们一般都用这样的保存方法，现在我们暂时不用这个，因为不好调试
	 * 
	 * @param name
	 * @param sex
	 * @return
	 */
	@Before(Tx.class)
	public static boolean save(final UserInfoModel use) {
		boolean succeed = Db.tx(new IAtom() {

			@Override
			public boolean run() throws SQLException {
				use.save();
				return true;
			}
		});
		return succeed;
	}

	/**
	 * 更新
	 */
	public static boolean update(String id, String username, String password, String create_time, String img,
			int status) {
		UserInfoModel model = UserInfoModel.getById(id);
		if (model == null) {
			return false;
		}
		model.setId(id);
		model.setUsername(username);
		model.setPassword(password);
		model.setImg(img);
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
	 * 
	 * @param no
	 * @return
	 */
	public static boolean delUserInfoById(String id) {
		try {
			String delsql = "DELETE FROM " + tableName + " WHERE id=?";
			int iRet = Db.update(delsql, id);
			if (iRet > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	 public static UserInfoModel findModelbyUsername(String username) {
	    	String sql="select * from "+tableName+" where username=?";
	    	return dao.findFirst(sql,username);
	    }
	 /**
		 *  功能：获取班主任的信息列表
		 *  修改时间：2019年3月27日19:51:43
		 *  作者： xiao
		 */
	 public static List<UserInfoModel> getHeadmasters(String type) {
	    	String sql="select id,username from "+tableName+"";
	    	return dao.find(sql);
	    }
}
