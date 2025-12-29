<%@page import="com.entity.DoctorDetails"%>
<%@page import="com.db.DataBaseConnection"%>
<%@page import="com.dao.AppointmentDao"%>
<%@page import="com.dao.DoctorDetailsDao"%>
<%@page import="com.entity.UserAppointment"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient Details</title>
<%@ include file="../component/allCss.jsp" %>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="col-md-12">
		<div class="card paint-card">
			<div class="card-body">
				<p class="fs-3 text-center">Patient Details</p>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Full Name</th>
							<th scope="col">Gender</th>
							<th scope="col">Age</th>
							<th scope="col">Appointment</th>
							<th scope="col">Email</th>
							<th scope="col">Mob No</th>
							<th scope="col">Diseases</th>
							<th scope="col">Doctor Name</th>
							<th scope="col">Address</th>
							<th scope="col">Status</th>
						</tr>
					</thead>
					<tbody>
						<%
							AppointmentDao dao = new AppointmentDao(DataBaseConnection.getConnection());
							List<UserAppointment>list = dao.getAllAppointment();
							
							DoctorDetailsDao dao2 = new DoctorDetailsDao(DataBaseConnection.getConnection());
			
							for(UserAppointment obj : list)
							{
								DoctorDetails  doc = dao2.getDoctorDetailById(obj.getDoctorId());
						%>
							<tr>
								<td><%=obj.getFullName() %></td>
								<td><%=obj.getGender() %></td>
								<td><%=obj.getAge() %></td>
								<td><%=obj.getAppointmentDate() %></td>
								<td><%=obj.getEmail() %></td>
								<td><%=obj.getPhNo() %></td>
								<td><%=obj.getDiseases() %></td>
								<td><%=doc.getFullName() %></td>
								<td><%=obj.getAddress() %></td>
								<td><%=obj.getStatus() %></td>
							</tr>
						<% 		
							}
						%>
					</tbody>
				</table>

			</div>
		</div>
	</div>

<%@ include file="../component/footer.jsp" %>
<%@ include file="../component/allScripts.jsp" %>
</body>
</html>