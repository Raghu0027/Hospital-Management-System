<%@page import="com.entity.UserAppointment"%>
<%@page import="com.db.DataBaseConnection"%>
<%@page import="javax.xml.crypto.Data"%>
<%@page import="com.dao.AppointmentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add comment</title>
<%@include file="../component/allCss.jsp" %>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}


.backImg {
	background: linear-gradient(rgba(0, 0, 0, .4), rgba(0, 0, 0, .4)),
		url("../img/hospital.jpg");
	height: 20vh;
	width: 100%;
	background-size: cover;
	background-repeat: no-repeat;
}
</style>
</head>
<body>
<%@include file="navbar.jsp" %>
	<c:if test="${empty docObj }">
		<c:redirect url="../doctor_login.jsp"></c:redirect>
	</c:if>
	
	<div class="container-fulid backImg p-5">
		<p class="text-center fs-2 text-white"></p>
	</div>
	<div class="container p-3">
		<div class="row">

			<div class="col-md-6 offset-md-3">
				<div class="card paint-card">
					<div class="card-body">
						<p class="text-center fs-4">Patient Comment</p>
							
				
							
						<%
							int id = Integer.parseInt(request.getParameter("id"));
							AppointmentDao dao = new AppointmentDao(DataBaseConnection.getConnection());
							UserAppointment appointment = dao.getAppointmentById(id);
						%>	
					
						<form class="row" action="../updateCommentStatus" method="post">
							<div class="col-md-6">
								<label>Patient Name</label> <input type="text" readonly
									value="<%=appointment.getFullName() %>" class="form-control">
							</div>

							<div class="col-md-6">
								<label>Age</label> <input type="text" value="<%=appointment.getAge()%>"
									readonly class="form-control">
							</div>


							<div class="col-md-6">
								<br> <label>Mob No</label> <input type="text" readonly
									value="<%=appointment.getPhNo() %>" class="form-control">
							</div>

							<div class="col-md-6">
								<br> <label>Diseases</label> <input type="text" readonly
									value="<%=appointment.getDiseases() %>" class="form-control">
							</div>

							<div class="col-md-12">
								<br> <label>Comment</label>
								<textarea required name="comment" class="form-control" row="3"
									cols=""></textarea>
							</div>

							<input type="hidden" name="id" value="<%=appointment.getId()%>"> <input
								type="hidden" name="docId" value="<%=appointment.getDoctorId()%>">

							<button class=" mt-3 btn btn-primary col-md-6 offset-md-3">Submit</button>

						</form>

					</div>
				</div>
			</div>
		</div>
	</div>




	
	
<%@include file="../component/footer.jsp" %>
	<%@include file="../component/allScripts.jsp" %>
</body>
</html>