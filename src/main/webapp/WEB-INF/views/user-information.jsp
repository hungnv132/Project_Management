
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Bookstore | Dashboard</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

<jsp:include page="/WEB-INF/templates/resources_path.jsp"></jsp:include>
	
	
	<joda:format pattern="dd-MM-yyyy" value="${requestScope.user.dob}" var="formattedDOB" />
	<joda:format pattern="dd-MM-yyyy" value="${requestScope.user.enrollmentDate}" var="formattedEnDate" />
<style>
</style>
</head>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include page="/WEB-INF/templates/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/templates/menu-sidebar.jsp"></jsp:include>


		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<%-- <jsp:include page="/WEB-INF/templates/breadcrumb.jsp"></jsp:include> --%>
			<section class="content">
				<!-- Info boxes -->
				<%--   <fmt:formatDate value="${date}" pattern="dd-MM-yyyy HH-mm-ss" type="date" var="d" dateStyle="short"/>
        	<c:out value="${d}"></c:out>  --%>
        	
				<h4>Thông tin cá nhân người dùng</h4>
				<c:choose>
				  <c:when test="${changePassSuccessfully == 'success' }">
				    <div class="alert my-alert-success"> 
						<button class="close" aria-hidden="true" data-dismiss="alert" type="button">x</button>
						<i class="icon fa fa-check"></i> Đổi mật khẩu thành công
					</div>
				  </c:when>
				  <c:when test="${updateSuccessfully == 'success' }">
				    <div class="alert my-alert-success"> 
						<button class="close" aria-hidden="true" data-dismiss="alert" type="button">x</button>
						 <i class="icon fa fa-check"></i> Cập nhật thông tin thành công
					</div>
				  </c:when>
				
				</c:choose>
			
				
				<div class="edit-form">
						<div class="row">
							<div class="col-xs-2">Họ và tên:</div>
							<div class="col-xs-5">
								<label class="view-info"><c:out value="${requestScope.user.fullName}" /></label>

							</div>

						</div>
						<div class="row">
							<div class="col-xs-2">Ngày sinh:</div>
							<div class="col-xs-5">
								<label class="view-info"> <c:out value="${formattedDOB}" />  </label>	
							</div>

						</div>
						<div class="row">
							<div class="col-xs-2">Giới tinh:</div>
							<div class="col-xs-5"> 
								<label class="view-info"><c:out value="${requestScope.user.gender == 'Nam' ? 'Nam': 'Nữ'}"/></label>
							</div>

						</div>
						<div class="row">
							<div class="col-xs-2">Email:</div>
							<div class="col-xs-5">
								<label class="view-info"> <c:out value="${requestScope.user.email}" /></label>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-2">Address:</div>
							<div class="col-xs-5">
								<label class="view-info">  <c:out value="${requestScope.user.address}" /></label>
							</div>

						</div>
						<div class="row">
							<div class="col-xs-2">Ngày vào cty:</div>
							<div class="col-xs-5">
								<label class="view-info"> <c:out value="${formattedEnDate}" /></label>
							</div>
						</div>
						<div class="row view-info" >
							<div class="col-xs-2">Vị trí:</div>
							<div class="col-xs-5">
								<label class="view-info"><c:out value="${requestScope.user.position.name}" /> </label>
							</div>
						</div>

					
					<div class="row">
						<div class="col-xs-2"></div>
						<div class="col-xs-5">
							<a href="/staff/update"> <button id="editProfile" class="btn btn-primary">Thay đổi</button></a>
							<a href="/staff/change-password"><button id="changePassword" class="btn btn-primary">Đổi mật khẩu</button></a>
						</div>

					</div>
					<div class="row">
						<div class="col-xs-3"></div>
						<div class="col-xs-9">
							<ul id="errorMessages">
							</ul>
						</div>

					</div>
				</div>



		
			</section>
			<!-- /.content -->
		</div>
	
	<!-- /.content-wrapper -->

		<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>
	</div>

	<div class="modal fade" id='create-success'>
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header">
					<span class="close-modal ion ion-close-round" data-dismiss="modal" aria-label="Close"></span>
				</div>
				<!-- end header -->
				<div class="modal-body modal-body-sign-in">
					<div style="height: 120px; margin: 0px auto; text-align: center;">
						<h3 style="">Thêm nhân viên thành công!</h3>
						<a href="/user/manage-user">Về trang chủ</a> <br /> <i class="icon ion-checkmark-circled"
							style="color: green; font-size: 40px;"></i>
					</div>
				</div>
				<!-- end body -->
			</div>
			<!-- end content -->
		</div>
		<!-- end dialog -->
	</div>
	
</body>
	<script type="text/javascript">
		$(document).ready(function() {
		
		
			
		});
	</script>
</html>