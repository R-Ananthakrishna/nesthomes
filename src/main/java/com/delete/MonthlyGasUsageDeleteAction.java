package com.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.MonthlyGasUsageBean;
import com.dao.MonthlyGasUsageDAO;

public class MonthlyGasUsageDeleteAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int mgcId=Integer.parseInt(request.getParameter("mgcId"));
		
		boolean flag=false;
		
		MonthlyGasUsageBean monthlyGasUsageBean=new MonthlyGasUsageBean();
		
		monthlyGasUsageBean.setMgcId(mgcId);
		
		
		
		try {
			flag = MonthlyGasUsageDAO.deleteMonthlyGasUsage(monthlyGasUsageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(flag) {
			response.sendRedirect("MonthlyGasUsageView");
		}else {
			response.sendRedirect("error.html");
		}
		
		
	}

}
