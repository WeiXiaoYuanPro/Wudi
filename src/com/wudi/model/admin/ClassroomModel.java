package com.wudi.model.admin;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wudi.util.StringUtil;

public class ClassroomModel extends Model<ClassroomModel> {
	private static final long serialVersionUID = 1L;
	public static final String tableName = "classroom";
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
	
	public String getBuilding_id() {
		return get("building_id");
	}
	public void setBuilding_id(String building_id) {
		set("building_id" , building_id);
	}
	
	public int getCapacity(){
		return get("capacity");
	}
	public void setCapacity(int capacity)
	{
		set("capacity",capacity);
	}
	
	public int getType(){
		return get("type");
	}
	public void setType(int type)
	{
		set("type",type);
	}
	
	public int getStatus(){
		return get("status");
	}
	public void setStatus(int status)
	{
		set("status",status);
	}
	
	public BigDecimal getLatitudes(){
		return get("latitude");
	}
	public void setLatitude(BigDecimal latitude)
	{
		set("latitude",latitude);
	}
	public BigDecimal getLongitude(){
		return get("longitude");
	}
	
	public void setLongitude(BigDecimal longitude)
	{
		set("longitude",longitude);
	}
	
	//因为经常用他，所以干脆给他一个静态的，让他一直存在，免得我们每次new
			public static final ClassroomModel dao = new ClassroomModel();
			
			/**
			 * 分页查询显示，就是查找
			 * @param pageNumber
			 * @param pageSize
			 * @param key
			 * @return
			 */
			public static Page<ClassroomModel> getList(int pageNumber, int pageSize,String key) {
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
			public static ClassroomModel getById(Object id){
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
			public static boolean save(String id,String name,String building_id,int capacity,int type,int status,BigDecimal latitude,BigDecimal longitude) {
				ClassroomModel s=new ClassroomModel();
				s.setId(StringUtil.getId());
				s.setName(name);
				s.setBuilding_id(building_id);
				s.setCapacity(capacity);
				s.setType(type);
				s.setStatus(status);
				s.setLatitude(latitude);
				s.setLongitude(longitude);
				return s.save();
			}
			
			/**
			 * 保存，这个保存时事务的保存，关于支付，关于钱的保存，我们一般都用这样的保存方法，现在我们暂时不用这个，因为不好调试
			 * @param name
			 * @param sex
			 * @return
			 */
			@Before(Tx.class)
			public static boolean save(final ClassroomModel classroom){
				boolean succeed = Db.tx(new IAtom() {
							
							@Override
							public boolean run() throws SQLException {
								classroom.save();
								return true;
							}
							});
						return succeed;
			}
			/**
			 * 更新
			 */
			public static boolean update(String id,String name,String building_id,int capacity,int type,int status,BigDecimal latitude,BigDecimal longitude){
				ClassroomModel model=ClassroomModel.getById(id);
				if(model==null){
					return false;
				}
				model.setId(id);
				model.setName(name);
				model.setBuilding_id(building_id);
				model.setCapacity(capacity);
				model.setType(type);
				model.setStatus(status);
				model.setLatitude(latitude);
				model.setLongitude(longitude);
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
			public static boolean delClassroomByID(String id) {
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
