package com.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.GasUnitRateBean;
import com.dao.GasUnitRateDAO;

public class GasUnitRateAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		float gasRate=Float.parseFloat(request.getParameter("gasRate"));
		boolean flag=false;
		
		GasUnitRateBean gasUnitRateBean=new GasUnitRateBean();
		
		gasUnitRateBean.setGasUnitRate(gasRate);
		
		try {
			flag=GasUnitRateDAO.insertGasUnitRate(gasUnitRateBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(flag) {
			response.sendRedirect("GasUnitRateView");
		}else {
			response.sendRedirect("error.html");
		}
		
		
	}

}
