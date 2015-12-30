<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		
		
		<c:url value="/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js" var="bootstrap_min_js" />
		<c:url value="/resources/js/jquery-2.1.4.min.js" var="jquery214" />
		<c:url value="/resources/js/jquery-ui.min.js" var="jquery_ui_js" />
		<c:url value="/resources/js/jquery.dataTables.min.js" var="datatable_js" />
		<c:url value="/resources/js/app.min.js" var="app_js" />
		<c:url value="/resources/js/bootstrap-datetimepicker.js" var="datetimepicker_js" />
		<c:url value="/resources/js/bootstrap-datepicker.min.js" var="datepicker_js" />
		<c:url value="/resources/locales/bootstrap-datepicker.vi.min.js" var="datepicker_vi_js" />
		<c:url value="/resources/js/jquery.slimscroll.min.js" var="slimscroll_js" />
		<c:url value="/resources/js/jquery.timepicker.min.js" var="timepicker_js" />
		
		<c:url value="/resources/js/date_format.js" var="date_format_js" />		
		<c:url value="/resources/js/moment.js" var="moment_js" />
		<c:url value="/resources/js/jquery.raty.js" var="raty_js" />
		<c:url value="/resources/js/jquery.tags.js" var="tags_js" />
		<c:url value="/resources/js/dropzone.js" var="dropzone_js" />
		<c:url value="/resources/js/jquery.nestable.js" var="nestable_js" />
		
		<c:url value="/resources/js/jquery.knob.min.js" var="knob_js" />
		
		<c:url value="/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css" var="bootstrap_min_css" />
		<c:url value="/resources/css/jquery-ui.min.css" var="jquery_ui_css" />
		<c:url value="/resources/css/ionicons.min.css" var="font_ionicons_css" />
		<c:url value="/resources/css/ionicons-animation.min.css" var="font_ionicons_animation_css" />
		<c:url value="/resources/css/font-awesome.min.css" var="font_awesome_css" />
		<c:url value="/resources/css/admin.min.css" var="admin_css" />
		<c:url value="/resources/css/_all-skins.min.css" var="all_skin_css" />
		<c:url value="/resources/css/jquery.dataTables.min.css" var="datatable_css" />
		<c:url value="/resources/css/bootstrap-datetimepicker.min.css" var="datetimepicker_css" />
		<c:url value="/resources/css/bootstrap-datepicker3.min.css" var="datepicker3_css" />
		<c:url value="/resources/css/jquery.timepicker.css" var="timepicker_css" />
		<c:url value="/resources/css/jquery.raty.css" var="raty_css" />
		<c:url value="/resources/css/jquery.tags.css" var="tags_css" />
		<c:url value="/resources/css/jquery.nestable.css" var="nestable_css" />
		<c:url value="/resources/css/mystyle.css" var="mystyle_css" />
		
		
		
		
		<link href="${bootstrap_min_css}" rel="stylesheet" >
		<%-- <link href="${jquery_ui_css}" rel="stylesheet" > --%>
		<link href="${font_ionicons_css}" rel="stylesheet" >
		<link href="${font_ionicons_animation_css}" rel="stylesheet" >
		<link href="${font_awesome_css}" rel="stylesheet" >
		<link href="${admin_css}" rel="stylesheet" >
		<link href="${all_skin_css}" rel="stylesheet" >
		<link href="${datatable_css}" rel="stylesheet" >
		<link href="${datetimepicker_css}" rel="stylesheet" >
		<link href="${datepicker3_css}" rel="stylesheet" >
		<link href="${timepicker_css}" rel="stylesheet" >
		<link href="${raty_css}" rel="stylesheet" >
		<link href="${tags_css}" rel="stylesheet" >
		<link href="${nestable_css}" rel="stylesheet" >
		<link href="${mystyle_css}" rel="stylesheet" >
		
		
		<script src="${jquery214}"></script>
		<script src="${jquery_ui_js}"></script>
		<script src="${bootstrap_min_js} "></script>
		<script src="${app_js} "></script>
		<script src="${datatable_js} "></script>
		<script src="${datetimepicker_js} "></script>
		<script src="${datepicker_js} "></script>
		<script src="${datepicker_vi_js} "></script>
		<script src="${date_format_js} "></script>
		<script src="${moment_js} "></script>
		<script src="${timepicker_js} "></script>
		<script src="${slimscroll_js} "></script>
		<script src="${simplecalendar_js} "></script>
		<script src="${raty_js} "></script>
		<script src="${tags_js} "></script>
		<script src="${dropzone_js} "></script>
		<script src="${nestable_js} "></script>
		<script src="${knob_js} "></script>								
		

		
    
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="/resources/js/html5shiv.min.js"></script>
        <script src="/resources/js/respond.min.js"></script>
    <![endif]-->