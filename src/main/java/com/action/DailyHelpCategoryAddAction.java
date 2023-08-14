package com.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.DailyHelpCategoryBean;
import com.dao.DailyHelpCategoryDAO;

public class DailyHelpCategoryAddAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String categoryName=request.getParameter("categoryName");
		boolean flag=false;
		
		DailyHelpCategoryBean dailyHelpCategoryBean=new DailyHelpCategoryBean();
		
		dailyHelpCategoryBean.setDhcCategory(categoryName.toUpperCase());
		
		try {
			flag=DailyHelpCategoryDAO.insertDailyHelpCategory(dailyHelpCategoryBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(flag) {
			response.sendRedirect("DailyHelpCategoryView");
		}else {
			response.sendRedirect("error.html");
		}
		
		
	}

}
