package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.FlatBean;
import com.bean.GasUnitRateBean;
import com.bean.MonthlyGasUsageBean;

public class MonthlyGasUsageDAO {
	
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
	
	//--------------START------calculate gas dues---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose : Calculate and insert values into gas dues table
	* @Exception : SQLException
	* @param : Object of Flat Bean
	* @return: Nothing
	*/
	
	public static void calculateGasDues(FlatBean flatBean) throws SQLException{
		
		
		MonthlyGasUsageBean monthlyGasUsageBean=null;
		GasUnitRateBean gasUnitRateBean=null;
		
		monthlyGasUsageBean=fetchGasUsage(flatBean);
		gasUnitRateBean=fetchGasUnit();
		
		double gasDues=monthlyGasUsageBean.getGasUnit()*gasUnitRateBean.getGasUnitRate();
		
		
		conn=getDbConnection();
		
		try {
			pst=conn.prepareStatement("INSERT INTO gas_dues(fl_id,gd_due_amount) VALUES(?,?)");
			pst.setInt(1, flatBean.getFlId());
			pst.setDouble(2, gasDues);
			pst.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		
		
	}
	
	//--------------END------calculate gas dues---------------
	
	//--------------START------insert monthly gas usage---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Insert values into monthly gas unit table
	* @Exception : SQLException
	* @param : Object of Monthly Gas Usage Bean and Flat Bean
	* @return: boolean flag
	*/
	
	
	public static boolean insertMonthlyGasUsage(MonthlyGasUsageBean monthlyGasUsageBean, FlatBean flatBean) throws SQLException {
		
		
		conn=getDbConnection();
		
		try {
			pst=conn.prepareStatement("INSERT INTO monthly_gas_unit(fl_id,mgc_monthly_unit) VALUES(?,?)");
			pst.setInt(1, flatBean.getFlId());
			pst.setDouble(2, monthlyGasUsageBean.getGasUnit());
			pst.executeUpdate();
			
			calculateGasDues(flatBean);
			
			flag=true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return flag;
		
	}
	
	//--------------END------insert monthly gas usage---------------
	
	//--------------START------fetch monthly gas usage for flat id---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Fetch gas usage from monthly gas unit table by flid
	* @Exception : SQLException
	* @param : Flat Bean object
	* @return: Monthly Gas Usage Bean object
	*/
	
	public static MonthlyGasUsageBean fetchGasUsage(FlatBean flatBean) throws SQLException {
		
		MonthlyGasUsageBean monthlyGasUsageBean=new MonthlyGasUsageBean();
		
		conn=getDbConnection();
		
		try {
			pst=conn.prepareStatement("SELECT mgc_monthly_unit FROM monthly_gas_unit WHERE fl_id="+flatBean.getFlId()+" ");
			rs=pst.executeQuery();
			
			while(rs.next()) {  
				monthlyGasUsageBean.setGasUnit(rs.getDouble(1));
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		
		return monthlyGasUsageBean;
	}
	
	//--------------END------fetch monthly gas usage for flat id---------------
	
	//--------------START------fetch gas unit rate---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Fetch unit rate from gas rate table
	* @Exception : SQLException
	* @param : Nothing
	* @return: Gas Unit Rate Bean object
	*/
	
	
	public static GasUnitRateBean fetchGasUnit() throws SQLException{
		
		GasUnitRateBean gasUnitRateBean=new GasUnitRateBean();
		
		conn=getDbConnection();
		
		try {
			pst=conn.prepareStatement("SELECT gas_unit_rate FROM gas_rate ORDER BY gas_id DESC LIMIT 1");
			rs=pst.executeQuery();
			
			while(rs.next()) {  
				gasUnitRateBean.setGasUnitRate(rs.getFloat(1));
			
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return gasUnitRateBean;
		
	}
	
	//--------------END------fetch gas unit rate---------------
	
	//--------------START------update gas dues---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Update gas dues in gas dues table
	* @Exception : SQLException
	* @param : Flat Bean object
	* @return: boolean flag
	*/
	
	
	public static boolean updateGasDues(FlatBean flatBean) throws SQLException {
		
		
		MonthlyGasUsageBean monthlyGasUsageBean=null;
		GasUnitRateBean gasUnitRateBean=null;
		
		monthlyGasUsageBean=fetchGasUsage(flatBean);
		gasUnitRateBean=fetchGasUnit();
		
		double gasDues=monthlyGasUsageBean.getGasUnit()*gasUnitRateBean.getGasUnitRate();
		
		conn=getDbConnection();
		try {
			
			pst=conn.prepareStatement("UPDATE gas_dues SET gd_due_amount=? WHERE fl_id="+flatBean.getFlId()+" ");
			pst.setDouble(1, gasDues);
			pst.executeUpdate();
			
			flag=true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
		

	}
	
	//--------------END------update gas dues---------------
	
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
	
	//--------------END------get lists of flat---------------
	
	//--------------START------get monthly gas usage list---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get details from monthly gas unit table and flat as Array list
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type Monthly Gas Usage Bean
	*/
	
	public static ArrayList<MonthlyGasUsageBean> listMonthlyGasUsage() throws SQLException{
		
		ArrayList<MonthlyGasUsageBean> monthlyGasUsage=new ArrayList<MonthlyGasUsageBean>();
		
		conn=getDbConnection();
		try {
			pst=conn.prepareStatement("SELECT monthly_gas_unit.mgc_id,flat.fl_no,flat.fl_tower,monthly_gas_unit.mgc_monthly_unit FROM flat,monthly_gas_unit WHERE flat.fl_id=monthly_gas_unit.fl_id");
			rs=pst.executeQuery();
			
			while(rs.next()) {  
				MonthlyGasUsageBean monthlyGasUsageBean=new MonthlyGasUsageBean();
				monthlyGasUsageBean.setMgcId(rs.getInt(1));
				monthlyGasUsageBean.setFlatNo(rs.getString(2));
				monthlyGasUsageBean.setFlatTower(rs.getString(3));
				monthlyGasUsageBean.setGasUnit(rs.getDouble(4));
				monthlyGasUsage.add(monthlyGasUsageBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return monthlyGasUsage;
		
	}
	
	//--------------END------get monthly gas usage list---------------
	
	//--------------START------fetch monthly gas usage by id---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Fetch monthly gas usage from monthly gas unit table as Array list by mgcid
	* @Exception : SQLException
	* @param : int mgcId
	* @return: Array List of type Monthly Gas Usage Bean
	*/
	
	public static ArrayList<MonthlyGasUsageBean> fetchMonthlyGasUsageById(int mgcId)throws SQLException{
		
		ArrayList<MonthlyGasUsageBean> monthlyGasUsage=new ArrayList<MonthlyGasUsageBean>();
		conn=getDbConnection();
		
		try{
			pst=conn.prepareStatement("SELECT monthly_gas_unit.mgc_id,flat.fl_no,flat.fl_tower,monthly_gas_unit.mgc_monthly_unit FROM flat,monthly_gas_unit WHERE flat.fl_id=monthly_gas_unit.fl_id AND monthly_gas_unit.mgc_id="+mgcId+" ");
			rs=pst.executeQuery();
		
			while(rs.next()){
				MonthlyGasUsageBean monthlyGasUsageBean=new MonthlyGasUsageBean();
				monthlyGasUsageBean.setMgcId(rs.getInt(1));
				monthlyGasUsageBean.setFlatTower(rs.getString(3));
				monthlyGasUsageBean.setGasUnit(rs.getDouble(4));
				monthlyGasUsage.add(monthlyGasUsageBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return monthlyGasUsage;
	}
	
	//--------------END------fetch monthly gas usage by id---------------
	
	//--------------START------update monthly gas usage---------------
						
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Update value in monthly gas unit table
	* @Exception : SQLException
	* @param : Object of Flat Bean and Monthly Gas Usage Bean
	* @return: boolean flag
	*/
	
	public static boolean updateMonthlyGasUsage(MonthlyGasUsageBean monthlyGasUsageBean,FlatBean flatBean)throws Exception{
		
		conn=getDbConnection();
		
		boolean flag=false;
		
		try {
		
			pst=conn.prepareStatement("UPDATE monthly_gas_unit SET fl_id=?,mgc_monthly_unit=? WHERE mgc_id =?");
			pst.setInt(1,flatBean.getFlId());
			pst.setDouble(2,monthlyGasUsageBean.getGasUnit());
			pst.setInt(3,monthlyGasUsageBean.getMgcId());
			pst.executeUpdate();
			
			updateGasDues(flatBean);
			flag=true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		return flag;
		
	}
	
	//--------------END------update monthly gas usage---------------
	
	//--------------START------delete monthly gas usage---------------
	
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Delete value in monthly gas unit table
	* @Exception : SQLException
	* @param : Object of Monthly Gas Usage Bean
	* @return: boolean flag
	*/
		
	
	public static boolean deleteMonthlyGasUsage(MonthlyGasUsageBean monthlyGasUsageBean)throws Exception{
		
		conn=getDbConnection();
		boolean flag=false;
		
		try {
			
			pst=conn.prepareStatement("DELETE FROM  monthly_gas_unit WHERE mgc_id =?");
			pst.setInt(1,monthlyGasUsageBean.getMgcId());
			pst.executeUpdate();
			flag=true;
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBDAO.close();
		}
		
		
		return flag;
		
	}

	//--------------END------delete monthly gas usage---------------


}
