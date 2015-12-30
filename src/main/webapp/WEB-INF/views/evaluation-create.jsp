
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
        	
				<div style="text-align: center;"><h3>Bảng đánh giá nhân viên</h3></div>
				<c:choose>
				  <c:when test="${changePassSuccessfully == 'success' }">
				    <div class="alert my-alert-success"> 
						<button class="close" aria-hidden="true" data-dismiss="alert" type="button">x</button>
						<i class="icon fa fa-check"></i> Đổi mật khẩu thành công
					</div>
				  </c:when>
				  <c:when test="${updateSuccessfully == 'success' }">
				    <div class="alert my-alert-success"> 
						<button class="close" aria-hidden="true" data-dismiss="alert" type="button">x</button>
						 <i class="icon fa fa-check"></i> Cập nhật thông tin thành công
					</div>
				  </c:when>
				
				</c:choose>
			
				<form:form action="/manager/evaluation/create" method="post" commandName="evaluationForm">
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
											
											</div>
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
										<div class="tag-course">
											<span class="my-label">Khóa học đã tham gia: </span>
											<!-- <input type="text" class="form-control new-tag-form " />
											<input type="button" class="btn btn-add-tag pull-right" value="Thêm" />
											<input type="hidden" class="form-control tag-input trainingCourseAttended" name="trainingCourseAttended"/>
											<div class="tag-area"></div> -->
										</div>
									</div>
									<div class="field-box ">
										<div class="tag-certificates">
												<span class="my-label">Chứng chỉ đạt được:</span>
											<!-- 	<input type="text" class="form-control new-tag-form " />
												<input type="button" class="btn  btn-add-tag pull-right" value="Thêm " />
												<input type="hidden" class="form-control tag-input  certificate" name="certificate"/>
												<div class=" tag-area"></div> -->
										</div>
									</div>
									<!-- <div class="field-box">
										<div class="tag-languageScopes">
											<span class="my-label">Ngôn ngữ lập trình:</span>
											<input type="text" class="form-control new-tag-form" />
											<input type="button" class="btn  btn-add-tag pull-right" value="Thêm	" />
											<input type="hidden" class="form-control tag-input languageScope" name="languageScope"/>
											<div class=" tag-area"></div>
										</div>
									</div> -->
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
												<span class="my-label">Nội dung:</span>
												<form:textarea path="award" cssErrorClass="has-error form-control" cssClass="form-control " rows="5"/>
												<form:errors path="award" cssClass="form-error" />
											</div>
										</div>
										<%-- <div class="field-box row">
											<div class="col-xs-4"><span class="my-label">Khóa học:</span></div>
											<div class="col-xs-7">
												<!-- <input type="text" class="form-control " name="trainingCourse" /> -->
												<form:input path="awardCourse" cssErrorClass="has-error form-control" cssClass="form-control "/>
												<form:errors path="awardCourse" cssClass="form-error" />
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
											</div>
											<div class="col-xs-3">
												<form:errors path="awardCourseDate" cssClass="form-error" />
											</div>
										</div> 
										
										<div class="field-box row">
											<div class="col-xs-11">
												<span class="my-label">Nội dung khóa học:</span>
												<form:textarea path="awardCourseContent" cssErrorClass="has-error form-control" cssClass="form-control " rows="3"/>
												<form:errors path="awardCourseContent" cssClass="form-error" />
											</div>
										</div>--%>
								</div>
								<!-- ----------------PHAT----------------- -->
								
								<div class="col-xs-6 my-col">
										<span  style="text-align: center;  margin:0px; padding:0px; display: block; color: #265a79; text-transform: uppercase;"><h4> hình phạt</h4></span>
										<div class="field-box row">
											<div class="col-xs-12">
												<span class="my-label">Nội dung hình phạt:</span>
												<form:textarea path="penalty" cssErrorClass="has-error form-control" cssClass="form-control " rows="5"/>
												<form:errors path="penalty" cssClass="form-error" />
											</div>
										</div>
								</div>
								
							</div>
							<div class="row">
								<div class="col-xs-4 my-label"></div>
								<div class="col-xs-5">
									<input type="submit" value="Đánh giá" class="btn btn-primary" />
									<input type="reset" value="Nhập lại"  class="btn btn-primary" />
								</div>
							</div>
							
							
					</div> <!-- end my-box-100 -->
					
				</form:form>
				
				<%-- <form:form action="/manager/evaluation/create" method="post" commandName="evaluationForm">
					<div class="my-box">
						<div class="row">
								<div class="col-xs-4 my-label" >Ngày đánh giá:</div>
								<div class="col-xs-5">
									<div class='input-group date datePicker'>
											<!-- <input type='text' class="form-control short-input-form createAt" name="createAt" /> -->
											<form:input path="createAt" cssErrorClass="has-error form-control createAt " cssClass="form-control  createAt "/>
											 <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
										
									</div>
								</div>
								<div class="col-xs-3">
									<form:errors path="createAt" cssClass="form-error" />	
								</div>
							</div>
							<div class="row">
								<div class="col-xs-4 my-label" >Đánh giá nhân viên:</div>
								<div class="col-xs-5">
									<select class="form-control  staffId" name="staffId">
										<c:forEach items="${staffList}" var="currentStaff">
											<option value="${currentStaff.id}"><c:out value="${currentStaff.firstName} ${currentStaff.midName} ${currentStaff.lastName}" /> </option>
										</c:forEach>
									</select>
									<form:select path="staffId" cssClass="form-control " cssErrorClass="form-control has-error">
										<form:options items="${staffList}" itemValue="id" itemLabel="fullName"/>
									</form:select>
								</div>
							</div>
					</div>
					<br/>
					<!-- ------------------------------------------------------- -->
					<div class="my-box">
						<h3> Hiệu suất công việc</h3>
						<div class="row">
							<div class="col-xs-4 my-label" > Dự án tham gia:</div>
							<div class="col-xs-5">
								<select class="form-control" name="projectId" >
										<c:forEach items="${projectList}" var="currentProject">
											<option value="${currentProject.id}"><c:out value="${currentProject.projectName}" /></option>
										</c:forEach>
									</select>
									<form:select path="projectId" cssClass="form-control " cssErrorClass="form-control has-error">
										<form:options items="${projectList}" itemValue="id" itemLabel="projectName"/>
									</form:select>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Vai trò trong dự án:</div>
							<div class="col-xs-5">
								<select class="form-control " name="position">
										<c:forEach items="${positionList}" var="currentPosition">
											<option value="${currentPosition}"><c:out value="${currentPosition}" /></option>
										</c:forEach>
									</select>
									<form:select path="position" cssClass="form-control " cssErrorClass="form-control has-error">
										<form:options items="${positionList}" itemValue="currentPosition" itemLabel="currentPosition"/>
									</form:select>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Mức độ cố gắng:</div>
							<div class="col-xs-5">
								<label class="my-label"> 22</label>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Mức độ hoàn thành công việc:</div>
							<div class="col-xs-8">
								<form:input type="text" class="form-control short-input-form " name="taskCompletion" />
								<form:input path="taskCompletion" cssErrorClass="has-error form-control short-input-form" cssClass="form-control  short-input-form"/>
								<span class="raty-taskCompletion"></span>
								<span class="target-taskCompletion raty-target"></span>
								<form:errors path="taskCompletion" cssClass="form-error" />
								
							</div>
						
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Độ phức tạp của công việc:</div>
							<div class="col-xs-8">
								<!-- <input type="text" class="form-control short-input-form"  name="taskComplexity"/> -->
								<form:input path="taskComplexity" cssErrorClass="has-error form-control short-input-form" cssClass="form-control  short-input-form"/>
								<span class="raty-taskComplexity"></span>
								<span class="target-taskComplexity raty-target"></span>
								<form:errors path="taskComplexity" cssClass="form-error" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Kỷ luật khi làm việc:</div>
							<div class="col-xs-8">
								<!-- <input type="text" class="form-control short-input-form" name="workingDiscipline"/> -->
								<form:input path="workingDiscipline" cssErrorClass="has-error form-control short-input-form" cssClass="form-control  short-input-form"/>
								<span class="raty-workingDiscipline"></span>
								<span class="target-workingDiscipline raty-target"></span>
								<form:errors path="workingDiscipline" cssClass="form-error" />
							</div>
						</div>
						
						<div class="row">
							<div class="col-xs-4 my-label" >Đánh giá hiệu suất:</div>
							<div class="col-xs-5">
								<label class="my-label"> 22</label>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Nhận xét về hiệu suất:</div>
							<div class="col-xs-6">
								<!-- <textarea rows="2"   class="form-control " name="comment"></textarea> -->
								<form:textarea path="comment" cssErrorClass="has-error form-control " cssClass="form-control  "/>
								<form:errors path="comment" cssClass="form-error" />
							</div>
						</div>
					
					
					</div>
					<br/>
					<!-- ------------------------------------------------------- -->
					
					<div class="my-box">
						<h3>Kỹ năng và thái độ làm việc</h3>
						<div class="row">
							<div class="col-xs-4 my-label" >Kỹ năng làm việc:</div>
							<div class="col-xs-8">
								<!-- <input type="text" class="form-control short-input-form" name="skill"/> -->
								<form:input path="skill" cssErrorClass="has-error form-control short-input-form" cssClass="form-control  short-input-form"/>
								<span class="raty-skill"></span>
								<span class="target-skill raty-target"></span>
								<form:errors path="skill" cssClass="form-error" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Thái độ làm việc:</div>
							<div class="col-xs-8">
								<!-- <input type="text" class="form-control short-input-form" name="behavior"/> -->
								<form:input path="behavior" cssErrorClass="has-error form-control short-input-form" cssClass="form-control  short-input-form"/>
								<span class="raty-behavior"></span>
								<span class="target-behavior raty-target"></span>
								<form:errors path="behavior" cssClass="form-error" />
							</div>
						</div>
						
						<div class="row">
							<div class="col-xs-4 my-label" >Đánh giá:</div>
							<div class="col-xs-5">
								<label class="my-label"> 22</label>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Nhân xét về kỹ năng và thái độ:</div>
							<div class="col-xs-6">
								<!-- <textarea rows="2"   class="form-control " name="skillAndBehaviorComment"></textarea> -->
								<form:textarea path="skillAndBehaviorComment" cssErrorClass="has-error form-control " cssClass="form-control  "/>
								<form:errors path="skillAndBehaviorComment" cssClass="form-error" />
							</div>
						</div>
						
					</div>
						<br/>
					<!-- ------------------------------------------------------- -->
					<div class="my-box">
						<h3>Đào tạo, khóa học</h3>
						<div class="row">
							<div class="col-xs-4 my-label" >Những khóa đào tạo tham gia:</div>
							<div class="col-xs-8 tag-box">
								<div class=" tag-area"></div>
								<input type="hidden" class="form-control tag-input trainingCourseAttended" name="trainingCourseAttended"/>
								<input type="text" class="form-control new-tag-form " />
								<input type="button" class="btn btn-succes btn-add-tag " value="Thêm khóa học" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Chứng chỉ nhận được:</div>
							<div class="col-xs-8 tag-box">
								<div class=" tag-area"></div>
								<input type="hidden" class="form-control tag-input  certificate" name="certificate"/>
								<input type="text" class="form-control new-tag-form " />
								<input type="button" class="btn btn-succes btn-add-tag " value="Thêm chứng chỉ" />
							</div>
							
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Ngôn ngữ lập trình sử dụng:</div>
							<div class="col-xs-8 tag-box">
								<div class=" tag-area"></div>
								<input type="hidden" class="form-control tag-input languageScope" name="languageScope"/>
								<input type="text" class="form-control new-tag-form" />
								<input type="button" class="btn btn-succes btn-add-tag" value="Thêm ngôn ngữ" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Đánh giá:</div>
							<div class="col-xs-5">
								<label class="my-label"> 22</label>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Nhận xét:</div>
							<div class="col-xs-6">
								<!-- <textarea rows="2"   class="form-control " name="trainingComment"></textarea> -->
								<form:textarea path="trainingComment" cssErrorClass="has-error form-control " cssClass="form-control  "/>
								<form:errors path="trainingComment" cssClass="form-error" />
							</div>
						</div>
						
					</div>
						<br/>
					<!-- ------------------------------------------------------- -->
					
					<div class="my-box">
						<h3>Phần thưởng</h3>
						<hr/>
						<h4>Training contribution</h4>
						<div class="row">
							<div class="col-xs-4 my-label" >Khóa học:</div>
							<div class="col-xs-5">
								<!-- <input type="text" class="form-control " name="trainingCourse" /> -->
								<form:input path="trainingCourse" cssErrorClass="has-error form-control" cssClass="form-control "/>
								<form:errors path="trainingCourse" cssClass="form-error" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Nội dung khóa học:</div>
							<div class="col-xs-5">
								<!-- <textarea rows="2"   class="form-control " name="content"></textarea> -->
								<form:textarea path="content" cssErrorClass="has-error form-control" cssClass="form-control "/>
								<form:errors path="content" cssClass="form-error" />
							</div>
						</div>
						<hr/>
						<h4>IP contribution</h4>
						<div class="row">
							<div class="col-xs-4 my-label" >Mức độ ảnh hưởng:</div>
							<div class="col-xs-5">
								<!-- <input type="text" class="form-control short-input-form" name="scoreImpact" /> -->
								<form:input path="scoreImpact" cssErrorClass="has-error form-control short-input-form" cssClass="form-control  short-input-form"/>
								<form:errors path="scoreImpact" cssClass="form-error" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Mô tả khóa học:</div>
							<div class="col-xs-5">
								<!-- <textarea rows="2"   class="form-control description" name="description"></textarea> -->
								<form:textarea path="description" cssErrorClass="has-error form-control " cssClass="form-control  "/>
								<form:errors path="description" cssClass="form-error" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Lợi ích của khóa học:</div>
							<div class="col-xs-5">
								<!-- <textarea rows="2"   class="form-control benefit" name="benefit"></textarea> -->
								<form:textarea path="benefit" cssErrorClass="has-error form-control"  cssClass="form-control "/>
							</div>
							<div class="col-xs-3">
								<form:errors path="benefit" cssClass="form-error" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Ngày tham gia khóa học</div>
							<div class="col-xs-5">
								<div class='input-group date datePicker'>
										<!-- <input type='text' class="form-control  submitDate" name="submitDate" /> -->
										<form:input path="submitDate" cssErrorClass="has-error form-control " cssClass="form-control  "/>
										 <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>	
							</div>
							<div class="col-xs-3">
								<form:errors path="submitDate" cssClass="form-error" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Cấp độ khóa học:</div>
							<div class="col-xs-5">
								<!-- <textarea rows="2"   class="form-control " name="rank"></textarea> -->
								<form:input path="rank" cssErrorClass="has-error form-control " cssClass="form-control " />
								<form:errors path="rank" cssClass="form-error" />
							</div>
						</div>
						<hr/>
						<h4>Thưởng/Phạt</h4>
						<div class="row">
							<div class="col-xs-4 my-label" >Phần thưởng</div>
							<div class="col-xs-5">
								<!-- <textarea rows="2"   class="form-control " name="award"></textarea> -->
								<form:textarea path="award" cssErrorClass="has-error form-control " cssClass="form-control  "/>
								<form:errors path="award" cssClass="form-error" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Những quy định phạm vào:</div>
							<div class="col-xs-5">
								<!-- <textarea rows="2"   class="form-control " name="penalty"></textarea> -->
								<form:textarea path="penalty" cssErrorClass="has-error form-control " cssClass="form-control  "/>
								<form:errors path="penalty" cssClass="form-error" />
							</div>
						</div>
						
						<div class="row">
							<div class="col-xs-4 my-label" >Đánh giá:</div>
							<div class="col-xs-5">
								<label class="my-label"> 22</label>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 my-label" >Nhận xét:</div>
							<div class="col-xs-6">
								<!-- <textarea rows="2"   class="form-control " name="bonusCourseComment"></textarea> -->
								<form:textarea path="bonusCourseComment" cssErrorClass="has-error form-control " cssClass="form-control  "/>
								<form:errors path="bonusCourseComment" cssClass="form-error" />
							</div>
						</div>
						
						<div class="row">
							<div class="col-xs-4 my-label"></div>
							<div class="col-xs-5">
								<input type="submit" value="Đánh giá" class="btn btn-primary" />
								<input type="reset" value="Nhập lại"  class="btn btn-primary" />
							</div>
						</div>
						
					</div>
						<br/>
					<!-- ------------------------------------------------------- -->
					
					
				</form:form> --%>
			</section>
			
	</div>

	<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>

</div>
	
</body>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.datePicker').datepicker({
				language: 'vi',
		        format: "dd-mm-yyyy",
		        todayBtn: true,
		        autoclose: true,
		        todayHighlight: true
		    });
			
			$('.tag-course').mytag({
				input_name:"trainingCourseAttended",
				btn_name:"Thêm"
			});
			$('.tag-languageScopes').mytag({
				input_name:"languageScope",
				btn_name:"Thêm"
			});
			
			$('.tag-certificates').mytag({
				input_name:"certificate",
				btn_name:"Thêm"
			});
			/* -------------------- TAG TAG TAG TAG --------------- */
			/* var modifyTag = '<span class="modifyTag"><i class="icon ion-edit"></i> <i class="icon ion-close-circled"></i> </span>';
			$('.new-tag-form').hide().attr("disabled", true);
			
			var tagContainer =[];
			/* --------------- */
			/* var oldTagValue="";
			var TagValueString="";
			
			
			$('.tag-box').each(function( index ) {
				console.log("each");
				  $(this).attr("data-tag-id",index);
				  $(this).addClass("tag-class-"+index);
				  tagContainer[index] = new Array();
			});
			console.log(tagContainer.length);
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
			});  */
			
			/* ----------------------------------------------------- */
			
			/* -----------------------------RATY------------------- */
			
			$('.raty-taskCompletion').raty({ 
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
				path: '/resources/images',
				number: 10,
				scoreName: "taskComplexity",
				target:".target-taskComplexity",
				targetKeep : true,
				hints: ['Quá đơn giản','Khá dễ dàng', 'Dễ dàng'
				        ,'Bình thường', 
				        ,'Khó','Rất khó'
				        ,'Phức tạp', 'Rất phức tạp','Khó và phức tạp']
			});
			$('.raty-workingDiscipline').raty({ 
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