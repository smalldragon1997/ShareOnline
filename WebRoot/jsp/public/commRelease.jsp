<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<div class="container-fluid">
	<div class="row">
		<div class="col-lg-3"></div>
		<div class="col-lg-6">
			<div class="row line">
				<h2>发布</h2>
			</div>
			<div class="row ">
				<form class="form-horizontal" id="releaseFrom">
					<div class=" form-group row">
						<div class="col-lg-2">
							<h3>类型：</h3>
						</div>
						<div class="col-lg-10">
							<h3>
								<select class="form-control" name="typeId" id="typeList">
									<option value="0">--请选择账号类型--</option>
								</select>
							</h3>

							<input type="hidden" name="userId"
								value="${sessionScope.user.userId}">
						</div>
					</div>
					<div class=" form-group row">
						<div class="col-lg-2">
							<h3>标题：</h3>
						</div>
						<div class="col-lg-10">
							<h3>
								<input type="text" class="form-control" name="commTitle"
									placeholder="输入商品标题">
							</h3>
						</div>
					</div>
					<div class=" form-group row">
						<div class="col-lg-2">
							<h3>价格：</h3>
						</div>
						<div class="col-lg-10">
							<h3>
								<input type="text" class="form-control" name="commPrice"
									placeholder="输入商品价格">
							</h3>
						</div>
					</div>
					<div class=" form-group row">
						<div class="col-lg-2">
							<h3>有效时间：</h3>
						</div>
						<div class="col-lg-10">
							<h3>
								开始时间:<input class="datetimepicker" id="startInput"
									name="startTime">
								<button class="btn btn-warning" type="button" id="startBtn">选择时间</button>
							</h3>
							<h3>
								结束时间:<input class="datetimepicker" id="endInput" name="endTime">
								<button class="btn btn-warning" type="button" id="endBtn">选择时间</button>
							</h3>
							<h3 id="timeSelect" style="color:red;"></h3>
						</div>
					</div>

					<div class="form-group row">
						<div class="col-lg-2">
							<h3>简介：</h3>
						</div>
						<div class="col-lg-10">
							<h3>
								<textarea class="form-control" rows="8" placeholder="介绍一下商品吧"
									name="introduce" style="resize:none;"></textarea>
							</h3>
						</div>
					</div>
					<input type="hidden" name="imgName" id="imgName">
				</form>
			</div>
			<div class="row line">
				<form>
					<div class="form-group row">
						<div class="col-lg-2">
							<h3>上传图片：</h3>
						</div>
						<div class="col-lg-10">
							<h3>
								<input type="file" id="imgUpload" onchange="uploadImg(this);">
								<span><img id="imgShow" src="" width="120" height="120" /></span>
							</h3>
						</div>
					</div>
				</form>
			</div>
			<div class="row">
				<div class="col-lg-10">
					<h2>
						<button type="submit" class="btn btn-warning"
							id="releaseSubmitBtn">确认发布</button>
					</h2>
				</div>
			</div>
		</div>
		<div class="col-lg-3"></div>
	</div>
</div>
