<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid">
	<div class="row header-top">
		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<div class="row">
				<div class="col-lg-6 header-top-left">联系我们QQ：842234092</div>
				<div class="col-lg-6 text-right header-top-right">
					<c:if test="${sessionScope.isLogin ne 'yes' }">
						游客，请<a id="login-btn" href="javascript:void(0)"> 登录 </a>或 <a id="register-btn" href="javascript:void(0)">注册</a>
					</c:if>
					<c:if test="${sessionScope.isLogin eq 'yes' }">
						欢迎你，${sessionScope.user.userName}  <a href="javascript:void(0)" id="exit-btn">退出 </a>
					</c:if>
				</div>
			</div>
		</div>
		<div class="col-lg-2"></div>
	</div>
	<div class="row header-middle">
		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<div class="row">
			<h1></h1>
			</div>
			<div class="row">
			<h1></h1>
			</div>
			<div class="row">
				<div class="col-lg-3 ">
					<a href="/shareOnline/user/index"><img src="<%=path%>/images/logo_orange.png"></a>
				</div>
				<div class="col-lg-4">
					<form action="/shareOnline/main/getCommPageByType" method="get" style="display: inline">
						<input class="header-middle-center-input input-lg" placeholder="请输入搜索内容..." type="text" name="query" id="searchInput"/>
						<input type="hidden" name="typeId" value="1">
						<input class="header-middle-center-btn input-lg" type="submit" value="Search" id="searchBtn">
					</form>
				</div>
				<div class="col-lg-5 ">
					<button class="header-middle-right-btn" id="releasedBtn">已发布</button>
					<button class="header-middle-right-btn" id="billBtn">已交易</button>
					<button class="header-middle-right-btn" id="boughtBtn">已购买</button>
					<button class="header-middle-right-btn" id="personalBtn">我的资料</button>
					<button class="header-middle-right-btn" id="releaseBtn">我要发布</button>
				</div>
			</div>
			<div class="row">
			<div class="row">
			<h1></h1>
			</div>
			<div class="row">
			<h1></h1>
			</div>
				<div class="header-bottom-bar bar">
					<ul class="nav nav-pills">
						<li><a href="/shareOnline/user/index">主页</a></li>
						<li><a href="#">分类</a></li>
						<li><a href="#">关于我们</a></li>
						<li><a href="#">联系我们</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-lg-2"></div>
	</div>
</div>

