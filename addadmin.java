package com.admin.servlet;

import java.io.IOException;

import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminLogin")
public class AdminLogin extends HttpServlet 
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try {
			String email = req.getParameter("email");
			String pass = req.getParameter("password");

			HttpSession session = req.getSession();

			if(email.equals("admin@gmail.com") && pass.equals("admin"))
			{
				session.setAttribute("adminObj", new User());
				resp.sendRedirect("admin/index.jsp");
			}
			else
			{
				session.setAttribute("failureMsg", "invalid username or password !");
				resp.sendRedirect("admin_login.jsp");
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}