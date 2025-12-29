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

@WebServlet("/userLogin")
public class UserLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = req.getParameter("email");
			String pass = req.getParameter("password");

			HttpSession session = req.getSession();
			UserDao dao = new UserDao(DataBaseConnection.getConnection());

			User user = dao.login(email, pass);

			if (user != null) {
				session.setAttribute("userObj", user);
				resp.sendRedirect("user_appointment.jsp");
			} else {
				session.setAttribute("failureMsg", "invalid username or password !");
				resp.sendRedirect("user_login.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}ā