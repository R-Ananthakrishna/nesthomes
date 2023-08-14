package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.FlatBean;
import com.bean.OwnerDetailsBean;
import com.bean.OwnershipBean;

public class OwnershipDAO {
	
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
	
	//--------------START------insert ownership details---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Insert values into ownership table
	* @Exception : SQLException
	* @param : Object of OwnerDetails Bean and Flat Bean
	* @return: boolean flag
	*/
	
	public static boolean insertOwnership(OwnerDetailsBean ownerDetailsBean, FlatBean flatBean) throws SQLException {
		
		conn=getDbConnection();
		
		try {
			pst=conn.prepareStatement("INSERT INTO ownership(ow_id,fl_id) VALUES(?,?)");
			pst.setInt(1, ownerDetailsBean.getOwId());
			pst.setInt(2,flatBean.getFlId());
			pst.executeUpdate();
			
			flag=true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		
		return flag;
		
	}
	
	//--------------END------insert ownership details---------------
	
	//--------------START------get list of owners---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get list of owners from owner table
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type OwnerDetails Bean
	*/
	
	
	public static ArrayList<OwnerDetailsBean> listOnlyOwner() throws SQLException{
		
		ArrayList<OwnerDetailsBean> ownerDetails=new ArrayList<OwnerDetailsBean>();
		
		conn=getDbConnection(); 
		try {
			pst=conn.prepareStatement("SELECT ow_id,ow_name FROM owner");
			rs=pst.executeQuery();
			
			while(rs.next()) {  
				OwnerDetailsBean ownerDetailsBean=new OwnerDetailsBean();
				ownerDetailsBean.setOwId(rs.getInt(1));
				ownerDetailsBean.setOwName(rs.getString(2));
				ownerDetails.add(ownerDetailsBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return ownerDetails;
		
	}
	
	//--------------END------get list of owners---------------
	
	//--------------START------get list of flats---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get list of flats from flat table
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type Flat Bean
	*/
	
	public static ArrayList<FlatBean> listOnlyFlat() throws SQLException{
		
		ArrayList<FlatBean> flat=new ArrayList<FlatBean>();
		
		conn=getDbConnection(); 
		try {
			pst=conn.prepareStatement("SELECT fl_id,fl_no,fl_tower FROM flat");
			rs=pst.executeQuery();
			
			while(rs.next()) {  
				FlatBean flatBean=new FlatBean();
				flatBean.setFlId(rs.getInt(1));
				flatBean.setFlNo(rs.getString(2));
				flatBean.setFlTower(rs.getString(3));
				flat.add(flatBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return flat;
		
	}
	
	//--------------END------get list of flats---------------
	
	
	//--------------START------get ownership list---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get ownership details from ownership table as Array list
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type Ownership Bean
	*/
	
	public static ArrayList<OwnershipBean> listOwnership() throws SQLException{
		
		ArrayList<OwnershipBean> ownership=new ArrayList<OwnershipBean>();
		
		conn=getDbConnection(); 
		try {
			pst=conn.prepareStatement("SELECT ownership.fl_ow_id,owner.ow_name,flat.fl_no,flat.fl_tower FROM ownership,owner,flat WHERE owner.ow_id=ownership.ow_id AND flat.fl_id=ownership.fl_id ");
			rs=pst.executeQuery();
			
			while(rs.next()) { 
				OwnershipBean ownershipBean=new OwnershipBean();
				ownershipBean.setFlOwId(rs.getInt(1));
				ownershipBean.setOwName(rs.getString(2));
				ownershipBean.setFlatNo(rs.getString(3));
				ownershipBean.setFlatTower(rs.getString(4));
				ownership.add(ownershipBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return ownership;
		
	}
	
	//--------------END------get ownership list---------------
	
	//--------------START------fetch ownership details by id---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Fetch ownership details from ownership,owner,flat table as Array list by flOwId
	* @Exception : SQLException
	* @param : int flOwId
	* @return: Array List of type Ownership Bean
	*/
	
	public static ArrayList<OwnershipBean> fetchOwnershipById(int flOwId)throws SQLException{
		
		ArrayList<OwnershipBean> ownership=new ArrayList<OwnershipBean>();
		conn=getDbConnection();
		
		try{
			pst=conn.prepareStatement("SELECT ownership.fl_ow_id,owner.ow_name,flat.fl_no,flat.fl_tower FROM ownership,owner,flat WHERE owner.ow_id=ownership.ow_id AND flat.fl_id=ownership.fl_id AND ownership.fl_ow_id="+flOwId+" ");
			rs=pst.executeQuery();
		
			while(rs.next()){
				OwnershipBean ownershipBean=new OwnershipBean();
				ownershipBean.setFlOwId(rs.getInt(1));
				ownershipBean.setOwName(rs.getString(2));
				ownershipBean.setFlatNo(rs.getString(3));
				ownershipBean.setFlatTower(rs.getString(4));
				ownership.add(ownershipBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return ownership;
	}
	
	//--------------END------fetch ownership details by id---------------
	
	//--------------START------update ownership details---------------
					
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Update value in ownership table
	* @Exception : SQLException
	* @param : Object of Ownership Bean, OwnerDetails Bean, Flat Bean
	* @return: boolean flag
	*/
	
	public static boolean updateOwnership(OwnershipBean ownershipBean,OwnerDetailsBean ownerDetailsBean,FlatBean flatBean)throws Exception{
		
		conn=getDbConnection();
		
		boolean flag=false;
		
		try {
		
			pst=conn.prepareStatement("UPDATE ownership SET ow_id=?,fl_id=? WHERE fl_ow_id =?");
			pst.setInt(1,ownerDetailsBean.getOwId());
			pst.setInt(2,flatBean.getFlId());
			pst.setInt(3,ownershipBean.getFlOwId());
			pst.executeUpdate();
			flag=true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		return flag;
		
	}
	
	//--------------END------update ownership details---------------
	
	//--------------START------delete ownership details---------------
	
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Delete value in ownership table
	* @Exception : SQLException
	* @param : Object of Ownership Bean
	* @return: boolean flag
	*/
	
	public static boolean deleteOwnership(OwnershipBean ownershipBean)throws Exception{
		
		conn=getDbConnection();
		boolean flag=false;
		
		try {
			
			pst=conn.prepareStatement("DELETE FROM ownership WHERE fl_ow_id =?");
			pst.setInt(1,ownershipBean.getFlOwId());
			pst.executeUpdate();
			flag=true;
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		
		return flag;
		
	}
	
	//--------------START------delete ownership details---------------


}
