package com.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.DailyHelpCategoryBean;
import com.dao.DailyHelpCategoryDAO;

public class DailyHelpCategoryDeleteAction extends HttpServlet {


	private static final long serialVersionUID = 1L;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int dhcId=Integer.parseInt(request.getParameter("dhcId"));
		
		boolean flag=false;
		
		DailyHelpCategoryBean dailyHelpCategoryBean = new DailyHelpCategoryBean();
		dailyHelpCategoryBean.setDhcId(dhcId);
		
		try {
			flag=DailyHelpCategoryDAO.deleteDailyHelpCategory(dailyHelpCategoryBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(flag) {
			response.sendRedirect("DailyHelpCategoryView");
		}else {
			response.sendRedirect("error.html");
		}
		
		
	}
	

}
