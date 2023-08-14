package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.GasUnitRateBean;

public class GasUnitRateDAO {
	
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
	
	//--------------START------insert gas unit rate details---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Insert values into gas rate table
	* @Exception : SQLException
	* @param : Object of Gas Unit Rate Bean
	* @return: boolean flag
	*/
	
	public static boolean insertGasUnitRate(GasUnitRateBean gasUnitRateBean) throws SQLException {
		
		conn=getDbConnection();
		
		try {
			pst=conn.prepareStatement("INSERT INTO gas_rate(gas_unit_rate) VALUES(?)");
			pst.setFloat(1, gasUnitRateBean.getGasUnitRate());
			pst.executeUpdate();
			
			flag=true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return flag;
	}
	
	//--------------END------insert gas unit rate---------------
	
	//--------------START------get gas unit rate list---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get unit rate from gas rate table as Array list
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type Gas Unit Rate Bean
	*/
	
	public static ArrayList<GasUnitRateBean> listGasUnitRate() throws SQLException{
		
		ArrayList<GasUnitRateBean> gasUnitRate=new ArrayList<GasUnitRateBean>();
		
		conn=getDbConnection();
		try {
			pst=conn.prepareStatement("SELECT * FROM gas_rate");
			rs=pst.executeQuery();
			
			while(rs.next()) { 
				GasUnitRateBean gasUnitRateBean=new GasUnitRateBean();
				gasUnitRateBean.setGasId(rs.getInt(1));
				gasUnitRateBean.setGasUnitRate(rs.getFloat(2));
				gasUnitRate.add(gasUnitRateBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return gasUnitRate;
		
	}
	
	//--------------END------get gas unit rate list---------------
	
	//--------------START------fetch gas unit rate by id---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Fetch unit rate from gas rate table as Array list by gasid
	* @Exception : SQLException
	* @param : int gasId
	* @return: Array List of type Gas Unit Rate Bean
	*/
	
	public static ArrayList<GasUnitRateBean> fetchGasUnitRateById(int gasId)throws SQLException{
		
		ArrayList<GasUnitRateBean> gasUnitRate=new ArrayList<GasUnitRateBean>();
		conn=getDbConnection();
		
		try{
			pst=conn.prepareStatement("SELECT * FROM gas_rate where gas_id="+gasId+" ");
			rs=pst.executeQuery();
		
			while(rs.next()){
				GasUnitRateBean gasUnitRateBean=new GasUnitRateBean();
				gasUnitRateBean.setGasId(rs.getInt(1));
				gasUnitRateBean.setGasUnitRate(rs.getFloat(2));
				gasUnitRate.add(gasUnitRateBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		
		return gasUnitRate;
	}
	
	//--------------END------fetch gas unit rate by id---------------
	
	//--------------START------update gas unit rate---------------
					
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Update value in gas rate table
	* @Exception : SQLException
	* @param : Object of Gas Unit Rate Bean
	* @return: boolean flag
	*/
	
	public static boolean updateGasUnitRate(GasUnitRateBean gasUnitRateBean)throws Exception{
		
		conn=getDbConnection();
		
		boolean flag=false;
		
		try {
		
			pst=conn.prepareStatement("UPDATE gas_rate SET gas_unit_rate=? WHERE gas_id =?");
			pst.setFloat(1,gasUnitRateBean.getGasUnitRate());
			pst.setInt(2,gasUnitRateBean.getGasId());
			pst.executeUpdate();
			flag=true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		return flag;
		
	}
	
	//--------------END------update gas unit rate---------------
	
	//--------------START------delete gas unit rate---------------
		
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Delete value in gas rate table
	* @Exception : SQLException
	* @param : Object of Gas Unit Rate Bean
	* @return: boolean flag
	*/
	
	public static boolean deleteGasUnitRate(GasUnitRateBean gasUnitRateBean)throws Exception{
		
		conn=getDbConnection();
		boolean flag=false;
		
		try {
			
			pst=conn.prepareStatement("DELETE FROM gas_rate WHERE gas_id =?");
			pst.setInt(1,gasUnitRateBean.getGasId());
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
