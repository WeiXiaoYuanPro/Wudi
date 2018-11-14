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
 * 学生信息
 * @author xiao
 *
 */
public class CmsUserModel extends Model<CmsUserModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "cms_user";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public String getNo() {
		return get("username");
	}
	public void setNo(String no) {
		set("no", no);
	}
	public String getName() {
		return get("name");
	}
	public void setName(String name) {
		set("name" , name);
	}
	public String getCls() {
		return get("cls");
	}
	public void setCls(String cls) {
		set("cls" , cls);
	}
	public int getSex(){
		return get("sex");
	}
	public String getSexStr(){
		int sex=getSex();
		if(sex==1){
			return "男";
		}
		else{
			return "女";
		}
	}
	public void setSex(int sex)
	{
		set("sex",sex);
	}
//	//因为经常用他，所以干脆给他一个静态的，让他一直存在，免得我们每次new
//	public static final StudentModel dao = new StudentModel();
//	
//	/**
//	 * 分页查询显示，就是查找
//	 * @param pageNumber
//	 * @param pageSize
//	 * @param key
//	 * @return
//	 */
//	public static Page<StudentModel> getList(int pageNumber, int pageSize,String key) {
//		String sele_sql="select * ";
//		StringBuffer from_sql=new StringBuffer();
//		from_sql.append("from ").append(tableName);
//		if(!StringUtil.isBlankOrEmpty(key)) {
//			from_sql.append(" where name like '%"+key+"%'");
//		}
//		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
//	}  
//	/**
//	 * 根据no查找
//	 * @param no
//	 * @return
//	 */
//	public static StudentModel getByNo(String no){
//		return dao.findFirst("select * from " + tableName + " where no = ? " , no);
//	}
//	/**
//	 * 根据id查找
//	 * @param id
//	 * @return
//	 */
//	public static StudentModel getById(Object id){
//		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
//	}
///**
// * 
//* @Title: save
//* @Description:保存，这里是以分别参数传下来的，你们还可以用对象的信息传下来，喜欢这么写就怎么写
//* @param @param no
//* @param @param name
//* @param @param cls
//* @param @param sex
//* @param @return    参数
//* @return boolean    返回类型
//* @throws
// */
//	public static boolean save(String no,String name,String cls,int sex) {
//		StudentModel s=new StudentModel();
//		s.setCls(cls);
//		s.setName(name);
//		s.setSex(sex);
//		s.setNo(no);
//		s.setId(no);
//		return s.save();
//	}
//	
//	/**
//	 * 保存，这个保存时事务的保存，关于支付，关于钱的保存，我们一般都用这样的保存方法，现在我们暂时不用这个，因为不好调试
//	 * @param name
//	 * @param sex
//	 * @return
//	 */
//	@Before(Tx.class)
//	public static boolean save(final StudentModel student){
//		boolean succeed = Db.tx(new IAtom() {
//					
//					@Override
//					public boolean run() throws SQLException {
//						student.save();
//						return true;
//					}
//					});
//				return succeed;
//	}
//	/**
//	 * 更新
//	 */
//	public static boolean update(String no,String name,int sex,String cls){
//		StudentModel model=StudentModel.getById(no);
//		if(model==null){
//			return false;
//		}
//		model.setNo(no);
//		model.setName(name);
//		model.setSex(sex);
//		model.setCls(cls);
//		try {
//			model.update();
//		} catch (Exception e) {
//			return false;
//		}
//		return true;
//	}
//	/**
//	 * 根据学号删除数据
//	 * @param no
//	 * @return
//	 */
//	public static boolean delStudentByNO(String no) {
//		try {
//			String delsql="DELETE FROM "+tableName+" WHERE no=?";
//			int iRet=Db.update(delsql, no);
//			if(iRet > 0)
//			{
//				return true;
//			}
//			else
//			{
//				return false;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}

}
