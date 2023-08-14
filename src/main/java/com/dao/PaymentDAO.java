package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bean.FlatBean;
import com.bean.PaymentBean;
import com.bean.SocietyDuesCategoryBean;

public class PaymentDAO {
	
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
	
	//--------------START------update gas dues---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Update gas dues in gas dues table
	* @Exception : SQLException
	* @param : Payment Bean object, Flat Bean object
	* @return: Nothing
	*/
	
	public static void updateGasDues(PaymentBean paymentBean,FlatBean flatBean) throws SQLException {
		
		conn=getDbConnection();
		try {
			
			pst=conn.prepareStatement("UPDATE gas_dues SET gd_due_amount=? WHERE fl_id="+flatBean.getFlId()+" ");
			double gasDues=(paymentBean.getTotal()-paymentBean.getPaid());
			pst.setDouble(1, gasDues);
			pst.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	//--------------END------update gas dues---------------
	
	//--------------START------insert payment details---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Insert values into society dues table
	* @Exception : SQLException
	* @param : Object of Payment Bean, Society Dues Category Bean, Flat Bean
	* @return: boolean flag
	*/
	
	
	public static boolean insertPayment(PaymentBean paymentBean, SocietyDuesCategoryBean societyDuesCategoryBean, FlatBean flatBean) throws SQLException, ParseException {
		
		conn=getDbConnection();
		
		String pattern="yyyy-MM-dd";
		String inputPattern="yyyy-MM-dd";
		SimpleDateFormat inputFormat=new SimpleDateFormat(inputPattern);
		SimpleDateFormat outputFormat=new SimpleDateFormat(pattern);
		
		//SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
		String inputDate=paymentBean.getDate();
		Date dateParse=inputFormat.parse(inputDate);
		
		String date=outputFormat.format(dateParse);
	
		
		try {
			pst=conn.prepareStatement("INSERT INTO society_dues(soc_id,fl_id,so_date,so_total,so_paid_amount) VALUES(?,?,?,?,?)");
			pst.setInt(1, societyDuesCategoryBean.getSocId());
			pst.setInt(2, flatBean.getFlId());
			pst.setString(3, date);
			pst.setDouble(4, paymentBean.getTotal());
			pst.setDouble(5, paymentBean.getPaid());
			pst.executeUpdate();
			
			updateGasDues(paymentBean, flatBean);
			
			flag=true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		
		return flag;
		
	}
	
	//--------------END------insert payment details---------------
	
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
	
	
	//--------------START------get list of society dues categories---------------
	/**
	*
	* @author: R Ananthakrishna
	* @Date: 19-07-2023
	* @version : Java JDK 8
	* @purpose :Get list of categories from society dues category table
	* @Exception : SQLException
	* @param : Nothing
	* @return: Array List of type Society Dues Category Bean Bean
	*/
	
	public static ArrayList<SocietyDuesCategoryBean> listDuesType() throws SQLException{
		
		ArrayList<SocietyDuesCategoryBean> duesType=new ArrayList<SocietyDuesCategoryBean>();
		
		conn=getDbConnection(); 
		try {
			pst=conn.prepareStatement("SELECT soc_id,soc_category FROM society_dues_category");
			rs=pst.executeQuery();
			
			while(rs.next()) {  
				SocietyDuesCategoryBean societyDuesCategoryBean=new SocietyDuesCategoryBean();
				societyDuesCategoryBean.setSocId(rs.getInt(1));
				societyDuesCategoryBean.setCategory(rs.getString(2));
				duesType.add(societyDuesCategoryBean);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return duesType;
		
	}
	
	
	//--------------END------get list of society dues categories---------------
	

}
