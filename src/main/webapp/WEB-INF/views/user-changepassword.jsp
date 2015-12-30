
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Bookstore | Dashboard</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

<jsp:include page="/WEB-INF/templates/resources_path.jsp"></jsp:include>
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
				<h4>Đổi mật khẩu mới</h4>
				<div class="edit-form">
					<form:form action="/staff/change-password"  id="formChangePass" commandName="form" >
						<div class="row">
							<div class="col-xs-3">Mật khẩu cũ:</div>
							<div class="col-xs-5">
								<%--  <input type="text" value="<c:out value="${requestScope.user.email}" />"
									class="form-control enable-edit " id="email" name="email" placeholder="Nhập địa chỉ email" /> --%>
								<form:password class="form-control enable-edit" path="oldPassword" placeholder="Nhập mật khẩu cũ" cssErrorClass="form-control has-error"  />
								
							</div>
							<div class="col-xs-4">	<form:errors path="oldPassword" cssClass="errors"/></div>

						</div>
						<div class="row">
							<div class="col-xs-3">Mật khẩu mới:</div>
							<div class="col-xs-5">
								<%--  <input type="text" value="<c:out value="${requestScope.user.email}" />"
									class="form-control enable-edit " id="email" name="email" placeholder="Nhập địa chỉ email" /> --%>
								<form:password class="form-control enable-edit" path="newPassword" placeholder="Nhập mật khẩu mới" cssErrorClass="form-control has-error"  />
							</div>
							<div class="col-xs-4">	<form:errors path="newPassword" cssClass="errors"/></div>
						</div>
						<div class="row">
							<div class="col-xs-3">Nhập lại mật khẩu:</div>
							<div class="col-xs-5">
								<%--  <input type="text" value="<c:out value="${requestScope.user.email}" />"
									class="form-control enable-edit " id="email" name="email" placeholder="Nhập địa chỉ email" /> --%>
								<form:password class="form-control enable-edit" path="reNewPassword" placeholder="Nhập lại mật khẩu mới" cssErrorClass="form-control has-error"  />
							</div>
							<div class="col-xs-4">	<form:errors path="reNewPassword" cssClass="errors"/></div>

						</div>


						<div class="row enable-edit">
							<div class="col-xs-3"></div>
							<div class="col-xs-5">
								 <button id="btnChangePass" class="btn btn-primary">Đổi mật khẩu</button></a>
								<a href="/staff/information"class="btn btn-primary" >Hủy bỏ</a>
							</div>

						</div>
					</form:form>
					
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
	</div>
	<!-- /.content-wrapper -->

	<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function() {
			
			var status = '${requestScope.response.status}';
			if(status == 'FAILED'){
			
				console.log("fail");
			}
			
			/* ---------Configure Datetime picker-------------- */
			
			

		});
	</script>
	</body>
</html>