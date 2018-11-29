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
public class StuinfoModel extends Model<StuinfoModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "stuinfo";
	public String getNo() {
		return get("no");
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
	public int getSex(){
		return get("sex");
	}
	public void setSexS(String value) {
		  set("sexS", value);
		}

	public String getSexS() {
		  return get("sexS");
	}
	public void setSex(int sex)
	{
		if(sex==1) {
			setSexS("男");
		}else {
			setSexS("女");
		}
		set("sex",sex);
	}
	public String getBirth() {
		return get("birth");
	}
	public void setBirth(String birth) {
		set("birth" , birth);
	}
	public String getImg() {
		return get("img");
	}
	public void setImg(String img) {
		set("img" , img);
	}
	//因为经常用他，所以干脆给他一个静态的，让他一直存在，免得我们每次new
	public static final StuinfoModel dao = new StuinfoModel();
	
	/**
	 * 分页查询显示，就是查找
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<StuinfoModel> getList(int pageNumber, int pageSize,String key) {
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
	public static StuinfoModel getByNo(String no){
		return dao.findFirst("select * from " + tableName + " where no = ? " , no);
	}
	/**
	 * 根据name查找
	 * @param name
	 * @return
	 */
	public static StuinfoModel getByName(Object name){
		return dao.findFirst("select *  from " + tableName + " where name = ? " , name);
	}
/**
 * 
* @Title: save
* @Description:保存，这里是以分别参数传下来的，你们还可以用对象的信息传下来，喜欢这么写就怎么写
* @param @param no
* @param @param name
* @param @param sex
* @param @param birth
* * @param @param img
* @param @return    参数
* @return boolean    返回类型
* @throws
 */
	public static boolean save(String no,String name,int sex,String birth,String img) {
		StuinfoModel s=new StuinfoModel();
		s.setNo(no);
		s.setName(name);
		s.setSex(sex);
		s.setBirth(birth);
		s.setImg(img);
		return s.save();
	}
	
	/**
	 * 保存，这个保存时事务的保存，关于支付，关于钱的保存，我们一般都用这样的保存方法，现在我们暂时不用这个，因为不好调试
	 * @param name
	 * @param sex
	 * @return
	 */
	@Before(Tx.class)
	public static boolean save(final StuinfoModel s){
		boolean succeed = Db.tx(new IAtom() {
					
					@Override
					public boolean run() throws SQLException {
						s.save();
						return true;
					}
					});
				return succeed;
	}
	/**
	 * 更新
	 * TODO:帮蒋霜 修改
	 */
	public static boolean update(String no,String name,int sex,String birth,String img){
		boolean result=false;
		try {
			String sql="UPDATE "+tableName+" set name=?, sex=?,birth=?,img=?  where no=?";
			//上面的几个问号就是对应下面name,sex,birth,img,no
			int r=Db.update(sql,name,sex,birth,img,no);
			if(r>0) {
				result=true;
			}
		} catch (Exception e) {
			result=false;
		}
		return result;
	}
	/**
	 * 根据学号删除数据
	 * @param no
	 * @return
	 */
	public static boolean delStuinfoByNO(String no) {
		try {
			String delsql="DELETE FROM "+tableName+" WHERE no=?";
			int iRet=Db.update(delsql, no);
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
