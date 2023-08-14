package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.FlatBean;
import com.bean.RentalBean;
import com.bean.TenantDetailsBean;

public class RentalDAO {
	
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
	
	//--------------START------insert rental details---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Insert values into rental table
	* @Exception : SQLException
	* @param : Object of TenantDetails Bean and Flat Bean
	* @return: boolean flag
	*/
	
	public static boolean insertRental(TenantDetailsBean tenantDetailsBean, FlatBean flatBean) throws SQLException {
		
		conn=getDbConnection();
		
		try {
			pst=conn.prepareStatement("INSERT INTO rental(t_id,fl_id) VALUES(?,?)");
			pst.setInt(1, tenantDetailsBean.getTId());
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
	
	//--------------END------insert rental details---------------
	
	//--------------START------get list of tenants---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get list of tenants from tenant table
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type TenantDetails Bean
	*/
	
	public static ArrayList<TenantDetailsBean> listOnlyTenant() throws SQLException{
		
		ArrayList<TenantDetailsBean> tenantDetails=new ArrayList<TenantDetailsBean>();
		
		conn=getDbConnection(); 
		try {
			pst=conn.prepareStatement("SELECT t_id,t_name FROM tenant");
			rs=pst.executeQuery();
			
			while(rs.next()) {  
				TenantDetailsBean tenantDetailsBean=new TenantDetailsBean();
				tenantDetailsBean.setTId(rs.getInt(1));
				tenantDetailsBean.setTName(rs.getString(2));
				tenantDetails.add(tenantDetailsBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return tenantDetails;
		
	}
	
	//--------------END------get list of tenants---------------
	
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
	
	
	//--------------START------get rental list---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get rental details from rental table as Array list
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type Rental Bean
	*/
	
	public static ArrayList<RentalBean> listRental() throws SQLException{
		
		ArrayList<RentalBean> rental=new ArrayList<RentalBean>();
		
		conn=getDbConnection(); 
		try {
			pst=conn.prepareStatement("SELECT rental.r_id,tenant.t_name,flat.fl_no,flat.fl_tower FROM rental,tenant,flat WHERE tenant.t_id=rental.t_id AND flat.fl_id=rental.fl_id ");
			rs=pst.executeQuery();
			
			while(rs.next()) { 
				RentalBean rentalBean=new RentalBean();
				rentalBean.setrId(rs.getInt(1));
				rentalBean.setTName(rs.getString(2));
				rentalBean.setFlatNo(rs.getString(3));
				rentalBean.setFlatTower(rs.getString(4));
				rental.add(rentalBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return rental;
		
	}
	
	//--------------END------get rental list---------------
	
	//--------------START------fetch rental details by id---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Fetch rental details from rental,tenant,flat table as Array list by rId
	* @Exception : SQLException
	* @param : int rId
	* @return: Array List of type Rental Bean
	*/
	
	public static ArrayList<RentalBean> fetchRentalById(int rId)throws SQLException{
		
		ArrayList<RentalBean> rental=new ArrayList<RentalBean>();
		conn=getDbConnection();
		
		try{
			pst=conn.prepareStatement("SELECT rental.r_id,tenant.t_name,flat.fl_no,flat.fl_tower FROM rental,tenant,flat WHERE tenant.t_id=rental.t_id AND flat.fl_id=rental.fl_id AND rental.r_id="+rId+" ");
			rs=pst.executeQuery();
		
			while(rs.next()){
				RentalBean rentalBean=new RentalBean();
				rentalBean.setrId(rs.getInt(1));
				rentalBean.setTName(rs.getString(2));
				rentalBean.setFlatNo(rs.getString(3));
				rentalBean.setFlatTower(rs.getString(4));
				rental.add(rentalBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return rental;
	}
	
	//--------------END------fetch rental details by id---------------
	
	//--------------START------update rental details---------------
					
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Update value in rental table
	* @Exception : SQLException
	* @param : Object of Rental Bean, TenantDetails Bean, Flat Bean
	* @return: boolean flag
	*/
	
	public static boolean updateRental(RentalBean rentalBean, TenantDetailsBean tenantDetailsBean, FlatBean flatBean)throws Exception{
		
		conn=getDbConnection();
		
		boolean flag=false;
		
		try {
		
			pst=conn.prepareStatement("UPDATE rental SET t_id=?,fl_id=? WHERE r_id =?");
			pst.setInt(1,tenantDetailsBean.getTId());
			pst.setInt(2,flatBean.getFlId());
			pst.setInt(3,rentalBean.getrId());
			pst.executeUpdate();
			flag=true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		return flag;
		
	}
	
	//--------------END------update rental details---------------
	
	//--------------START------delete rental details---------------
	
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Delete value in rental table
	* @Exception : SQLException
	* @param : Object of Rental Bean
	* @return: boolean flag
	*/
	
	public static boolean deleteRental(RentalBean rentalBean)throws Exception{
		
		conn=getDbConnection();
		boolean flag=false;
		
		try {
			
			pst=conn.prepareStatement("DELETE FROM rental WHERE r_id =?");
			pst.setInt(1,rentalBean.getrId());
			pst.executeUpdate();
			flag=true;
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		
		return flag;
		
	}
	
	//--------------START------delete rental details---------------

}
