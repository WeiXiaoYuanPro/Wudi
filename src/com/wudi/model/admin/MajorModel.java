package com.wudi.model.admin;

import java.sql.SQLException;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wudi.util.StringUtil;

public class MajorModel extends Model<MajorModel> {
	private static final long serialVersionUID = 1L;
	public static final String tableName = "major";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	
	public String getName() {
		return get("name");
	}
	public void setName(String name) {
		set("name" , name);
	}
	public String getDep_no() {
		return get("dep_no");
	}
	public void setDep_no(String dep_no) {
		set("dep_no" , dep_no);
	}
	public String getRemark() {
		return get("remark");
	}
	public void setRemark(String Remark) {
		set("remark" , Remark);
	}
	
	//因为经常用他，所以干脆给他一个静态的，让他一直存在，免得我们每次new
		public static final MajorModel dao = new MajorModel();
		
		/**
		 * 分页查询显示，就是查找
		 * @param pageNumber
		 * @param pageSize
		 * @param key
		 * @return
		 */
		public static Page<MajorModel> getList(int pageNumber, int pageSize,String key) {
			String sele_sql="select * ";
			StringBuffer from_sql=new StringBuffer();
			from_sql.append("from ").append(tableName);
			if(!StringUtil.isBlankOrEmpty(key)) {
				from_sql.append(" where name like '%"+key+"%'");
			}
			return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
		}  
		/**
		 * 根据id查找
		 * @param id
		 * @return
		 */
		public static MajorModel getById(Object id){
			return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
		}
		public static boolean save(String name,String Remark,String dep_no) {
			MajorModel s=new MajorModel();
			s.setId(StringUtil.getId());
			s.setName(name);
			s.setDep_no(dep_no);
			s.setRemark(Remark);
			return s.save();
		}
		
		/**
		 * 保存，这个保存时事务的保存，关于支付，关于钱的保存，我们一般都用这样的保存方法，现在我们暂时不用这个，因为不好调试
		 * @param name
		 * @param sex
		 * @return
		 */
		@Before(Tx.class)
		public static boolean save(final MajorModel dormitory){
			boolean succeed = Db.tx(new IAtom() {
						
						@Override
						public boolean run() throws SQLException {
							dormitory.save();
							return true;
						}
						});
					return succeed;
		}
		/**
		 * 更新
		 */
		public static boolean update(String id,String name,String Remark,String dep_no){
			MajorModel model=MajorModel.getById(id);
			if(model==null){
				return false;
			}
			model.setId(id);
			model.setName(name);
			model.setDep_no(dep_no);
			model.setRemark(Remark);
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
		public static boolean delMajorByID(String id) {
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
