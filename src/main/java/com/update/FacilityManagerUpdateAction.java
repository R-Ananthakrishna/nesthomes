package com.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FacilityManagerBean;
import com.dao.FacilityManagerDAO;

public class FacilityManagerUpdateAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int fmId=Integer.parseInt(request.getParameter("fmId"));
		String uName=request.getParameter("uName");
		String designation=request.getParameter("designation");
		String duties=request.getParameter("duties");
		long contact=Long.parseLong(request.getParameter("contact"));
		
		boolean flag=false;
		
		FacilityManagerBean facilityManagerBean=new FacilityManagerBean();
		facilityManagerBean.setFmId(fmId);
		facilityManagerBean.setFmName(uName);
		facilityManagerBean.setFmDesignation(designation);
		facilityManagerBean.setFmDuties(duties);
		facilityManagerBean.setFmContact(contact);
		
		try {
			
			flag=FacilityManagerDAO.updateFacilityManager(facilityManagerBean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (flag){
			response.sendRedirect("FacilityManagerView");
		}else {
			response.sendRedirect("error.html");
		}
	
	}

}
