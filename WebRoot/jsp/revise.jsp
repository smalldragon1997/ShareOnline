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
<link href="<%=path%>/css/common.css" rel="stylesheet">
<link href="<%=path%>/css/footer.css" rel="stylesheet">
<link href="<%=path%>/css/header.css" rel="stylesheet">
<link href="<%=path%>/css/jquery.datetimepicker.css" rel="stylesheet">
<title>商品修改</title>

</head>

<body>
	<jsp:include page="public/header.jsp" flush="true"></jsp:include>
	<jsp:include page="public/commRevise.jsp" flush="true"></jsp:include>
	<jsp:include page="public/footer.jsp" flush="true"></jsp:include>

	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script src="<%=path%>/js/jquery.js"></script>
	<script src="<%=path%>/js/jquery.validate.min.js"></script>
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<script src="<%=path%>/js/jquery.datetimepicker.full.js"></script>
	<script src="<%=path%>/js/share.js"></script>
	<script src="<%=path%>/js/revise.js"></script>
	
</body>
</html>
