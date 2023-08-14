package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.UserDetailsBean;

public class UserDetailsDAO {
	
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
	
	//--------------START------insert user details---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Insert values into user login table
	* @Exception : SQLException
	* @param : Object of UserDetails Bean
	* @return: boolean flag
	*/
	
	
	public static boolean insertUser(UserDetailsBean userDetailsBean) throws SQLException {
		
		conn=getDbConnection();
		
		try {
			pst=conn.prepareStatement("INSERT INTO user_login(u_username,u_password,u_usertype) VALUES(?,?,?)");
			pst.setString(1, userDetailsBean.getuName());
			pst.setString(2, userDetailsBean.getPass());
			pst.setString(3, userDetailsBean.getuType());
			pst.executeUpdate();
			
			flag=true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		
		return flag;
		
	}
	
	//--------------END------insert user details---------------
	
	//--------------START------get user list---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get user details from user login table as Array list
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type UserDetails Bean
	*/
	
	public static ArrayList<UserDetailsBean> listUser() throws SQLException{
		
		ArrayList<UserDetailsBean> userDetails=new ArrayList<UserDetailsBean>();
		
		conn=getDbConnection(); 
		try {
			pst=conn.prepareStatement("SELECT * FROM user_login");
			rs=pst.executeQuery();
			
			while(rs.next()) {
				UserDetailsBean userDetailsBean=new UserDetailsBean();
				userDetailsBean.setuId(rs.getInt(1));
				userDetailsBean.setuName(rs.getString(2));
				userDetailsBean.setPass(rs.getString(3));
				userDetailsBean.setuType(rs.getString(4));
				userDetails.add(userDetailsBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return userDetails;
		
	}
	
	//--------------END------get user list---------------
	
	//--------------START------fetch user details by id---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Fetch user details from user login table as Array list by uId
	* @Exception : SQLException
	* @param : int uId
	* @return: Array List of type UserDetails Bean
	*/
	
	public static ArrayList<UserDetailsBean> fetchUserById(int uId)throws SQLException{
		
		ArrayList<UserDetailsBean> userDetails=new ArrayList<UserDetailsBean>();
		conn=getDbConnection();
		
		try{
			pst=conn.prepareStatement("SELECT * FROM user_login where u_id="+uId+" ");
			rs=pst.executeQuery();
		
			while(rs.next()){
				UserDetailsBean userDetailsBean=new UserDetailsBean();
				userDetailsBean.setuId(rs.getInt(1));
				userDetailsBean.setuName(rs.getString(2));
				userDetailsBean.setPass(rs.getString(3));
				userDetailsBean.setuType(rs.getString(4));
				userDetails.add(userDetailsBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return userDetails;
	}
	
	//--------------END------fetch user details by id---------------
	
	//--------------START------update user details---------------
					
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Update value in user login table
	* @Exception : SQLException
	* @param : Object of UserDetails Bean
	* @return: boolean flag
	*/
	
	public static boolean updateUser(UserDetailsBean userDetailsBean)throws Exception{
		
		conn=getDbConnection();
		
		boolean flag=false;
		
		try {
		
			pst=conn.prepareStatement("UPDATE user_login SET u_username=?,u_password=?,u_usertype=? WHERE u_id =?");
			pst.setString(1,userDetailsBean.getuName());
			pst.setString(2,userDetailsBean.getPass());
			pst.setString(3,userDetailsBean.getuType());
			pst.setInt(4,userDetailsBean.getuId());
			pst.executeUpdate();
			flag=true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		return flag;
		
	}
	
	//--------------END------update user details---------------
	
	//--------------START------delete user details---------------
	
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Delete value in user login table
	* @Exception : SQLException
	* @param : Object of UserDetails Bean
	* @return: boolean flag
	*/
	
	public static boolean deleteUser(UserDetailsBean userDetailsBean)throws Exception{
		
		conn=getDbConnection();
		boolean flag=false;
		
		try {
			
			pst=conn.prepareStatement("DELETE FROM user_login WHERE u_id =?");
			pst.setInt(1,userDetailsBean.getuId());
			pst.executeUpdate();
			flag=true;
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		
		return flag;
		
	}
	
	//--------------END------delete user details---------------

}
