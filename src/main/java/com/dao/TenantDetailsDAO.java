package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.TenantDetailsBean;

public class TenantDetailsDAO {
	
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
	
	//--------------START------insert tenant details---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Insert values into tenant table
	* @Exception : SQLException
	* @param : Object of TenantDetails Bean
	* @return: boolean flag
	*/
	
	public static boolean insertTenant(TenantDetailsBean tenantDetailsBean) throws SQLException {
		
		conn=getDbConnection();
		
		try {
			pst=conn.prepareStatement("INSERT INTO tenant(t_name,t_address,t_contact) VALUES(?,?,?)");
			pst.setString(1, tenantDetailsBean.getTName());
			pst.setString(2, tenantDetailsBean.getTAddress());
			pst.setLong(3, tenantDetailsBean.getTContact());
			pst.executeUpdate();
			
			flag=true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		
		return flag;
		
	}
	
	//--------------END------insert tenant details---------------
	
	//--------------START------get tenant list---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get tenant details from tenant table as Array list
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type TenantDetails Bean
	*/
	
	public static ArrayList<TenantDetailsBean> listTenant() throws SQLException{
		
		ArrayList<TenantDetailsBean> tenantDetails=new ArrayList<TenantDetailsBean>();
		
		conn=getDbConnection(); 
		try {
			pst=conn.prepareStatement("SELECT * FROM tenant");
			rs=pst.executeQuery();
			
			while(rs.next()) {  
				TenantDetailsBean tenantDetailsBean=new TenantDetailsBean();
				tenantDetailsBean.setTId(rs.getInt(1));
				tenantDetailsBean.setTName(rs.getString(2));
				tenantDetailsBean.setTAddress(rs.getString(3));
				tenantDetailsBean.setTContact(rs.getLong(4));
				tenantDetails.add(tenantDetailsBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return tenantDetails;
		
	}
	
	//--------------END------get tenant list---------------
	
	//--------------START------fetch tenant details by id---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Fetch tenant details from tenant table as Array list by tId
	* @Exception : SQLException
	* @param : int tId
	* @return: Array List of type TenantDetails Bean
	*/
	
	
	public static ArrayList<TenantDetailsBean> fetchTenantById(int tId)throws SQLException{
		
		ArrayList<TenantDetailsBean> tenantDetails=new ArrayList<TenantDetailsBean>();
		conn=getDbConnection();
		
		try{
			pst=conn.prepareStatement("SELECT * FROM tenant where t_id="+tId+" ");
			rs=pst.executeQuery();
		
			while(rs.next()){
				TenantDetailsBean tenantDetailsBean=new TenantDetailsBean();
				tenantDetailsBean.setTId(rs.getInt(1));
				tenantDetailsBean.setTName(rs.getString(2));
				tenantDetailsBean.setTAddress(rs.getString(3));
				tenantDetailsBean.setTContact(rs.getLong(4));
				tenantDetails.add(tenantDetailsBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return tenantDetails;
	}
	
	//--------------END------fetch tenant details by id---------------
	
	//--------------START------update tenant details---------------
					
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Update value in tenant table
	* @Exception : SQLException
	* @param : Object of TenantDetails Bean
	* @return: boolean flag
	*/
	
	public static boolean updateTenant(TenantDetailsBean tenantDetailsBean)throws Exception{
		
		conn=getDbConnection();
		
		boolean flag=false;
		
		try {
		
			pst=conn.prepareStatement("UPDATE tenant SET t_name=?,t_address=?,t_contact=? WHERE t_id =?");
			pst.setString(1,tenantDetailsBean.getTName());
			pst.setString(2,tenantDetailsBean.getTAddress());
			pst.setLong(3,tenantDetailsBean.getTContact());
			pst.setInt(4,tenantDetailsBean.getTId());
			pst.executeUpdate();
			flag=true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		return flag;
		
	}
	
	//--------------END------update tenant details---------------
	
	//--------------START------delete tenant details---------------
	
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Delete value in tenant table
	* @Exception : SQLException
	* @param : Object of TenantDetails Bean
	* @return: boolean flag
	*/
	
	public static boolean deleteTenant(TenantDetailsBean tenantDetailsBean)throws Exception{
		
		conn=getDbConnection();
		boolean flag=false;
		
		try {
			
			pst=conn.prepareStatement("DELETE FROM tenant WHERE t_id =?");
			pst.setInt(1,tenantDetailsBean.getTId());
			pst.executeUpdate();
			flag=true;
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		
		return flag;
		
	}

	//--------------END------delete tenant details---------------


}
