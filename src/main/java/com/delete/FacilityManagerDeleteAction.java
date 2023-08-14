package com.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FacilityManagerBean;
import com.dao.FacilityManagerDAO;

public class FacilityManagerDeleteAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int fmId=Integer.parseInt(request.getParameter("fmId"));
		
		boolean flag=false;
		
		FacilityManagerBean facilityManagerBean=new FacilityManagerBean();
		facilityManagerBean.setFmId(fmId);
		
		
		try {
			
			flag=FacilityManagerDAO.deleteFacilityManager(facilityManagerBean);
			
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
