
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>

<head>
<meta charset="UTF-8">
<title>Admin - Bookstore | Dashboard</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

<c:url value="/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js" var="bootstrap_min_js" />
<c:url value="/resources/js/jquery-2.1.4.min.js" var="jquery214" />
<c:url value="/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css" var="bootstrap_min_css" />
<c:url value="/resources/css/admin.min.css" var="admin_css" />
<c:url value="/resources/css/login.css" var="login_css" />
<c:url value="/resources/css/ionicons.min.css" var="font_ionicons_css" />
<c:url value="/resources/css/ionicons-animation.min.css" var="font_ionicons_animation_css" />

<link href="${bootstrap_min_css}" rel="stylesheet">
<link href="${font_ionicons_css}" rel="stylesheet">
<link href="${font_ionicons_animation_css}" rel="stylesheet">
<script src="${jquery214} "></script>
<script src="${bootstrap_min_js} "></script>

<link href="${admin_css}" rel="stylesheet">
<link href="${login_css}" rel="stylesheet">
<style>
	

</style>


</head>
<body>
	<div class="wrapper">
	<%-- 		<spring:message  code="hello"/>  --%>
	
		
			<h3 class="title">Đăng nhập hệ thống</h3>
			<form:form action="/login" class="form" commandName="form" method="post" id="loginForm">
				<div class="row">
					<div class="col-xs-3">Email: </div>
					<div class="col-xs-9">
						<form:input path="email" cssErrorClass="form-control has-error"  value="${requestScope.email}" cssClass="form-control" placeholder="Nhập địa chỉ email" />
					</div>

				</div>
				<div class="row">
					<div class="col-xs-3">Mật khẩu: </div>
					<div class="col-xs-9">
						<form:password path="password" cssErrorClass="form-control has-error" cssClass="form-control" placeholder="Nhập nhập khẩu" />
					</div>

				</div>
				<div class="row">
					<div class="col-xs-3"></div>
					<div class="col-xs-9">
						<input type="submit" value="Đăng nhập" id="loginSubmit" class="btn btn-primary" />
						<a href="/staff/reset-password" class="forget-password">Quên mật khẩu?</a>
					</div>
				</div>
				
				
				<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
					<div class="alert alert-error" style="margin:10px 0px 0px 0px;text-align: center;">						
							<span style="color: red;"><spring:message code="Invalid.Login" /></span>
					</div>
				</c:if>
				
			</form:form>
			<!-- <a href="/login/staff" ><button value="Đăng nhập" id="loginSubmit" class="btn btn-primary" > staff </button></a>
			<a href="/login/pm" ><button value="Đăng nhập" id="loginSubmit" class="btn btn-primary" > PM </button></a> -->

	</div>
	<!-- ./wrapper -->
	
	<div
			class="modal fade"
			id="reset-password-modal">
			<div class="modal-dialog " style="width: 350px; margin-top: 100px;">
				<div class="modal-content ">
					<div class="modal-header my-modal-header">
						<h4>Quên mật khẩu?</h4>
						Nhập địa chỉ e-mail để nhận mật khẩu mới.
						<span
						class="close-modal ion ion-close-round"
						data-dismiss="modal"
						aria-label="Close"></span>
					</div>
					<div class="modal-body">
					<form>
						<div style="margin-top: 10px;" class="row">
		                  	<div class="col-xs-2">Email: </div>
		                  	<div class="col-xs-10">
		                  		<input type="text" name="email" class="email form-control"/>
		                  	</div>
		                 </div>
		                <div  class="row">
							<div class="col-xs-9 col-xs-offset-3" >
								<button class="btn btn-primary btnResetPassword " style="width: 80px;" >Gửi  </button>
								<button class="btn btn-primary" data-dismiss="modal" style="width: 80px;">Hủy</button>
							</div>
						</div>
						<div  class="row error-messages-box">
							<span class="col-xs-9 col-xs-offset-3" style="color: red;" >
								
							</span>
						</div>
					</form>	
					</div>
				</div>
				<!-- 	end body -->
			</div>
			<!-- 	end content -->
		</div>
	<!-- end dialog -->
	
	
	<!-- -----------------EDIT EDIT EDIT EDIT EDIT FORM---------------------->
	
	
	

	
	<!-- --------------------SUCCESS SUCCESS SUCCESS SUCCESS FORM---------------------- -->
	<div
		class="modal fade"
		id='alert-box'>
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header">
					<span
						class="close-modal ion ion-close-round"
						data-dismiss="modal"
						aria-label="Close"></span>
				</div>
				<!-- end header -->
				<div class="modal-body modal-body-sign-in">
					<div style="height: 120px; margin: 0px auto; text-align: center;">
						<h3 style="" class="alert-message"></h3>
						<a href="/staff/requests">Quay lại</a> <br /> 
						<span class="alert-icon"></span>
					</div>
				</div>
				<!-- end body -->
			</div>
			<!-- end content -->
		</div>
		<!-- end dialog -->
	</div>
	
	<div class="modal" id='waiting'>
		<div class="modal-dialog ">
			<i class="ion ion-loading-a"></i>
		</div>
		<!-- end dialog -->
	</div>
		
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			$(" #reset-password-modal .error-messages-box").hide();
			$(".forget-password").on("click", function(event){
				event.preventDefault();
				$("#reset-password-modal").modal({
					show: true
				});
			});
			
			
			$('.btnResetPassword').on("click", function(event){
				event.preventDefault();
				var requestId = $(this).data("id");
				$(".btnResetPassword").html(' <i class="ion ion-loading-a"></i> Xin chờ').attr("disabled", true);
				var email= $('#reset-password-modal form').serialize();
				
					$.ajax({
					    url: '/staff/reset-password',
					    type: 'POST',
					    data: email, 
					    success: function(response){ 
					    	$('#reset-password-modal .has-error').removeClass('has-error');
					    	
							if (response.status == 'FAILED') {
								$("#reset-password-modal input.email").addClass('has-error');
								$(" #reset-password-modal .error-messages-box").show();
								$('#reset-password-modal  .error-messages-box span').html(response.message);
								$(".btnResetPassword").html( 'Gửi').removeAttr("disabled");
							} else {
								$("#reset-password-modal .error-messages-box").hide();
							 	$(".btnResetPassword").html('Gửi').removeAttr("disabled");
								$('#reset-password-modal').modal('hide');
								showAlertBox("Thay đổi THÀNH CÔNG", "success","/login");
							}
						},
						error : function(response) {
							console.log(response);
						}
					});
								
			});
			var showAlertBox = function(message, status, urlRedirect){
				$('#alert-box h3.alert-message').html(message);
				if (status == 'success'){
					$('#alert-box span.alert-icon').html('<i class="icon ion-checkmark-circled" style="color: green; font-size: 40px;">');
				}else{
					$('#alert-box span.alert-icon').html('<i class="icon ion-alert-circled" style="color: red; font-size: 40px;">');
				}
				
				$('#alert-box').modal('show');
				setTimeout(function() {
					window.location = urlRedirect;
				}, 500); 
			}
			
			
			var waiting = function(status){
				$('#waiting').modal(status);
			}
			
		});
	</script>
</html>