package com.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FlatBean;
import com.bean.PaymentBean;
import com.bean.SocietyDuesCategoryBean;
import com.dao.PaymentDAO;

public class PaymentAddAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int flatNo=Integer.parseInt(request.getParameter("flatNo"));
		int duesType=Integer.parseInt(request.getParameter("duesType"));
		String date = request.getParameter("date");
		double amount=Double.parseDouble(request.getParameter("amount"));
		double payable=Double.parseDouble(request.getParameter("payable"));
		

		boolean flag=false;
		
		PaymentBean paymentBean=new PaymentBean();
		SocietyDuesCategoryBean societyDuesCategoryBean=new SocietyDuesCategoryBean();
		FlatBean flatBean=new FlatBean();
		
		flatBean.setFlId(flatNo);
		societyDuesCategoryBean.setSocId(duesType);
		paymentBean.setDate(date);
		paymentBean.setTotal(amount);
		paymentBean.setPaid(payable);
		
		
		try {
			flag = PaymentDAO.insertPayment(paymentBean, societyDuesCategoryBean, flatBean);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(flag) {
			response.sendRedirect("adminpaymentsuccess.html");
		}else {
			response.sendRedirect("error.html");
		}
		
		
		
	}
	
	

}
