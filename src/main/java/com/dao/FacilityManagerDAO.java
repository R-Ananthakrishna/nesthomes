package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.FacilityManagerBean;

public class FacilityManagerDAO {
	
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
	
	
	//--------------START------insert facility manager---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Insert values into facility manager table
	* @Exception : SQLException
	* @param : Object of Facility Manager Bean
	* @return: boolean flag
	*/
	
	public static boolean insertFacilityManager(FacilityManagerBean facilityManagerBean) throws SQLException {
		
		conn=getDbConnection();
		
		try {
			pst=conn.prepareStatement("INSERT INTO facility_manager(fm_name,fm_designation,fm_duties,fm_contact) VALUES(?,?,?,?)");
			pst.setString(1, facilityManagerBean.getFmName());
			pst.setString(2, facilityManagerBean.getFmDesignation());
			pst.setString(3, facilityManagerBean.getFmDuties());
			pst.setLong(4, facilityManagerBean.getFmContact());
			pst.executeUpdate();
			
			flag=true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return flag;
	}
	
	//--------------END------insert dailyhelp---------------
	
	//--------------START------get facility manager list---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get datas from facility manager table as Array list
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type Facility Manager Bean
	*/
	
	
	public static ArrayList<FacilityManagerBean> listFacilityManager() throws SQLException{
		
		ArrayList<FacilityManagerBean> facilityManager=new ArrayList<FacilityManagerBean>();
		
		conn=getDbConnection(); 
		try {
			pst=conn.prepareStatement("SELECT * FROM facility_manager");
			rs=pst.executeQuery();
			
			while(rs.next()) {  
				FacilityManagerBean facilityManagerBean=new FacilityManagerBean();
				facilityManagerBean.setFmId(rs.getInt(1));
				facilityManagerBean.setFmName(rs.getString(2));
				facilityManagerBean.setFmDesignation(rs.getString(3));
				facilityManagerBean.setFmDuties(rs.getString(4));
				facilityManagerBean.setFmContact(rs.getLong(5));
				facilityManager.add(facilityManagerBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return facilityManager;
		
	}
	
	//--------------END------get facility manager list---------------
	
	//--------------START------fetch facility manager by id---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Fetch facility manager data from facility manager table as Array list by fmid
	* @Exception : SQLException
	* @param : int fmId
	* @return: Array List of type Facility Manager Bean
	*/
	
	public static ArrayList<FacilityManagerBean> fetchFacilityManagerById(int fmId)throws SQLException{
		
		ArrayList<FacilityManagerBean> facilityManager=new ArrayList<FacilityManagerBean>();
		conn=getDbConnection();
		
		try{
			pst=conn.prepareStatement("SELECT * FROM facility_manager where fm_id="+fmId+" ");
			rs=pst.executeQuery();
		
			while(rs.next()){
				FacilityManagerBean facilityManagerBean=new FacilityManagerBean();
				facilityManagerBean.setFmId(rs.getInt(1));
				facilityManagerBean.setFmName(rs.getString(2));
				facilityManagerBean.setFmDesignation(rs.getString(3));
				facilityManagerBean.setFmDuties(rs.getString(4));
				facilityManagerBean.setFmContact(rs.getLong(5));
				facilityManager.add(facilityManagerBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return facilityManager;
	}
	
	//--------------END------fetch facility manager by id---------------
	
	//--------------START------update facility manager---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Update value in facility manager table
	* @Exception : SQLException
	* @param : Object of Facility Manager Bean
	* @return: boolean flag
	*/
	
	public static boolean updateFacilityManager(FacilityManagerBean facilityManagerBean)throws Exception{
		
		conn=getDbConnection();
		
		boolean flag=false;
		
		try {
		
			pst=conn.prepareStatement("UPDATE facility_manager SET fm_name=?,fm_designation=?,fm_duties=?,fm_contact=? WHERE fm_id =?");
			pst.setString(1,facilityManagerBean.getFmName());
			pst.setString(2,facilityManagerBean.getFmDesignation());
			pst.setString(3,facilityManagerBean.getFmDuties());
			pst.setFloat(4,facilityManagerBean.getFmContact());
			pst.setInt(5,facilityManagerBean.getFmId());
			pst.executeUpdate();
			flag=true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		return flag;
		
	}
	
	//--------------END------update facility manager---------------
	
	//--------------START------delete facility manager---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Delete value in facility manager table
	* @Exception : SQLException
	* @param : Object of Facility Manager Bean
	* @return: boolean flag
	*/
	
	public static boolean deleteFacilityManager(FacilityManagerBean facilityManagerBean)throws Exception{
		
		conn=getDbConnection();
		boolean flag=false;
		
		try {
			
			pst=conn.prepareStatement("DELETE FROM facility_manager WHERE fm_id =?");
			pst.setInt(1,facilityManagerBean.getFmId());
			pst.executeUpdate();
			flag=true;
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		
		return flag;
		
	}
	
	//--------------END------delete facility manager---------------


}
