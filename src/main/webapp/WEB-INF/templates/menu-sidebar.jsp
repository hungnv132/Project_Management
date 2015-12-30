
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="/resources/img/avatar5.png" class="img-circle"
					alt="User Image" />
			</div>
			<div class="pull-left info">
				<p><security:authentication property="principal.fullName"/> </p> 

				<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
			</div>
		</div>
		<!-- search form -->
		<%-- <form action="#" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="q" class="form-control"
					placeholder="Search..." /> <span class="input-group-btn">
					<button type='submit' name='search' id='search-btn'
						class="btn btn-flat">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form> --%>
		<!-- /.search form -->
		<!-- sidebar menu: : style can be found in sidebar.less -->
		 <ul class="sidebar-menu">
			
             
             <li class=" treeview manage-book"><a href="/staff/information">
					<i class="fa fa-info-circle"></i> <span>Thông tin cá nhân</span>
			</a></li>
			</li>
			 <li class="treeview">
              <a href="/staff/report">
               <i class="fa fa-book"></i>
                <span>Báo cáo công việc</span>
                
                <security:authorize access="hasAnyRole('ADMIN','MANAGER')">
                 <i class="fa fa-angle-left pull-right"></i>
                 </security:authorize>
              </a>
              <security:authorize access="hasAnyRole('ADMIN','MANAGER')">
	              <ul class="treeview-menu">
	                <li><a href="/staff/report"><i class="fa fa-circle-o"></i> Báo cáo</a></li>
	                <li><a href="/manager/reports"><i class="fa fa-circle-o"></i> Duyệt báo cáo</a></li>
	              </ul>
              </security:authorize>
            </li>
             <li class="treeview">
              <a href="/staff/requests">
               <i class="fa fa-book"></i>
                <span>Xin đi muộn, nghỉ, OT</span>
                   <security:authorize access="hasAnyRole('ADMIN','MANAGER')"> <i class="fa fa-angle-left pull-right"></i> </security:authorize>
              </a>
              <security:authorize access="hasAnyRole('ADMIN','MANAGER')">
	              <ul class="treeview-menu">
	                <li><a href="/staff/requests"><i class="fa fa-circle-o"></i> Yêu cầu</a></li>
	                <li><a href="/manager/requests"><i class="fa fa-circle-o"></i> Duyệt yêu cầu</a></li>
	              </ul>
            	</security:authorize>   
            </li>
            
			  <security:authorize access="hasAnyRole('ADMIN','MANAGER')">
			<li class=" treeview manage-user">
				<a href="/manager/evaluations">
						<i class="fa fa-user"></i> <span>Đánh giá nhân viên</span>
				</a>
			</li>
			 </security:authorize>
			 
			 
			 <security:authorize access="hasAnyRole('ADMIN','MANAGER')">
				<li class=" treeview manage-project">
					<a href="/manager/projects">
						<i class="fa fa-user"></i> 
						<span >Quản lý dự án</span>
						<i class="fa fa-angle-left pull-right"></i>		
					</a>
					<ul class="treeview-menu "  id="project-list">
					 	
					 
	              	</ul> 
				</li>
			 </security:authorize>
			<security:authorize access="hasAnyRole('ADMIN')">
				<li class=" treeview manage-user"><a href="/admin/manage-user">
						<i class="fa fa-user"></i> <span>Quản lý nhân viên</span>
				</a>
				</li>
			 </security:authorize>
		<!-- 	<li class=" treeview manage-order"><a href="#">
					<i class="fa fa-book"></i> <span>Thống kê</span>
			</a></li> -->
			
			 
		</ul> 
	</section>
	<!-- /.sidebar -->
</aside>
<script>
	$(function() {
		/* $('.${requestScope.selectedMenu}').addClass('active'); */
		$('.manage-project').on('click', function(){
			$('#project-list').empty();
			$('#project-list').append('<li style="border-bottom: 1px solid white;"><a href="/manager/create-project"><i class="icon ion-plus-circled icon-running"></i> Tạo dự án</a></li>');
			$.post('/manager/get-all-projects/', null, function(data) {
				for ( var i in data) {
					$('#project-list').append('<li><a href="/manager/project/'+data[i].id+'"><i class="icon ion-asterisk icon-running"></i>'+data[i].name+'</a></li>');
				}
			});
		});
	});
</script>
