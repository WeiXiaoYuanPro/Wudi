package com.wudi.model.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
public class LogModel extends Model<LogModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "login_log";
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public String getUserName() {
		return get("username");
	}
	public void setName(String username) {
		set("username" , username);
	}
	public Date getLogin_Time() {
		return get("login_time");
	}
	public void setLogin_Time(Date login_time){
		set("login_time" , login_time);
	}
	
	public String getIp() {
		return get("ip");
	}
	public void setIp(String ip){
		set("ip" , ip);
	}
	public String getAddr() {
		return get("addr");
	}
	public void setAddr(String addr){
		set("addr" , addr);
	}
	public String getRemark() {
		return get("remark");
	}
	public void setRemark(String remark){
		set("remark" , remark);
	}
	public int getstatus(){
		return get("status");
	}

	public void setStatus(int status)
	{
		set("status",status);
	}
	//因为经常用他，所以干脆给他一个静态的，让他一直存在，免得我们每次new
	public static final LogModel dao = new LogModel();
	
	/**
	 * 分页查询显示，就是查找
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public static Page<LogModel> getList(int pageNumber, int pageSize,String key) {
		String sele_sql="select * ";
		StringBuffer from_sql=new StringBuffer();
		from_sql.append("from ").append(tableName);
		if(!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append(" where username like '%"+key+"%'");
		}
		return dao.paginate(pageNumber,pageSize,sele_sql,from_sql.toString());
	}  
	/**
	 * 根据username查找
	 * @param usernames
	 * @return
	 */
	public static LogModel getByNo(String username){
		return dao.findFirst("select * from " + tableName + " where username = ? " , username);
	}
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public static LogModel getById(Object id){
		return dao.findFirst("select *  from " + tableName + " where id = ? " , id);
	}
	/**
	 * 根据学号删除数据
	 * @param no
	 * @return
	 */
	public static boolean delLoginLogByID(String id) {
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
	@Before(Tx.class)
	private static boolean save(final LogModel m){
		boolean succeed = Db.tx(new IAtom() {
					
					@Override
					public boolean run() throws SQLException {
						m.save();
						return true;
					}
					});
				return succeed;
	}

	public static boolean save(String name,String ip,String addr,String remark,int status) {
		LogModel c = new LogModel();
		c.setId(StringUtil.getId());
		c.setName(name);
		c.setLogin_Time(new Date());
		c.setIp(ip);
		c.setAddr(addr);
		c.setRemark(remark);
		c.setStatus(status);
		return LogModel.save(c);
	}
	public static boolean saveLog(String name,int status,String remark,HttpServletRequest request) {
		LogModel c = new LogModel();
		c.setId(StringUtil.getId());
		c.setName(name);
		c.setLogin_Time(new Date());
		c.setIp(getIpAddr(request));
		c.setAddr(getIpAddr(request));
		c.setRemark(remark);
		c.setStatus(status);//0成功，1失败
		return LogModel.save(c);
	}
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	public static String getMACAddress(String ip) throws Exception {
		String line = "";
		String macAddress = "";
		final String MAC_ADDRESS_PREFIX = "MAC Address = ";
		final String LOOPBACK_ADDRESS = "127.0.0.1";
		// 如果为127.0.0.1,则获取本地MAC地址。
		if (LOOPBACK_ADDRESS.equals(ip)) {
			InetAddress inetAddress = InetAddress.getLocalHost();
			// 貌似此方法需要JDK1.6。
			byte[] mac = NetworkInterface.getByInetAddress(inetAddress)
					.getHardwareAddress();
			// 下面代码是把mac地址拼装成String
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				if (i != 0) {
					sb.append("-");
				}
				// mac[i] & 0xFF 是为了把byte转化为正整数
				String s = Integer.toHexString(mac[i] & 0xFF);
				sb.append(s.length() == 1 ? 0 + s : s);
			}
			// 把字符串所有小写字母改为大写成为正规的mac地址并返回
			macAddress = sb.toString().trim().toUpperCase();
			return macAddress;
		}
		// 获取非本地IP的MAC地址
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader isr = new InputStreamReader(p.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				if (line != null) {
					int index = line.indexOf(MAC_ADDRESS_PREFIX);
					if (index != -1) {
						macAddress = line
								.substring(index + MAC_ADDRESS_PREFIX.length())
								.trim().toUpperCase();
					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress;
	}
}