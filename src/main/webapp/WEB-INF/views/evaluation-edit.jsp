
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
<joda:format pattern="dd-MM-yyyy" value="${evaluationForm.createAt}" var="createAt" /> 	
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
        	
        	<form:form action="/manager/evaluation/update" method="post" commandName="evaluationForm">
        		<form:hidden path="id" cssErrorClass="has-error form-control id" cssClass="form-control id "/>
				<div style="text-align: center;"><h3>Bảng đánh giá nhân viên</h3></div>
					<div class="my-box-100 evaluation-box">
						
						<div class="row" style="margin-top: 10px;">
								<div class="col-xs-5 my-col" style="border-right: 1px solid #f39c12;">
									<span style="text-align: center; margin:0px; padding:0px;  display: block; color: #265a79; text-transform: uppercase;"><h4> Thông tin</h4></span>
									<div class="field-box row">
										<div class="col-xs-4"><span class="my-label">Ngày tạo:</span></div>
										<div class="col-xs-8">
										 	<div class='input-group date datePicker'>
												<!-- <input type='text' class="form-control short-input-form createAt" name="createAt" /> -->
												<form:input path="createAt" cssErrorClass="has-error form-control createAt " cssClass="form-control  createAt "/>
												 <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
												
											</div >
											
										</div>
									</div>
									<div class="field-box row">
										<div class="col-xs-4">
											<span class="my-label">Nhân viên:</span>
										</div>
										<div class="col-xs-8">
											 <form:select path="staffId" cssClass="form-control " cssErrorClass="form-control has-error">
												<form:options items="${staffList}" itemValue="id" itemLabel="fullName"/>
											</form:select>
											<%-- <span class="my-label fullName">${evaluation.staffEvaluation.firstName } ${evaluation.staffEvaluation.midName } ${evaluation.staffEvaluation.lastName } </span> --%>
										</div>
									</div>
									<div class="field-box row">
										<div class="col-xs-4">
												<span class="my-label">Dự án tham gia:</span>
										</div>
										<div class="col-xs-8">
											<form:select path="projectId" cssClass="form-control " cssErrorClass="form-control has-error">
												<form:options items="${projects}" itemValue="id" itemLabel="name"/>
											</form:select>
										</div>
									</div>
									<hr style="border-top: 1px solid #f39c12; "/>
									<div class="field-box ">
										<div class="tag-box">
											<span class="my-label">Khóa học đã tham gia: </span>
											 <input type="text" class="form-control new-tag-form " />
											<input type="button" class="btn btn-add-tag pull-right" value="Thêm" />
											<form:input type="hidden"  path ="trainingCourseAttended" class="form-control tag-input trainingCourseAttended" name="trainingCourseAttended"/> 
											<div class="tag-area trainingCourseAttended-tags">
												<c:forEach items="${trainingCourseAttendedList }" var="ccourse" >
													<span class="my-tag"><span class="tag-value ">${ccourse }</span><span class="modifyTag"><i class="icon ion-edit"></i> <i class="icon ion-close-circled"></i> </span></span>
												</c:forEach>
											</div>
										</div>
									</div>
									<div class="field-box ">
										<div class="tag-box">
												<span class="my-label">Chứng chỉ đạt được:</span>
												<input type="text" class="form-control new-tag-form " />
												<input type="button" class="btn  btn-add-tag pull-right" value="Thêm " />
												<form:input type="hidden"  path ="certificate" class="form-control tag-input  certificate" name="certificate"/>
												<div class=" tag-area">
													<c:forEach items="${certificateList }" var="cer" >
													<span class="my-tag"><span class="tag-value ">${cer }</span><span class="modifyTag"><i class="icon ion-edit"></i> <i class="icon ion-close-circled"></i> </span></span>
												</c:forEach> 
												</div>
										</div>
									</div>
								</div>
								<!-- -----------------dANH GIA-------------- -->
								<div class="col-xs-7 my-col">
									<span  style="text-align: center;  margin:0px; padding:0px; display: block; color: #265a79; text-transform: uppercase;"><h4> Đánh giá nhân viên qua công việc</h4></span>									
									<div class="field-box field-box-1">
										<span class="my-label-100">Độ khó công việc:</span>
										<span class="raty-taskComplexity"></span>
										<span class="target-taskComplexity raty-target"></span>
										<form:errors path="taskComplexity" cssClass="form-error" />
									</div>
									 <div class="field-box field-box-1">
										<span class="my-label-100">Mức độ hoàn thành:</span>
										<span class="raty-taskCompletion"></span>
										<span class="target-taskCompletion raty-target"></span>
										<form:errors path="taskCompletion" cssClass="form-error"  />
									</div>
									 <div class="field-box field-box-1">
										<span class="my-label-100">Kỹ năng làm việc:</span>
										<span class="raty-skill"></span>
										<span class="target-skill raty-target"></span>
										<form:errors path="skill" cssClass="form-error" />
									</div>
									 <div class="field-box field-box-1">
										<span class="my-label-100">Kỷ luật:</span>
										<span class="raty-workingDiscipline"></span>
										<span class="target-workingDiscipline raty-target"></span>
										<form:errors path="workingDiscipline" cssClass="form-error" />
									</div>
									<div class="field-box field-box-1">
										<span class="my-label-100">Thái độ:</span>
										<span class="raty-behavior"></span>
										<span class="target-behavior raty-target"></span>
										<form:errors path="behavior" cssClass="form-error" />
									</div>
									 <div class="field-box field-box-1">
										<span class="my-label"><h4><u> Nhận xét </u> :</h4></span>
										<form:textarea path="comment" cssErrorClass="has-error form-control " cssClass="form-control " rows="5"/>
										<form:errors path="comment" cssClass="form-error" /> 
										<%-- <textarea name="comment" class="form-control "  rows="5">${evaluation.comment }</textarea> --%>
									</div>
									
								</div>
							
							</div>
							
							<!-- ---------------THUONG PHAT-------------------- -->
								<hr style="border-top: 1px solid #f39c12;  margin-top:10px 0px 5px 0px;"/>
								<div class="row" style="margin-top: 10px;">
									<div class="col-xs-6 my-col" style="border-right: 1px solid #f39c12;">
										<span  style="text-align: center;  margin:0px; padding:0px; display: block; color: #265a79; text-transform: uppercase;"><h4> Phần thưởng</h4></span>
										<div class="field-box row">
											<div class="col-xs-12">
												<span class="my-label">Nội dung khóa học:</span>
												 <form:textarea path="award" cssErrorClass="has-error form-control" cssClass="form-control " rows="5"/>
												<form:errors path="award" cssClass="form-error" />
												<%-- <textarea name="award" class="form-control "  rows="5">${evaluation.award }</textarea> --%>
											</div>
										</div> 
										<%-- <div class="field-box row">
											<div class="col-xs-4"><span class="my-label">Khóa học:</span></div>
											<div class="col-xs-7">
												<!-- <input type="text" class="form-control " name="trainingCourse" /> -->
												<form:input path="awardCourse" cssErrorClass="has-error form-control" cssClass="form-control "/>
												<form:errors path="awardCourse" cssClass="form-error" /> 
												<span class="my-label">${evaluation.awardCourse}</span>
											</div>
										</div>
										<div class="field-box row">
											<div class="col-xs-4"><span class="my-label">Ngày tham gia:</span></div>
											<div class="col-xs-7">
												 <div class='input-group date datePicker'>
														<!-- <input type='text' class="form-control  submitDate" name="submitDate" /> -->
														<form:input path="awardCourseDate" cssErrorClass="has-error form-control " cssClass="form-control  "/>
														 <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>	 
												<span class="my-label">${awardCourseDate}</span>
											</div>
											<div class="col-xs-3">
												<form:errors path="awardCourseDate" cssClass="form-error" />
											</div>
										</div>
										
										<div class="field-box row">
											<div class="col-xs-11">
												<span class="my-label">Nội dung khóa học:</span>
												 <form:textarea path="award" cssErrorClass="has-error form-control" cssClass="form-control " rows="3"/>
												<form:errors path="award" cssClass="form-error" />
												<textarea name="award" class="form-control "  rows="5">${evaluation.award }</textarea>
											</div>
										</div> --%>
								</div>
								<!-- ----------------PHAT----------------- -->
								
								<div class="col-xs-6 my-col">
										<span  style="text-align: center;  margin:0px; padding:0px; display: block; color: #265a79; text-transform: uppercase;"><h4>  phạt</h4></span>
										<%-- <div class="field-box row">
											<div class="col-xs-4"><span class="my-label">Ngày thực hiện:</span></div>
											<div class="col-xs-7">
												- <div class='input-group date datePicker'>
														<!-- <input type='text' class="form-control  penaltyDate" name="penaltyDate" /> -->
														<form:input path="penaltyDate" cssErrorClass="has-error form-control " cssClass="form-control  "/>
														 <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>	 
												<span class="my-label">${penaltyDate}</span>
											</div>
											<div class="col-xs-3">
												<form:errors path="penaltyDate" cssClass="form-error" /> 
											</div>
										</div> --%>
										<div class="field-box row">
											<div class="col-xs-12">
												<span class="my-label">Nội dung hình phạt:</span>
												 <form:textarea path="penalty" cssErrorClass="has-error form-control" cssClass="form-control " rows="5"/>
												<form:errors path="penalty" cssClass="form-error" /> 
												<%-- <textarea name="penalty" class="form-control "  rows="5">${evaluation.penalty }</textarea> --%>
											</div>
										</div>
								</div>
								
							</div>
							<div class="row">
								<div class="col-xs-4 my-label"></div>
								<div class="col-xs-5">
									<input type="submit" value="Cập nhật" class="btn btn-primary" />
								</div>
							</div>
							
							
					</div> <!-- end my-box-100 -->
			</form:form>
								
			</section>
			
	</div>

	<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>

</div>
	
</body>
	<script type="text/javascript">
		$(document).ready(function() {
			
			var initialtags =  function( str, splitingChar, tagArea){
				str = str.trim();
				while(str.contains("  ")){
					str = str.replace("  ", " ");
				}
				var start = 0;
				for (var i = 0; i < str.length; i++) {
					var c = str.charAt(i);
					if (c == splitingChar) {
						var subStr = str.substring(start, i);
						start = i+1;
						tagArea.append('<span class="my-tag"><span class="tag-value ">'+trainingCourseAttended[i]+'</span>' + modifyTag +'</span>');
					}
					if ( i == str.length - 1) {
						var subStr = str.substring(start, i + 1);
						if (subStr.trim().length > 0) {
							tagArea.append('<span class="my-tag"><span class="tag-value ">'+trainingCourseAttended[i]+'</span>' + modifyTag +'</span>');
						}
					}
				}
			}
			
			
			var projectId = ${evaluationForm.projectId}
			var staffId =${evaluationForm.staffId}
						
			
			
			$('#projectId').val(projectId);
			$('#staffId').val(staffId);
			
			
			
			
			$('.datePicker').datepicker({
				language: 'vi',
		        format: "dd-mm-yyyy",
		        todayBtn: true,
		        autoclose: true,
		        todayHighlight: true
		    });
			/* -------------------- TAG TAG TAG TAG --------------- */
			var modifyTag = '<span class="modifyTag"><i class="icon ion-edit"></i> <i class="icon ion-close-circled"></i> </span>';
			$('.new-tag-form').hide().attr("disabled", true);
			
			var tagContainer =[];
			/* --------------- */
			var oldTagValue="";
			var TagValueString="";
			
			
			$('.tag-box').each(function( index ) {
				console.log("each");
					var tag_box = $(this); 
				  $(this).attr("data-tag-id",index);
				  $(this).addClass("tag-class-"+index);
				  tagContainer[index] = new Array();
				  tag_box.find('.tag-value').each(function( i ){
					  tagContainer[index].push($(this).text()); 
				  });
			});
			console.log(tagContainer[0].length);
			var editTag = false;
			var tagToEdit;
			
			var addNewTagFunction = function(tagValue, tagArea, tagInput, tagArray){
				var newTagValue = tagValue.trim();
				var numberOfTag = tagArray.length +1;
				tagArea.append('<span class="my-tag"><span class="tag-value tag-'+numberOfTag +'">'+newTagValue+'</span>' + modifyTag +'</span>');
				addTagToArray(newTagValue, tagArray	);
				tagInput.val(getTagFromArray(tagArray));
				
			}
			var editTagFromArray= function(oldTagValue, newTagValue, tagArray){
				editTag = false;
				addTagToArray(newTagValue,tagArray,tagArray.indexOf(oldTagValue));
			}
			
			var addTagToArray = function(newTagValue, tagArray, position){
				if (position === undefined || position === null) {
					tagArray.splice(tagArray.length, 1, newTagValue);
				}else{
					console.log("position: "+ position);
					tagArray.splice(position, 1, newTagValue);
				}
			}
			
			var removeTagFromArray = function(removeValue, tagArray){
				var removeIndex= tagArray.indexOf(removeValue);
				tagArray.splice(removeIndex, 1);
				
			}
			var getTagFromArray= function(tagArray){
				var tagValueString = "";
				 for ( var index in tagArray) {
					 tagValueString= tagValueString + tagArray[index] + "#";
				}
				return 	tagValueString;
			}
			
			$('.btn-add-tag').on("click",function(){
				$(this).siblings(".new-tag-form").show().removeAttr("disabled").focus();
			});
			
			
			$('.new-tag-form').on("blur",function(){
				var tagId= $(this).closest('.tag-box').data("tag-id");
				var newTagValue = $(this).val().trim();
				var arrayHere = tagContainer[tagId];
				if (arrayHere.indexOf(newTagValue) == -1 && newTagValue.length != 0) {
					if (editTag == true) {// edit tag
						editTagFromArray(tagToEdit.text(),newTagValue, arrayHere);
						tagToEdit.text(newTagValue);
						$(this).siblings(".tag-input").val(getTagFromArray( arrayHere));
					}else{//create new tag
						addNewTagFunction(newTagValue,$(this).siblings(".tag-area"), $(this).siblings(".tag-input"), arrayHere);
					} 
				}
				$(this).hide().attr("disabled", true).val("");
				
			});
			
			$('.new-tag-form').keypress(function (e) {
				var key = e.which;
				if(key == 13){  // the enter key code
					e.preventDefault();
					var newTagValue = $(this).val().trim();
					var tagId= $(this).closest('.tag-box').data("tag-id");
					var arrayHere = tagContainer[tagId];
					if (arrayHere.indexOf(newTagValue) == -1 && newTagValue.length != 0) {
						if (editTag == true) {// edit tag
							editTagFromArray(tagToEdit.text(),newTagValue, arrayHere);
							tagToEdit.text(newTagValue);
							$(this).siblings(".tag-input").val(getTagFromArray( arrayHere));
						}else{//create new tag
							addNewTagFunction(newTagValue,$(this).siblings(".tag-area"), $(this).siblings(".tag-input"), arrayHere);
						} 
					}
					$(this).show().focus().val("");  
					return false;
				  }
				});   
			
			
			$('.tag-box').on("click",".modifyTag i.ion-edit", function(){
				editTag = true;
				tagToEdit =$(this).parent().siblings();
				oldTagValue = tagToEdit.text().trim();
				$(this).closest('.tag-box').find('.new-tag-form').show().removeAttr("disabled").focus().val(oldTagValue);
			});
		
			$('.tag-box').on("click",".modifyTag i.ion-close-circled", function(){
				 var tagId= $(this).closest('.tag-box').data("tag-id");
				 var arrayHere = tagContainer[tagId];
				 var removeValue = $(this).parent().siblings().text().trim();
				 removeTagFromArray(removeValue, arrayHere);
				 $(this).closest('.tag-box').find(".tag-input").val(getTagFromArray( arrayHere));
				 $(this).parent().parent().remove();
			});
			
			/* ----------------------------------------------------- */
			
			/* -----------------------------RATY------------------- */
			
			var raty_taskCompletion = ${evaluationForm.taskCompletion};
			var raty_taskComplexity = ${evaluationForm.taskComplexity};
			var raty_workingDiscipline = ${evaluationForm.workingDiscipline};
			var raty_skill = ${evaluationForm.skill};
			var raty_behavior = ${evaluationForm.behavior};
			
			$('.raty-taskCompletion').raty({
				
				score: raty_taskCompletion,
				path: '/resources/images',
				number: 10,
				scoreName: "taskCompletion",
				target:".target-taskCompletion",
				targetKeep : true,
				hints: ['Cảnh cáo','Quá kém', 'Kém'
				        ,'Bình thường', 'Bình thường'
				        ,'Cũng Khá','Khá lắm'
				        ,'Tốt', 'Rất tốt','Xuất sắc']
			});
			$('.raty-taskComplexity').raty({ 
				
				score : raty_taskComplexity ,
				path: '/resources/images',
				number: 10,
				scoreName: "taskComplexity",
				target:".target-taskComplexity",
				targetKeep : true,
				hints: ['Quá đơn giản','Khá dễ dàng', 'Dễ dàng'
				        ,'Bình thường' 
				        ,'Khá','Khó','Rất khó'
				        ,'Phức tạp', 'Rất phức tạp','Khó và phức tạp']
			});
			$('.raty-workingDiscipline').raty({ 
				
				score: raty_workingDiscipline,
				path: '/resources/images',
				number: 10,
				scoreName: "workingDiscipline",
				target:".target-workingDiscipline",
				targetKeep : true,
				hints: ['Cảnh cáo','Rất kém', 'Kém'
				        ,'Bình thường'
				        ,'Cũng Khá','Khá', 'Khá lắm'
				        ,'Tốt', 'Rất tốt','Xuất sắc']
			});
			$('.raty-skill').raty({ 
				
				score: raty_skill,
				path: '/resources/images',
				number: 10,
				scoreName: "skill",
				target:".target-skill",
				targetKeep : true,
				hints: ['Rất kém','Kém', 'Quá kém'
				        ,'Bình thường'
				        ,'Cũng Khá','Khá', 'Khá lắm'
				        ,'Tốt', 'Rất tốt','Xuất sắc']
			});
			$('.raty-behavior').raty({ 
				
				score: raty_behavior,
				path: '/resources/images',
				number: 10,
				scoreName: "behavior",
				target:".target-behavior",
				targetKeep : true,
				hints: ['Rất kém','Kém', 'Quá kém'
				        ,'Bình thường'
				        ,'Cũng Khá','Khá', 'Khá lắm'
				        ,'Tốt', 'Rất tốt','Xuất sắc']
			});
			
		});
	</script>
</html>