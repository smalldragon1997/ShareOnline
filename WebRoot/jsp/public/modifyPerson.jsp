<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<div class="container-fluid">
	<div class="row ">
		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<div class="row line">
                <h2>修改个人信息</h2>
            </div>
            <form class="form-horizontal" action="#">
                <div class=" form-group row">
                    <div class="col-lg-2">
                        <h3>用户名：</h3>
                    </div>
                    <div class="col-lg-2">
                        <h3><input type="text" class="form-control" placeholder="输入新的用户名"></h3>
                    </div>
                </div>
                <div class="form-group row line">
                    <div class="col-lg-2">
                        <h3>个人简介：</h3>
                    </div>
                    <div class="col-lg-10">
                        <h3><textarea class="form-control" rows="8" placeholder="介绍一下自己吧"></textarea></h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-warning" id="modify-pwd-confirm">确认修改</button>
                    </div>
                </div>
            </form>
		</div>
		<div class="col-lg-2"></div>
	</div>
</div>
