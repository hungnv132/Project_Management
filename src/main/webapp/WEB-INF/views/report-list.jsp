
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
<body class="skin-blue sidebar-mini" id="report-page">
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
				<c:if test="${reportSuccess == 'success' }">
				    <div class="alert my-alert-success"> 
						<button class="close" aria-hidden="true" data-dismiss="alert" type="button">x</button>
						 <i class="icon fa fa-check"></i> Cập nhật thông tin thành công
					</div>
				  </c:if>
				<button class="btn btn-success" id="add-new-report">Tạo báo cáo</button>
				<div style="width:250px;" class="pull-right ">
					<input class="form-control search-input" style="font-size: 16px;" type="search" placeholder="tìm kiếm dự án " >
				</div>
				<table id="tblReport" class="display">
					<thead>
						<tr>
							<th>Ngày tạo</th>
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
	</div>
	
		<!-- ---------------- CREATE CREATE CREATE CREATE FORM-------------- -->
		<div
			class="modal fade"
			id='create-report'>
			<div class="modal-dialog ">
				<div class="modal-content ">
					<div class="modal-header my-modal-header">
						<h4>Báo cáo công việc.</h4>
						<span
							class="close-modal ion ion-close-round"
							data-dismiss="modal"
							aria-label="Close"></span>
							
					</div>
					<div class="modal-body">
						<!-- /.box-body -->
						
						
					<form action="" method="post" id="createForm">
						<div class="row">
							<div class="col-xs-3" style="width:140px !important;">Ngày báo cáo:</div>
							<div class="col-xs-5">
								<div class='input-group date createAtPicker'>
										<input type='text' class="form-control createAt" name="createAt" id=""/>
										 <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
					<%-- 	<div class="row">
							<div class="col-xs-3" style="width:140px !important;">Người quản lý:</div>
							<div class="col-xs-5">
								<select class="form-control" name="pmId" id="pmId">
									<c:forEach items="${pm}" var="currentPM">
										<option value="${currentPM.id}"><c:out value="${currentPM.fullName}" /></option>
									</c:forEach>
								</select>
							</div>
						</div> --%>
						<div class="row">
							<div class="col-xs-3" style="width:140px !important;">Dự án:</div>
							<div class="col-xs-5">
								<select class="form-control" name="projectId" id="projectId">
									<c:forEach items="${projects}" var="project">
										<option value="${project.id}"><c:out value="${project.name}" /></option>
									</c:forEach>
								</select>
							</div>
						</div>
						<hr/>
						<p><label>Nội dung báo cáo:</label></p>
							
							<table class="table mytable create-task-list">
								<tbody class="task">
									<tr>
										<td rowspan="2" class="label-task"><label># 1</label></td>
										<td width="80px;"><i>Nội dung</i>: </td>
										<td>
											<textarea rows="3"  autofocus class="form-control content-0" name="tasks[0].content" placeholder="Nội dung công việc"></textarea>
										</td>
									</tr>
									<tr>
										<td><i>Thời gian</i>: </td>
										<td>
											<!-- <input type="text" class="form-control time-0" list="time"  name="tasks[0].time" placeholder="ví dụ: 2 giờ" ><br/> -->
											<span style="margin-right: 5px;">Bắt đầu: </span> <input name="tasks[0].timeStart" class=" form-control timeStart-0 time-start-input time-input" data-time-format="H:i" placeholder="chọn giờ" />
											<span style="margin:0px 5px 0px 5px;">kết thúc: </span> <input name="tasks[0].timeEnd" class="form-control  timeEnd-0 time-end-input time-input" data-time-format="H:i" placeholder="chọn giờ" />
											
										</td>
									</tr>
								</tbody>
							</table>
								
						<span class="new-task"><i class="ion-plus-circled"></i> Thêm</span>
						<div class="row">
							<div class="col-xs-4"></div>
							<div class="col-xs-5">
								<input type="submit" value="Gửi báo cáo" id="createSubmit" class="btn btn-primary" />
								<input type="reset" value="Nhập lại" id="createSubmit" class="btn btn-primary" />
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
			</div>
			<!-- 	end body -->
		</div>
		<!-- 	end content -->
	</div>
	<!-- end dialog -->
	
	<!-- ----------------DETAIL DETAIL DETAIL DETAIL  FORM ----------------->
	<div
		class="modal fade"
		id="detail-report">
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
					<div style="margin-top: 20px;">
						<table class="table table-striped" style="margin-bottom: 5px;">
							<tr>
								<td style="width: 150px;">Dự án:</td>
								<td style="width: 240px;" colspan ="3"><label class="projectName"></label></td>
								
							</tr>
							<tr>
								<td style="width: 150px;">Ngày báo cáo:</td>
								<td style="width: 240px;"><label class="createAt"></label></td>
								<td style="width: 150px;">Người duyệt:</td>
								<td style="width: 200px;"><label class="confirmBy"></label></td>
							</tr>
							<tr>
								<td style="width: 150px;">Ngày cập nhật:</td>
								<td style="width: 240px;"><label class="updateAt"></label></td>
								<td style="width: 150px;">Trạng thái:</td>
								<td style="width: 200px;"><label class="status"></label></td>
							</tr>
							<tr>
								<td style="width: 150px;">Nhận xét:</td>
								<td style="width: 240px;" colspan ="3"><i class="comment"></i></td>
								
							</tr>
						</table>
						<!-- <hr/ style="margin: 5px 0px;"> -->
						<p style="margin:10px 0px 5px 8px;"> <label>Nội dung báo cáo:</label></p>
						<table class="table detail-task-list" style="margin-bottom: 5px;">
						</table>
					
						<button class="btn btn-primary " data-dismiss="modal" style="margin-left: 250px;">Trờ về</button>
					</div>
				</div>
			</div>
			<!-- 	end body -->
		</div>
		<!-- 	end content -->
	</div>
	<!-- end dialog -->
	
	
	<!-- -----------------EDIT EDIT EDIT EDIT EDIT FORM---------------------->
	
	<div
			class="modal fade"
			id='edit-report'>
			<div class="modal-dialog ">
				<div class="modal-content ">
					<div class="modal-header my-modal-header">
						<h4>Thay đổi báo cáo.</h4>
						<span
							class="close-modal ion ion-close-round"
							data-dismiss="modal"
							aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<!-- /.box-body -->
					<form action="" method="post" id="editForm">
						<div class="row">
							<div class="col-xs-3" style="width:140px !important;">Ngày báo cáo:</div>
							<div class="col-xs-5">
								<div class='input-group date createAtPicker'>
										<input type='text' class="form-control createAt" name="createAt"  />
										 <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-3" style="width:140px !important;">Dự án:</div>
							<div class="col-xs-5">
								<select class="form-control projectId" name="projectId" >
									<c:forEach items="${projects}" var="project">
										<option value="${project.id}"><c:out value="${project.name}" /></option>
									</c:forEach>
								</select>
							</div>
						</div>
						<hr/>
						<p><label>Nội dung báo cáo:</label></p>
						<table class="table mytable edit-task-list">
						</table>
						<span class="new-task"><i class="ion-plus-circled"></i> Thêm</span>
						<div class="row">
							<div class="col-xs-4"></div>
							<div class="col-xs-5">
								<input type="submit" value="Cập nhật" id="updateSubmit" class="btn btn-primary" /> 
								<input id="updateReset" type="reset" value="Nhập lại" class="btn btn-primary" />
								<button  class="btn btn-primary btnDelete" >Xóa</button>
							</div>
						</div>
						<div class="row errorArea">
							<div class="col-xs-3"></div>
							<div class="col-xs-9">
								<ul id="errorMessages">
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
		
	<script type="text/javascript">
		$(document).ready(
				function() {
					
					

					/* --------------------TIME PICKER CONFIG----------------- */
					
					$('.time-start-input').timepicker({
						'show2400': true,						
					});
					$('.time-end-input').timepicker({
						'show2400': true,						
					});
					$('.time-input').on('showTimepicker', function(e){
						
						console.log("click");
					});
					
					
					
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
					
					
					$('form#editForm div.errorArea').hide();
					
					var userId = '${userId}';
					var tblReport = $('#tblReport').DataTable(
							{
								"dom" : 'rtip', //Define the table control elements to appear on the page and in what order.
								"info" : true, // Showing 1 to 15 of 51 entries
								"lengthChange" : false, // selext box 10 20 50
								"displayLength" : 10, // number of row will be displayed default 10
								"processing" : true,
								"ordering": true,
								"serverSide" : true,
								"deferLoading" : 10,
								"pagingType" : "simple_numbers", //customize First, Last, Next, Previous in paging.
								"language" : { // change text display at button in paging
									"paginate" : {
										"first" : "First",
										"previous" : "Trước",
										"next" : "Sau"
									},
									"zeroRecords" : "Không có report nào.",
									"search" : "Tìm kiếm:",
									"processing" : "Đang tải dữ liệu...",
									"info" : "Tổng số: <b> _TOTAL_ </b> report",
									"infoEmpty" : "Tổng số: <b> 0 </b>report.",
								},
								"ajax" : {
									"url" : "/staff/get-all-reports",
									"type" : "POST",
									"data":function(data){
										modifyParameter(data);
									}
								},
								"columnDefs" : [ 
									{
										"targets" : 1,
										/* "data" : "tasks[0].content", */
										"render" : function(content, type, full, meta) {
											if (content.length > 150) {
												return content.substring(0, 150) + ' .....' ;
											}
											return content;
										}
									},
									{
										"targets" : 2,
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
										"targets" : 3,
										/* "data" : "id", */
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
									"width" : "30px"
								}, {
									"data" : "projectName",
									"width" : "240px",
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

					tblReport.ajax.url('/staff/get-all-reports').load();
					
					
					$('input.search-input').on( 'keyup', function () {
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
						tblReport.ajax.url('/staff/get-all-reports').load();
						console.log(reportStatus);
					});
					
					
					/* ------------ DETAIL report----------- */
					
					$('#tblReport').on('click', 'tbody tr #btnDetail', function(event) {
						event.preventDefault();
						var reportId = $(this).data("id");
						var task = function(index, conent, timeStart, timeEnd){
							var num = index +1;
							return '<tr>'
										+' <td style="width: 10px;"># '+ num+':</td>'
										+' <td style="width: 240px;">'
										+' <div class="content-task">'
										+		conent
										+' </div>'
										+' <i>Thời gian: </i> Từ <label class="timeStart" style="margin-top:10px;">'+ timeStart+'</label> đến <label class="timeEnd" style="margin-top:10px;">'+ timeEnd+'</label>'
										+' </td>'
								  + '</tr>';
						}
						$.get('/staff/detail-report/'+reportId, null, function(data) {
							console.log(data);
							$('#detail-report label.projectName').html(data.projectName);
							$('#detail-report label.createAt').html(data.createAt);
							$('#detail-report label.updateAt').html(data.updateAt);
							$('#detail-report label.confirmBy').html(data.confirmBy);
							$('#detail-report label.status').html(data.status);
							$('#detail-report i.comment').html(data.comment);
							for (var i = 0; i < data.tasks.length; i++) {
								$('table.detail-task-list').append(task(i, data.tasks[i].content, data.tasks[i].timeStart,data.tasks[i].timeEnd));
							}
							
						});
						
						$('#detail-report').modal({
							keyboard : true,
							show : true
						});
					});

					$("#detail-report").on("hidden.bs.modal", function(){		
						$('table.detail-task-list').empty();
					});
					
					
					
					
					/* --------------- EDIT report form-------------- */
					var numberOfEditTask =0;
					var taskEdit = function(number, content, timeStart, timeEnd, id){
						var  index = number + 1;
						return '<tbody class="task">'
									+' 	<tr>'
									+' 		<td rowspan="2" class="label-task"><label># '+ index+':</label> <p class="cancel-task"><i class="ion-android-remove-circle"></i> Hủy</p></td>'
									+' 		<td width="80px;">Nội dung: </td>'
									+' 		<td>'
									+' 			<textarea rows="3" class="form-control content-'+ number+'"  name="tasks['+ number+'].content" placeholder="Nội dung công việc ">'+content+'</textarea>'
									+' 		</td>'
									+' 	</tr>'
									+' 	<tr>'
									+' 		<td>Thời gian: </td>'
									+' 		<td>'
									/* +' 			<input type="hidden" class="form-control " value="'+ id+'"  name="tasks['+ number+'].id">' */
									+'           <span style="margin-right: 5px;">Bắt đầu: </span> <input name="tasks['+ number+'].timeStart" value="'+ timeStart+'" class=" form-control timeStart-'+ number+' time-start-input time-input" data-time-format="H:i" placeholder="chọn giờ" />'
									+'           <span style="margin:0px 5px 0px 5px;">kết thúc: </span> <input name="tasks['+ number+'].timeEnd" value="'+ timeEnd+'" class="form-control  timeEnd-'+ number+' time-end-input time-input" data-time-format="H:i" placeholder="chọn giờ" />'
									+' 		</td>'
									+' 	</tr>'
									+' </tbody>';			
								
					}
					var newTaskEdit = function(number){
						var  index = number + 1;
						return '<tbody class="task">'
									+' 	<tr>'
									+' 		<td rowspan="2" class="label-task"><small style="margin-bottom:10px; display:block;"class="label bg-green ">new</small><label># '+ index+':</label> <p class="cancel-task"><i class="ion-android-remove-circle"></i> Hủy</p></td>'
									+' 		<td width="80px;">Nội dung: </td>'
									+' 		<td>'
									+' 			<textarea rows="3" class="form-control content-'+ number+'"  name="tasks['+ number+'].content" placeholder="Nội dung công việc "></textarea>'
									+' 		</td>'
									+' 	</tr>'
									+' 	<tr>'
									+' 		<td>Thời gian: </td>'
									+' 		<td>'
									/* +' 			<input type="hidden" class="form-control " value="-999"  name="tasks['+ number+'].id">' */
									+'          <span style="margin-right: 5px;">Bắt đầu: </span> <input name="tasks['+ number+'].timeStart" class=" form-control timeStart-'+ number+' time-start-input time-input" data-time-format="H:i" placeholder="chọn giờ" />'
									+'          <span style="margin:0px 5px 0px 5px;">kết thúc: </span> <input name="tasks['+ number+'].timeEnd" class="form-control  timeEnd-'+ number+' time-end-input time-input" data-time-format="H:i" placeholder="chọn giờ" />'
									+' 		</td>'
									+' 	</tr>'
									+' </tbody>';			
								
					}
					$('#tblReport').on('click', 'tbody tr #btnEdit', function(event) {
						event.preventDefault();
						var reportId = $(this).data("id");
						$('#edit-report input#updateSubmit').attr("data-id",reportId);
						$('#edit-report button.btnDelete').attr("data-id",reportId);
					
						$.get('/staff/edit-report/'+reportId, null, function(data) {
							console.log(data);
							if (data.status == 'FAILED') {
								showAlertBox(data.message, data.status, '/staff/report');
							}else{
								$('#edit-report input.createAt').val(data.returnedData.createAt);
								$('#edit-report select').val(data.returnedData.projectId);
								
								for (var i = 0; i < data.returnedData.tasks.length; i++) {
									$('#edit-report table.edit-task-list').append(taskEdit(i, data.returnedData.tasks[i].content, data.returnedData.tasks[i].timeStart, data.returnedData.tasks[i].timeEnd, data.returnedData.tasks[i].id));
									numberOfEditTask ++;
								}
								$('#edit-report').modal({
									keyboard : true,
									show : true
								});
								$('.time-start-input').timepicker({
									'show2400': true,						
								});
								$('.time-end-input').timepicker({
									'show2400': true,						
								});
							}
						});
					});
					
					
			
					$('#edit-report .new-task').on('click', function(){
						$('#updateSubmit').removeAttr("disabled");
						$('#updateReset').removeAttr("disabled");
						$('#edit-report table.edit-task-list').append(newTaskEdit(numberOfEditTask));
						numberOfEditTask ++;
						$('.time-start-input').timepicker({
							'show2400': true,						
						});
						$('.time-end-input').timepicker({
							'show2400': true,						
						});
					});
					
					
					$('#edit-report table.edit-task-list').on('click','tbody.task p.cancel-task', function(){
						
						console.log("cancel");
						numberOfEditTask =  $("#edit-report table.edit-task-list tbody.task").length;
						if( numberOfEditTask <= 1){
							alert("Tối thiểu phải có một công việc đê báo cáo");
						}else{
							$(this).parent().parent().parent().remove();
							$('#edit-report table.edit-task-list tbody.task').each(function(index){
								console.log("index: "+  index);
								$(this).find($('textarea')).attr('class','content-'+ index+' form-control');
								$(this).find($('textarea')).attr('name','tasks['+ index+'].content');
								$(this).find($('input.time-start-input')).attr('name','tasks['+ index+'].timeStart');
								$(this).find($('input.time-start-input')).attr('class','timeStart-'+ index+' form-control time-start-input time-input');
								$(this).find($('input.time-end-input')).attr('name','tasks['+ index+'].timeEnd');
								$(this).find($('input.time-end-input')).attr('class','timeEnd-'+ index +' form-control time-end-input time-input');
								$(this).find($('.label-task label')).html('# '+ (index +1) + ':');
							});
						}
						
						
					});
				
					
					$('#updateSubmit').attr("disabled",true);
					$('#updateReset').attr("disabled",true);
					
					$('form#editForm').on('keyup', ":input, select", function(){
						console.log("change");
						$('#updateSubmit').removeAttr("disabled");
						$('#updateReset').removeAttr("disabled");
					});
					$('form#editForm').on('change', "input, select", function(){
						console.log("select");
						$('#updateSubmit').removeAttr("disabled");
						$('#updateReset').removeAttr("disabled");
					});
					
	
					$("#edit-report").on("hidden.bs.modal", function(){		
						$('#edit-report table.edit-task-list').empty();
						$('form#editForm input').removeClass('has-error');
						$('form#editForm textarea').removeClass('has-error');
						$('form#editForm div.errorArea').hide();
						$('#updateSubmit').attr("disabled",true);
						$('#updateReset').attr("disabled",true);
						 $('#edit-report select option').each(function(index){
								 $(this).removeAttr("selected");
							
						 });
						 numberOfEditTask = 0;
					});
					
					
					/* --------------end edit---------- */
					
					/* ------------- CREATE NEW  report and tasks----------------- */
					
					$('#create-report').on('shown.bs.modal', function () {
						$('#create-report textarea.content-0').focus();
					});
					$('#add-new-report').on('click', function() {
						
						$('#create-report').modal({
							keyboard : true,
							show : true
						}); 
						
					
						  
					});
					
				
					/* ---------- add task for creating report------ */
					var taskCreate = function(number){
						var index = number + 1;
						return '<tbody class="task">'
								+' 	<tr>'
								+' 		<td rowspan="2" class="label-task"><label># '+ index+':</label> <p class="cancel-task"><i class="ion-android-remove-circle"></i> Hủy</p></td>'
								+' 		<td width="80px;"><i>Nội dung</i>: </td>'
								+' 		<td>'
								+' 			<textarea rows="3"  id="content-'+ number+'" class="form-control content-'+ number+'" name="tasks['+ number+'].content" placeholder="Nội dung công việc "></textarea>'
								+' 		</td>'
								+' 	</tr>'
								+' 	<tr>'
								+' 		<td><i>Thời gian</i>: </td>'
								+' 		<td>'
								+'           <span style="margin-right: 5px;">Bắt đầu: </span> <input id="timeStart-'+ number+'" name="tasks['+ number+'].timeStart" class=" form-control timeStart-'+ number+' time-start-input time-input" data-time-format="H:i" placeholder="chọn giờ" />'
								+'           <span style="margin:0px 5px 0px 5px;">kết thúc: </span> <input id="timeEnd-'+ number+'" name="tasks['+ number+'].timeEnd" class="form-control  timeEnd-'+ number+' time-end-input time-input" data-time-format="H:i" placeholder="chọn giờ" />'
								+' 		</td>'
								+' 	</tr>'
								+' </tbody>';			
					}
					var numberOfTask =  $("#create-report table.create-task-list tbody.task").length;
					$('#create-report .new-task').on('click', function(){
						$('#create-report table.create-task-list').append(taskCreate(numberOfTask));
						$('#create-report textarea.content-'+ numberOfTask).focus();
						numberOfTask = numberOfTask + 1;
						$('.time-start-input').timepicker({
							'show2400': true,						
						});
						$('.time-end-input').timepicker({
							'show2400': true,						
						});
					});
					
					$('#create-report table.create-task-list').on('click','tbody.task p.cancel-task', function(){
						$(this).parent().parent().parent().remove();
						console.log("cancel");
						 numberOfTask =  $("#create-report table.create-task-list tbody.task").length;
						 console.log(numberOfTask);
						$('#create-report table.create-task-list tbody.task').each(function(index){
							console.log("index: "+  index);
							$(this).find($('textarea')).attr('class','content-'+ index+' form-control');
							$(this).find($('textarea')).attr('name','tasks['+ index+'].content');
							$(this).find($('input.time-start-input')).attr('name','tasks['+ index+'].timeStart');
							$(this).find($('input.time-start-input')).attr('class','timeStart-'+ index+' form-control time-start-input time-input');
							$(this).find($('input.time-end-input')).attr('name','tasks['+ index+'].timeEnd');
							$(this).find($('input.time-end-input')).attr('class','timeEnd-'+ index +' form-control time-end-input time-input');
							$(this).find($('.label-task label')).html('# '+ (index +1) + ':');
						});
						
						
					});
					
					$("#create-report").on("hidden.bs.modal", function(){		
						$('#create-report table.create-task-list tbody.task:not(:first-child)').remove();
					    numberOfTask =  $('#create-report table.create-task-list tbody.task').length;
					});
					
					
					/* ----------------------- END CREATE -------------- */
					
					/* ---------Configure Datetime picker-------------- */
					$('#create-report .createAtPicker').datetimepicker({
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
					
					$('#edit-report .createAtPicker').datetimepicker({
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
					
					/* ----------------click CREATE NEW REPORT--------------- */
					$('#createForm').submit(function(evt) {
						console.log("click register button");
						evt.preventDefault();

						dataCreate = $('#createForm').serialize();
						$("#createSubmit").prop('value', 'Xin chờ...');
						$.ajax({
							url : '/staff/create-report',
							type : 'POST',
							data : dataCreate,
							dataType : 'json',
							success : function(response) {
								$('form#createForm input').removeClass('has-error');
								$('form#createForm textarea').removeClass('has-error');
								if (response.status == 'FAILED') {
									console.log("fail");
									var list = response.errorList;
									var messages = "";
									for (var i = 0; i < list.length; i++) {
										var field = list[i].field;
										var errorTag = $('form#createForm .' + field);
										/* errorTag.css({
											border : '1px solid red'
										}); */
										console.log(errorTag);
										errorTag.addClass('has-error');
										if((list[i].message).localeCompare("existedMessage") != 0){
											messages = messages + '<li>'+ list[i].message + '</li>';
										}
									}
									/* $('#createForm label.label-error').html('Lỗi:'); */
									$('#createForm #errorMessages').html(messages);
									$("#createSubmit").prop('value', 'Gửi báo cáo');
								} else {
									
									console.log("success");
									$("#createSubmit").prop('value', 'Gửi báo cáo');
									
									$('#new-staff').modal('hide');
									showAlertBox("Gửi THÀNH CÔNG", "SUCCESS","/staff/report");
								}
							},
							error : function(response) {
								console.log(response);
							}
						});
					});
					
					
				/* ------------------------CLICK UPDATE REPORT--------------------- */
					$('#editForm').submit(function(evt) {
						var reportId = $('#updateSubmit').data("id");
						console.log("click update button");
						evt.preventDefault();
						var dataUpdate= $('#editForm').serialize();
						$("#editForm").prop('value', 'Xin chờ...');
						$.ajax({
							url : '/staff/update-report/'+ reportId,
							type : 'POST',
							data : dataUpdate,
							dataType : 'json',
							success : function(response) {
								$('form#editForm .has-error').removeClass('has-error');
								if (response.status == 'FAILED') {
									console.log("fail");
									var list = response.errorList;
									var messages = "";
									for (var i = 0; i < list.length; i++) {
										var field = list[i].field;
										var errorTag = $('form#editForm .' + field);
										console.log(errorTag);
										errorTag.addClass('has-error');
										if((list[i].message).localeCompare("existedMessage") != 0){
											messages = messages + '<li>'+ list[i].message + '</li>';
										}
									}
									$('#edit-report #errorMessages').html(messages);
									$('form#editForm div.errorArea').show();
									$("#updateSubmit").prop('value', 'Cập nhật');
								} else {
									 $('form#editForm div.errorArea').hide();
									console.log("success");
								 	$("#updateSubmit").prop('value', 'Cập nhật');
								 	waiting('hide');
									$('#edit-report').modal('hide');
									showAlertBox("Cập nhật THÀNH CÔNG", "SUCCESS","/staff/report"); 
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
					var reportId = $(this).data("id");
					 var result = confirm("Bạn thực sự muốn XÓA báo cáo này?");
					if (result) {
						waiting('show');
						$.ajax({
						    url: '/staff/delete-report/'+ reportId,
						    type: 'DELETE',
						    success: function(result) {
						        if (result == 'success') {
						        	waiting('hide');
						        	showAlertBox("Xóa THÀNH CÔNG", "SUCCESS","/staff/report");
								}else{
									showAlertBox("Xóa THẤT BẠI", "FAILED","/staff/report");
									 
								
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
