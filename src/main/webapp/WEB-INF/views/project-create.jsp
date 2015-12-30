
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
	
	
	<joda:format pattern="dd-MM-yyyy" value="${requestScope.user.dob}" var="formattedDOB" />
	<joda:format pattern="dd-MM-yyyy" value="${requestScope.user.enrollmentDate}" var="formattedEnDate" />
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
				
				<div class="wrapper-box">
					<h3 style="margin: 0px auto 15px auto; font-weight: bold;"> Tạo dự án mới</h3>
				<form action="" method="post" id="create-project-form">
					<div class="row field-box">
						<span class="col-xs-2 label-box w-180 ">Tên dự án:</span>	
							<span class="col-xs-5 "><input type="text" class="form-control  projectName" name="projectName"/></span>
					</div>
					<div class="row field-box">
						<span class="col-xs-2 label-box w-180 ">Ngày tạo dự án:</span>	
						<div class="col-xs-3">
							<div class="input-group date datepicker  w-250">
								<input type="text" class="form-control createAt" name="createAt" /> 
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</div>
						
					</div>
					
					<div class="row field-box">
						<span class="col-xs-2 label-box w-180 ">Ngân sách:</span>	
						<span class="col-xs-3 "><input type="text" class="form-control w-250 budget" name="budget"/></span>
						<span class="col-xs-3 label-box s-12">(VNĐ)</span>
					</div>
					<div class="row field-box">
						<span class="col-xs-2 label-box w-180 ">Thời gian bắt đầu:</span>	
						<div class="col-xs-3">
							<div class="input-group date datepicker  w-250">
								<input type="text" class="form-control startDate" name="startDate" /> 
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</div>
						<span class="col-xs-2 label-box w-180 ">Thời gian kết thúc:</span>
						<div class="col-xs-3">
							<div class="input-group date datepicker  w-250">
								<input type="text" class="form-control endDate" name="endDate" /> 
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</div>
					</div>
					
					
					<div class=" row field-box">
						<span class="col-xs-2 label-box w-180">Số lượng thành viên:</span>	
						<span class="col-xs-3"><input type="text" class="form-control w-250 numOfMem" name="numOfMem"/></span>
						<span class="col-xs-3 label-box s-12">( người )</span>
					
					</div>
					<div class=" row field-box">
						<span class="col-xs-2 label-box w-180">Công nghệ sử dụng:</span>	
						<div class="col-xs-4 ">
								<input type="text" class="form-control  technology" name="technology"/>
						</div>
					</div>
					<div class=" row field-box">
						<span class="col-xs-2 label-box w-180">Mô tả dự án:</span>	
						<span class="col-xs-3">
							<textarea class="form-control w-500 projectDesc" name="projectDesc" rows="5" ></textarea>
						</span>
					
					</div>				
					
					<hr />
      				<h4 class="field-title" style="display: inline-block;">Nhiệm vụ chính trong dự án</h4>
      				<button type="button" class="btn btn-create" style="display: inline-block;">Thêm nhiệm vụ</button>
      				<br/><br/>
					<div class=" project-field process-box ">
			                 
			                 <div class="dd" id="nestable">
					            <ol class="dd-list list-1" data-id="1">
					               <!--  <li class="dd-item" data-id="1">
					                    <div class="dd-handle">
					                    	<span class="dd-field business-name">
													<span class="value">
														Phân tích thiết kế hệ thống, thiêt kế cơ sở dữ liệu Phân tích thiết kế hệ thống, thiêt kế cơ sở dữ liệu Phân tích thiết kế hệ thống, thiêt kế cơ sở dữ liệu
													</span>
											</span>
					                    	<span class="dd-field  business-time ">
					                    		<span class="value">
													Từ 31/12/132 dến 31/12/2015 
												</span>
					                    	</span>
					                    </div>
					                </li> -->
					               
							    </ol>
		        			</div>
								
					</div>
      				<br class="clear"/>
      				
      				<div class="row">
						<div class="col-xs-7 col-xs-offset-4">
		      				<button class="btn btn-success" id="btn-create-project">Tạo dự án</button>
							<button type="reset" class="btn btn-success" >Reset</button>
							</div>
      				</div>
      				
      				</form>
      				<div class="row">
						<div class="col-xs-7 col-xs-offset-4">
							<ul id="errorMessages">
							</ul>
						</div>
					</div>
				</div>
				
				<br class="clear" />
			</section>
			<!-- /.content -->
		</div>
		
	<!-- /.content-wrapper -->

		<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>
	</div>
	<div class="modal fade" id="create-business">
			<div class="modal-dialog " style="width: 800px;">
				<div class="modal-content ">
					<div class="modal-header my-modal-header">
						<h4>Chi tiết công việc</h4>
						<span class="close-modal ion ion-close-round" data-dismiss="modal" aria-label="Close"></span>
					</div>
					<div class="modal-body">
						<div class="row field-box">
							<div class="col-xs-2 label-box">Tên công việc</div>	
							<div class="col-xs-5">
								<input type="text" class="form-control name nullable " name="name" />
							</div>
						</div>
					
						<div class="row">
							<div class="col-xs-2  label-box"><label>Mô tả:</label></div>	
							<div  class="col-xs-10">
								<textarea class="form-control description nullable" rows = "3" name="description" ></textarea>
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
							<div class="col-xs-5 selected-member-list"> 
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
								<button class="btn btn-success" id="btn-udpate-business">Cập nhật</button>
								<button class="btn btn-success" id="btn-add-business">Xong</button>
								<button class="btn btn-success cancel"  data-dismiss="modal">Hủy</button>
							 </div>
						</div>
					
				</div>
			<!-- 	end body -->
		</div>
		<!-- 	end content -->
	</div>
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
		    /* -----------------TAG TECHNOLOGY------------------------- */
		    $('.tag-technology').mytag({
		    	input_name:"technology",
		    	btn_name: "Thêm"
		    })
			/* ---------------DATE PIkCER----------------------------- */
			$('.datepicker').datepicker({
						language: 'vi',
				        format: "dd-mm-yyyy",
				        todayBtn: true,
				        todayHighlight: true,
				        autoclose: true,
				        orientation: "bottom left"
			});
			
			
			/* -------------------------KNOB------------------------ */
			$('.knob').knob({
				format : function (value) {
				     return value + '%';
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
			 
			/* ---------------DROP ZONE---------------- */
			
			$("div.document-box").dropzone({ url: "/file/post" });
			
			/* Dropzone.options.myDropzone = {

					  // Prevents Dropzone from uploading dropped files immediately
					  autoProcessQueue: false,

					  init: function() {
					    var submitButton = document.querySelector("#submit-all")
					        myDropzone = this; // closure

					    submitButton.addEventListener("click", function() {
					      myDropzone.processQueue(); // Tell Dropzone to process all queued files.
					    });

					    // You might want to show the submit button only when 
					    // files are dropped here:
					    this.on("addedfile", function() {
					      // Show submit button here and/or inform user to click it.
					    });

					  }
					};
			$("div.document-box").dropzone({ url: "/file/post" });
			 */
			/* -----------------EDITABLE------------------ */
		
			
			$('.project-list >ul').slimScroll({
			    height: '500px'
			    /* alwaysVisible: true */
			});
			$('.project-list .slimScrollDiv').css({"padding-bottom":"10px"});
		
			
			
			
			
			/* -------------------- CREATE BUSSINES  and ADD MEMBER --------------------- */
			
			$('#create-business #btn-udpate-business').hide();
			
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
				$('#create-business #btn-udpate-business').hide();
				$('#create-business #btn-add-business').show();
				$('#create-business').modal({
					show:true
				});
				tblAvaliableMember_cre_bus.ajax.url('/manager/get-all-available-members').load();
			});
				
			$('#create-business .selected-member-list .wrapper-table').slimScroll({
			    height: '350px'
			    /* alwaysVisible: true */
			});
			
			$('#create-business ').on('click', 'button.add', function(){
				$('#create-business  #tblAvaliableMember .cbx-staff:checked').not(':disabled').each(function(index){
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
								+'	<td><input type="checkbox" class="cbx-member" data-id="'+ id+'"/></td>'
								+'	<td>'+ name +' </td>'
								+'	<td>'+ position +'</td>'
								+'</tr>'
								);
						$(this).attr("disabled",true);
					
					}
				});
			});
			
			$('#create-business ').on('click', 'button.remove', function(){
				
				$('#create-business .selected-member-list .cbx-member:checked').each(function(index){
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
			});
			
			
			
			var add_business_item = function(selector, name, description, memArray ){
				var count = $('#nestable ol li').length;
				var item= 
					'<li class="dd-item item-'+count+'" data-count="'+ count +'">'
			            +'    <div class="dd-handle">'
			            +'    	<span class="dd-field business-name">'
			            +'				<span class="value">'
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
				$('#create-business .nullable').val("");
				$('#create-business .selected-member-list table tbody').empty();
				
			});
			$('#create-business  #btn-add-business').on('click', function(){
				console.log('click here');
				$('#create-business .has-error').removeClass('has-error');
				
				var name = 			$('#create-business .name').val();
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
				}
				
				
				
				
			});
			
			$('#create-business #btn-udpate-business').on('click', function(){
				$('li.item-'+itemId).children('.removeable').remove();
				
				$('#create-business .has-error').removeClass('has-error');
				
				var name = 			$('#create-business .name').val();
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
	 				$('li.item-'+itemId).find('span.value').text(name);
	 				$('li.item-'+itemId).children('input.name').val(name);
	 				$('li.item-'+itemId).children('input.description').val(description);
					
					for ( var i in memberArray) {
						$('li.item-'+itemId).append('<input type="hidden" class="member removeable" data-name="'+memberArray[i].name+'" data-position="'+memberArray[i].position+'"   name="business['+itemId+'].members['+i+']" value="'+memberArray[i].id+'" />');
					}     
					$('#create-business').modal('hide');
				}
			});
			
			
			$('#nestable').on('click', 'button.view-item', function(event){
				event.preventDefault();
				itemId= $(this).closest("li").data("count");
				console.log("itemId: "+ itemId);
				$('#create-business #btn-udpate-business').show();
				$('#create-business #btn-add-business').hide();
				$('#create-business').modal({
					show:true
				});
				var name = $(this).closest('li').children('input.name').val();
				var description = $(this).closest('li').children('input.description').val();
				$('#create-business .name').val(name);
				$('#create-business .description').val(description);
				
				$(this).closest('li').children('input.member').each(function(index){
					var id = $(this).val();
					var name = $(this).data("name");
					var position = $(this).data("position");
					memberArray.push({id:id, name: name, position: position})
					console.log(memberArray[0]);
					$('#create-business  .selected-member-list .wrapper-table table tbody').append(
							'<tr>'
							+'	<td><input type="checkbox" class="cbx-member" data-id="'+ id+'"/></td>'
							+'	<td>'+ name +' </td>'
							+'	<td>'+ position +'</td>'
							+'</tr>'
					);
				});
				tblAvaliableMember_cre_bus.ajax.url('/manager/get-all-available-members').load();
			});
			$('#nestable').on('click', 'button.delete-item', function(event){
				event.preventDefault();
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
				}
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
			
			/* -------------------------- create project--------------------------- */
			
			$('button#btn-create-project').on('click',function(event){
				event.preventDefault();
				var data = $('#create-project-form').serialize();
				$("#btn-create-project").text('Xin chờ').attr("disabled", true);
				$.ajax({
					url : '/manager/create-new-project',
					type : 'POST',
					data : data,
					dataType : 'json',
					success : function(response) {
						$('form#create-project-form .has-error').removeClass('has-error');
						if (response.status == 'FAILED') {
							console.log(response.errorList);
							var list = response.errorList;
							var messages = "";
							for (var i = 0; i < list.length; i++) {
								var field = list[i].field;
								var errorTag = $('#create-project-form  .' + field);
								errorTag.addClass('has-error');
								messages = messages + '<li>'+ list[i].message + '</li>';
								console.log(messages);
							}
							$('#errorMessages').html(messages);
							$("#btn-create-project").text('Tạo dự án').removeAttr("disabled");
						} else {
							$("#btn-create-project").text('Tạo dự án').removeAttr("disabled");
							console.log("success");
						}
					},
					error : function(response) {
						console.log(response);
					}
				});
				
			});
			
		});	
			/* -------------------- ADD MEMBER INTO PROJCT MODAL--------------------- */
			/* 
			/* $('#btn-add-member').on('click', function(){
				$('#member-into-project-modal').modal({
					show:true
				})
				tblAvaliableMember.ajax.url('/manager/get-all-reports').load();
			});
				
			$('#member-into-project-modal .selected-member-list .wrapper-table').slimScroll({
			    height: '350px'
			     alwaysVisible: true 
			});
			var memberArray= new Array();
			
			$('#member-into-project-modal').on('click', 'button.add', function(){
				$('#member-into-project-modal #tblAvaliableMember .cbx-staff:checked').not(':disabled').each(function(index){
					var id = $(this).data("id");
					if (memberArray.indexOf(id) == -1) {
						var name = $(this).parent().next().text();
						memberArray.push(id);
						
						$('#member-into-project-modal .selected-member-list .wrapper-table table tbody').append(
								'<tr>'
								+'	<td><input type="checkbox" class="cbx-member" data-id="'+ id+'"/></td>'
								+'	<td>'+ name +' </td>'
								+'	<td>'
								+'		<select style="width: 100%;">'
								+'			<option value="1">PHO911</option>'
								+'			<option value="1">PHO911</option>'
								+'			<option value="1">PHO911</option>'
								+'			<option value="1">PHO911</option>'
								+'		</select>'
								+'	</td>'
								+'</tr>'
								);
						$(this).attr("disabled",true);
					}
				});
			});
			
			$('#member-into-project-modal').on('click', 'button.remove', function(){
				console.log("click remove");
				$('#member-into-project-modal .selected-member-list .cbx-member:checked').each(function(index){
					var id = $(this).data("id");
					var removeIndex= memberArray.indexOf(id);
					memberArray.splice(removeIndex, 1);
					$('#member-into-project-modal #tblAvaliableMember .cbx-staff').each(function(index){
						console.log("id");
						if(id ==  $(this).data("id")){
							$(this).attr("checked",false).removeAttr("disabled");
						}
					});
					$(this).parent().parent().remove();
				});
			});
			
			
			$("#member-into-project-modal").on("hidden.bs.modal", function(){
				memberArray= new Array();
				$('#member-into-project-modal .selected-member-list table tbody').empty();
				
			});
			
			
			var tblAvaliableMember = $('#member-into-project-modal #tblAvaliableMember').DataTable(
					{
						"dom" : 'tp', //Define the table control elements to appear on the page and in what order.
						"info" : false, // Showing 1 to 15 of 51 entries
						"lengthChange" : false, // selext box 10 20 50
						"displayLength" : 10, // number of row will be displayed default 10
						"processing" : true,
						"serverSide" : true,
						"deferLoading" : 10,
						"pagingType" : "simple", //customize First, Last, Next, Previous in paging.
						"language" : { // change text display at button in paging
							"paginate" : {
								"first" : "First",
								"previous": "Trước",
								"next" : "Sau"
							},
							"zeroRecords": "Không có report nào.",
							 "search":     "Tìm kiếm:",
							 "processing":  "Đang tải dữ liệu...",
						},
						"ajax" : {
							"url" : "/manager/get-all-user",
							"type" : "POST",
							"data": function(data){
								modifyParameter(data);
							}
						},
						 "columnDefs" : [ 
						{
							"targets" : 2,
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
						
						"columns" : [
						{
							"data" :  function(row, type, full, meta) {
								var checkbox = '<input type="checkbox" class="cbx-staff"  data-id="'+ row.id+'"/>';	
								return checkbox;
							},
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
							"data" : "position.name",
							"name":"status",
							"width" : "80px",
							"orderable": false 
						} ]

					}); */
			 
			
		
	</script>
</html>