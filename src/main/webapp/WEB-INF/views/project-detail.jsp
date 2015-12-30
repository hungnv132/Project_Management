
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
	
	
	<joda:format pattern="dd-MM-yyyy" value="${project.createAt}" var="createAt" />
	<joda:format pattern="dd-MM-yyyy" value="${project.startDate}" var="startDate" />
	<joda:format pattern="dd-MM-yyyy" value="${project.endDate}" var="endDate" />
 <style type="text/css">



    </style>
</head>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include page="/WEB-INF/templates/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/templates/menu-sidebar.jsp"></jsp:include>


		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			
			<section class="content">
				
				<div class="project-view  ">
					<div class="row">
						<div class="col-xs-6">
							
							<div class=" project-field name-box">
								<span class="value">${project.name }</span>
							</div>
							<div class=" project-field status-box">
								<span class="plabel">Trạng thái:</span>	
								<span class="value editable " >
									<span>${project.status }</span>
								</span>
							</div>
							<div class=" project-field createAt-box">
								<span class="plabel">Ngày tạo dự án:</span>	
								<span class="value editable edit-date">${createAt }</span>
							</div>
							<div class=" project-field budget-box">
								<span class="plabel">Ngân sách:</span>	
								<span class="value 	editable">
									<span><fmt:formatNumber value="${project.budget }" type="currency"/><span>
								</span>
							</div>
							<div class=" project-field member-box">
								<span class="plabel">Số lượng thành viên:</span>	
								<span class="value">${project.numOfMem }</span>
								<span class="unit">( người )</span>
							</div>
							<div class=" project-field technology-box">
								<span class="plabel">Công nghệ sử dụng:</span>	
								<span class="value">
									${project.technology }
									
								</span>
							</div>
						</div>
						<!-- <div class=" col-xs-2  volume-box " style="width: 150px; padding-top: 50px;">
				               	<input type="text" class="knob current-volumne" value="30" data-thickness=.25 data-width="120" data-height="120" data-fgColor="#3c8dbc"/>
	                      		<label style="text-align: center;">Khối lượng công việc hoàn thành</label>
				         </div>
				          <div class=" col-xs-2 col-xs-offset-1 budget-box " style="width: 150px;padding-top: 50px;">
				               	<input type="text" class="knob current-budget" value="60" data-width="120" data-thickness=.25 data-height="120" data-fgColor="#3c8dbc" style="margin-top: 300px !important;"/>
	                      		<label style="text-align: center;">Ngân sách đã dùng</label>
				           </div> -->
						     <div class=" col-xs-1">
				               	<button class="btn btn-success" id="btn-edit-project" data-projectid="${project.id }">Thay đổi</button>
				           </div>
							 
					
					</div>
					
					<hr />
      				<h4 class="field-title">Thời gian của dự án</h4>
					<div class=" project-field process-box ">
						<span class="timeline-plan ">
							<i class="icon ion-record start"></i>
							<i class="icon ion-disc end"></i>
							<span class=" date-point start-date">${startDate }</span>
							<span class=" date-point end-date">${endDate} </span>
							<span class="timeline-now" style="width:${percentDay}%;">
								<i class="icon ion-arrow-down-a now"></i>
								<span class=" date-point date-now-title">Hôm nay</span>
							</span>
						</span>
							
			                  <br/>
							<hr />
		      				<h4 class="field-title" style="display: inline-block;">Nhiệm vụ chính trong dự án</h4>
		      				<button type="button" class="btn btn-create" style="display: inline-block;" data-projectid="${project.id }">Thêm nhiệm vụ</button>
		      				<br/><br/>
			                 <div class="dd" id="nestable">
					            <ol class="dd-list list-1" data-id="1">
					            <c:forEach items="${project.business }" var="business">
					             	<li class="dd-item" data-id="${business.id }"  data-projectid="${project.id }">
					                    <div class="dd-handle">
					                    	<span class="dd-field business-name">
													<span >
														${business.name }
													</span>
											</span>
					                    </div>
					                </li> 
					               </c:forEach>
							    </ol>
		        			</div> 
			                 
						
					</div>
      				<br class="clear"/>
      				<!-- <hr />
      				<h4 class="field-title">Thành viên của dự án</h4>
					<div class="member-box ">
						<table id="tblMember" class="display" >
							<thead>
								<tr>
									<th>Vai trò</th>
									<th>Thành viên</th>
									<th>Ngày tham gia</th>
									<th>Trạng thái</th>
								</tr>
							</thead>
						</table>
					</div> -->
					
				
      				
				</div>
				<br class="clear" />
			</section>
			<!-- /.content -->
		</div>
		
	<!-- /.content-wrapper -->

		<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>
	</div>
	
	<div class="modal fade" id="edit-project-modal">
			<div class="modal-dialog " style="width: 800px;">
				<div class="modal-content ">
					<div class="modal-header my-modal-header">
						<h4>Thay đổi thông tin dự án</h4>
						<span class="close-modal ion ion-close-round" data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
					<form>
						<div class="row field-box">
							<div class="col-xs-2 label-box">Tên dự án</div>	
							<div class="col-xs-6">
								<input type="text" class="form-control name nullable " name="name" />
								<input type="hidden" class="form-control id nullable " name="id" />
							</div>
						</div>
						<div class="row field-box">
							<div class="col-xs-2 label-box">Trạng thái</div>	
							<div class="col-xs-4">
								<select class="form-control status" name="status">
									<option value="RUNNING"> RUNNING</option>
									<option value="FINISHED"> FINISHED</option>
									<option value="FAILED"> FAILED</option>
								</select>
							</div>
						</div>
						<div class="row field-box">
							<div class="col-xs-2 label-box">Ngày tạo</div>	
							<div class="col-xs-4">
							    <div class="input-group date datepicker ">
									<input type="text" class="form-control createAt" name="createAt" /> 
									<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
						<div class="row field-box">
							<div class="col-xs-2 label-box">Ngân sách:</div>	
							<div class="col-xs-4">
								<input type="text" class="form-control budget nullable " name="budget" /> (VND)
							</div>
						</div>
						<div class="row field-box">
							<div class="col-xs-2 label-box">Số thành viên:</div>	
							<div class="col-xs-4">
								<input type="text" class="form-control numOfMem nullable " name="numOfMem" />
							</div>
						</div>
						<div class="row field-box">
							<div class="col-xs-2 label-box">Công nghệ:</div>	
							<div class="col-xs-4">
								<input type="text" class="form-control technology nullable " name="technology" />
							</div>
						</div>
						<div class="row field-box">
							<div class="col-xs-2 label-box">Bắt đầu:</div>	
							<div class="col-xs-4">
								 <div class="input-group date datepicker ">
									<input type="text" class="form-control startDate" name="startDate" /> 
									<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
							<div class="col-xs-2 label-box">Kết thúc:</div>
							<div class="col-xs-4">
								 <div class="input-group date datepicker ">
									<input type="text" class="form-control endDate" name="endDate" /> 
									<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-xs-2  label-box"><label>Mô tả:</label></div>	
							<div  class="col-xs-10">
								<textarea class="form-control description nullable" rows = "3" name="description" ></textarea>
							</div>
						</div>
						
						<hr/>
						<div class="row">
							<div class="col-xs-4 col-xs-offset-4" >
								<button class="btn btn-success" id="btn-update-project">Cập nhật</button>
								<button class="btn btn-success cancel"  data-dismiss="modal">Hủy</button>
							 </div>
						</div>
						<div class="row">
							<div class="col-xs-4 col-xs-offset-4" >
							<ul id="errorMessages">
							</ul>
							</div>
						</div>
					</form>
				</div>
			<!-- 	end body -->
		</div>
		<!-- 	end content -->
	</div>
	</div>
	
	<div class="modal fade" id="create-business">
		
			<div class="modal-dialog " style="width: 800px;">
				<div class="modal-content ">
				<form id="create-business-form">
					<div class="modal-header my-modal-header">
						<h4>Chi tiết nhiệm vụ</h4>
						<span class="close-modal ion ion-close-round" data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="row field-box">
							<div class="col-xs-2 label-box">Tên nhiệm vụ</div>	
							<div class="col-xs-5">
								<input type="text" class="form-control businessName nullable " name="businessName" />
								<input type="hidden" class="form-control projectId nullable " name="projectId" value=${project.id } />
							</div>
						</div>
					
						<div class="row">
							<div class="col-xs-2  label-box"><label>Mô tả:</label></div>	
							<div  class="col-xs-10">
								<textarea class="form-control businessDesc nullable" rows = "3" name="businessDesc" ></textarea>
							</div>
						</div>
						
						<hr/>
						<div class="row adding-member"  style="padding:0px 5px;">
							<div class="col-xs-6 avalible-member-list">
								<h4 style="text-align: center;">Danh sách nhân viên</h4>
								<table id="tblAvaliableMember" >
									<thead>
										<tr>
											<th></th>
											<th>Nhân viên</th>
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
										</tr>
									</thead>
								</table>
							 </div>
							<div class="col-xs-1 add-remove">
								<button class=" btn add">Thêm</button>
								<button class=" btn remove">Xóa</button>
							</div>
							<div class="col-xs-5 selected-member-list members"> 
									<h4 style="text-align: center;">Các nhân viên đã chọn</h4>
									<div class="wrapper-table ">
										<table class="table">
											<thead>
												<tr>
													<td style="width: 20px;"></td>
													<td style="width: 110px;"><label>Nhân viên</label></td	>
													<td style="width: 80px;"> <label>Vị trí</label></td>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
							</div>
						</div>		
						<div class="row">
							<div class="col-xs-4 col-xs-offset-5" >
								<button class="btn btn-success" id="btn-update-business" data-projectid="${project.id }">Cập nhật</button>
								<button class="btn btn-success" id="btn-create-business" data-projectid="${project.id }">Tạo</button>
								<button class="btn btn-success cancel"  data-dismiss="modal">Hủy</button>
							 </div>
						</div>
							<div class="row">
							<div class="col-xs-7 col-xs-offset-4" >
								<ul id="errorMessages">
								</ul>
							 </div>
						</div>
					
				</div>
			<!-- 	end body -->
			</form>
		</div>
		<!-- 	end content -->
	</div>
	
	</div>
	
	
	
	<div class="modal" id='waiting'>
		<div class="modal-dialog ">
			<i class="ion ion-loading-a"></i>
		</div>
		<!-- end dialog -->
	</div>
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
			
			/* ------------------DATE PICKER----------------------- */
			
			$('.datepicker').datepicker({
				language: 'vi',
		        format: "dd-mm-yyyy",
		        todayBtn: true,
		        todayHighlight: true,
		        autoclose: true
		        /* orientation: "bottom left" */
			});
			
			/* -------------------------KNOB------------------------ */
			$('.knob').knob({
				format : function (value) {
				     return value + '%';
				  },
				 draw : function(){
					
				 },
				 readOnly:true 
			});
			
			$('.current-volumne').knob({
				
			});
			
			/* ----------------------- NESTABLE ----------------------- */
			$('#nestable').nestable({ 
				enableDrag: false
			});
			$('#nestable').nestable('collapseAll');
			
			
			var add_nestable_tool = function(){
				$('#nestable .dd-handle').append(
		    			'<div class="action">'
			        	+'	<button class="view-item" >Xem</button>'
			        	+'	<button class="delete-item">Xóa</button>'
			        	+' </div>'
		    	);
					
			}
			add_nestable_tool();
			 $('#nestable-menu').on('click', function(e)
					 {
			        var target = $(e.target),
			            action = target.data('action');
			        if (action === 'expand-all') {
			            $('#nestable').nestable('expandAll');
			        }
			        if (action === 'collapse-all') {
			            $('#nestable').nestable('collapseAll');
			        }
			    });

			$('.dd-item').append('<span class="point-line"></span>');
			 
			$('#nestable-menu button.btn-create').on('click', function(){
				$('#create-business').modal({
					show:true
				})
			});
			
			 
			/* ---------------DROP ZONE---------------- */
			
			$("div.document-box").dropzone({ url: "/file/post" });
			
			
			/* -----------------EDITABLE------------------ */
			
			$('.editable').append('<i class="icon ion-edit"></i>')
			$('input.form-input-short').hide();
			$('i.ion-edit').hide();
			$('.editable').hover( 
				function(){
					$(this).children("i.ion-edit").show();
				},function(){
					$(this).children("i.ion-edit").hide();
			});
			
			$('.editable').on("click", "i.ion-edit", function(){
				$(this).hide();
				$(this).siblings("span").hide();				
				$(this).siblings("input.form-input-short").show().focus();
				
			});
			
			$('input.form-input-short').on("blur",function(){
				$(this).hide();
				$(this).siblings("span").show().text($(this).val());
				$(this).siblings("i.ion-edit").hide();
			});
			
			$('input.form-input-short').keypress(function (e) {
				var key = e.which;
				if(key == 13){  // the enter key code
					e.preventDefault();
					$(this).hide();
					$(this).siblings("span").show().text($(this).val());
					$(this).siblings("i.ion-edit").hide();
				}
			});
			$('.project-list >ul').slimScroll({
			    height: '500px'
			    /* alwaysVisible: true */
			});
			$('.project-list .slimScrollDiv').css({"padding-bottom":"10px"});
		
			
	/* -------------------- CREATE BUSSINES  and ADD MEMBER --------------------- */
			
			$('#create-business #btn-update-business').hide();
			
			var memberArray= new Array();
			var business_item_array = new Array();
			var countBusiness = 0;
			var itemId; 
			var userPosition = -1;
			function modifyParameter(data) {
			    for (var i = 0; i < data.columns.length; i++) {
			        column = data.columns[i];
			        column.searchRegex = column.search.regex;
			        column.searchValue = column.search.value;
			        delete(column.search);
			    }
			    data.position = userPosition;
			}
			
			var tblAvaliableMember_cre_bus= $('#create-business  #tblAvaliableMember').DataTable(
					{
						"dom" : 'tp', //Define the table control elements to appear on the page and in what order.
						"info" : false, // Showing 1 to 15 of 51 entries
						"lengthChange" : false, // selext box 10 20 50
						"displayLength" : 7, // number of row will be displayed default 10
						"processing" : true,
						"serverSide" : true,
						"deferLoading" : 7,
						"pagingType" : "simple", //customize First, Last, Next, Previous in paging.
						"language" : { // change text display at button in paging
							"paginate" : {
								"first" : "First",
								"previous": "Trước",
								"next" : "Sau"
							},
							"zeroRecords": "Không có nhân viên nào.",
							 "search":     "Tìm kiếm:",
							 "processing":  "Đang tải dữ liệu...",
						},
						"ajax" : {
							"url" : "/manager/get-all-available-members",
							"type" : "POST",
							"data": function(data){
								modifyParameter(data);
							}
						},
						 "columnDefs" : [ 
						
						], 
						
						"columns" : [
						{
							"data" :  function(row, type, full, meta) {
								var checkbox = "";
								if(!containMember(row.id, memberArray)){
									checkbox = '<input type="checkbox" class="cbx-staff"  data-id="'+ row.id+'"/>';	
								}else{
									checkbox = '<input type="checkbox" disabled="disabled" checked="checked" class="cbx-staff"  data-id="'+ row.id+'"/>';
								}	
								return checkbox;
							},
							"name":"id",
							"width" : "5px",
							"orderable": false
						}, 
						{
							"data" :  function(row, type, full, meta) {
								return row.firstName + " "+row.midName+ " "+ row.lastName;
							},
							"name":"fullName",
							"width" : "120px",
							"orderable": false 
						},{
							"data" : "position",
							"name":"position",
							"width" : "80px",
							"orderable": false 
						} ]

					});
			
			

			$('#create-business  #tblAvaliableMember').on('click', 'thead tr th ul.dropdown-menu-position li a', function(event) {
				event.preventDefault();
				var position= $(this).data("position");
				userPosition = position;
				
				if (position == -1) {
					searchbox = false;
					$('#create-business  #tblAvaliableMember thead tr th span.label-position').html("Tất cả");
				}else{
					$('#create-business  #tblAvaliableMember thead tr th span.label-position').html($(this).text());
				}
				$('input.search-user-input').val("");
				console.log(position);
				tblAvaliableMember_cre_bus.ajax.url('/manager/get-all-available-members').load(); 
			});
			
			
			
			$('button.btn-create').on('click', function(){
				$('#create-business #btn-update-business').hide();
				$('#create-business #btn-create-business').show();
				$('#create-business').modal({
					show:true
				});
				tblAvaliableMember_cre_bus.ajax.url('/manager/get-all-available-members').load();
			});
				
			$('#create-business .selected-member-list .wrapper-table').slimScroll({
			    height: '350px'
			    /* alwaysVisible: true */
			});
			
			$('#create-business ').on('click', 'button.add', function(event){
				event.preventDefault();
				var countMember = $('#create-business  .selected-member-list .wrapper-table table tbody tr').length;
				$('#create-business  #tblAvaliableMember .cbx-staff:checked').not(':disabled').each(function(index){
					var newCountMember = countMember +1;
					var id = $(this).data("id");
					if (!containMember(id, memberArray)) {
						var name = $(this).parent().next().text();
						var position = $(this).parent().next().next().text();
						var member = {
							id: id,
							name: name,
							position: position
						};
						memberArray.push(member);
						$('#create-business  .selected-member-list .wrapper-table table tbody').append(
								'<tr>'
								+'	<td><input type="checkbox" checked="checked" class="cbx-member" name="members['+countMember+']" value="'+ id+'" data-id="'+ id+'"/></td>'
								+'	<td>'+ name +' </td>'
								+'	<td>'+ position +'</td>'
								+'</tr>'
								);
						countMember ++;
						$(this).attr("disabled",true);
					
					}
				});
			});
			
			$('#create-business ').on('click', 'button.remove', function(event){
				
				event.preventDefault();
				$('#create-business .selected-member-list input.cbx-member').not(':checked').each(function(index){
					var id = $(this).data("id");
					var removeIndex= getIndex(id, memberArray);
					memberArray.splice(removeIndex, 1);
					$('#create-business  #tblAvaliableMember .cbx-staff').each(function(index){
						console.log("id");
						if(id ==  $(this).data("id")){
							$(this).attr("checked",false).removeAttr("disabled");
						}
					});
					$(this).parent().parent().remove();
				});
				$('#create-business .selected-member-list input.cbx-member:checked').each(function(index){
					 $(this).removeAttr('name');
					 $(this).attr('name', 'members['+ index+']'); 
				 });
				
			});
			
			
			
			var add_business_item = function(selector, name, description, memArray ){
				var count = $('#nestable ol li').length;
				var item= 
					'<li class="dd-item item-'+count+'" data-count="'+ count +'">'
			            +'    <div class="dd-handle">'
			            +'    	<span class="dd-field business-name">'
			            +'				<span>'
			            + 				name				
			            +'				</span>'
			            +'		</span>'
			            +'   </div>'
			            +'	 <span class="point-line"></span>'	
			            +'   <input type="hidden"  class="name" name="business['+count +'].businessName" value="'+ name+'"/>'
			            +'   <input type="hidden" class="description" name="business['+count +'].businessDesc" value="'+ description+'"/>';
			          
			    for ( var i in memArray) {
			    	   item =  item  + '<input type="hidden" class="member removeable" data-name="'+memArray[i].name+'" data-position="'+memArray[i].position+'"   name="business['+count +'].members['+i+']" value="'+ memArray[i].id+'" />';
				}     
			    tem =  item  +'</li>';		          
				return selector.append(item);
				
			}
			$("#create-business ").on("hidden.bs.modal", function(){
				memberArray.length= 0;
				$('#create-business .has-error').removeClass('has-error');
				$('#create-business .errorMessages').empty();
				$('#create-business .nullable').val("");
				$('#create-business .selected-member-list table tbody').empty();
				
			});
			$('#create-business  #btn-create-business').on('click', function(event){
				console.log('click here');
				$('#create-business .has-error').removeClass('has-error');
				
				/* 	var name = 			$('#create-business .name').val();
				var description = 	$('#create-business .description').val();
				
				var validate = true;
				if (name.trim().length ==0) {
					$('#create-business .name').addClass("has-error");
					validate = false;
				}
				if (description.trim().length ==0) {
					$('#create-business .description').addClass("has-error");
					validate = false;
				}
				if (memberArray.length ==0) {
					$('#create-business .selected-member-list').addClass("has-error");
					validate = false;
				}
				if (validate ==true) {
					add_business_item(	$('.list-1'), name,description, memberArray );
					$('#nestable div.action').remove();
					add_nestable_tool();
					$('#create-business').modal('hide');
				} */
				
				var projectId = $(this).data('projectid');
				event.preventDefault();
				 var data = $('#create-business-form').serialize();
				
				$("#btn-create-business").text('Xin chờ').attr("disabled", true);
				$.ajax({
					url : '/manager/project/business/create',
					type : 'POST',
					data : data,
					dataType : 'json',
					success : function(response) {
						$('form#create-business-form .has-error').removeClass('has-error');
						if (response.status == 'FAILED') {
							console.log(response.errorList);
							var list = response.errorList;
							var messages = "";
							for (var i = 0; i < list.length; i++) {
								var field = list[i].field;
								var errorTag = $('#create-business-form  .' + field);
								errorTag.addClass('has-error');
								messages = messages + '<li>'+ list[i].message + '</li>';
								console.log(messages);
							}
							$('#errorMessages').html(messages);
							$("#btn-create-business").text('Tạo').removeAttr("disabled");
						} else {
							showAlertBox(" THÀNH CÔNG", "SUCCESS","/manager/project/"+projectId);
							$("#btn-create-business").text('Tạo').removeAttr("disabled");
							$('#create-business').modal('hide');	
							console.log("success");
						}
					},
					error : function(response) {
						console.log(response);
					}
				});
				
				
				
			});
			
			$('#create-business #btn-update-business').on('click', function(event){
				
				$('#create-business .has-error').removeClass('has-error');
				event.preventDefault();
				var data = $('#create-business-form').serialize();
				data = data + '&businessId='+businessId;
				$("#btn-create-business").text('Xin chờ').attr("disabled", true);
				$.ajax({
					url : '/manager/project/business/update',
					type : 'POST',
					data : data,
					dataType : 'json',
					success : function(response) {
						$('form#create-business-form .has-error').removeClass('has-error');
						if (response.status == 'FAILED') {
							console.log(response.errorList);
							var list = response.errorList;
							var messages = "";
							for (var i = 0; i < list.length; i++) {
								var field = list[i].field;
								var errorTag = $('#create-business-form  .' + field);
								errorTag.addClass('has-error');
								messages = messages + '<li>'+ list[i].message + '</li>';
								console.log(messages);
							}
							$('#errorMessages').html(messages);
							$("#btn-create-business").text('Tạo').removeAttr("disabled");
						} else {
							$("#btn-create-business").text('Tạo').removeAttr("disabled");
							$('#create-business').modal('hide');	
							console.log("success");
						}
					},
					error : function(response) {
						console.log(response);
					}
				});
			});
			
			var businessId;
			$('#nestable').on('click', 'button.view-item', function(event){
				event.preventDefault();
				businessId= $(this).closest("li").data("id");
				console.log("businessId: "+ businessId);
				$('#create-business #btn-update-business').show();
				$('#create-business #btn-create-business').hide();
				
				$.get("/manager/project/business/"+businessId, null, function(data){
					$('#create-business .businessName').val(data.name);
					$('#create-business .businessDesc').val(data.description);
					
					for ( var i in data.members) {
						var id = data.members[i].id;
						var name = data.members[i].firstName +' '+data.members[i].midName +' '+data.members[i].lastName;
						var position = data.members[i].position.name;
						var member = {
								id: id,
								name: name,
								position: position
							};
						memberArray.push(member);
						$('#create-business  .selected-member-list .wrapper-table table tbody').append(
								'<tr>'
								+'	<td><input type="checkbox" checked="checked" value="'+id+'" name="members['+i+']" class="cbx-member" data-id="'+ id+'"/></td>'
								+'	<td>'+ name +' </td>'
								+'	<td>'+ position +'</td>'
								+'</tr>'
						);
					}
					
					tblAvaliableMember_cre_bus.ajax.url('/manager/get-all-available-members').load();
					$('#create-business').modal({
						show:true
					});
				});
				
				
			});
			$('#nestable').on('click', 'button.delete-item', function(event){
				/* event.preventDefault();
				var remove = confirm("Muốn xóa không?");
				if (remove == true) {
					$(this).closest('li').remove();
					$('#nestable ol li').each(function(index){
						var count = $(this).data("count");
						$(this).removeClass("item-"+count);
						$(this).addClass("item-"+index);
						$(this).attr("data-count", index);
						$(this).children('input.name').attr('name','business['+index+'].businessName');
						$(this).children('input.description').attr('name','business['+index+'].businessDesc');
						$(this).children('input.member').each(function(i){
							$(this).attr('name','business['+index+'].members['+i+']')
						});
					})
					
					
				} */
				event.preventDefault();
				var businessId = $(this).closest('li').data("id");
				var projectId = $(this).closest('li').data("projectid");
				var result = confirm("Bạn thực sự muốn XÓA ");
				if (result) {
					
					waiting('show');
					$.ajax({
					    url: '/manager/project/business/delete/'+businessId,
					    type: 'DELETE',
					    success: function(result) {
					        if (result == 'success') {
					        	waiting('hide');
					        	$(this).closest('li').remove();
					        	showAlertBox("Xóa THÀNH CÔNG", "SUCCESS","/manager/project/"+projectId);
							}else{
								showAlertBox("Xóa THẤT BẠI", "FAILED","/manager/project/"+projectId);
								 
							
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
			
			
			/* ---------------------------------Edit project information----------------- */
			
			$('#btn-edit-project').on('click', function(event){
				event.preventDefault();
				var projectId = $(this).data('projectid');
				$.post("/manager/project/edit/"+ projectId, null, function(data){
					$('#edit-project-modal  form input.id').val(data.id);
					$('#edit-project-modal  form input.name').val(data.name);
					$('#edit-project-modal  form input.createAt').val(data.createAt);
					$('#edit-project-modal  form input.budget').val(data.budget);
					$('#edit-project-modal  form input.startDate').val(data.startDate);
					$('#edit-project-modal  form input.endDate').val(data.endDate);
					$('#edit-project-modal  form input.numOfMem').val(data.numOfMem);
					$('#edit-project-modal  form input.technology').val(data.technology);
					$('#edit-project-modal  form textarea.description').val(data.description);
					$('#edit-project-modal  form select.status').val(data.status);
					
				});
				
				$('#edit-project-modal').modal({
					show: true
				})
			});
			
			
			$('#edit-project-modal #btn-update-project').on('click', function(event){
				event.preventDefault();
				var projectId = $('#edit-project-modal  form input.id').val();
				var data = $('#edit-project-modal form').serialize();
				$("#btn-update-project").text('Xin chờ').attr("disabled", true);
				$.ajax({
					url : '/manager/project/update',
					type : 'POST',
					data : data,
					dataType : 'json',
					success : function(response) {
						$('#edit-project-modal  form .has-error').removeClass('has-error');
						if (response.status == 'FAILED') {
							console.log(response.errorList);
							var list = response.errorList;
							var messages = "";
							for (var i = 0; i < list.length; i++) {
								var field = list[i].field;
								var errorTag = $('#edit-project-modal .' + field);
								errorTag.addClass('has-error');
								messages = messages + '<li>'+ list[i].message + '</li>';
								console.log(messages);
							}
							$('#errorMessages').html(messages);
							$("#btn-update-project").text('Cập nhật').removeAttr("disabled");
							
						} else {
							showAlertBox("Cập nhật THÀNH CÔNG", "SUCCESS","/manager/project/"+projectId);
							$("#btn-update-business").text('Cập nhật').removeAttr("disabled");
							$('#edit-project-modal').modal('hide');	
							console.log("success");
						}
					},
					error : function(response) {
						console.log(response);
					}
				});
			});
			
			
			$("#edit-project-modal").on("hidden.bs.modal", function(){
				$('#edit-project-modal .has-error').removeClass('has-error');
				
			});
			
			function stringtoArray(string, array){
				var ar = string.split(",");
				console.log(ar);
				for ( var i in ar) {
					if(ar[i].trim().length != 0){
						array.push(parseInt(ar[i]));
					}
				}
			}
			
			function containMember(memberId, array) {
			    for (var i = 0; i < array.length; i++) {
			        if (array[i].id == memberId) {
			            return true;
			            break;
			        }
			    }
			    return false;
			}
			function getIndex(memberId, array) {
			    for (var i = 0; i < array.length; i++) {
			        if (array[i].id == memberId) {
			            return i;
			            break;
			        }
			    }
			    return -1;
			}
			
			
		});
	</script>
</html>