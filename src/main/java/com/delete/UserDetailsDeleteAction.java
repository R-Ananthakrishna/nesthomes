package com.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.UserDetailsBean;
import com.dao.UserDetailsDAO;

public class UserDetailsDeleteAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int uId=Integer.parseInt(request.getParameter("uId"));
		
		boolean flag=false;
		
		UserDetailsBean userDetailsBean=new UserDetailsBean();
		userDetailsBean.setuId(uId);
		
		try {
			
			flag=UserDetailsDAO.deleteUser(userDetailsBean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (flag){
			response.sendRedirect("UserDetailsView");
		}else {
			response.sendRedirect("error.html");
		}
		
	}

}
