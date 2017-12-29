<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<div class="container-fluid">
	<div class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<div class="row line typeName">

			</div>
			<div class="row line" id="commsByType">
				
			</div>

			</div>
		</div>
		<div class="col-lg-2"></div>
	</div>
</div>
