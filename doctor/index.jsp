<%@page import="com.entity.DoctorDetails"%>
<%@page import="com.db.DataBaseConnection"%>
<%@page import="com.dao.CountDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Doctor</title>
<%@include file="../component/allCss.jsp" %>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@include file="navbar.jsp" %>
	<c:if test="${empty docObj }">
		<c:redirect url="../doctor_login.jsp"></c:redirect>
	</c:if>

	<%
		DoctorDetails doc = (DoctorDetails)session.getAttribute("docObj");
		CountDao dao = new CountDao(DataBaseConnection.getConnection());
	%>	

	<p class="text-center fs-3">Doctor Dashboard</p>

	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-2">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-md fa-3x"></i><br>
						<p class="fs-4 text-center">
							Doctor<br><%=dao.doctorCount() %>
						</p>
					</div>
				</div>
			</div>


			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="far fa-calendar-check fa-3x"></i><br>
						<p class="fs-4 text-center">
							Total Appointmnt<br><%=dao.appointmentCountById(doc.getId()) %>
						</p>
					</div>
				</div>
			</div>

		</div>
	</div>
	
	<%@include file="../component/footer.jsp" %>
	<%@include file="../component/allScripts.jsp" %>
</body>
</html>