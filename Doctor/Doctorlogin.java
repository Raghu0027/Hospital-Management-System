package com.doctor.servlet;

import java.io.IOException;

import com.dao.DoctorDetailsDao;
import com.db.DataBaseConnection;
import com.entity.DoctorDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/doctorLogin")
public class DoctorLogin extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = req.getParameter("email");
			String pass = req.getParameter("password");

			HttpSession session = req.getSession();
			DoctorDetailsDao dao = new DoctorDetailsDao(DataBaseConnection.getConnection());
			DoctorDetails doctorDetails = dao.doctorLogin(email, pass);

			if (doctorDetails != null) {
				session.setAttribute("docObj", doctorDetails);
				resp.sendRedirect("doctor/index.jsp");
			} else {
				session.setAttribute("failureMsg", "invalid username or password !");
				resp.sendRedirect("doctor_login.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}