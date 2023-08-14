package com.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.DailyHelpCategoryBean;
import com.dao.DailyHelpCategoryDAO;

public class DailyHelpCategoryUpdateAction extends HttpServlet {

	
	private static final long serialVersionUID = 1L;


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int dhcId=Integer.parseInt(request.getParameter("dhcId"));
		String categoryName=request.getParameter("categoryName");
		
		boolean flag=false;
		
		DailyHelpCategoryBean dailyHelpCategoryBean = new DailyHelpCategoryBean();
		dailyHelpCategoryBean.setDhcId(dhcId);
		dailyHelpCategoryBean.setDhcCategory(categoryName.toUpperCase());
		try {
			flag=DailyHelpCategoryDAO.updateDailyHelpCategory(dailyHelpCategoryBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (flag){
			response.sendRedirect("DailyHelpCategoryView");
		}else {
			response.sendRedirect("error.html");
		}
		
	}
	
}
