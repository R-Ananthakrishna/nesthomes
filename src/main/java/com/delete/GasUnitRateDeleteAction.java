package com.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.GasUnitRateBean;
import com.dao.GasUnitRateDAO;

public class GasUnitRateDeleteAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int gasId=Integer.parseInt(request.getParameter("gasId"));
		
		boolean flag=false;
		
		GasUnitRateBean gasUnitRateBean = new GasUnitRateBean();
		gasUnitRateBean.setGasId(gasId);
		
		try {
			flag=GasUnitRateDAO.deleteGasUnitRate(gasUnitRateBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(flag) {
			response.sendRedirect("GasUnitRateView");
		}else {
			response.sendRedirect("error.html");
		}
		
		
	}

}
