
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

                  <!-- <h4 style="text-align: center;"><label> DUYỆT CÁC BÁO CÁO CỦA NHÂN VIÊN </label></h4> -->
                  <br/>
               <a href="/manager/evaluation/create"><button class="btn btn-success" >Tạo bản đánh giá</button></a>
				<div style="width:250px;" class="pull-right ">
					<input class="form-control search-input" style="font-size: 16px;" type="search" placeholder="tìm kiếm tên nhân viên " >
				</div>
				
				
				<table id="tblEvaluation" class="display" style=" margin-top:30px ;" >
					<thead>
						<tr>
							<th>Nhân viên</th>
							<th>Ngày tạo</th>
							<th>Tổng điểm( /50)</th>
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
						<a href="/staff/evaluation">Quay lại</a> <br /> 
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
		id="pm-detail-evaluation">
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
							<p><span>Quản lý: </span> <label class="pmApprove"></label></p>
							<p><span>Ngày gửi: </span> <i class="createAt"></i></p>
							<p><span>Ngày sửa: </span> <i class="updateAt"></i></p>
							<p><span>Trạng thái: </span> <i class="status"></i></p>							
						</div>						
						<div class="col-xs-9">
							<div class="label-evaluation-content"> <label>Nội dung báo cáo:</label></div>
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
					
					$('.tag-test1').mytag({
						input_name:"hung",
						button_name:"Them"
					});
					$('.tag-test2').mytag({});
					
					/*  $('.wrapper-task-pm').slimScroll({
					        size: '5px',
					        wheelStep :10,
					        alwaysVisible :true,
					        touchScrollStep :100
					    });
					 */
					 
					 var evaluationStatus = "ALL";
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
						    data.evaluationStatus = evaluationStatus;
						}
						
					var userId ='${userId}';
					var tblEvaluation = $('#tblEvaluation').DataTable(
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
									"zeroRecords": "Không có evaluation nào.",
									 "search":     "Tìm kiếm:",
									 "processing":  "Đang tải dữ liệu...",
									 "info":           "Tổng số: <b> _TOTAL_ </b> evaluation",
									 "infoEmpty":      "Tổng số: <b> 0 </b>evaluation.",
								},
								"ajax" : {
									"url" : "/manager/get-all-evaluations",
									"type" : "POST",
									 "data": function(data){
										modifyParameter(data);
									} 
								},
								 "columnDefs" : [ {
									"targets" : 3,
									"data" : "id",
									"render" : function(id, type, full, meta) {
										var editEnable = '<a href="/manager/evaluation/'+ id +'/edit"  class="btn  btn-action" > Sửa  </a>';;
										var deleteEnable = '<button  class="btn  btn-action btnDelete" data-id="'+ id +'" > Xóa </button>';
										var finalButton =  '<a href="/manager/evaluation/'+ id +'/view"  class="btn  btn-action" > Xem  </a>';
										finalButton += editEnable + deleteEnable;
										return finalButton;
												
									}
								}								
								], 
								
								"columns" : [ {
									"data" :  function(row, type, full, meta) {
										return row.firstName + " "+row.midName+ " "+ row.lastName;
									},
									"name":"fullName",
									"width" : "60px"
								}, {
									"data" : "createAt",
									"name":"createAt",
									"width" : "50px"
								},{
									"data" :  function(row, type, full, meta) {
										/* var total= row.skill+ row.behavior+ row.taskCompletion + row.taskComplexity + row.workingDiscipline; */
										var total= row.totalPoint;
										if (total < 15) {
											return total +" (Kém)";
										} else if (total >= 15 && total < 30 ) {
											return total +" (Trung bình)";
										} else  if (total >= 30 && total < 45 ) {
											return total +" (Khá)";				
										}else{
											return total +" (Xuất sắc)";
										}
									},
									"name":"totalPoint",
									"width" : "50px"
								}
								,{
									"data" : "id",
									"width" : "60px",
									"orderable": false 
								} ]

							});

					tblEvaluation.ajax.url('/manager/get-all-evaluations').load();
					
					
					$('input.search-input').on( 'keyup', function () {
						searchbox = true;
						tblEvaluation.search(this.value).draw();
						console.log("searching");
					} );
					
					$('body').on("click", "button.btnDelete", function(event){
						event.preventDefault();
						var reportId = $(this).data("id");
						 var result = confirm("Bạn thực sự muốn XÓA ?");
						if (result) {
							waiting('show');
							$.ajax({
							    url: '/manager/evaluation/'+ reportId+'/delete',
							    type: 'DELETE',
							    success: function(result) {
							        if (result == 'success') {
							        	waiting('hide');
							        	showAlertBox("Xóa THÀNH CÔNG", "SUCCESS","/manager/evaluations");
									}else{
										showAlertBox("Xóa THẤT BẠI", "FAILED","/manager/evaluations");
										 
									
									}
							    }
							});
						}					
					});
					
					var showAlertBox = function(message, status, urlRedirect){
						$('#alert-box h3.alert-message').html(message);
						if (status == 'SUCCESS'){
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
