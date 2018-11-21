package com.wudi.model.admin;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wudi.util.StringUtil;

public class UserLoginLogModel extends Model<UserLoginLogModel> {
	private static final long serialVersionUID = 1L;
	public static final String tableName = "userloginlog";

	// private String id;
	// private String username;
	// private String login_time;
	// private String ip;
	// private String addr;
	// private String remark;
	// private int status;

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

	public String getLogin_time() {
		return get("login_time");
	}

	public void setLogin_time(String login_time) {
		set("login_time", login_time);
	}

	public String getIp() {
		return get("ip");
	}

	public void setIp(String ip) {
		set("ip", ip);
	}

	public String getAddr() {
		return get("addr");
	}

	public void setAddr(String addr) {
		set("addr", addr);
	}

	public String getRemark() {
		return get("remark");
	}

	public void setRemark(String remark) {
		set("remark", remark);
	}

	public int getStatus() {
		return get("status");
	}

	public void setStatus(int status) {
		set("status", status);
	}

	// 因为经常用他，所以干脆给他一个静态的，让他一直存在，免得我们每次new
	public static final UserLoginLogModel dao = new UserLoginLogModel();

	/**
	 * 分页查询显示，就是查找
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<UserLoginLogModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName);
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append(" where name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}

	/**
	 * 根据id查找
	 * 
	 * @param id
	 * @return
	 */
	public static UserLoginLogModel getById(Object id) {
		return dao.findFirst("select *  from " + tableName + " where id = ? ", id);
	}

	/**
	 * 
	 * @Title: save @Description:保存，这里是以分别参数传下来的，你们还可以用对象的信息传下来，喜欢这么写就怎么写 @param
	 * id @param username @param login_time @param ip @param addr @param
	 * remark @param status @param @return 参数 @return boolean 返回类型 @throws
	 */
	public static boolean saveModel(String id, String username, String login_time, String ip, String addr, String remark,
			int status) {
		UserLoginLogModel s = new UserLoginLogModel();
		s.setId(id);
		s.setUsername(username);
		s.setLogin_time(login_time);
		s.setId(ip);
		s.setAddr(addr);
		s.setRemark(remark);
		s.setStatus(status);
		return s.save();
	}

	/**
	 * 保存，这个保存时事务的保存，关于支付，关于钱的保存，我们一般都用这样的保存方法，现在我们暂时不用这个，因为不好调试
	 * 
	 * @param name
	 * @param sex
	 * @return
	 */
	@Before(Tx.class)
	public static boolean save(final UserLoginLogModel userloginlog) {
		boolean succeed = Db.tx(new IAtom() {

			@Override
			public boolean run() throws SQLException {
				userloginlog.save();
				return true;
			}
		});
		return succeed;
	}

	/**
	 * 更新
	 */
	public static boolean update(String id, String username, String login_time, String ip, String addr, String remark,int status) {
		UserLoginLogModel model = UserLoginLogModel.getById(id);
		if (model == null) {
			return false;
		}
		model.setId(id);
		model.setUsername(username);
		model.setLogin_time(login_time);
		model.setId(ip);
		model.setAddr(addr);
		model.setRemark(remark);
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
	 * @param ID
	 * @return
	 */
	public static boolean delUserLoginLogByID(String id) {
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
	

}
