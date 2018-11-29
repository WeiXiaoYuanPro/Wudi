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

public class BuildingModel extends Model<BuildingModel> {
	private static final long serialVersionUID = 1L;
	public static final String tableName = "building";
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
	public String getSchoolzone_id() {
		return get("schoolzone_id");
	}
	public void setSchoolzone_id(String schoolzone_id) {
		set("schoolzone_id" , schoolzone_id);
	}
	
	//因为经常用他，所以干脆给他一个静态的，让他一直存在，免得我们每次new
		public static final BuildingModel dao = new BuildingModel();
		
		/**
		 * 分页查询显示，就是查找
		 * @param pageNumber
		 * @param pageSize
		 * @param key
		 * @return
		 */
		public static Page<BuildingModel> getList(int pageNumber, int pageSize,String key) {
			String sele_sql="SELECT  a.*, b.`name` AS schoolname ";
			StringBuffer from_sql=new StringBuffer();
			from_sql.append("from ").append(tableName).append(" AS a LEFT JOIN ").append(SchoolZoneModel.tableName).append(" AS b ON a.schoolzone_id = b.id ");
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
		public static BuildingModel getById(Object id){
			return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
		}
	/**
	 * 
	* @Title: save
	* @Description:保存，这里是以分别参数传下来的，你们还可以用对象的信息传下来，喜欢这么写就怎么写
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
		public static boolean save(String name,String addr,String remark,String school_id) {
			BuildingModel s=new BuildingModel();
			s.setAddr(addr);
			s.setName(name);
			s.setRemark(remark);
			s.setSchoolzone_id(school_id);
			s.setId(StringUtil.getId());
			return s.save();
		}
		
		/**
		 * 保存，这个保存时事务的保存，关于支付，关于钱的保存，我们一般都用这样的保存方法，现在我们暂时不用这个，因为不好调试
		 * @param name
		 * @param sex
		 * @return
		 */
		@Before(Tx.class)
		public static boolean save(final BuildingModel building){
			boolean succeed = Db.tx(new IAtom() {
						
						@Override
						public boolean run() throws SQLException {
							building.save();
							return true;
						}
						});
					return succeed;
		}
		/**
		 * 更新
		 */
		public static boolean update(String id,String name,String addr,String remark,String school_id){
			BuildingModel model=BuildingModel.getById(id);
			if(model==null){
				return false;
			}
			model.setAddr(addr);
			model.setName(name);
			model.setRemark(remark);
			model.setSchoolzone_id(school_id);
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
		public static boolean delBuildingByID(String id) {
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
