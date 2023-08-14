package com.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FlatBean;
import com.bean.MonthlyGasUsageBean;
import com.dao.MonthlyGasUsageDAO;

public class MonthlyGasUsageAddAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int flat=Integer.parseInt(request.getParameter("flat"));
		double unit=Double.parseDouble(request.getParameter("unit"));
		boolean flag=false;
		
		MonthlyGasUsageBean monthlyGasUsageBean=new MonthlyGasUsageBean();
		FlatBean flatBean=new FlatBean();
		
		flatBean.setFlId(flat);
		monthlyGasUsageBean.setGasUnit(unit);
		
		
		try {
			flag = MonthlyGasUsageDAO.insertMonthlyGasUsage(monthlyGasUsageBean, flatBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(flag) {
			response.sendRedirect("MonthlyGasUsageView");
		}else {
			response.sendRedirect("error.html");
		}
		
		
		
	}

}
