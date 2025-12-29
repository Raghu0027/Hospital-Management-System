package com.user.servlet;

import java.io.IOException;

import com.dao.UserDao;
import com.db.DataBaseConnection;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user_register")
public class UserRegister extends HttpServlet 
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		boolean check  = false;
		try {
			String fullName = req.getParameter("fullName");
			String email = req.getParameter("email");
			String pass = req.getParameter("password");

			User user = new User(fullName, email, pass);
			UserDao dao = new UserDao(DataBaseConnection.getConnection());

			HttpSession session = req.getSession();

			boolean result = dao.newUserRegistration(user);

			if(result)
			{
				session.setAttribute("successMsg", "User registered successfuly !");
				resp.sendRedirect("user_login.jsp");
				System.out.println("User registered");
			}
			else
			{
				session.setAttribute("failureMsg", "Something went wrong !");
				resp.sendRedirect("signup.jsp");
				System.out.println("Something went wrong");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}