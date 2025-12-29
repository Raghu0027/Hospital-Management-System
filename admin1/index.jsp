<%@page import="com.dao.CountDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.db.DataBaseConnection"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<%@include file="../component/allCss.jsp"%>

<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>

</head>
<body>
	<%@include file="navbar.jsp"%>
	
	<% Connection connection = DataBaseConnection.getConnection();
		out.print(connection);
	%>

	<c:if test="${ empty adminObj }">
		<c:redirect url="../admin_login.jsp"></c:redirect>
	</c:if>


	<div class="container p-5">
		<p class="text-center fs-3">Admin Dashboard</p>

		<c:if test="${not empty failureMsg}">
			<p class="fs-3 text-center text-danger">${failureMsg}</p>
			<c:remove var="failureMsg" scope="session" />
		</c:if>
		
		<c:if test="${not empty successMsg}">
			<div class="fs-3 text-center text-success" role="alert">${successMsg}</div>
			<c:remove var="successMsg" scope="session" />
		</c:if>
		
		<%
			CountDao dao = new CountDao(DataBaseConnection.getConnection());
		%>

		<div class="row">
			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-md fa-3x"></i><br>
						<p class="fs-4 text-center">
							Doctor <br><%=dao.doctorCount() %>
						</p>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-circle fa-3x"></i><br>
						<p class="fs-4 text-center">
							User <br><%=dao.userCount() %>
						</p>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="far fa-calendar-check fa-3x"></i><br>
						<p class="fs-4 text-center">
							Total Appointment <br><%=dao.appointmentCount() %>
						</p>
					</div>
				</div>
			</div>

			<div class="col-md-4 mt-2">

				<div class="card paint-card " data-bs-toggle="modal" data-bs-target="#staticBackdrop">
					<div class="card-body text-center text-success">
						<i class="far fa-calendar-check fa-3x"></i><br>
						<p class="fs-4 text-center">
							Specialist <br><%=dao.specialistCount() %>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title fs-5" id="staticBackdropLabel">Add Specialist</h2>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				
				<div class="modal-body">
					<form action="../addSpecialist" method="post">
						<div class="form-group">
							<label>Enter Specialist Name </label>
							<input type="text" name="specialistName" class="form-control">
						</div>
						
						<div class="text-center">
							<button type="submit"  class="btn btn-primary mt-2">Add</button>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="../component/footer.jsp"%>
	<%@include file="../component/allScripts.jsp"%>
	
</body>
</html>