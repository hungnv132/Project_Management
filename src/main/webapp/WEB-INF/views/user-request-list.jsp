
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<body class="skin-blue sidebar-mini" id="request-page">
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
				<br />
				<c:if test="${requestSuccess == 'success' }">
				    <div class="alert my-alert-success"> 
						<button class="close" aria-hidden="true" data-dismiss="alert" type="button">x</button>
						 <i class="icon fa fa-check"></i> Cập nhật thông tin thành công
					</div>
				  </c:if>
				     
				<%--   <form action="/test/post" method="post">
						<div class="row">
							<div class="col-xs-3" style="width:140px !important;">Ngày yêu cầu:</div>
							<div class="col-xs-4">
								<div class='input-group date createAtPicker1'>
										<input type='text' class="form-control createAt" name="createAt" />
										 <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
								<div class="input-group date multidate">
							      <input type="text" class="form-control">
							      <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
							   </div>
								<input type="submit" value="Gửi yêu cầu" id="createSubmit" class="btn btn-primary" />
							</div>
						</div>
				</form> --%>
				  
				<button class="btn btn-success" id="add-new-request">Tạo yêu cầu</button>
				<div style="width:250px;" class="pull-right ">
					<input class="form-control search-input" style="font-size: 16px;" type="search" placeholder="tìm kiếm lý do " >
				</div>
				<table id="tblRequest" class="display">
					<thead>
						<tr>
							
							<th style="width:50px !important;">Ngày tạo</th>
							<th class="dropdown-th dropdown-th-status">
								<span class="dropdown my-dropdown">
									<div class="dropdown-toggle"  data-toggle="dropdown" style="width:100%; text-align: center; ">
										<span class="label-type" data-type="ALL">Yêu cầu</span> <i class="icon ion-chevron-down" style="margin-left: 8px;"></i>
									</div>
									<ul class="dropdown-menu dropdown-menu-type">
										<li role="presentation"><a 	 href="#"	role="menuitem"  data-type="ALL">Tất cả</a></li>
										<li role="presentation"><a 	 href="#"	role="menuitem"  data-type="ABSENCE">Xin nghỉ</a></li>
										<li role="presentation"><a   href="#"	role="menuitem"  data-type="LATE_WORKING">Đi làm muộn</a></li>
										<li role="presentation"><a   href="#"	role="menuitem"  data-type="OVERTIME" >Làm thêm giờ</a></li>
										
										
									</ul>
								</span>
							</th>
							<th>Lý do</th>
							<th class="dropdown-th dropdown-th-status">
								<span class="dropdown my-dropdown">
									<div class="dropdown-toggle"  data-toggle="dropdown" style="width:100%; text-align: center; ">
										<span class="label-status" data-status="ALL">Status</span> <i class="icon ion-chevron-down" style="margin-left: 8px;"></i>
									</div>
									<ul class="dropdown-menu dropdown-menu-status">
										<li role="presentation"><a 	 href="#"	role="menuitem"  data-status="ALL">Tất cả</a></li>
										<li role="presentation"><a 	 href="#"	role="menuitem"  data-status="APPROVED">APPROVED</a></li>
										<li role="presentation"><a   href="#"	role="menuitem"  data-status="REJECTED">REJECTED</a></li>
										<li role="presentation"><a   href="#"	role="menuitem"  data-status="WAITING" >WAITING</a></li>
										
										
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
	</div>
	
		<!-- ---------------- CREATE CREATE CREATE CREATE FORM-------------- -->
		<div
			class="modal fade"
			id='create-request'>
			<div class="modal-dialog ">
				<div class="modal-content ">
					<div class="modal-header my-modal-header">
						<h4>Yêu cầu.</h4>
						<span class="close-modal ion ion-close-round" data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<!-- /.box-body -->
						
						
					<form action="" method="post" id="createForm">
						<div class="row">
							<div class="col-xs-2" ></div>
							<div class="col-xs-2"><label>Gửi tới:</label></div>
							<div class="col-xs-5" >
								<select class="form-control sendTo" name="sendTo" >
									<c:forEach items="${managers}" var="manager">
										<option value="${manager.id}"><c:out value="${manager.fullName}" /></option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-2" ></div>
							<div class="col-xs-2" ><label>Yêu cầu:</label></div>
							<div class="col-xs-5">
								<select class="form-control requestType" name="requestType">
									<option value="ABSENCE">Xin nghỉ</option>
									<option value="LATE_WORKING">Đi làm muộn</option>									
									<option value="OVERTIME">Làm thêm giờ</option>									
								</select>
							</div>
						</div>
						<hr style="margin-bottom: 0px;"/>
						<div class="row" style="margin-top: 0px;">
							<div class="col-xs-12" >
								<div class="daysoff-wrapper">
									<div class="row">
										<div class="col-xs-4" >
											<div style="text-align: center; margin: 0px 0px 3px 0px;"><label>Chọn ngày:</label></div>
											<div class="multidate-picker pickedDate" style="border: 1px solid #dddddd; display: inline-block;" ></div>
											<div class="date-picker pickedDate" style="border: 1px solid #dddddd; display: inline-block;" ></div>
										</div>
										<div class="col-xs-8" style="position: relative;">
											<input type="hidden" class="form-control datepicker-input " name="pickedDate"/>
											<div class="selectedDate-box">
												 <div style=" margin: 0px 0px 5px 0px;">Ngày đã chọn:</div>
												<div class="daysoff-box"></div>
											</div>
											<div class="overtime-box">
											 	<div style=" margin: 0px 0px 3px 0px;"><label>Giờ làm thêm:</label></div>
												<span style="margin-right: 5px;">Bắt đầu lúc:</span> <input name="overtimeStart" class=" form-control overtimeStart overtime-start-input time-input" data-time-format="H:i" placeholder="chọn giờ" /> 
												<span style="margin:0px 5px 0px 5px;">kết thúc lúc:</span> <input name="overtimeEnd" class="form-control  overtimeEnd overtime-end-input time-input" data-time-format="H:i" placeholder="chọn giờ" />
											</div>
											<div class="lateworking-box">
											 	<div style=" margin: 0px 0px 3px 0px;"><label>Giờ đi muộn:</label></div>
												<span style="margin-right: 5px;">Bắt đầu lúc:</span> <input name="lateworkingStart" class="form-control lateworkingStart lateworking-time-input time-input" data-time-format="H:i" placeholder="chọn giờ" /> 
												
											</div>
											<div class="reason-box" style="position: absolute; top:120px; width: 90%; ">
												<p style="margin:0px 0px 5px 8px;"><label>Lý do:</label></p>
			                  					<textarea name="reason" class="reason form-control " id="reason" placeholder="Nêu lý do "></textarea>
											</div>
										</div>
									</div>		
								</div>
							</div>
							
						</div>
		                 <div class="row">
							<div class="col-xs-12" style="text-align: center;">
								<input type="button" value="Gửi yêu cầu" id="createSubmit" class="btn btn-primary createRequest" />
								<input type="button" value="Nhập lại" id="createReset" class="btn btn-primary createRequest" />
								
								<input type="button" value="Cập nhật " id="updateSubmit" class="btn btn-primary editRequest" />
							</div>
						</div>
						
						<div class="row" id="errorMessages">
							<div class="col-xs-3"></div>
							<div class="col-xs-9">
								<ul >
								</ul>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- 	end body -->
		</div>
		<!-- 	end content -->
	</div>
	<!-- end dialog -->
	
	<!-- ----------------DETAIL DETAIL DETAIL DETAIL  FORM ----------------->
	<div
			class="modal fade"
			id="detail-request">
			<div class="modal-dialog ">
				<div class="modal-content ">
					<div class="modal-header my-modal-header">
						<h4>Thông tin chi tiết yêu cầu.</h4>
						<span
							class="close-modal ion ion-close-round"
							data-dismiss="modal"
							aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div style="margin-top: 10px;">
							<table class="table table-striped">
			                    <tr>
			                      <td style="width: 150px;">Nhân viên:</td>
			                      <td style="width: 240px;"><label class="userRequest"></label></td>
			                      <td style="width: 150px;">Ngày tạo:</td>
			                      <td style="width: 200px;"><i class="createAt"></i></td>
			                    </tr>
			                    <tr>
			                      <td style="width: 150px;">Gửi tới:</td>
			                      <td style="width: 240px;"><i class="pmApprove"></i></td>
			                      <td style="width: 160px;">Ngày cập nhật:</td>
			                      <td style="width: 200px;"><i class="updateAt"></i></td>
			                    </tr>
			                    <tr>
			                      <td style="width: 150px;">Yêu cầu:</td>
			                      <td style="width: 240px;"><label class="requestType"></label></td>
			                      <td style="width: 150px;">Trạng thái:</td>
			                      <td style="width: 200px;"><label class="status"></label></td>
			                    </tr>
			                    <tr>
			                      <td style="width: 150px;" >Nhận xét:</td>
			                      <td style="width: 240px;" colspan="3">
										<b><i>"</i></b><span class="comment"> </span><b><i>"</i></b>
								 </td>
			                    </tr>
			                   
		                  	</table>
		                  	<div>
		                  		<p style="margin:20px 0px 5px 8px;"><label>Lý do: </label></p>
		                  		<div class="reason">
		                  		</div>
		                  		<div style="margin-top: 10px; "><label>Thời gian: </label><br/>
		                  		 <span class="time"></span></div>
		                  	</div>
		                  	<div style="margin-top: 5px; text-align: center; padding-top: 10px;" class="row">
						
								<button class="btn btn-primary btnEdit">Sửa</button>
								<button class="btn btn-primary btnDelete">Xóa</button>
							</div>
						</div>
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
		$(document).ready(
				function() {
					
					var modalEditRequest = false;
					$("#create-request #errorMessages").hide();
					/* ---------------- FOR CREATING------------------ */
				 	var  DatePicker = 		 $('#create-request .date-picker');
					var  MultiDatePicker  =  $('#create-request .multidate-picker');
					var  OverTimeBox  = 	 $('#create-request .overtime-box');
					var  LateWorkingBox  =	 $('#create-request .lateworking-box');
					var  SelectedDateBox  =	 $('#create-request .selectedDate-box');
					var  DatePickerInput  =  $('#create-request input.datepicker-input');
					var  DaysoffBox  =       $('#create-request div.daysoff-box');
					
					
					/* ----------- DATE PICKER CONFIG------------ */
					MultiDatePicker.datepicker({
						language: 'vi',
				        format: "dd-mm-yyyy",
				        todayBtn: true,
				        multidate: true,
				        todayHighlight: true
				    });
					
					DatePicker.datepicker({
						language: 'vi',
				        format: "dd-mm-yyyy",
				        todayBtn: true,
				        todayHighlight: true
				    });
					

					/* --------------------TIME PICKER CONFIG----------------- */
					
					$('.overtime-start-input').timepicker({
						'show2400': true,						
					});
					$('.overtime-end-input').timepicker({
						'show2400': true,						
					});
					$('.lateworking-time-input').timepicker({
						'show2400': true,
						'minTime': '06:00',
					    'maxTime': '18:00',
					});
					
					$('.time-input').on('showTimepicker', function(e){
						
						console.log("click");
					});
					
					/* ------------------------------------------ */
					
					 DatePicker.hide();
					 OverTimeBox.hide();
					 LateWorkingBox.hide();
					 LateWorkingBox.children('input').attr("disabled", "disabled");
					 OverTimeBox.children('input').attr("disabled", "disabled");
					 
					var showAbsence = function(){
						 DatePicker.hide();
						 MultiDatePicker.show();
						 MultiDatePicker.datepicker('clearDates');
						 SelectedDateBox.show();
						 OverTimeBox.hide();
						 LateWorkingBox.hide();
						 LateWorkingBox.children('input').attr("disabled", "disabled");
						 OverTimeBox.children('input').attr("disabled", "disabled");
					}
					var showLateWorking = function(){
						 DatePicker.show();
						 MultiDatePicker.hide();
						 SelectedDateBox.hide()
						 OverTimeBox.hide();
						 OverTimeBox.children('input').attr("disabled", "disabled");
						 LateWorkingBox.show();
						 LateWorkingBox.children('input').removeAttr("disabled");
						 
					}
					
					var showOvertime = function(){
						 DatePicker.show();
						 MultiDatePicker.hide();
						 SelectedDateBox.hide()
						 OverTimeBox.show();
						 OverTimeBox.children('input').removeAttr("disabled");
						 LateWorkingBox.hide();
						 LateWorkingBox.children('input').attr("disabled", "disabled");
						 
					}
					
					$('#create-request .requestType').on('change', function(){
						DatePickerInput.val("");
						var requestType = $(this).val();
						console.log(requestType);
						switch (requestType) {
							case "ABSENCE":
								console.log('showAbsence');
								showAbsence();
								break;
							case "LATE_WORKING":
								showLateWorking();
								break;
							case "OVERTIME":
								showOvertime();
								break;
							}
						
					});
					
					
					
					MultiDatePicker.datepicker().on('changeDate', function(e) {
						 DaysoffBox.empty();
				        DatePickerInput.val("");
				        var stringDate = "";
				        var dates = MultiDatePicker.datepicker('getDates');
				        for (var i = 0; i < dates.length; i++) {
				        	var selectedDate = dates[i].format('dd-mm-yyyy');
				        	 var badgeDate = '<span class="badge badge-daysoff" data-date="'+ selectedDate+'">'+ selectedDate+' <i class="ion-close-circled icon-remove-daysoff"></i></span>';
				        	 DaysoffBox.append(badgeDate);
						     stringDate = stringDate + selectedDate + " "; 
						} 
				        DatePickerInput.val( stringDate); 
				       
				    }); 
					 
					 DatePicker.datepicker().on('changeDate', function(e) {
				        $('div.daysoff-box').empty();
				        DatePickerInput.val("");
				        var d =DatePicker.datepicker('getDate');
				        DatePickerInput.val(d.format('dd-mm-yyyy'));
				       
				    });
					 
					
					$('#create-request div.daysoff-box').on('click', 'i.icon-remove-daysoff', function(){
						console.log('click');
						var date = $(this).parent().data("date")
						console.log(date);
						
					});
					
					/* ------------- show creating modal form ----------------- */
					$('#add-new-request').on('click', function() {
						modalEditRequest = false;
						$('.createRequest').show().removeAttr("disabled");
						$('.editRequest').hide();
						$('#create-request').modal({
							keyboard : true,
							show : true
						}); 
					});
					$("#create-request").on("hidden.bs.modal", function(){
						modalEditRequest = false;
						$("#create-request form").get(0).reset(); // get() method to convert Jquery elemtet to native javascript elemtent because Jquery has no reset() method
						$("#create-request .has-error").removeClass("has-error");
						$("#create-request #errorMessages").hide();
						DatePicker.hide();
						MultiDatePicker.show();
						OverTimeBox.hide();
						LateWorkingBox.hide();
						MultiDatePicker.datepicker('clearDates');
						DatePicker.datepicker('clearDates');
						SelectedDateBox.show();
					});
					
					$("#create-request #createReset").on("click", function(){
						/* event.preventDefault(); */
						console.log("reset");
						$("#create-request form").find("input:text , textarea").val("");
						MultiDatePicker.datepicker('clearDates');
						DatePicker.datepicker('clearDates');
					});
					
				
					
					/* ------------------------------------------------ */
					
					var requestStatus = "ALL";
					var requestType = "ALL";
					var searchbox = true;
					var requestTypeObject={"ABSENCE":"Xin nghỉ","LATE_WORKING":"Đi làm muộn","OVERTIME":"Làm thêm giờ"};
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
					    data.requestStatus = requestStatus;
					    data.requestType = requestType;
					}
					
					
					
					
					$('form#editForm div.errorArea').hide();
					
					var userId = '${userId}';
					var tblRequest = $('#tblRequest').DataTable(
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
										"previous" : "Trước",
										"next" : "Sau"
									},
									"zeroRecords" : "Không có request nào.",
									"search" : "Tìm kiếm:",
									"processing" : "Đang tải dữ liệu...",
									"info" : "Tổng số: <b> _TOTAL_ </b> request",
									"infoEmpty" : "Tổng số: <b> 0 </b>request.",
								},
								"ajax" : {
									"url" : "/staff/get-all-requests",
									"type" : "POST",
									"data":function(data){
										modifyParameter(data);
									}
								},
								"columnDefs" : [ 
									{
										"targets" : 2,
										"data" : "reason",
										"render" : function(reason, type, full, meta) {
											if (reason.length > 150) {
												return reason.substring(0, 100) + ' .....' ;
											}
											return reason;
										}
									},
									{
										"targets" : 3,
										"data" : "status",
										"render" : function(status, type, full, meta) {
											if (status == 'WAITING') {
												return 	 status + '<small class="label pull-right bg-green">new</small>' ;	
											}else if (status == 'APPROVED') {
													return 	 status + '<span class="my-icon pull-right"><i  class="icon  ion-checkmark icon-approved"></i><span>';
												}
											
											return 	status + '<span class="my-icon pull-right "><i  class="icon ion-close-round icon-rejected"></i></span>' ;
										}
									},
									{
										"targets" : 4,
										"render" : function(row, type, full, meta) {
											var editDisable = '<button id = "btnEdit"   class="btn  btn-action" data-id="'+ row.id +'" disabled> Sửa  </button>';
											var editEnable = '<button id = "btnEdit"   class="btn  btn-action" data-id="'+ row.id +'" > Sửa  </button>';
											var deleteDisable = '<button  class="btn  btn-action btnDelete" data-id="'+ row.id +'" disabled> Xóa </button>';
											var deleteEnable = '<button  class="btn  btn-action btnDelete" data-id="'+ row.id +'" > Xóa </button>';
											var finalButton = '<button id = "btnDetail" class="btn  btn-action" data-id="'+ row.id +'"> Xem  </button></a>';
											if(row.status != 'WAITING'){
												finalButton += editDisable + deleteDisable;
											}else{
												finalButton += editEnable + deleteEnable;
											}
											return finalButton;	
										}
									}
								],

								"columns" : [ {
									"data" : "createAt",
									"name" : "createAt",
									"width" : "20px"
								}, {
									"data" : "requestType.type",
									"width" : "100px",
									"orderable": false 
								}, {
									"data" : "reason",
									"width" : "200px",
									"orderable": false 
								}, {
									"data" : "status",
									"width" : "50px",
									"orderable": false 
								}, {
									"data": function ( row, type, val, meta ) {
										   return row;
										},
									"width" : "70px",
									"orderable": false 
								} ]

							});

					tblRequest.ajax.url('/staff/get-all-requests').load();
					
					
					$('input.search-input').on( 'keyup', function () {
						searchbox = true;
						tblRequest.search(this.value).draw();
						console.log("searching");
					} );
					
					$('#tblRequest').on('click', 'thead tr th ul.dropdown-menu-status li a', function(event) {
						event.preventDefault();
						var status= $(this).data("status");
						requestStatus =status;
						if (status == "ALL") {
							searchbox = false;
							$('#tblRequest thead tr th span.label-status').html("Tất cả");
						}else{
							$('#tblRequest thead tr th span.label-status').html(requestStatus);
							requestType ="ALL";
							$('#tblRequest thead tr th span.label-type').html("Yêu cầu");
						}
						$('input.search-user-input').val("");
						tblRequest.ajax.url('/staff/get-all-requests').load();
						console.log(requestStatus);
					});
					

					
					$('#tblRequest').on('click', 'thead tr th ul.dropdown-menu-type li a', function(event) {
						
						
						event.preventDefault();
						var type= $(this).data("type");
						requestType= type;
						if (type == "ALL") {
							searchbox = false;
							$('#tblRequest thead tr th span.label-type').html("Tất cả");
						}else{
							$('#tblRequest thead tr th span.label-type').html(requestTypeObject[type]);
							requestStatus ="ALL";
							$('#tblRequest thead tr th span.label-status').html("Status");
						}
						$('input.search-user-input').val("");
						tblRequest.ajax.url('/staff/get-all-requests').load();
						console.log(requestType);
					});
					
					/* ------------ DETAIL request----------- */
					
					$('#tblRequest').on('click', 'tbody tr #btnDetail', function(event) {
						
						event.preventDefault();
						var requestId = $(this).data("id");
						
						$('button.btnApprove').attr("data-id",requestId);
						$('button.btnReject').attr("data-id",requestId);
						
						$.get('/staff/detail-request/'+requestId , null, function(data) {
							console.log(data);
							$('#detail-request label.userRequest').html(data.userRequest);
							$('#detail-request i.createAt').html(data.createAt);
							$('#detail-request i.updateAt').html(data.updateAt);
							$('#detail-request i.pmApprove').html(data.pmApprove);
							$('#detail-request label.status').html(data.status);
							$('#detail-request label.requestType').html(requestTypeObject[data.requestType]);
							$('#detail-request div.reason').html(data.reason);
							$('#detail-request span.comment').html(data.comment);
							console.log(data.requestType);
							
							if (data.requestType == 'ABSENCE') {
								var stringDate ="";
								for ( var i in data.daysoff) {
									 stringDate =   stringDate + '<span class="badge badge-daysoff">'+ data.daysoff[i]+'</span>';
								}
								console.log(stringDate);
								$('#detail-request span.time').html("<u>Nghỉ các ngày</u>: "+ stringDate);
							} else if(data.requestType == 'LATE_WORKING'){
								$('#detail-request span.time').html("<i><u>Ngày</u> :</i> "+'<label>'+  data.date+'</label> <i> lúc</i> <label>'+ data.lateworkingStart+ '</label>');
							}else{
								$('#detail-request span.time').html("<i><u>Ngày</u> :</i> "+'<label>'+  data.date +'</label> <br/><i><u>Giờ</u> : Từ </i> <label>'+ data.overtimeStart+ '</label>'+'</label> <i> đến </i> <label>'+ data.overtimeEnd+ '</label>');
							}
						});
						 
						$('#detail-request').modal({
							keyboard : true,
							show : true
						});
					});

					$("#detail-request").on("hidden.bs.modal", function(){		
						
					});
					
					
					/* --------------- EDIT request form-------------- */
					
					$('.editRequest').hide();
					
					/* $('#create-request form').on('keyup', "input:text, select, textarea", function(){
						console.log("change");
						if(modalEditRequest == true){
							$('.editRequest').removeAttr("disabled");	
						}
						
					}); */
	
					$("#edit-request").on("hidden.bs.modal", function(){		
						$('form#editForm div.errorArea').hide();
						$('form#editForm .has-error').removeClass('has-error');
						$('form#editForm div.errorArea').hide();
						$('#updateSubmit').attr("disabled",true);
						$('#updateReset').attr("disabled",true);
						 $('#edit-request select option').each(function(index){
								 $(this).removeAttr("selected");
							
						 });
					});
					
					
					$('#tblRequest').on('click', 'tbody tr #btnEdit', function(event) {
						event.preventDefault();
						modalEditRequest = true;
						
						$('.editRequest').show();
						$('.createRequest').hide().attr("disabled",true);
						
						var requestId = $(this).data("id");
						$('#create-request input#updateSubmit').attr("data-id",requestId);
						$('#create-request button.btnDelete').attr("data-id",requestId);
						
						$.get('/staff/edit-request/'+requestId , null, function(data) {
							console.log(data);
							if (data.status == 'FAILED') {
								showAlertBox(data.message, data.status, '/staff/requests');
							}else{
								$('#create-request .requestType').val(data.returnedData.requestType);
								$('#create-request .sendTo').val(data.returnedData.sendTo);
								$('#create-request textarea.reason').val(data.returnedData.reason);
								var requestType = data.returnedData.requestType;
								
								switch (requestType) {
								case "ABSENCE":
									var dateArray = [];
								 	for ( var index in data.returnedData.daysoff) {
										dateArray.push(moment(data.returnedData.daysoff[index], "DD-MM-YYYY").toDate());
										console.log(moment(data.returnedData.daysoff[index], "DD-MM-YYYY").toDate());
									}			
								 	console.log(data.daysoff);
									showAbsence();
									MultiDatePicker.datepicker("setDates",dateArray);
									break;
								case "LATE_WORKING":
									/* console.log(moment(data.date, "DD-MM-YYYY").toDate());  */
									DatePicker.datepicker("setDate",(moment(data.returnedData.date, "DD-MM-YYYY").toDate())); 
									$('#create-request .lateworkingStart').val(data.returnedData.lateworkingStart);
									showLateWorking();
									break;
								case "OVERTIME":
									DatePicker.datepicker("setDate",moment(data.returnedData.date, "DD-MM-YYYY").toDate());
									$('#create-request .overtimeStart').val(data.returnedData.overtimeStart);
									$('#create-request .overtimeEnd').val(data.returnedData.overtimeEnd);
									showOvertime();
									break;
								}
								$('#create-request').modal({
									keyboard : true,
									show : true
								});
								
							}
						});
						
						
					});
					
					$('#create-request').on('shown.bs.modal', function () {
						console.log("show modal");
						$('#create-request textarea.reason').focus();
					});
					MultiDatePicker.on('changeDate', function(){
						$('.editRequest').removeAttr("disabled");
					});
					
					/*
					$('#edit-request table.edit-task-list').on('click','tbody.task p.cancel-task', function(){
						$(this).parent().parent().parent().remove();
						console.log("cancel");
						 numberOfEditTask =  $("#edit-request table.edit-task-list tbody.task").length;
						$('#edit-request table.edit-task-list tbody.task').each(function(index){
							console.log("index: "+  index);
							$(this).find($('textarea')).attr('id','content-'+ index);
							$(this).find($('textarea')).attr('name','tasks['+ index+'].content');
							$(this).find($('input')).attr('name','tasks['+ index+'].time');
							$(this).find($('input')).attr('id','time-'+ index);
							$(this).find($('.label-task label')).html('Task '+ (index +1) + ':');
						});
						
						
					}); */
				
					
					
					
					/* --------------end edit---------- */
					
					
					
					
					/* ---------Configure Datetime picker-------------- */
					$('#create-request .createAtPicker').datetimepicker({
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
					$('.createAtPicker1').datetimepicker({
						language : 'vi',
					    todayBtn: true,
					    multidate: true,
					    todayHighlight: true,
						format : "dd-mm-yyyy"
					});
				
					$('#edit-request .createAtPicker').datetimepicker({
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
				
					
				
					
					/* ----------------click CREATE NEW REQUEST--------------- */
					$('#createSubmit').click(function(evt) {
						console.log("click register button");
						evt.preventDefault();

						dataCreate = $('#createForm').serialize();
						$("#createSubmit").prop('value', 'Xin chờ...');
						$.ajax({
							url : '/staff/create-request', 							
							type : 'POST',
							data : dataCreate,
							dataType : 'json',
							success : function(response) {
								$('#create-request .has-error').removeClass('has-error');
								if (response.status == 'FAILED') {
									console.log(response.status);
									console.log(response.errorList);
									var list = response.errorList;
									var messages = "";
									for (var i = 0; i < list.length; i++) {
										var field = list[i].field;
										var errorTag = $('form#createForm .' + field);
										console.log(errorTag);
										errorTag.addClass('has-error');
										if((list[i].message).localeCompare("existedMessage") != 0){
											messages = messages + '<li>'+ list[i].message + '</li>';
										}
										console.log(messages);
									}
									/* $('#createForm label.label-error').html('Lỗi:'); */
									$('#createForm #errorMessages').show();
									$('#createForm #errorMessages ul').html(messages);
									$("#createSubmit").prop('value', 'Gửi yêu cầu');
								} else {
									
									console.log("success");
									$("#createSubmit").prop('value', 'Gửi yêu cầu');
									
									$('#new-staff').modal('hide');
									showAlertBox("Gửi THÀNH CÔNG", "success","/staff/requests");
								}
							},
							error : function(response) {
								console.log(response);
							}
						});
					});
					
					
				/* ------------------------CLICK UPDATE REPORT--------------------- */
					$('#updateSubmit').click(function(evt) {
						console.log("click update button");
						evt.preventDefault();
						var requestId = $('#updateSubmit').data("id");
						var dataUpdate= $('#create-request form').serialize();
						$("#editForm").prop('value', 'Xin chờ...');
						$.ajax({
							url : '/staff/update-request/'+ requestId ,
							type : 'POST',
							data : dataUpdate,
							dataType : 'json',
							success : function(response) {

								$('#create-request .has-error').removeClass('has-error');
								if (response.status == 'FAILED') {
									console.log("fail");
									var list = response.errorList;
									var messages = "";
									for (var i = 0; i < list.length; i++) {
										var field = list[i].field;
										var errorTag = $('#create-request .' + field);
										console.log(errorTag);
										errorTag.addClass('has-error');
										if((list[i].message).localeCompare("existedMessage") != 0){
											messages = messages + '<li>'+ list[i].message + '</li>';
										}
										console.log(messages);
									}
									/* $('#createForm label.label-error').html('Lỗi:'); */
									$('#create-request #errorMessages').show()
									$('#create-request #errorMessages ul').html(messages);
									$('form#editForm div.errorArea').show();
									$("#updateSubmit").prop('value', 'Cập nhật');
								} else {
									$('form#editForm div.errorArea').hide();
									console.log("success");
								 	$("#updateSubmit").prop('value', 'Cập nhật');
									$('#create-request').modal('hide');
									showAlertBox("Cập nhật THÀNH CÔNG", "success","/staff/requests");
								}
							},
							error : function(response) {
								console.log(response);
							}
						});
					});
				
/* ------------------DELETE DELETE DELETE DELETE REPORT-------------------- */
				$('body').on("click", "button.btnDelete", function(event){
					event.preventDefault();
					var requestId = $(this).data("id");
					 var result = confirm("Bạn thực sự muốn XÓA yêu cầu này?");
					if (result) {
						waiting('show');
						$.ajax({
						    url: '/staff/delete-request/'+ requestId,
						    type: 'DELETE',
						    success: function(result) {	
						    	waiting('hide');
						        if (result == 'success') {
						        	showAlertBox("Xóa THÀNH CÔNG", "success","/staff/requests");
								}else{
									showAlertBox("Xóa THẤT BẠI", "failed","/staff/requests");
								}
						    }
						});
					}					
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
