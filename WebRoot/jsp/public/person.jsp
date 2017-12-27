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
			<div class="row line">
                <h2>个人信息</h2>
            </div>
            <div class="row line">
                <div class="col-lg-2">
                    <h3>用户名：</h3>
                </div>
                <div class="col-lg-4">
                    <h3>哈哈</h3>
                </div>
                <div class="col-lg-2">
                    <h3>注册时间：</h3>
                </div>
                <div class="col-lg-4">
                    <h3>哈哈</h3>
                </div>
            </div>
            <div class="row  line" style="height: 500px;">
                <div class="col-lg-2">
                    <h3>个人简介：</h3>
                </div>
                <div class="col-lg-10">
                    <h3></h3>
                </div>
            </div>
            <div class="row ">
                <div class="col-lg-2 line">
                    <a href="#" class="btn btn-warning" role="button" style="font-size: 20px;margin: 10px">修改资料</a>
                </div>
                <div class="col-lg-10 line">
                    <a href="#" class="btn btn-warning" role="button" style="font-size: 20px;margin: 10px">修改密码</a>
                </div>
            </div>
		</div>
		<div class="col-lg-2"></div>
	</div>
</div>
