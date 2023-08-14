package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.DailyHelpCategoryBean;

public class DailyHelpCategoryDAO {
	
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
	
	public static Connection getDbConnection() throws SQLException {
		
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
	
	
	//--------------START------insert dailyhelpcategory---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Insert category valuesinto daily help category table
	* @Exception : SQLException
	* @param : Object of Daily Help Category Bean
	* @return: boolean flag
	*/
	
	public static boolean insertDailyHelpCategory(DailyHelpCategoryBean dailyHelpCategoryBean) throws SQLException {
		
		conn=getDbConnection();
		
		try {
			pst=conn.prepareStatement("INSERT INTO daily_help_category(dhc_category) VALUES(?)");
			pst.setString(1, dailyHelpCategoryBean.getDhcCategory());
			pst.executeUpdate();
			
			flag=true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return flag;
	}
	
	//--------------END------insert dailyhelpcategory---------------
	
	//--------------START------get daily help category list---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get daily help category datas from daily help category table as Array list
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type Daily Help Category Bean
	*/
	
	public static ArrayList<DailyHelpCategoryBean> listDailyHelpCategory() throws SQLException{
		
		ArrayList<DailyHelpCategoryBean> dailyHelpCategory=new ArrayList<DailyHelpCategoryBean>();
		
		conn=getDbConnection();
		try {
			pst=conn.prepareStatement("SELECT * FROM daily_help_category");
			rs=pst.executeQuery();
			
			while(rs.next()) { 
				DailyHelpCategoryBean dailyHelpCategoryBean=new DailyHelpCategoryBean();
				dailyHelpCategoryBean.setDhcId(rs.getInt(1));
				dailyHelpCategoryBean.setDhcCategory(rs.getString(2));
				dailyHelpCategory.add(dailyHelpCategoryBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return dailyHelpCategory;
		
	}
	
	//--------------END------get daily help category list---------------
	
	//--------------START------fetch daily help category by id---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Fetch daily help category data from daily help category table as Array list by dhcid
	* @Exception : SQLException
	* @param : int dhcId
	* @return: Array List of type Daily Help Category Bean
	*/
	
	public static ArrayList<DailyHelpCategoryBean> fetchDailyHelpCategoryById(int dhcId)throws SQLException{
		
		ArrayList<DailyHelpCategoryBean> dailyHelpCategory=new ArrayList<DailyHelpCategoryBean>();
		conn=getDbConnection();
		
		try{
			pst=conn.prepareStatement("SELECT * FROM daily_help_category where dhc_id="+dhcId+" ");
			rs=pst.executeQuery();
		
			while(rs.next()){
				DailyHelpCategoryBean dailyHelpCategoryBean=new DailyHelpCategoryBean();
				dailyHelpCategoryBean.setDhcId(rs.getInt(1));
				dailyHelpCategoryBean.setDhcCategory(rs.getString(2));
				dailyHelpCategory.add(dailyHelpCategoryBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		
		return dailyHelpCategory;
	}
	
	//--------------END------fetch daily help category by id---------------
	
	//--------------START------update daily help category---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Update value in daily help category table
	* @Exception : SQLException
	* @param : Object of Daily Help Category Bean
	* @return: boolean flag
	*/
	
	public static boolean updateDailyHelpCategory(DailyHelpCategoryBean dailyHelpCategoryBean)throws Exception{
		
		conn=getDbConnection();
		
		boolean flag=false;
		
		try {
		
			pst=conn.prepareStatement("UPDATE daily_help_category SET dhc_category=? WHERE dhc_id =?");
			pst.setString(1,dailyHelpCategoryBean.getDhcCategory());
			pst.setInt(2,dailyHelpCategoryBean.getDhcId());
			pst.executeUpdate();
			flag=true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		return flag;
		
	}
	
	//--------------END------update daily help category---------------
	
	//--------------START------delete daily help category---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Delete value in daily help category table
	* @Exception : SQLException
	* @param : Object of Daily Help Category Bean
	* @return: boolean flag
	*/
	
	public static boolean deleteDailyHelpCategory(DailyHelpCategoryBean dailyHelpCategoryBean)throws Exception{
		
		conn=getDbConnection();
		boolean flag=false;
		
		try {
			
			pst=conn.prepareStatement("DELETE FROM daily_help_category WHERE dhc_id =?");
			pst.setInt(1,dailyHelpCategoryBean.getDhcId());
			pst.executeUpdate();
			flag=true;
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		
		return flag;
		
	}

	//--------------END------delete daily help category---------------

}
