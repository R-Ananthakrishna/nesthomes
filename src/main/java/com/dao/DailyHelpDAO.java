package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.DailyHelpBean;
import com.bean.DailyHelpCategoryBean;

public class DailyHelpDAO {
	
	static Connection conn=null;
	static PreparedStatement pst=null;
	static ResultSet rs=null;
	static boolean flag=false;
	
	//--------------START------get db connection---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get DB Connection
	* @Exception : SQLException
	* @param : Nothing
	* @return: Connection object.
	*/
	
	public static Connection getDbConnection() throws SQLException{
		
		try {
			DBDAO.connect();
			conn=DBDAO.getDbCon();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//--------------END------get db connection---------------
	
	
	//--------------START------get dailyhelpcategory by id---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get category from daily help category table
	* @Exception : SQLException
	* @param : Object of Daily Help Category Bean
	* @return: DailyHelpCategory
	*/
	
	public static DailyHelpCategoryBean getCategoryId(DailyHelpCategoryBean dailyHelpCategoryBean) throws SQLException{
		
		
		
		conn=getDbConnection();
		DailyHelpCategoryBean dailyHelpCategoryBean2=new DailyHelpCategoryBean();
		
		try {
			
			pst=conn.prepareStatement("SELECT dhc_id FROM daily_help_category WHERE dhc_category=?");
			pst.setString(1, dailyHelpCategoryBean.getDhcCategory());
			rs=pst.executeQuery();
			while(rs.next()) {  
				dailyHelpCategoryBean2.setDhcId(rs.getInt(1));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return dailyHelpCategoryBean2;
		
		
		
	}
	
	//--------------END------get dailyhelpcategory by id---------------
	
	
	//--------------START------insert dailyhelp---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Insert values into daily help table
	* @Exception : SQLException
	* @param : Object of Daily Help Bean and Daily Help Category Bean
	* @return: boolean flag
	*/
	
	
	public static boolean insertDailyHelp(DailyHelpBean dailyHelpBean,DailyHelpCategoryBean dailyHelpCategoryBean) throws SQLException {
		
		DailyHelpCategoryBean dailyHelpCategoryBean2=new DailyHelpCategoryBean();
		
		conn=getDbConnection();
		try {
			
			dailyHelpCategoryBean2 = getCategoryId(dailyHelpCategoryBean);
			 
			pst=conn.prepareStatement("INSERT INTO daily_help(dhc_id,dh_name,dh_address,dh_contact)  VALUES(?,?,?,?)");
			pst.setInt(1, dailyHelpCategoryBean2.getDhcId());
			pst.setString(2, dailyHelpBean.getDhName());
			pst.setString(3, dailyHelpBean.getDhAddress());
			pst.setLong(4, dailyHelpBean.getDhContact());
			pst.executeUpdate();
			
			flag=true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return flag;
		
	}
	
	//--------------END------insert dailyhelp---------------
	
	//--------------START------get category list---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get categories from daily help category table as Array list
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type Daily Help Category Bean
	*/
	
	public static ArrayList<DailyHelpCategoryBean> listOnlyCategory() throws SQLException{
		
		ArrayList<DailyHelpCategoryBean> dailyHelpCategory=new ArrayList<DailyHelpCategoryBean>();
		
		conn=getDbConnection(); 
		try {
			pst=conn.prepareStatement("SELECT dhc_category FROM daily_help_category");
			rs=pst.executeQuery();
			
			while(rs.next()) {  
				DailyHelpCategoryBean dailyHelpCategoryBean=new DailyHelpCategoryBean();
				dailyHelpCategoryBean.setDhcCategory(rs.getString(1));
				dailyHelpCategory.add(dailyHelpCategoryBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return dailyHelpCategory;
		
	}
	
	//--------------END------get category list---------------
	
	
	//--------------START------get daily help list---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get daily help datas from daily help table as Array list
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type Daily Help Bean
	*/
	
	public static ArrayList<DailyHelpBean> listDailyHelp() throws SQLException{
		
		ArrayList<DailyHelpBean> dailyHelp=new ArrayList<DailyHelpBean>();
		
		conn=getDbConnection(); 
		try {
			pst=conn.prepareStatement("SELECT daily_help.dh_id, daily_help_category.dhc_category,daily_help.dh_name,dh_address,dh_contact FROM daily_help,daily_help_category WHERE daily_help_category.dhc_id=daily_help.dhc_id ");
			rs=pst.executeQuery();
			
			while(rs.next()) { 
				DailyHelpBean dailyHelpBean=new DailyHelpBean();
				dailyHelpBean.setDhId(rs.getInt(1));
				dailyHelpBean.setDhCategory(rs.getString(2));
				dailyHelpBean.setDhName(rs.getString(3));
				dailyHelpBean.setDhAddress(rs.getString(4));
				dailyHelpBean.setDhContact(rs.getLong(5));
				dailyHelp.add(dailyHelpBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return dailyHelp;
		
	}
	
	//--------------END------get daily help list---------------
	
	//--------------START------fetch daily help by id---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Fetch daily help data from daily help table as Array list by dhid
	* @Exception : SQLException
	* @param : int dhId
	* @return: Array List of type Daily Help Bean
	*/
	
	public static ArrayList<DailyHelpBean> fetchDailyHelpById(int dhId)throws SQLException{
		
		ArrayList<DailyHelpBean> dailyHelp=new ArrayList<DailyHelpBean>();
		conn=getDbConnection();
		
		try{
			pst=conn.prepareStatement("SELECT daily_help.dh_id, daily_help_category.dhc_category,daily_help.dh_name,dh_address,dh_contact FROM daily_help,daily_help_category WHERE daily_help_category.dhc_id=daily_help.dhc_id AND daily_help.dh_id="+dhId+" ");
			rs=pst.executeQuery();
		
			while(rs.next()){
				DailyHelpBean dailyHelpBean=new DailyHelpBean();
				dailyHelpBean.setDhId(rs.getInt(1));
				dailyHelpBean.setDhCategory(rs.getString(2));
				dailyHelpBean.setDhName(rs.getString(3));
				dailyHelpBean.setDhAddress(rs.getString(4));
				dailyHelpBean.setDhContact(rs.getLong(5));
				dailyHelp.add(dailyHelpBean);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return dailyHelp;
	}
	
	//--------------END------fetch daily help by id---------------
	
	//--------------START------update daily help---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Update value in daily help table
	* @Exception : SQLException
	* @param : Object of Daily Help Bean
	* @return: boolean flag
	*/
	
	public static boolean updateDailyHelp(DailyHelpBean dailyHelpBean, DailyHelpCategoryBean dailyHelpCategoryBean)throws Exception{
		
		
		DailyHelpCategoryBean dailyHelpCategoryBean2=new DailyHelpCategoryBean();
		
		conn=getDbConnection();
		
		boolean flag=false;
		
		try {
			
			dailyHelpCategoryBean2=getCategoryId(dailyHelpCategoryBean);
		
			pst=conn.prepareStatement("UPDATE daily_help SET dhc_id=?,dh_name=?,dh_address=?,dh_contact=? WHERE dh_id =?");
			pst.setInt(1,dailyHelpCategoryBean2.getDhcId());
			pst.setString(2, dailyHelpBean.getDhName());
			pst.setString(3,dailyHelpBean.getDhAddress());
			pst.setLong(4,dailyHelpBean.getDhContact());
			pst.setInt(5,dailyHelpBean.getDhId());
			pst.executeUpdate();
			flag=true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		return flag;
		
	}
	
	
	//--------------END------update daily help---------------
	
	//--------------START------delete daily help---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Delete value in daily help table
	* @Exception : SQLException
	* @param : Object of Daily Help Bean
	* @return: boolean flag
	*/
	
	public static boolean deleteDailyHelp(DailyHelpBean dailyHelpBean)throws Exception{
		
		conn=getDbConnection();
		boolean flag=false;
		
		try {
			
			pst=conn.prepareStatement("DELETE FROM daily_help WHERE dh_id =?");
			pst.setInt(1,dailyHelpBean.getDhId());
			pst.executeUpdate();
			flag=true;
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		
		return flag;
		
	}
	
	//--------------END------delete daily help---------------


}
