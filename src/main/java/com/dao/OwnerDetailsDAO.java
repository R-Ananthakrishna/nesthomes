package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.OwnerDetailsBean;

public class OwnerDetailsDAO {
	
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
	
	//--------------START------insert owner details---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Insert values into owner table
	* @Exception : SQLException
	* @param : Object of OwnerDetails Bean
	* @return: boolean flag
	*/
	
	
	public static boolean insertOwner(OwnerDetailsBean ownerDetailsBean) throws SQLException {
		
		conn=getDbConnection();
		
		try {
			pst=conn.prepareStatement("INSERT INTO owner(ow_name,ow_address,ow_contact) VALUES(?,?,?)");
			pst.setString(1, ownerDetailsBean.getOwName());
			pst.setString(2, ownerDetailsBean.getOwAddress());
			pst.setLong(3, ownerDetailsBean.getOwContact());
			pst.executeUpdate();
			
			flag=true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		
		return flag;
		
	}
	
	//--------------END------insert owner details---------------
	
	//--------------START------get owner list---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get owner details from owner table as Array list
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type OwnerDetails Bean
	*/
	
	
	public static ArrayList<OwnerDetailsBean> listOwner() throws SQLException{
		
		ArrayList<OwnerDetailsBean> ownerDetails=new ArrayList<OwnerDetailsBean>();
		
		conn=getDbConnection();
		try {
			pst=conn.prepareStatement("SELECT * FROM owner");
			rs=pst.executeQuery();
			
			while(rs.next()) {  
				OwnerDetailsBean ownerDetailsBean=new OwnerDetailsBean();
				ownerDetailsBean.setOwId(rs.getInt(1));
				ownerDetailsBean.setOwName(rs.getString(2));
				ownerDetailsBean.setOwAddress(rs.getString(3));
				ownerDetailsBean.setOwContact(rs.getLong(4));
				ownerDetails.add(ownerDetailsBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return ownerDetails;
		
	}
	
	//--------------END------get owner list---------------
	
	//--------------START------fetch owner details by id---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Fetch owner details from owner table as Array list by owid
	* @Exception : SQLException
	* @param : int owId
	* @return: Array List of type OwnerDetails Bean
	*/
	
	public static ArrayList<OwnerDetailsBean> fetchOwnerById(int owId)throws SQLException{
		
		ArrayList<OwnerDetailsBean> ownerDetails=new ArrayList<OwnerDetailsBean>();
		conn=getDbConnection();
		
		try{
			pst=conn.prepareStatement("SELECT * FROM owner where ow_id="+owId+" ");
			rs=pst.executeQuery();
		
			while(rs.next()){
				OwnerDetailsBean ownerDetailsBean=new OwnerDetailsBean();
				ownerDetailsBean.setOwId(rs.getInt(1));
				ownerDetailsBean.setOwName(rs.getString(2));
				ownerDetailsBean.setOwAddress(rs.getString(3));
				ownerDetailsBean.setOwContact(rs.getLong(4));
				ownerDetails.add(ownerDetailsBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return ownerDetails;
	}
	
	//--------------END------fetch owner details by id---------------
	
	//--------------START------update owner details---------------
					
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Update value in owner table
	* @Exception : SQLException
	* @param : Object of OwnerDetails Bean
	* @return: boolean flag
	*/
	
	public static boolean updateOwner(OwnerDetailsBean ownerDetailsBean)throws Exception{
		
		conn=getDbConnection();
		
		boolean flag=false;
		
		try {
		
			pst=conn.prepareStatement("UPDATE owner SET ow_name=?,ow_address=?,ow_contact=? WHERE ow_id =?");
			pst.setString(1,ownerDetailsBean.getOwName());
			pst.setString(2,ownerDetailsBean.getOwAddress());
			pst.setLong(3,ownerDetailsBean.getOwContact());
			pst.setInt(4,ownerDetailsBean.getOwId());
			pst.executeUpdate();
			flag=true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		return flag;
		
	}
	
	//--------------END------update owner details---------------
	
	//--------------START------delete owner details---------------
	
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Delete value in owner table
	* @Exception : SQLException
	* @param : Object of OwnerDetails Bean
	* @return: boolean flag
	*/
	
	public static boolean deleteOwner(OwnerDetailsBean ownerDetailsBean)throws Exception{
		
		conn=getDbConnection();
		boolean flag=false;
		
		try {
			
			pst=conn.prepareStatement("DELETE FROM owner WHERE ow_id =?");
			pst.setInt(1,ownerDetailsBean.getOwId());
			pst.executeUpdate();
			flag=true;
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		
		return flag;
		
	}

	//--------------END------delete owner details---------------

}
