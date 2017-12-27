<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.isLogin ne 'yes' }">
	<div id="wall" class="wall" ></div>
	<div id="login-form" class="container" style="display: none;">
		<div class="row">
			<div class="col-lg-3 login-form ">
				<form class="form-horizontal" href="#">
					<br> <img src="<%=path%>/images/logo_orange.png"> <br>
					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="loginEmail"
								placeholder="请输入账号">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">密码：</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name="loginPassword"
								placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label>
									<input type="checkbox" name="loginRemenber" value="true"> 7天内免登录 
									<input type="hidden" name="loginRemenber" value="false">
									<input type="hidden" name="loginRemenber" value="true">
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default" id="login-confirm" onclick="login()">登录</button>
							<button type="button" class="btn btn-warming" id="form-register-btn">注册</button>
							<button type="button" class="btn btn-warming" id="login-back">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="register-form" class="container" style="display: none;">
		<div class="row">
			<div class="col-lg-3 register-form ">
				<form class="form-horizontal">
					<br> <img src="<%=path%>/images/logo_orange.png"> <br>
					<div class="form-group">
						<label class="col-sm-3 control-label">用户名：</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="registerUsername"
								placeholder="请输入用户名" required="true">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">邮箱：</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="registerEmail"
								placeholder="请输入账号（登录用）" required="true">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">密码：</label>
						<div class="col-sm-6">
							<input type="password" class="form-control"
								name="registerPassword" placeholder="请输入密码"
								id="registerPassword" required="true">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">确认密码：</label>
						<div class="col-sm-6">
							<input type="password" class="form-control"
								name="registerPasswordAgain" placeholder="请输入确认密码"
								required="true">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default"
								id="register-confirm">确认注册</button>
							<button class="btn btn-warming" id="register-back">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</c:if>
