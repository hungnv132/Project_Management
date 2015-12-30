
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Bookstore | Dashboard</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
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
				
				<!-- <button class="btn btn-success" id="test">Thêm nhân viên</button> -->
				<br/>
			<%-- 	<c:if test="${sessionScope.joinedUser.role == 'ADMIN'}">
					<a href="/user/admin/manage-user"><button class="btn btn-primary pull-right" id="add-new-staff" style="margin: 30px 20px 0px 0px;" >Quay lại</button></a>
					<div >
						Nhân viên: <label> <c:out value="${user.fullName}"/> </label> <br/>
						Tổng số report tháng <c:out value="${currentMonth}"/>: <label> <c:out value="${totalReportsThisMonth}"/> </label>  <br/>
						Tổng số report tháng <c:out value="${currentMonth}"/> được duyệt: <label> <c:out value="${totalReportsApprovedThisMonth}"/> </label> <br/>
							
					</div>
				</c:if> --%>
				
				<c:choose>
					<c:when test="${sessionScope.joinedUser.role == 'ADMIN'}">
						<a href="/user/admin/manage-user"><button class="btn btn-primary pull-right" id="add-new-staff" style="margin: 30px 20px 0px 0px;" >Quay lại</button></a>
						<div >
							Nhân viên: <label> <c:out value="${user.fullName}"/> </label> <br/>
							Tổng số report tháng <c:out value="${currentMonth}"/>: <label> <c:out value="${totalReportsThisMonth}"/> </label>  <br/>
							Tổng số report tháng <c:out value="${currentMonth}"/> được duyệt: <label> <c:out value="${totalReportsApprovedThisMonth}"/> </label> <br/>
								
						</div>
					</c:when>
					<c:otherwise>
						<button class="btn btn-success" id="add-new-report">Tạo report mới</button>
					</c:otherwise>
				</c:choose>
				<table
					id="tblUser"
					class="display">
					<thead>
						<tr>
							<th>Ngày tạo</th>
							<th>Content</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
				</table>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>
		<div
			id="detail-modal"
			class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header my-modal-header">
						header <span
							class="close-modal ion ion-close-round"
							data-dismiss="modal"
							aria-label="Close"></span>
					</div>
					<div class="modal-body">body</div>
					<div class="modal-footer">footer</div>
				</div>
			</div>
		</div>
		<div
			class="modal fade"
			id='new-staff'>
			<div class="modal-dialog ">
				<div class="modal-content ">
					<div class="modal-header my-modal-header">
						<h4>Thêm nhân viên mới.</h4>
						<span
							class="close-modal ion ion-close-round"
							data-dismiss="modal"
							aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<form:form
							action=""
							method="post"
							id="createForm"
							commandName="form">
							<div class="row">
								<div class="col-xs-4">
									<div class="col-xs-3">Họ:</div>
									<div class="col-xs-9">
										<input
											type="text"
											class="form-control  my-form-input "
											id="firstName"
											name="firstName"
											placeholder="Nhập họ" />
									</div>
								</div>
								<div class="col-xs-4">
									<div class="col-xs-3">Đệm:</div>
									<div class="col-xs-9">
										<input
											type="text"
											class="form-control  my-form-input email"
											name="midName"
											id="midName"
											placeholder="Nhập đệm" />
									</div>
								</div>
								<div class="col-xs-4">
									<div class="col-xs-3">Tên:</div>
									<div class="col-xs-9">
										<input
											type="text"
											class="form-control  my-form-input email"
											name="lastName"
											id="lastName"
											placeholder="Nhập tên" />
											
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3">Ngày sinh:</div>
								<div class="col-xs-5">
									<div
										class='input-group date'
										id='dobPicker'>
										<input
											type='text'
											class="form-control"
											name="dob"
											id="dob" /> <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3">Giới tinh:</div>
								<div class="col-xs-5">
									<input
										type="radio"
										name="gender"
										id="male"
										value="Nam"
										checked> Nam <input
										type="radio"
										name="gender"
										id="female"
										value="Nữ"
										style="margin-left: 15px;"> Nữ
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3">Email:</div>
								<div class="col-xs-5">
									<input
										type="text"
										class="form-control my-form-input "
										id="email"
										name="email"
										placeholder="Nhập địa chỉ email" />
								</div>
							</div>
								<div class="row">
								<div class="col-xs-3">Address:</div>
								<div class="col-xs-5">
									<textarea
										rows="2"
										class="form-control my-form-input "
										id="address"
										name="address"										
										placeholder="Nhập nơi ở hiện tại" ></textarea>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3">Ngày vào Công ty:</div>
								<div class="col-xs-5">
									<div
										class='input-group date'
										id='enrollmentDatePicker'>
										<input
											type='text'
											class="form-control"
											name="enrollmentDate"											
											id="enrollmentDate" /> <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3">Vị trí hiện tại:</div>
								<div class="col-xs-5">
									<select
										class="form-control"
										name="position"
										id="position">
										<option value="DEVELOPER">DEVELOPER</option>
										<option value="TESTER">TESTER</option>
										<option value="QUALITY ASSURANCE">QUALITY ASSURANCE</option>
										<option value="PROJECT MANAGER">PROJECT MANAGER</option>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3"></div>
								<div class="col-xs-5">
									<input
										type="submit"
										value="Tạo tài khoản"
										id="createSubmit"
										class="btn btn-primary" /> <input
										type="reset"
										value="Reset"
										id="createSubmit"
										class="btn btn-primary" />
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3"></div>
								<div class="col-xs-9">
									<ul id="errorMessages">
									</ul>
								</div>
							</div>
						</form:form>
					</div>
				</div>
				<!-- 	end body -->
			</div>
			<!-- 	end content -->
		</div>
		<!-- end dialog -->
		
		
		<div
			class="modal fade"
			id="detail-staff">
			<div class="modal-dialog ">
				<div class="modal-content ">
					<div class="modal-header my-modal-header">
						<h4>Thông tin chi tiết nhân viên .</h4>
						<span
							class="close-modal ion ion-close-round"
							data-dismiss="modal"
							aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div style="margin-top: 20px;">
							<table class="table table-striped">
			                    <tr>
			                      <td style="width: 150px;">Họ tên:</td>
			                      <td style="width: 240px;"><label class="fullName"></label></td>
			                      <td style="width: 150px;">Giới tính:</td>
			                      <td style="width: 200px;"><label class="gender"></label></td>
			                    </tr>
			                    <tr>
			                      <td style="width: 150px;">Ngày sinh:</td>
			                      <td style="width: 240px;"><label class="dob"></label></td>
			                      <td style="width: 150px;">Ngày vào cty:</td>
			                      <td style="width: 200px;"><label class="enrollmentDate"></label></td>
			                    </tr>
			                    <tr>
			                      <td style="width: 150px;">Địa chỉ:</td>
			                      <td style="width: 240px;"><label class="address"></label></td>
			                      <td style="width: 150px;">Email:</td>
			                      <td style="width: 200px;"><label class="email"></label></td>
			                    </tr>
			                   
			                     <tr>
			                      	<td style="width: 150px;">Vị trí:</td>
			                      	<td style="width: 240px;"><label class="position"></label></td>
									<td style="width: 150px;">Role:</td>
			                   	    <td style="width: 240px;"><label class="role"></label></td>	
			                    </tr>
			                     <tr>
			                      	<td style="width: 150px;">Được tạo ngày:</td>
			                      	<td style="width: 240px;"><label class="createAt"></label></td>
									<td style="width: 150px;">Cập nhật ngày:</td>
			                   	    <td style="width: 240px;"><label class="updateAt"></label></td>	
			                    </tr>
			                   
		                  	</table>
		                  	<button class="btn btn-primary" style="margin-left: 250px;">Cập nhật</button>	
						</div>
					</div>
				</div>
				<!-- 	end body -->
			</div>
			<!-- 	end content -->
		</div>
		<!-- end dialog -->
		
	</div>
	<div
		class="modal fade"
		id='create-success'>
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
						<h3 style="">Thêm nhân viên thành công!</h3>
						<a href="/user/manage-user">Về trang chủ</a> <br /> <i
							class="icon ion-checkmark-circled"
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
		$(document).ready(
				function() {
					var userId ='${userId}';
					var tblUser = $('#tblUser').DataTable(
							{
								"dom" : 'rtip', //Define the table control elements to appear on the page and in what order.
								"info" : true, // Showing 1 to 15 of 51 entries
								"lengthChange" : false, // selext box 10 20 50
								"displayLength" : 10, // number of row will be displayed default 10
								"processing" : true,
								"serverSide" : true,
								"deferLoading" : 10,
								"pagingType" : "simple_numbers", //customize First, Last, Next, Previous in paging.
								"language" : { // change text display at button in paging
									"paginate" : {
										"first" : "First",
										"previous": "Trước",
										"next" : "Sau"
									},
									"zeroRecords": "Không có report nào.",
									 "search":     "Tìm kiếm:",
									 "processing":  "Đang tải dữ liệu...",
									 "info":           "Tổng số: <b> _TOTAL_ </b> report",
									 "infoEmpty":      "Tổng số: <b> 0 </b>report.",
								},
								"ajax" : {
									"url" : "/user/admin/get-reports-of-user/"+ userId,
									"type" : "GET"
								},
								 "columnDefs" : [ {
									"targets" : 3,
									"data" : "id",
									"render" : function(id, type, full, meta) {
										return 	  '<button id = "btnDetail" class="btn  btn-action" data-id="'+ id +'"> detail  </button></a>'
												+ '<button id = "btnEdit"   class="btn  btn-action" data-id="'+ id +'"> edit  </button>'
												+ '<button id = "btnDelete" class="btn  btn-action" data-id="'+ id +'"> delete </button>';
									}
								},
								{
									"targets" : 2,
									"data" : "status",
									"render" : function(status, type, full, meta) {
										if (status == 'WAITING') {ion-checkmark
											return 	 status + '<small class="label pull-right bg-green">new</small>' ;	
										}else if (status == 'APPROVED') {
												return 	 status + '<i  class="icon pull-right ion-checkmark icon-approved"></i>'
											}
										
										return 	status + '<i  class="icon pull-right ion-close-round icon-rejected"></i>' ;
									}
								}
								], 
								
								"columns" : [ {
									"data" : "createAt",
									"width" : "50px"
								}, {
									"data" : "content",
									"width" : "240px"
								}, {
									"data" : "status",
									"width" : "50px"
								}, {
									"data" : "id",
									"width" : "70px"
								} ]

							});

					tblUser.ajax.url('/user/admin/get-reports-of-user/'+ userId).load();
					
				});
	</script>
</html>
