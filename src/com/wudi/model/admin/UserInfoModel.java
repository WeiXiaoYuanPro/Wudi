package com.wudi.model.admin;

import java.sql.SQLException;
import java.util.Date;

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

	public String getId() {
		return get("id");
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

	public void setCreatetime(String create_time) {
		set("create_time", create_time);
	}

	public String getCreatetime() {
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
	 * 
	 * @Title: save @Description:保存，这里是以分别参数传下来的，你们还可以用对象的信息传下来，喜欢这么写就怎么写 @param @param
	 *         no @param @param name @param @param cls @param @param
	 *         sex @param @return 参数 @return boolean 返回类型 @throws
	 */
	public static boolean save(String id, String username, String password, String create_time, String img,
			int status) {
		UserInfoModel model = new UserInfoModel();

		model.setId(id);
		model.setUsername(username);
		model.setPassword(password);
		model.setCreatetime(create_time);
		model.setImg(img);
		model.setStatus(status);

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
		model.setCreatetime(create_time);
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
}
