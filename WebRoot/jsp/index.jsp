<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- 引入 Bootstrap -->
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/css/easyui.css" rel="stylesheet">
<link href="<%=path%>/css/footer.css" rel="stylesheet">
<link href="<%=path%>/css/header.css" rel="stylesheet">
<link href="<%=path%>/css/slide.css" rel="stylesheet">
<link href="<%=path%>/css/common.css" rel="stylesheet">
<title>共享大师</title>

</head>

<body>
	<jsp:include page="public/header.jsp" flush="true"></jsp:include>
	<jsp:include page="public/main.jsp" flush="true"></jsp:include>
	<jsp:include page="public/footer.jsp" flush="true"></jsp:include>
	<jsp:include page="public/loginForm.jsp" flush="true"></jsp:include>

	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script src="<%=path%>/js/jquery.js"></script>
	<script src="<%=path%>/js/jquery.easyui.min.js"></script>		
	<script src="<%=path%>/js/jquery.validate.min.js"></script>
	<script src="<%=path%>/js/share.js"></script>
	<!-- 包括所有已编译的插件 -->
	<script src="<%=path%>/js/bootstrap.min.js"></script>
</body>
</html>
