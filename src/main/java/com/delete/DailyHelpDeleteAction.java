package com.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.DailyHelpBean;
import com.dao.DailyHelpDAO;

public class DailyHelpDeleteAction extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int dhId=Integer.parseInt(request.getParameter("dhId"));
		
		boolean flag=false;
		
		DailyHelpBean dailyHelpBean=new DailyHelpBean();
		
		
		dailyHelpBean.setDhId(dhId);
		
		
		try {
			flag = DailyHelpDAO.deleteDailyHelp(dailyHelpBean);
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
