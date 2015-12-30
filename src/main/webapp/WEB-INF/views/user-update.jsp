
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Bookstore | Dashboard</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

<jsp:include page="/WEB-INF/templates/resources_path.jsp"></jsp:include>
	<joda:format   pattern="dd-MM-yyyy" value="${requestScope.form.dob}" var="formattedDOB" />
	<joda:format   pattern="dd-MM-yyyy" value="${requestScope.form.enrollmentDate}" var="formattedEnDate" />
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
				<h4>Thông tin cá nhân người dùng:</h4>
				<div class="edit-form">
					<form:form action="/staff/update"  id="editForm" commandName="form" >
						<div class="row enable-edit">
							<div class="col-xs-4">
								<div class="col-xs-3">Họ</div>
								<div class="col-xs-9">
									<%-- <input type="text" class="form-control "  value="<c:out value="${requestScope.user.firstName}" />" id="firstName" name="firstName" placeholder="Nhập họ" /> --%>
									<form:input class="form-control " path="firstName" placeholder="Nhập họ" cssErrorClass="form-control has-error" />
								</div>
							</div>
							<div class="col-xs-4">
								<div class="col-xs-3">Đệm</div>
								<div class="col-xs-9">
									<%-- <input type="text" class="form-control email" name="midName" id="midName" value="<c:out value="${requestScope.user.midName}" />" 
										placeholder="Nhập đệm" /> --%>
										<form:input class="form-control " path="midName" placeholder="Nhập đệm" cssErrorClass="form-control has-error"  />
								</div>
							</div>
							<div class="col-xs-4">
								<div class="col-xs-3">Tên</div>
								<div class="col-xs-9">
									<%-- <input type="text" class="form-control  my-form-input email" name="lastName" id="lastName" value="<c:out value="${requestScope.user.lastName}" />" 
										placeholder="Nhập tên" /> --%>
									<form:input class="form-control " path="lastName" placeholder="Nhập tên" cssErrorClass="form-control has-error"  />

								</div>	
							</div>

						</div>
					
						<div class="row">
							<div class="col-xs-2">Ngày sinh:</div>
							<div class="col-xs-5">
								<div class='input-group date' id='dobPicker'>
									<form:input  class="form-control" name="dob" id="dob" path="dob" value="${formattedDOB}"  cssErrorClass="form-control has-error" /> 
									 <span class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>

							</div>

						</div>
						<div class="row">
							<div class="col-xs-2">Giới tinh:</div>
							<div class="col-xs-5"> 
								 <span class="enable-edit">
								 <input type="radio" name="gender" id="male" value="Nam" <c:if test="${requestScope.form.gender == 'Nam'}">checked</c:if>   /> Nam
								  <input type="radio" name="gender"<c:if test="${requestScope.form.gender == 'Nữ'}"> checked </c:if> id="female" value="Nữ" style="margin-left: 15px;"> Nữ </span>
							</div>

						</div>
						<div class="row">
							<div class="col-xs-2">Email:</div>
							<div class="col-xs-5">
								<%--  <input type="text" value="<c:out value="${requestScope.user.email}" />"
									class="form-control enable-edit " id="email" name="email" placeholder="Nhập địa chỉ email" /> --%>
								<form:input class="form-control enable-edit" path="email" placeholder="Nhập email" cssErrorClass="form-control has-error"  />
								<form:input class="form-control enable-edit" path="id" type="hidden"/>
							</div>

						</div>
						<div class="row">
							<div class="col-xs-2">Address:</div>
							<div class="col-xs-5">
								<form:input class="form-control enable-edit" path="address" placeholder="Nhập nơi ở" cssErrorClass="form-control has-error"  />
							</div>

						</div>
						<div class="row">
							<div class="col-xs-2">Ngày vào cty:</div>
							<div class="col-xs-5">
								<div class='input-group date enable-edit' id='enrollmentDatePicker'>
									<form:input type='text' class="form-control" cssErrorClass="form-control has-error"  path="enrollmentDate" value="${formattedEnDate}" name="enrollmentDate" id="enrollmentDate" /> <span
										class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>

						</div>
						<%-- <div class="row view-info" >
							<div class="col-xs-2">Vị trí:</div>
							<div class="col-xs-5">
								<select class="form-control enable-edit" name="position" id="position">
									<option value="DEVELOPER" <c:if test="${requestScope.form.position == 'DEVELOPER'}"> selected </c:if>>DEVELOPER</option>
									<option value="TESTER"  <c:if test="${requestScope.form.position == 'TESTER'}"> selected </c:if> >TESTER</option>
									<option value="QUALITY ASSURANCE"  <c:if test="${requestScope.form.position == 'QUALITY ASSURANCE'}"> selected </c:if>>QUALITY ASSURANCE</option>
									<option value="manager"  <c:if test="${requestScope.form.position == 'PM'}"> selected </c:if>>PROJECT MANAGER</option>
								</select>
							</div>
						</div> --%>


						<div class="row enable-edit">
							<div class="col-xs-2"></div>
							<div class="col-xs-5">

								<span class="enable-edit"><input type="submit" value="Cập nhật" id="createSubmit" class="btn btn-primary enable-edit" /> </span> 
								<span class="enable-edit"><input type="reset" value="nhập lại" id="createSubmit" class="btn btn-primary enable-edit" /> </span> 
								<span class="enable-edit"><a href="/staff/information" class="btn btn-primary" >Hủy bỏ</a> </span>
							</div>

						</div>
					<div class="row">
						<div class="col-xs-2"></div>
						<div class="col-xs-10">
								<form:errors path="*" cssClass="errors"/>
						</div>
					</div>
				</form:form>
			</div>




			</section>
			<!-- /.content -->
		</div>
		<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>
	</div>
	<!-- /.content-wrapper -->

	


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
	<script type="text/javascript">
		$(document).ready(function() {
			
	
			
			/* ---------Configure Datetime picker-------------- */
			$('#dobPicker').datetimepicker({
				language : 'vi',
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				startView : 2,
				minView : 2,
				forceParse : 0,
				format : "dd-mm-yyyy"
			});
			$('#enrollmentDatePicker').datetimepicker({
				language : 'vi',
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				startView : 2,
				minView : 2,
				forceParse : 0,
				format : "dd-mm-yyyy"
			});
			
			
			

		});
	</script>
	</body>
</html>