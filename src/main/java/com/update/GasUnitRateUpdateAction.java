package com.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.GasUnitRateBean;
import com.dao.GasUnitRateDAO;

public class GasUnitRateUpdateAction extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int gasId=Integer.parseInt(request.getParameter("gasId"));
		float gasRate=Float.parseFloat(request.getParameter("gasRate"));
		
		boolean flag=false;
		
		GasUnitRateBean gasUnitRateBean = new GasUnitRateBean();
		gasUnitRateBean.setGasId(gasId);
		gasUnitRateBean.setGasUnitRate(gasRate);
		
		try {
			flag=GasUnitRateDAO.updateGasUnitRate(gasUnitRateBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (flag){
			response.sendRedirect("GasUnitRateView");
		}else {
			response.sendRedirect("error.html");
		}
		
	}
	
}
