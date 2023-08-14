package com.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FacilityManagerBean;
import com.dao.FacilityManagerDAO;

public class FacilityManagerAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uName=request.getParameter("uName");
		String designation=request.getParameter("designation");
		String duties=request.getParameter("duties");
		long contact=Long.parseLong(request.getParameter("contact"));
		
		boolean flag=false;
		
		FacilityManagerBean facilityManagerBean=new FacilityManagerBean();
		
		facilityManagerBean.setFmName(uName);
		facilityManagerBean.setFmDesignation(designation);
		facilityManagerBean.setFmDuties(duties);
		facilityManagerBean.setFmContact(contact);
		
		try {
			flag=FacilityManagerDAO.insertFacilityManager(facilityManagerBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(flag) {
			response.sendRedirect("FacilityManagerView");
		}else {
			response.sendRedirect("error.html");
		}
		
		
	}

}
