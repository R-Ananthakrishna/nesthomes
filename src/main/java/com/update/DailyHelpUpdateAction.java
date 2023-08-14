package com.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.DailyHelpBean;
import com.bean.DailyHelpCategoryBean;
import com.dao.DailyHelpDAO;

public class DailyHelpUpdateAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int dhId=Integer.parseInt(request.getParameter("dhId"));
		String uName=request.getParameter("uName");
		String category=request.getParameter("category");
		String address=request.getParameter("address");
		long contact=Long.parseLong(request.getParameter("contact"));
		boolean flag=false;
		
		DailyHelpBean dailyHelpBean=new DailyHelpBean();
		DailyHelpCategoryBean dailyHelpCategoryBean=new DailyHelpCategoryBean();
		
		dailyHelpBean.setDhId(dhId);
		dailyHelpBean.setDhName(uName);
		dailyHelpCategoryBean.setDhcCategory(category);
		dailyHelpBean.setDhAddress(address);
		dailyHelpBean.setDhContact(contact);
		
		try {
			flag = DailyHelpDAO.updateDailyHelp(dailyHelpBean,dailyHelpCategoryBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(flag) {
			response.sendRedirect("DailyHelpView");
		}else {
			response.sendRedirect("error.html");
		}
		
		
	}
	

}
