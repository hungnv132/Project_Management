
<!DOCTYPE html>
<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="form"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib
	prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib
	prefix="spring"
	uri="http://www.springframework.org/tags"%>
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
        		<br/>
				<button class="btn btn-success" id="add-new-staff">Thêm nhân viên</button>
				<div style="width:250px;" class="pull-right ">
					
					<input class="form-control search-user-input" style="font-size: 16px;" type="search" placeholder="tìm kiếm tên nhân viên" >
					 
				</div>
				<br/>
				<br/>
				<table id="tblUser" class="display">
					<thead>
						<tr>
							<th>Họ và tên</th>
							<th>Email</th>
							<th>Ngày vào Cty</th>
							<th class="dropdown-th dropdown-th-position">
								<span class="dropdown my-dropdown">
									<div class="dropdown-toggle"  data-toggle="dropdown" style="width:100%; text-align: center; ">
										<span class="label-position" data-position="-1">Vị trí</span> <i class="icon ion-chevron-down" style="margin-left: 8px;"></i>
									</div>
									<ul class="dropdown-menu dropdown-menu-position">
										<li role="presentation"><a 	href="#"	role="menuitem"  data-position="-1">Tất cả</a></li>
										<c:forEach items="${positions}" var="position" >
											<li role="presentation"><a  href="#"	role="menuitem"  data-position="${position.id }">${position.name }</a></li>
										</c:forEach>
										
									</ul>
								</span>
							</th>
							<th class="dropdown-th">
								<span class="dropdown my-dropdown">
									<div class="dropdown-toggle"  data-toggle="dropdown" style="width:100%; text-align: center; ">
										<span class="label-role" data-role="ALL">Vai trò</span> <i class="icon ion-chevron-down" style="margin-left: 8px;"></i>
									</div>
									<ul class="dropdown-menu dropdown-menu-role">
										<li role="presentation"><a class="all_item"  	 href="#"	role="menuitem"  data-role="ALL">Tất cả</a></li>
										<c:forEach items="${roles}" var="role" >
											<li role="presentation"><a  href="#"	role="menuitem"  data-role="${role }">${role }</a></li>
										</c:forEach>
									</ul>
								</span>
							</th>
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
											class="form-control    "
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
											class="form-control    email"
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
											class="form-control    email"
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
										class="form-control   "
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
										class="form-control   "
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
								<div class="col-xs-3">Vị trí :</div>
								<div class="col-xs-5">
									<select class="form-control" name="positionId" id="positionId">
										<c:forEach items="${positions}" var="position" >
										<option value="${position.id }">${position.name }</option>
									</c:forEach>
									</select>
									
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3">Vai trò :</div>
								<div class="col-xs-5">
									<select class="form-control" name="role" id="role">
										<c:forEach items="${roles}" var="role" >
										<option value="${role}">${role}</option>
									</c:forEach>
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
						<h3 style="">Thành công!</h3>
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
	
	<div
		class="modal fade"
		id='edit-staff'>
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header my-modal-header">
						<h4>Thay đổi thông tin nhân viên.</h4>
						<span class="close-modal ion ion-close-round" data-dismiss="modal" aria-label="Close"></span>
					</div>
				<!-- end header -->
				<div class="modal-body">
					<form action="" method="post" id="editForm" commandName="form">
							<div class="row">
								<div class="col-xs-4">
									<div class="col-xs-3">Họ:</div>
									<div class="col-xs-9">
										<input type="hidden" class="form-control id "  name="id" />
										<input type="text" class="form-control firstName "  name="firstName" placeholder="Nhập họ" />
									</div>
								</div>
								<div class="col-xs-4">
									<div class="col-xs-3">Đệm:</div>
									<div class="col-xs-9">
										<input type="text" class="form-control midName  " name="midName" placeholder="Nhập đệm" />
									</div>
								</div>
								<div class="col-xs-4">
									<div class="col-xs-3">Tên:</div>
									<div class="col-xs-9">
										<input type="text" class="form-control  lastName " name="lastName"  placeholder="Nhập tên" />
											
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3">Ngày sinh:</div>
								<div class="col-xs-5">
									<div
										class='input-group date'
										id='dobPicker'>
										<input type='text' class="form-control dob" name="dob"  /> <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3">Giới tinh:</div>
								<div class="col-xs-5">
									<input type="radio" name="gender" class="male" value="Nam" > Nam 
									<input type="radio" name="gender" class="female" value="Nữ" style="margin-left: 15px;"> Nữ
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3">Email:</div>
								<div class="col-xs-5">
									<input type="text" class="form-control  email" name="email" placeholder="Nhập địa chỉ email" />
								</div>
							</div>
								<div class="row">
								<div class="col-xs-3">Address:</div>
								<div class="col-xs-5">
									<textarea rows="2" class="form-control  address"  name="address"	placeholder="Nhập nơi ở hiện tại" ></textarea>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3">Ngày vào Công ty:</div>
								<div class="col-xs-5">
									<div
										class='input-group date'
										id='enrollmentDatePicker'>
										<input type='text' class="form-control enrollmentDate" name="enrollmentDate" /> <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3">Vị trí hiện tại:</div>
								<div class="col-xs-5">
									<select class="form-control positionId" name="positionId">
										<c:forEach items="${positions}" var="position" >
											<option value="${position.id }">${position.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3">Role:</div>
								<div class="col-xs-5">
									<select class="form-control role" name="role" >
										<c:forEach items="${roles}" var="role" >
										<option value="${role}">${role}</option>
									</c:forEach>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3"></div>
								<div class="col-xs-5">
									<input type="submit" value="Cập nhật" id="updateSubmit" class="btn btn-primary" /> 
									<input type="button" value="Hủy" data-dismiss="modal" class="btn btn-primary" />
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3"></div>
								<div class="col-xs-9">
									<ul id="errorMessages">
									</ul>
								</div>
							</div>
						</form>
				</div>
				<!-- end body -->
			</div>
			<!-- end content -->
		</div>
		<!-- end dialog -->
	</div>
	
</body>
	<script type="text/javascript">
		$(document).ready(
				function() {
					var userRole = "ALL";
					var userPosition = -1;
					var searchbox = true;
					function modifyParameter(data) {
					    for (var i = 0; i < data.columns.length; i++) {
					        column = data.columns[i];
					        column.searchRegex = column.search.regex;
					        column.searchValue = column.search.value;
					        delete(column.search);
					    }
					    if(searchbox == false){
					    	data.search.value="";
					    }
					    data.role = userRole;
					    data.position = userPosition;
					}
					var tblUser = $('#tblUser').DataTable(
							{
								"dom" : 'rtip', //Define the table control elements to appear on the page and in what order.
								"info" : true, // Showing 1 to 15 of 51 entries
								"lengthChange" : true, // selext box 10 20 50
								"displayLength" : 10, // number of row will be displayed default 10
								"lengthMenu": [ 10, 25, 50],
								"processing" : true,
								"serverSide" : true,
								"deferLoading" : 10,
								"pagingType" : "simple_numbers", //customize First, Last, Next, Previous in paging.
								"language" : { // change text display at button in paging
									"paginate" : {
										"first" : "Frist",
										"previous": "Trước",
										"next" : "Sau"
									},
									"zeroRecords": "Không có nhân viên nào.",
									"search":     "Tìm kiếm:",
									"processing":  "Đang tải dữ liệu...",
									"info":           "Tổng số: <b> _TOTAL_ </b>nhân viên.",
									"infoEmpty":      "Tổng số: <b> 0 </b>nhân viên.",
									"lengthMenu":     "Hiển thị _MENU_ ",
								},
								"ajax" : {
									"url" : "get-all-user",
									"type" : "POST",
									"data": function(data){
										modifyParameter(data);
									}
								/* "dataSrc": function(json){
									json["data"] = json["data"];
									json["draw"] = json["totalPages"]; 
									json["recordsTotal"] = json["recordsTotal"];
									json["recordsFiltered"] = json["recordsTotal"];
									return json.data;
								} */

								},
								"columnDefs" : [
									{
										"targets" : 0,
										"render" : function(row, type, full, meta) {
											return row.firstName + " " + row.midName + " " + row.lastName;
										}
									},
								    {
									"targets" : 5,
									"data" : "id",
									"width" : "120px",
									"render" : function(id, type, full, meta) {
										return 	  '<button id = "btnDetail" class="btn  btn-action" data-id="'+ id +'"> Xem  </button></a>'
												+ '<button id = "btnEdit"   class="btn  btn-action" data-id="'+ id +'"> Sửa  </button>'
												+ '<button id = "btnDelete" class="btn  btn-action" data-id="'+ id +'"> Xóa </button>'
												+ '<div class="wrapper-action"> Chọn <i class="icon ion-chevron-down" style="margin-left:4px;"></i> '
												+  '<ul class="view-action">'
												+ '		<li><a href="/admin/reports/user/'+ id +'" > Báo cáo  </a> </li>'
												+ '		<li><a href="/admin/requests/'+ id +'"  > Yêu cầu  </a> </li>'
												+ '		<li><a href="/admin/evaluations/'+ id +'" > Đánh giá  </a> </li>'
												+  '</ul>'
												+ '</div>';
									}
								}
								],
								"columns" : [ 
								{
									"data": function ( row, type, val, meta ) {
										   return row;
										},
									"name":"fullName",
									"width" : "80px"
								}, {
									"data" : "email",
									"name":"email",
									"width" : "100px"
								}, {
									"data" : "enrollmentDate",
									"name":"enrollmentDate",
									"width" : "50px"
								}, {
									"data" : "position.name",
									"name":"position",
									"width" : "30px",
									"orderable": false 
								}, {
									"data" : "role",
									"name":"role",
									"width" : "30px",
									"orderable": false 
								}, {
									"data" : "id",
									"name":"id",
									"orderable": false 
								} ]

							});

					tblUser.ajax.url('get-all-user').load();
					
					/* tblUser.on( 'xhr', function () {
					    var data = tblUser.ajax.params();
					    console.log( data);
					} ); */
					
					$('input.search-user-input').on( 'keyup', function () {
						searchbox = true;
						tblUser.search(this.value).draw();
						console.log("searching");
					} );
					
					$('#tblUser').on('click', 'thead tr th ul.dropdown-menu-role li a', function(event) {
						event.preventDefault();
						var role= $(this).data("role");
						userRole =role;
						userPosition=-1;
						
						if (role == "ALL") {
							searchbox = false;
							$('#tblUser thead tr th span.label-role').html("Tất cả");
						}else{
							$('#tblUser thead tr th span.label-role').html(role);
						}
						
						$('#tblUser thead tr th span.label-position').html("Vị trí");
						$('input.search-user-input').val("");
						console.log(role);
						tblUser.ajax.url('get-all-user').load();
					});
					
					
					$('#tblUser').on('click', 'thead tr th ul.dropdown-menu-position li a', function(event) {
						event.preventDefault();
						var position= $(this).data("position");
						userRole ="ALL";
						userPosition = position;
						
						if (position == -1) {
							searchbox = false;
							$('#tblUser thead tr th span.label-position').html("Tất cả");
						}else{
							$('#tblUser thead tr th span.label-position').html($(this).text());
						}
						$('#tblUser thead tr th span.label-role').html("Vai trò");
						$('input.search-user-input').val("");
						console.log(position);
						tblUser.ajax.url('get-all-user').load(); 
					});
					$('#tblUser').on('click', 'tbody tr #btnDetail', function(event) {
						event.preventDefault();
						var userId = $(this).data("id");
						var requestData = {id: userId};
						$.get('/admin/detail-user/'+ userId, null, function(data) {
							console.log(data);
							$('#detail-staff label.fullName').html(data.firstName+" "+data.midName+ " "+ data.lastName);
							$('#detail-staff label.gender').html(data.gender);
							$('#detail-staff label.dob').html(data.dob);
							$('#detail-staff label.enrollmentDate').html(data.enrollmentDate);
							$('#detail-staff label.address').html(data.address);
							$('#detail-staff label.email').html(data.email);
							$('#detail-staff label.position').html(data.position.name);
							$('#detail-staff label.role').html(data.role);
							$('#detail-staff label.createAt').html(data.createAt);
							$('#detail-staff label.updateAt').html(data.updateAt);
							
							
						});
						
						$('#detail-staff').modal({
							keyboard : true,
							show : true
						});
					});

					$('#add-new-staff').on('click', function() {
						$('#new-staff').modal({
							keyboard : true,
							show : true
						});
						$('#tblUser thead tr th span.label-role').data("role","ff");
						  /* var lastRequestDatatable = tblUser.ajax.params();
						    console.log( data);  */
					});

					
					/* --------- EDIT EDIT EDIT EDIT EDIT ------------- */
						
					$('#tblUser').on('click', 'tbody tr #btnEdit', function(event){
						
						event.preventDefault();
						var userId = $(this).data("id");
						console.log("edit user");
						$.get('/admin/get-user/'+ userId, null, function(data){
							$('#editForm input.id').val(data.id);
							$('#editForm input.firstName').val(data.firstName);
							$('#editForm input.midName').val(data.midName);
							$('#editForm input.lastName').val(data.lastName);
							$('#editForm input.email').val(data.email);
							$('#editForm input.dob').val(data.dob);
							$('#editForm textarea.address').val(data.address);
							$('#editForm input.enrollmentDate').val(data.enrollmentDate);
							$('#editForm select.positionId').val(data.position.id);
							$('#editForm select.role').val(data.role);
							console.log(data.gender);
							if (data.gender == 'Nam') {
								console.log("nfdfName");
								$('#editForm input.male').prop("checked", true);
							}else{
								$('#editForm input.female').prop("checked",true);
							}
							
						});
						
						
						$('#edit-staff').modal({
							keyboard : true,
							show : true
						});
						
					});
					
					$("#edit-staff").on("hidden.bs.modal", function(){		
						$('#editForm input.male').prop("checked",false);
						$('#editForm input.female').prop("checked",false);
					});
					
					
					
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
					
					/* $('.dataTables_length select').addClass('form-control'); */
					
					/* ------------------create a new staff----------------- */
					$('#createForm').submit(function(evt) {
						console.log("click register button");
						evt.preventDefault();

						dataCreate = $('#createForm').serialize();
						$("#createSubmit").prop('value', 'Xin chờ...');
						$.ajax({
							url : '/admin/create-new-staff',
							type : 'POST',
							data : dataCreate,
							dataType : 'json',
							success : function(response) {
								/* $('#errorRegister').html(); */
								/* 	$('form#createForm input').css({
										border : '1px solid #cbcbcb'
									}); */
								$('form#createForm input').removeClass('has-error');
								$('form#createForm textarea').removeClass('has-error');
								if (response.status == 'FAILED') {
									console.log("fail");
									console.log(response.errorList);
									var list = response.errorList;
									var messages = "";
									for (var i = 0; i < list.length; i++) {
										var field = list[i].field;
										var errorTag = $('#' + field);
										/* errorTag.css({
											border : '1px solid red'
										}); */
										errorTag.addClass('has-error');
										messages = messages + '<li>'+ list[i].message + '</li>';
										console.log(messages);
									}
									/* $('#createForm label.label-error').html('Lỗi:'); */
									$('#errorMessages').html(messages);
									$("#createSubmit").prop('value', 'Tạo tài khoản');
								} else {
									$("#createSubmit").prop('value', 'Tạo tài khoản');
									console.log("success");
									$('#new-staff').modal('hide');
									$('#create-success').modal('show');
									setTimeout(function() {
										window.location = '/admin/manage-user';
									}, 500);
								}
							},
							error : function(response) {
								console.log(response);
							}
						});
					});
					/* ------------------admin update user ----------------- */
					$('#editForm').submit(function(evt) {
						console.log("click register button");
						evt.preventDefault();

						dataCreate = $('#editForm').serialize();
						$("#editForm").prop('value', 'Xin chờ...');
						$.ajax({
							url : '/admin/update-user',
							type : 'POST',
							data : dataCreate,
							dataType : 'json',
							success : function(response) {
								/* $('#errorRegister').html(); */
								/* 	$('form#createForm input').css({
										border : '1px solid #cbcbcb'
									}); */
								$('form#editForm .has-error').removeClass('has-error');								
								if (response.status == 'FAILED') {
									console.log("fail");
									console.log(response.errorList);
									var list = response.errorList;
									var messages = "";
									for (var i = 0; i < list.length; i++) {
										var field = list[i].field;
										var errorTag = $('#editForm .' + field);
										/* errorTag.css({
											border : '1px solid red'
										}); */
										errorTag.addClass('has-error');
										messages = messages + '<li>'+ list[i].message + '</li>';
										console.log(messages);
									}
									/* $('#createForm label.label-error').html('Lỗi:'); */
									$('#editForm #errorMessages').html(messages);
									$("#updateSubmit").prop('value', 'Cập nhật');
								} else {
									$("#createSubmit").prop('value', 'Cập nhật');
									console.log("success");
									$('#edit-staff').modal('hide');
									$('#create-success').modal('show');
									setTimeout(function() {
										window.location = '/admin/manage-user';
									}, 500);
								}
							},
							error : function(response) {
								console.log(response);
							}
						});
					});
				});
	</script>
</html>
