
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

                  <h4 style="text-align: center;"><label> DUYỆT CÁC BÁO CÁO CỦA NHÂN VIÊN </label></h4>
                  <br/>
                <div style="width:250px;" class="pull-right ">
					<input class="form-control search-user-input" style="font-size: 16px;" type="search" placeholder="tìm kiếm tên nhân viên" >
				</div>
                  <div class="pull-left" style="margin:5px 20px 10px 0px;">Hiện có 5 báo cáo mới cần duyệt</div>
                  <br/> 
				<table id="tblReport" class="display" style=" margin-top:30px ;" >
					<thead>
						<tr>
							<th>Nhân viên</th>
							<th>Ngày gửi</th>
							<th>Dự án</th>
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
		
		<!-- end dialog -->
		
		
		
		<!-- end dialog -->
		
	</div>
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
						<a href="/staff/report">Quay lại</a> <br /> 
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
	<div
		class="modal fade"
		id="pm-detail-report">
		<div class="modal-dialog ">
			<div class="modal-content ">
				<div class="modal-header my-modal-header">
					<h4>Thông tin chi tiết báo cáo .</h4>
					<span
						class="close-modal ion ion-close-round"
						data-dismiss="modal"
						aria-label="Close"></span>
				</div>
				<div class="modal-body">
					<div style="margin-top: 5px;" class="row">
						<div class="wrapper-staff col-xs-3" style="padding-top: 20px;">
							<p><span>Nhân viên: </span> <label class="staff"></label></p>
							<p><span>Ngày gửi: </span> <i class="createAt"></i></p>
							<p><span>Ngày sửa: </span> <i class="updateAt"></i></p>
							<p><span>Trạng thái: </span> <i class="status"></i></p>							
						</div>						
						<div class="col-xs-9">
							<div class=""><i>Dự án: </i> <label class="projectName"></label></div>
							<div class="label-report-content"> <i>Nội dung báo cáo:</i></div>
							<div class="wrapper-task-pm">
								<table class="table detail-task-list" style="margin-bottom: 5px;">
								</table>
								
							</div>
						<p style="margin:20px 0px 5px 8px;"> <label>Nhận xét:</label></p>
							<form>
				                  <textarea name="comment" class="comment" placeholder="Viết vài nhận xét về các công việc báo cáo" style="font-style:italic; width: 100%; height: 120px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
				          	  </form>
						</div>
						
					</div>
					<div style="margin-top: 5px; text-align: center; padding-top: 10px;" class="row">
						
						<button class="btn btn-primary btnApprove">Duyệt</button>
						<button class="btn btn-primary btnReject">Từ chối</button>
					</div>
				</div>
			</div>
			<!-- 	end body -->
		</div>
		<!-- 	end content -->
	</div>
	
	<script type="text/javascript">
		$(document).ready(
				function() {
					
					/*  $('.wrapper-task-pm').slimScroll({
					        size: '5px',
					        wheelStep :10,
					        alwaysVisible :true,
					        touchScrollStep :100
					    });
					 */
					 
					 var reportStatus = "ALL";
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
						    data.reportStatus = reportStatus;
						}
						
					var userId ='${userId}';
					var tblReport = $('#tblReport').DataTable(
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
									"url" : "/manager/get-all-reports",
									"type" : "POST",
									"data": function(data){
										modifyParameter(data);
									}
								},
								 "columnDefs" : [ {
									"targets" : 4,
									"data" : "id",
									"render" : function(id, type, full, meta) {
										return 	  '<button id = "btnDetail" class="btn  btn-action" data-id="'+ id +'"> Xem  </button></a>'
												+ '	<span class="dropdown">'
												+ '   <button class="dropdown-toggle btn  btn-action" data-toggle="dropdown" style="width:70px;">'
												+ '      Chọn <span class="caret"></span>'
												+ '   </button>'
												+ '   <ul class="dropdown-menu">'
												+ '     <li role="presentation"><a class="btnApprove" 	role="menuitem" tabindex="-1" href="#" data-id="'+ id +'" >Duyệt</a></li>'
												+ '     <li role="presentation"><a class="btnReject" 	role="menuitem" tabindex="-1" href="#" data-id="'+ id +'">Từ chối</a></li>'
												+ '     <li role="presentation"><a class="btnWait" 		role="menuitem" tabindex="-1" href="#" data-id="'+ id +'">Chờ tiếp</a></li>'
												+ '   </ul>'
												+ ' </span>';
												
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
										
										return 	status + '<span class="my-icon pull-right "><i  class="icon ion-close-round icon-rejected"></i></span>';
									}
								}
								], 
								
								"columns" : [ {
									"data" :  function(row, type, full, meta) {
										return row.staff.firstName + " "+row.staff.midName+ " "+ row.staff.lastName;
									},
									"name":"fullName",
									"width" : "60px"
								}, {
									"data" : "createAt",
									"name":"createAt",
									"width" : "50px"
								},{
									"data" : "projectName",
									"name":"content",
									"width" : "270px",
									"orderable": false 
								},{
									"data" : "status",
									"name":"status",
									"width" : "50px",
									"orderable": false 
								}, {
									"data" : "id",
									"width" : "60px",
									"orderable": false 
								} ]

							});

					tblReport.ajax.url('/manager/get-all-reports').load();
					
					
					$('input.search-user-input').on( 'keyup', function () {
						searchbox = true;
						tblReport.search(this.value).draw();
						console.log("searching");
					} );
					
					$('#tblReport').on('click', 'thead tr th ul.dropdown-menu-status li a', function(event) {
						event.preventDefault();
						var status= $(this).data("status");
						reportStatus =status;
						if (status == "ALL") {
							searchbox = false;
							$('#tblReport thead tr th span.label-status').html("Tất cả");
						}else{
							$('#tblReport thead tr th span.label-status').html(reportStatus);
						}
						$('input.search-user-input').val("");
						tblReport.ajax.url('/manager/get-all-reports').load();
						console.log(reportStatus);
					});
					
					
					/* ---------------VIEW DEATIL REPORT--------------- */
					$('#tblReport').on('click', 'tbody tr #btnDetail', function(event) {
						event.preventDefault();
						var reportId = $(this).data("id");
						console.log("detail");
						$('button.btnApprove').attr("data-id",reportId);
						$('button.btnReject').attr("data-id",reportId);
						var task = function(index, conent, timeStart, timeEnd){
							var num = index +1;
							return '<tr>'
										+' <td style="width: 10px;">Task '+ num+':</td>'
										+' <td style="width: 240px;">'
										+' <div class="content-task">'
										+		conent
										+' </div>'
										+' <i>Thời gian: </i> Từ <label class="timeStart" style="margin-top:10px;">'+ timeStart+'</label> đến <label class="timeEnd" style="margin-top:10px;">'+ timeEnd+'</label>'
										+' </td>'
								  + '</tr>';
						}
						$.get('/manager/view-report/'+reportId , null, function(data) {
							console.log(data);
							$('#pm-detail-report label.staff').html(data.userReport);
							$('#pm-detail-report i.createAt').html(data.createAt);
							$('#pm-detail-report i.updateAt').html(data.updateAt);
							$('#pm-detail-report label.projectName').html(data.projectName);
							$('#pm-detail-report i.status').html(data.status);
							$('#pm-detail-report textarea.comment').val(data.comment);
							for (var i = 0; i < data.tasks.length; i++) {
								$('table.detail-task-list').append(task(i, data.tasks[i].content, data.tasks[i].timeStart,data.tasks[i].timeEnd));
							}
							
						});
						
						$('#pm-detail-report').modal({
							keyboard : true,
							show : true
						});
					});

					$("#pm-detail-report").on("hidden.bs.modal", function(){		
						$('table.detail-task-list').empty();
					});
					
					
					/* ------------DUYỆT VÀ TỪ CHỐI REPORT--------------- */
					
					$('body').on('click', 'ul.dropdown-menu li a.btnApprove, button.btnApprove', function(event){
						console.log("confirm");
						event.preventDefault();
						var status =  "APPROVED";
						var id = $(this).data("id")	;
						var comment = $('body textarea.comment').val();
						changeStatus(id, status, comment);
						
					});
					
					$('body').on('click', 'ul.dropdown-menu li a.btnReject, button.btnReject', function(event){
						console.log("confirm");
						event.preventDefault();
						var status =  "REJECTED";
						var id = $(this).data("id")	;
						var comment = $('body textarea.comment').val();
						changeStatus(id, status, comment);
						
					});
					
					$('body').on('click', 'ul.dropdown-menu li a.btnWait', function(event){
						console.log("confirm");
						event.preventDefault();
						var status =  "WAITING";
						var id = $(this).data("id")	;
						var comment = $('body textarea.comment').val();
						changeStatus(id, status, comment);
						
					});
					
					var changeStatus = function(id, status, comment){
						var ok ;
						if (status =='REJECTED') {
							ok = confirm("Xác nhận TỪ CHỐI báo cáo?");
						}else{
							if(status =='APPROVED'){
							ok = confirm("Xác nhận DUYỆT báo cáo?");
							}else{
								ok = confirm("Xác nhận CHỜ báo cáo?");
							}
						}
						var dataSend = { 
								"id": id,
								"status": status,
								"comment":comment};
						if (ok ==true) {
							$('#pm-detail-report').modal('hide');
							waiting("show");
							$.ajax({
								url : '/manager/change-report-status',
								type : 'POST',
								data : dataSend,
								dataType : 'json',
								success : function(response) {
									console.log(response);
									 if (response.status == 'SUCCESS') {
										waiting("hide");
										showAlertBox("Thành công","success", "/manager/reports")
									} else {
										waiting("hide");
										/* showAlertBox("Có lỗi xảy ra","falied", "/manager/reports" )*/
									} 
								},
								error : function(response) {
									console.log(response);
									waiting("hide");
								 	/* showAlertBox("Có lỗi xảy ra","falied", "/manager/reports"); */
								}
							});
						}
					}
					
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
