package com.wudi.model.admin;

import java.sql.SQLException;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wudi.util.StringUtil;

public class ClassModel extends Model<ClassModel> {
	private static final long serialVersionUID = 1L;
	public static final String tableName = "class";
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
	public String getUser_no() {
		return get("user_no");
	}
	public void setUser_no(String user_no) {
		set("user_no" , user_no);
	}
	public String getmajor_no() {
		return get("major_no");
	}
	public void setmajor_no(String major_no) {
		set("major_no" , major_no);
	}
	public int getgrade() {
		return get("grade");
	}
	public void setgrade(int grade) {
		set("grade" , grade);
	}
	
	//因为经常用他，所以干脆给他一个静态的，让他一直存在，免得我们每次new
		public static final ClassModel dao = new ClassModel();
		
		/**
		 * 分页查询显示，就是查找
		 * @param pageNumber
		 * @param pageSize
		 * @param key
		 * @return
		 */
		public static Page<ClassModel> getList(int pageNumber, int pageSize,String key) {
			String sele_sql="select a.*,d.username AS teacher,c.`name` AS depname ";
			StringBuffer from_sql=new StringBuffer();
			from_sql.append("FROM ").append(tableName).append(" AS a LEFT JOIN ");
			from_sql.append(MajorModel.tableName).append(" AS b ON a.major_no = b.`name` ");
			from_sql.append(" LEFT JOIN ").append(UserInfoModel.tableName).append(" as d").append(" ON a.user_no = d.username ");
			from_sql.append(" LEFT JOIN ").append(DepmanModel.tableName).append(" as c").append(" ON b.dep_no = c.id ");
			if(!StringUtil.isBlankOrEmpty(key)) {
				from_sql.append(" where a.name like '%"+key+"%'");
			}
			return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
		}  
		/**
		 * 根据id查找
		 * @param id
		 * @return
		 */
		public static ClassModel getById(Object id){
			return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
		}
		public static boolean save(String name,String Remark,String no,int grade) {
			ClassModel s=new ClassModel();
			s.setId(StringUtil.getId());
			s.setName(name);
			s.setUser_no(no);
			s.setgrade(grade);
			return s.save();
		}
		
		/**
		 * 保存，这个保存时事务的保存，关于支付，关于钱的保存，我们一般都用这样的保存方法，现在我们暂时不用这个，因为不好调试
		 * @param name
		 * @param sex
		 * @return
		 */
		@Before(Tx.class)
		public static boolean save(final ClassModel dormitory){
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
		public static boolean update(String id,String name,String Remark,String no){
			ClassModel model=ClassModel.getById(id);
			if(model==null){
				return false;
			}
			model.setId(id);
			model.setName(name);
			model.setUser_no(no);
//			model.setRemark(Remark);
			try {
				model.update();
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		/**
		 * 根据ID删除数据
		 * @param no
		 * @return
		 */
		public static boolean delByID(String id) {
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
