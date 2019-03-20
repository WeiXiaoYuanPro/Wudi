package com.wudi.model.admin;

import java.sql.SQLException;
import java.util.UUID;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wudi.util.StringUtil;

/**
 * 学生联系信息
 * 
 * @author wangchi
 *
 */
public class StuContatcModel extends Model<StuContatcModel> {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "stucontatc";

	public String getId() {
		return get("id");
	}

	public void setId(String id) {
		set("id", id);
	}

	public String getTel() {
		return get("tel");
	}

	public void setTel(String tel) {
		set("tel", tel);
	}

	public String getQq() {
		return get("qq");
	}

	public void setQq(String qq) {
		set("qq", qq);
	}

	public String getWeixin() {
		return get("weixin");
	}

	public void setWeixin(String weixin) {
		set("weixin", weixin);
	}

	public String getOther() {
		return get("other");
	}

	public void setOther(String other) {
		set("other", other);
	}

	public String getStu_no() {
		return get("stu_no");
	}

	public void setStu_no(String stu_no) {
		set("stu_no", stu_no);
	}

	// 因为经常用他，所以干脆给他一个静态的，让他一直存在，免得我们每次new
	public static final StuContatcModel dao = new StuContatcModel();

	/**
	 * 分页查询显示，就是查找
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StuContatcModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName);
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append(" where stu_no like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}

	/**
	 * 根据no查找
	 * 
	 * @param no
	 * @return
	 */
	public static StuContatcModel getByStu_no(String stu_no) {
		return dao.findFirst("select * from " + tableName + " where stu_no = ? ", stu_no);
	}

	/**
	 * 根据id查找
	 * 
	 * @param id
	 * @return
	 */
	public static StuContatcModel getById(Object id) {
		return dao.findFirst("select *  from " + tableName + " where id = ? ", id);
	}

	/**
	 * 
	 * @Title: save
	 * @Description:保存，这里是以分别参数传下来的，你们还可以用对象的信息传下来，喜欢这么写就怎么写
	 * @param id
	 * @param tel
	 * @param qq
	 * @param weixin
	 * @param other
	 * @param stu_no
	 * @param 参数     @return boolean 返回类型 @throws
	 */
	public static boolean save(String id, String tel, String qq, String weixin, String other, String stu_no) {
		StuContatcModel s = new StuContatcModel();
		s.setId(StringUtil.getId());
		s.setTel(tel);
		s.setQq(qq);
		s.setWeixin(weixin);
		s.setOther(other);
		s.setStu_no(stu_no);
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
	public static boolean save(final StuContatcModel student) {
		boolean succeed = Db.tx(new IAtom() {

			@Override
			public boolean run() throws SQLException {
				student.save();
				return true;
			}
		});
		return succeed;
	}

	/**
	 * 更新
	 */
	public static boolean update(String id, String tel, String qq, String weixin, String other, String stu_no) {
		StuContatcModel model = StuContatcModel.getById(id);
		if (model == null) {
			return false;
		}
		model.setId(id);
		model.setTel(tel);
		model.setQq(qq);
		model.setWeixin(weixin);
		model.setOther(other);
		model.setStu_no(stu_no);
		try {
			model.update();
		} catch (Exception e) {
			return false;
		}
		return true;
	}


	/**
	 * 根据id删除数据
	 * 
	 * @param id
	 * @return
	 */
	public static boolean delStu_contatcById(String id) {
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
