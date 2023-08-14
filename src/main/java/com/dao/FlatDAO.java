package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.FlatBean;

public class FlatDAO {
	
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
	
	//--------------START------insert flat details---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Insert values into flat table
	* @Exception : SQLException
	* @param : Object of Flat Bean
	* @return: boolean flag
	*/
	
	public static boolean insertFlat(FlatBean flatBean) throws SQLException {
		
		conn=getDbConnection();
		try {
			pst=conn.prepareStatement("INSERT INTO flat(fl_no,fl_type,fl_tower) VALUES(?,?,?)");
			pst.setString(1, flatBean.getFlNo());
			pst.setString(2, flatBean.getFlType());
			pst.setString(3, flatBean.getFlTower());
			pst.executeUpdate();
			
			flag=true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return flag;
		
	}
	
	//--------------END------insert flat details---------------
	
	//--------------START------get flat list---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get flat details from flat table as Array list
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type Flat Bean
	*/

	
	public static ArrayList<FlatBean> listFlat() throws SQLException{
		
		ArrayList<FlatBean> flat=new ArrayList<FlatBean>();
		
		conn=getDbConnection();
		try {
			pst=conn.prepareStatement("SELECT * FROM flat");
			rs=pst.executeQuery();
			
			while(rs.next()) {  
				FlatBean flatBean=new FlatBean();
				flatBean.setFlId(rs.getInt(1));
				flatBean.setFlNo(rs.getString(2));
				flatBean.setFlType(rs.getString(3));
				flatBean.setFlTower(rs.getString(4));
				flat.add(flatBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return flat;
		
	}
	
	//--------------END------get flat list---------------
	
	//--------------START------fetch flat details by id---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Fetch flat details from flat table as Array list by flid
	* @Exception : SQLException
	* @param : int flId
	* @return: Array List of type Flat Bean
	*/
	
	public static ArrayList<FlatBean> fetchFlatById(int flId)throws SQLException{
		
		ArrayList<FlatBean> flat=new ArrayList<FlatBean>();
		conn=getDbConnection();
		
		try{
			pst=conn.prepareStatement("SELECT * FROM flat where fl_id="+flId+" ");
			rs=pst.executeQuery();
		
			while(rs.next()){
				FlatBean flatBean=new FlatBean();
				flatBean.setFlId(rs.getInt(1));
				flatBean.setFlNo(rs.getString(2));
				flatBean.setFlType(rs.getString(3));
				flatBean.setFlTower(rs.getString(4));
				flat.add(flatBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return flat;
	}
	
	//--------------END------fetch flat details by id---------------
	
	//--------------START------update flat details---------------
					
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Update value in flat table
	* @Exception : SQLException
	* @param : Object of Flat Bean
	* @return: boolean flag
	*/
	
	public static boolean updateFlat(FlatBean flatBean)throws Exception{
		
		conn=getDbConnection();
		
		boolean flag=false;
		
		try {
		
			pst=conn.prepareStatement("UPDATE flat SET fl_no=?,fl_type=?,fl_tower=? WHERE fl_id =?");
			pst.setString(1,flatBean.getFlNo());
			pst.setString(2,flatBean.getFlType());
			pst.setString(3,flatBean.getFlTower());
			pst.setInt(4,flatBean.getFlId());
			pst.executeUpdate();
			flag=true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		return flag;
		
	}
	
	//--------------END------update flat details---------------
	
	//--------------START------delete flat details---------------
	
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Delete value in flat table
	* @Exception : SQLException
	* @param : Object of Flat Bean
	* @return: boolean flag
	*/
	
	public static boolean deleteFlat(FlatBean flatBean)throws Exception{
		
		conn=getDbConnection();
		boolean flag=false;
		
		try {
			
			pst=conn.prepareStatement("DELETE FROM flat WHERE fl_id =?");
			pst.setInt(1,flatBean.getFlId());
			pst.executeUpdate();
			flag=true;
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		
		return flag;
		
	}

	//--------------END------delete flat details---------------

	

}
