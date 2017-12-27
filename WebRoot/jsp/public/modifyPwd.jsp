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
                <h2>修改密码</h2>
            </div>
            <form class="form-horizontal" action="#">
                <div class=" form-group row">
                    <div class="col-lg-2">
                        <h3>旧密码：</h3>
                    </div>
                    <div class="col-lg-2">
                        <h3><input type="text" class="form-control" placeholder="输入旧的密码"></h3>
                    </div>
                </div>
                <div class="form-group row line">
                    <div class="col-lg-2">
                        <h3>新密码：</h3>
                    </div>
                    <div class="col-lg-2">
                        <h3><input type="text" class="form-control" placeholder="输入新的密码"></h3>
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
