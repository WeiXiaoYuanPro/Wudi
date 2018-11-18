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
 * 学生家庭信息
 * @author leejinpeng
 *
 */
public class Stu_familyModel extends Model<Stu_familyModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "stu_family_info";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public String getAddr() {
		return get("addr");
	}
	public void setAddr(String addr) {
		set("addr", addr);
	}
	public String getTel() {
		return get("tel");
	}
	public void setTel(String tel) {
		set("tel", tel);
	}
	public String getRemark() {
		return get("remark");
	}
	public void setRemark(String remark) {
		set("remark", remark);
	}
	public String getStu_no() {
		return get("stu_no");
	}
	public void setStu_no(String stu_no) {
		set("stu_no", stu_no);
	}
	//因为经常用他，所以干脆给他一个静态的，让他一直存在，免得我们每次new
	public static final Stu_familyModel dao = new Stu_familyModel();
	
	/**
	 * 分页查询显示，就是查找
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<Stu_familyModel> getList(int pageNumber, int pageSize,String key) {
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
	public static Stu_familyModel getByNo(String no){
		return dao.findFirst("select * from " + tableName + " where no = ? " , no);
	}
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static Stu_familyModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
/**
 * 
* @Title: save
* @Description:保存，这里是以分别参数传下来的，你们还可以用对象的信息传下来，喜欢这么写就怎么写
* @param @param id
* @param @param addr
* @param @param tel
* @param @param remark
* @param @param stu_no
* @param @return    参数
* @return boolean    返回类型
* @throws
 */
	public static boolean save(String id,String addr,String tel,String remark,String stu_no) {
		Stu_familyModel s=new Stu_familyModel();
		s.setId(id);
		s.setAddr(addr);
		s.setTel(tel);
		s.setRemark(remark);
		s.setStu_no(stu_no);
		return s.save();
	}
	
	/**
	 * 保存，这个保存时事务的保存，关于支付，关于钱的保存，我们一般都用这样的保存方法，现在我们暂时不用这个，因为不好调试
	 * @param name
	 * @param sex
	 * @return
	 */
	@Before(Tx.class)
	public static boolean save(final Stu_familyModel student){
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
	public static boolean update(String id,String addr,String tel,String remark,String stu_no){
		
		Stu_familyModel model=Stu_familyModel.getById(id);
		if(model==null){
			return false;
		}
		model.setId(id);
		model.setAddr(addr);
		model.setTel(tel);
		model.setRemark(remark);
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
	 * @param id
	 * @return
	 */
	public static boolean delStu_familyById(String id) {
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
