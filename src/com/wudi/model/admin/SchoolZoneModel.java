package com.wudi.model.admin;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wudi.util.StringUtil;

public class SchoolZoneModel extends Model<SchoolZoneModel>{
	private static final long serialVersionUID = 1L;
	public static final String tableName = "school_zone";
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
	public String getAddr() {
		return get("addr");
	}
	public void setAddr(String addr) {
		set("addr" , addr);
	}
	public String getRemark() {
		return get("remark");
	}
	public void setRemark(String remark) {
		set("remark" , remark);
	}
	public String getSchool_id() {
		return get("school_id");
	}
	public void setSchool_id(String school_id) {
		set("school_id" , school_id);
	}
	
	//因为经常用他，所以干脆给他一个静态的，让他一直存在，免得我们每次new
		public static final SchoolZoneModel dao = new SchoolZoneModel();
		
		/**
		 * 分页查询显示，就是查找
		 * @param pageNumber
		 * @param pageSize
		 * @param key
		 * @return
		 */
		public static Page<SchoolZoneModel> getList(int pageNumber, int pageSize,String key) {
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
		public static SchoolZoneModel getById(Object id){
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
		public static boolean save(String id,String name,String addr,String remark,String school_id) {
			SchoolZoneModel s=new SchoolZoneModel();
			s.setId(UUID.randomUUID().toString());
			s.setName(name);
			s.setAddr(addr);
			s.setRemark(remark);
			s.setSchool_id(school_id);
			
			return s.save();
		}
		
		/**
		 * 保存，这个保存时事务的保存，关于支付，关于钱的保存，我们一般都用这样的保存方法，现在我们暂时不用这个，因为不好调试
		 * @param name
		 * @param sex
		 * @return
		 */
		@Before(Tx.class)
		public static boolean save(final SchoolZoneModel schoolzone){
			boolean succeed = Db.tx(new IAtom() {
						
						@Override
						public boolean run() throws SQLException {
							schoolzone.save();
							return true;
						}
						});
					return succeed;
		}
		/**
		 * 更新
		 */
		public static boolean update(String id,String name,String addr,String remark,String school_id){
			SchoolZoneModel model=SchoolZoneModel.getById(id);
			if(model==null){
				return false;
			}
			model.setId(id);
			model.setName(name);
			model.setAddr(addr);
			model.setRemark(remark);
			model.setSchool_id(school_id);
			
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
		public static boolean delSchoolZoneModelByID(String id) {
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
		/**
		 * TODO:XIAO 查找所有信息
		* @Title: getListAll
		* @Description:???
		* @param @return    参数
		* @return List<SchoolZoneModel>    返回类型
		* @throws
		 */
		public static List<SchoolZoneModel> getListBySchoolId(String school_id) {
			StringBuffer sql=new StringBuffer();
			sql.append("select *  from ").append(tableName).append(" where school_id=?");
			return dao.find(sql.toString(),school_id);
		}
		/**
		 * 用在数据联动下拉框
		* @Title: getListBySchoolId
		* @Description:???
		* @param @param school_id
		* @param @return    参数
		* @return List<SchoolZoneModel>    返回类型
		* @throws
		 */
		public static List<SchoolZoneModel> getListById(String schoolzone_id) {
			SchoolZoneModel m=dao.findById(schoolzone_id);
			StringBuffer sql=new StringBuffer();
			sql.append("select *  from ").append(tableName).append(" where school_id=?");
			return dao.find(sql.toString(),m.getSchool_id());
		} 
}
