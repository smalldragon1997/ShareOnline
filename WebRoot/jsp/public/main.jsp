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
			<!-- 热门 轮播图 开始 -->
			<div class="row list">
				<div class="col-lg-3" id="list-nav">
					<ul class="nav nav-pills nav-stacked" id="hotList">
						<li
							style="font-size: 30px;color: #ff7a01;font-weight: bold;text-align:center;">热门共享</li>
					</ul>
				</div>
				<div class="col-lg-9">
					<div id="myCarousel" class="carousel slide">
						<!-- 轮播（Carousel）指标 计数器-->
						<ol class="carousel-indicators" id="slideCount">
						</ol>
						<!-- 轮播（Carousel）项目 -->
						<div class="carousel-inner" id="slideImage"></div>
						<!-- 轮播（Carousel）导航 方向键-->
						<a class="carousel-control left" href="#myCarousel"
							data-slide="prev" style="line-height: 500px">&lsaquo;</a> <a
							class="carousel-control right" href="#myCarousel"
							data-slide="next" style="line-height: 500px">&rsaquo;</a>
					</div>
				</div>
			</div>
			<!-- 热门 轮播图 结束 -->
			<div commListId="">
				<div class="row line">
					<h2 class="commListName"></h2>
				</div>
				<div class="row commList"></div>
			</div>
			<div commListId="">
				<div class="row line">
					<h2 class="commListName"></h2>
				</div>
				<div class="row commList"></div>
			</div>
			<div commListId="">
				<div class="row line">
					<h2 class="commListName"></h2>
				</div>
				<div class="row commList"></div>
			</div>
			<div commListId="">
				<div class="row line">
					<h2 class="commListName"></h2>
				</div>
				<div class="row commList"></div>
			</div>
			<div commListId="">
				<div class="row line">
					<h2 class="commListName"></h2>
				</div>
				<div class="row commList"></div>
			</div>
			<div commListId="">
				<div class="row line">
					<h2 class="commListName"></h2>
				</div>
				<div class="row commList"></div>
			</div>
			<div commListId="">
				<div class="row line">
					<h2 class="commListName"></h2>
				</div>
				<div class="row commList"></div>
			</div>
			<div commListId="">
				<div class="row line">
					<h2 class="commListName"></h2>
				</div>
				<div class="row commList"></div>
			</div>
		</div>
		<div class="col-lg-2"></div>
	</div>
</div>
