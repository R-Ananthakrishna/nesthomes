package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDAO {
	
	private static Connection dbCon;
	private static String dbDriver;
	private static String dbURL;
	private static String userName;
	private static String passWord;
	
	
	//--------------START------database initialization---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Initialize database connection values
	* @param : Nothing
	* @see : Nothing
	* @return: Nothing
	*/
	
	public static void dbInit() {
		
		try {
			dbDriver="com.mysql.cj.jdbc.Driver";
			dbURL="jdbc:mysql://localhost:3306/luminar_nesthomes";
			userName="root";
			passWord="Luminar@2023";
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//-------------END---------database initialization---------------

	//--------------START----------database connection ------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose : database connection
	* @param : Nothing
	* @throws : Exception in case of missing driver class, SQL Exception
	* @return: Nothing
	*/
	
	public static void connect() throws ClassNotFoundException,SQLException{
		
		dbInit();
		
		try {
			Class.forName(dbDriver);
			dbCon=DriverManager.getConnection(dbURL,userName,passWord);
			setDbCon(dbCon);
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//--------------END----------database connection ------------
	
	
	//--------------START----------get db connection ------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose: Get Connection
	* @param : Nothing
	* @return : Connection
	*/
	
	public static Connection getDbCon() {
		return dbCon;
	}
	
	//------------------END-------------get db connection---------

	//--------------START----------set db connection ------------

	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose: Set Connection
	* @param : Connection
	* @return : Nothing
	*/
	
	public static void setDbCon(Connection con) {
		dbCon=con;
	}
	
	
	//------------------END-------------set connection---------

	//--------------START----------close connection ------------

	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose: Close connection
	* @param : Nothing
	* @throws :Exception in case of closing connection
	* @return : Nothing
	*/
	
	
	public static void close() throws SQLException {
		dbCon.close();
	}
	
	//------------------END-------------closing connection---------
	

}
