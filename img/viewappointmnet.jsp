<%@page import="com.entity.DoctorDetails"%>
<%@page import="com.entity.User" %>
<%@page import="com.entity.UserAppointment" %>
<%@page import="com.dao.AppointmentDao" %> 
<%@page import="com.dao.DoctorDetailsDao" %> 
<%@page import="com.db.DataBaseConnection" %> 
<%@page import="java.util.List" %> 


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appointment Details</title>
<%@include file="component/allCss.jsp"%>

<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}

.backImg {
	background: linear-gradient(rgba(0, 0, 0, .4), rgba(0, 0, 0, .4)),
		url("img/hospital.jpg");
	height: 20vh;
	width: 100%;
	background-size: cover;
	background-repeat: no-repeat;
}
</style>

</head>
<body>

	<c:if test="${empty userObj }">
		<c:redirect url="user_login.jsp"></c:redirect>
	</c:if>

	<%@include file="component/navbar.jsp"%>

	<div class="container-fulid backImg p-5">
		<p class="text-center fs-2 text-white"></p>
	</div>
	<div class="container p-3">
		<div class="row">
			<div class="col-md-9">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 fw-bold text-center text-success">Appointment
							List</p>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Full Name</th>
									<th scope="col">Gender</th>
									<th scope="col">Age</th>
									<th scope="col">Appoint Date</th>
									<th scope="col">Diseases</th>
									<th scope="col">Doctor Name</th>
									<th scope="col">Status</th>

								</tr>
							</thead>
							<tbody>
									
									<%
										User user = (User)session.getAttribute("userObj");
										int id = user.getId();
										AppointmentDao dao = new AppointmentDao(DataBaseConnection.getConnection());
										List<UserAppointment>list = dao.getAllAppointmentByUserLogin(id);
										DoctorDetailsDao docDao = new DoctorDetailsDao(DataBaseConnection.getConnection());
			
										for(UserAppointment obj : list)
										{
											DoctorDetails doc= docDao.getDoctorDetailById(obj.getDoctorId());
									%>
										<tr>
											<td><%=obj.getFullName() %></td>
											<td><%=obj.getGender() %></td>
											<td><%=obj.getAge() %></td>
											<td><%=obj.getAppointmentDate() %></td>
											<td><%=obj.getDiseases() %></td>
											<td><%=doc.getFullName() %></td>
											<td>
												<%
													if(obj.getStatus().equalsIgnoreCase("Pending"))
													{
												%>
													<a href="#" class="btn btn-sm btn-warning">Pending</a>
												<% 		
													}else {
												%>
													<%=obj.getStatus() %>
												<%
													}	
												%>
											</td>
										</tr>
									<%
										}
									%>
									
								

							</tbody>
						</table>

					</div>
				</div>
			</div>

			<div class="col-md-3 p-3">
				<img alt="" src="img/doct.jpg">
			</div>
		</div>
	</div>


	<%@include file="component/footer.jsp"%>
	<%@include file="component/allScripts.jsp"%>
</body>
</html>