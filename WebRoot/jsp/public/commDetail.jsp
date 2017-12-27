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
                <div class="col-lg-4">
                    <h2>发布人：<a href="#">吴小龙</a></h2>
                </div>
                <div class="col-lg-8">
                    <h2>最近编辑时间：2017-5-5</h2>
                </div>
            </div>
            <div class="row ">
                <div class="col-lg-8">
                    <div class="thumbnail" style="margin-top: 10px;">
                        <img src="" style="height: 500px;">
                    </div>
                </div>
                <div class="col-lg-4">
                    <h3>标题：快来看看</h3>
                    <h3>类型：英雄联盟</h3>
                    <h3>有效时间：</h3>
                    <h3>2017-5-5 至 2017-5-5</h3>
                    <h3>价格:998</h3>
                    <h3>
                        <a href="#" class="btn btn-warning" role="button" style="font-size: 20px">立即购买</a>
                    </h3>
                    <div class="row line">
                        <h3>该发布者的其他已发布</h3>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="thumbnail commPic">
                                <a href="#">
                                    <img src="">
                                </a>
                                <div class="caption">
                                    <h3></h3>
                                    <p></p>
                                    <p id="price"></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row line">
                <div class="col-lg-8">
                    <h2>卖家介绍</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <p>奥斯卡接电话空间撒活动开始安静的贾克斯活动空间啊上的扩散看到看 </p>
                </div>
            </div>
		</div>
		<div class="col-lg-2"></div>
	</div>
</div>
