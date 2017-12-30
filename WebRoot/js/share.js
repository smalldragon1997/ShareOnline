(function($) {
	$.fn.serializeJson = function() {
		var serializeObj = {};
		var array = this.serializeArray();
		var str = this.serialize();
		$(array).each(function() {
			if (serializeObj[this.name]) {
				if ($.isArray(serializeObj[this.name])) {
					serializeObj[this.name].push(this.value);
				} else {
					serializeObj[this.name] = [ serializeObj[this.name], this.value ];
				}
			} else {
				serializeObj[this.name] = this.value;
			}
		});
		return serializeObj;
	};
})(jQuery);

$(document).ready(function() {
	/*登录表单出现*/
	$('#login-btn').click(function() {
		$('#register-form').fadeOut(300);
		$('#login-form').fadeIn(300);
		$('#wall').fadeIn();
	})
	/*登录表单消失*/
	$('#login-back').click(function() {
		$('#wall').fadeOut();
		$('#login-form').fadeOut(300);
	})
	/*注册表单出现*/
	$('#register-btn').click(function() {
		$('#wall').fadeIn();
		$('#login-form').fadeOut(300);
		$('#register-form').fadeIn(300);
	})
	/*注册表单消失*/
	$('#register-back').click(function() {
		$('#wall').fadeOut();
		$('#register-form').fadeOut(300);
	})
	/*注册表单出现*/
	$('#form-register-btn').click(function() {
		$('#wall').fadeIn();
		$('#login-form').fadeOut(300);
		$('#register-form').fadeIn(300);
	})
	/*注册表单验证*/
	var registerForm = $('#register-form form');
	registerForm.validate({
		rules : { //name
			registerUsername : {
				required : true,
				rangelength : [ 6, 16 ]
			},
			registerEmail : {
				required : true,
				email : true,
				rangelength : [ 4, 30 ]
			},
			registerPassword : {
				rangelength : [ 6, 16 ],
				required : true
			},
			registerPasswordAgain : {
				required : true,
				equalTo : '#registerPassword'
			}
		},
		messages : {
			registerUsername : {
				required : "必须输入用户名",
				rangelength : "用户名长度6~16"
			},
			registerEmail : {
				required : "必须输入邮箱",
				email : "请输入正确的邮箱格式",
				rangelength : "邮箱长度不能大于30"
			},
			registerPassword : {
				rangelength : "密码长度在6-16为位",
				required : '必须输入密码'
			},
			registerPasswordAgain : {
				required : "必须输入确认密码",
				equalTo : '两次输入不一致'
			}
		}
	}
	);
	/*注册表单验证*/
	$('#register-confirm').click(function() {
		if (registerForm.valid()) {
			//防止重复提交
			$(this).attr("disabled", "true").text('正在注册...'); //设置变灰按钮
			var data = $('#register-form form').serializeJson();
			$.ajax({
				url : "/shareOnline/user/register",
				contentType : 'application/json;charset=utf-8',
				dataType : "json",
				data : JSON.stringify(data),
				type : "POST",
				success : function(data) {
					if (data.state) {
						/*这里使用的异步请求，当请求登录成功的时候，重新定位到index界面*/
						alert(data.msg);
						window.location.href = data.list[3].page;
					} else {
						alert(data.msg);
						setTimeout("$('#register-confirm').removeAttr('disabled').text('确认注册')", 1000); //设置1秒后提交按钮 显示
					}
				},
				error : function() {
					alert('注册失败！服务器崩溃');
					setTimeout("$('#register-confirm').removeAttr('disabled').text('确认注册')", 1000); //设置1秒后提交按钮 显示
				}
			});
		}
	});


	/*登录表单验证*/
	var loginForm = $('#login-form form');
	loginForm.validate({
		rules : { //name
			loginEmail : {
				required : true,
				email : true,
				rangelength : [ 4, 30 ],
			},
			loginPassword : {
				rangelength : [ 6, 16 ],
				required : true
			}
		},
		messages : {
			loginEmail : {
				required : "必须输入邮箱",
				email : "请输入正确的邮箱格式",
				rangelength : "邮箱长度不能大于30"
			},
			loginPassword : {
				rangelength : "密码长度在6-16为位",
				required : '必须输入密码'
			}
		}
	}
	);
	/*登录表单验证*/
	$('#login-confirm').click(function() {
		//通过表单验证即可提交请求
		if (loginForm.valid()) {
			//防止重复提交
			$(this).attr("disabled", "true").text('正在登录...'); //设置变灰按钮
			var data = $('#login-form form').serializeJson();
			$.ajax({
				url : "/shareOnline/user/login",
				contentType : 'application/json;charset=utf-8',
				dataType : "json",
				data : JSON.stringify(data),
				type : "POST",
				success : function(data) {
					if (data.state) {
						/*这里使用的异步请求，当请求登录成功的时候，重新定位到index界面*/
						window.location.href = data.list[3].page;
					} else {
						alert(data.msg);
						setTimeout("$('#login-confirm').removeAttr('disabled').text('登录')", 1000); //设置1秒后提交按钮 显示
					}
				},
				error : function() {
					alert('登陆失败！服务器崩溃');
					setTimeout("$('#login-confirm').removeAttr('disabled').text('登录')", 1000); //设置1秒后提交按钮 显示
				}
			});
		}
	});
	//退出登录
	$('#exit-btn').click(function() {
		$.ajax({
			url : "/shareOnline/user/exit",
			dataType : "json",
			type : "POST",
			success : function(data) {
				if (data.state) {
					/*这里使用的异步请求，当请求成功的时候，重新定位到index界面*/
					alert(data.msg);
					window.location.href = data.list[3].page;
				} else {
					alert(data.msg);
				}
			},
			error : function() {
				alert('退出失败！服务器崩溃');
			}
		});
	});
	//发布时是否登录
	$('#releaseBtn').click(function() {
		//按钮名称
		isLogin("release");
	});
	//查看已发布时是否登录
	$('#releasedBtn').click(function() {
		//按钮名称
		isLogin("released");
	});
	//点击搜索时是否有内容
	$('#searchBtn').click(function(){
		var data = $('#searchInput').val();
		if(data==''){
			return false
		}
	});
	//加载热门共享列表
	loadHotList();
	//加载轮图列表
	loadSlideList();
	//加载主页的商品列表名indexCommListName
	loadCommList();
//	//加载每个商品列表的商品
//	loadIndexCommList();
});
//判断是否登录ajax
var isLogin = function(data) {
	$.ajax({
		url : "/shareOnline/main/getLoginState",
		contentType : 'application/json;charset=utf-8',
		data : data,
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				/*这里使用的异步请求，当请求成功的时候，重新定位到index界面*/
				window.location.href = data.list[3].page;
			} else {
				alert(data.msg);
				$('#wall').fadeIn();
				$('#login-form').fadeIn(300);
			}
		},
		error : function() {
			alert('点击失败！服务器崩溃');
		}
	});
}
//获取热门共享列表
var loadHotList = function() {
	$.ajax({
		url : "/shareOnline/main/indexHotList",
		dataType : "json",
		type : "POST",
		success : function(data) {
			$.each(data.list[3].hotList, function(index, comment) {
				//对每一个列表名结果进行遍历
				$('#hotList').append('<li><a href="/shareOnline/main/getCommPageByType?typeId='+comment.typeId+'&query=">' + comment.typeName + '</a></li>');
			});
		},
		error : function() {
			alert('主页的热门列表 加载失败！服务器崩溃');
		}
	});
}
//获取轮播图列表
var loadSlideList = function() {
	$.ajax({
		url : "/shareOnline/main/indexSlideList",
		dataType : "json",
		type : "POST",
		success : function(data) {
			var i = 0;
			$.each(data.list[3].slideList, function(index, comment) {
				//对每一个列表名结果进行遍历
				if (i == 0) {
					$('#slideCount').append('<li data-target="#myCarousel" data-slide-to="' + i + '" class="active"></li>');
					$('#slideImage').append('<div class="item active">\n' +
						'<a href="/shareOnline/main/getCommPageByType?typeId='+comment.typeId+'&query="><img src="../images/type/' + comment.typeId + '.jpg" style="height: 500px;width:930px;"></a>\n' +
						'<div class="carousel-caption">' + comment.typeName + '</div>\n' +
						'</div>');
				} else {
					$('#slideCount').append('<li data-target="#myCarousel" data-slide-to="' + i + '"></li>');
					$('#slideImage').append('<div class="item">\n' +
						'<a href="/shareOnline/main/getCommPageByType?typeId='+comment.typeId+'&query="><img src="../images/type/' + comment.typeId + '.jpg" style="height: 500px;width:930px;"></a>\n' +
						'<div class="carousel-caption">' + comment.typeName + '</div>\n' +
						'</div>');
				}

				i++;
			});
			//设置轮播图跳转时间
			$('#myCarousel').carousel({
				interval : 2000
			});
		},
		error : function() {
			alert('主页的轮播图 加载失败！服务器崩溃');
		}
	});
}
//加载主页的商品列表indexCommList
var loadCommList = function() {
	$.ajax({
		url : "/shareOnline/main/indexCommList",
		dataType : "json",
		type : "POST",
		success : function(data) {
			//遍历带有commListId的元素
			$("[commListId]").each(function(index,comment){
				//更改当前的commListId值
				$(comment).attr('commListId',data.list[3].commNameList[index].typeId);
			});
			//遍历带有commListName的class的h2元素
			$("div.commListName").each(function(index,comment){
				//更改当前的name和跳转的链接
				$(comment).append('<div class="col-lg-11">' +
					'<h2> '+data.list[3].commNameList[index].typeName+'</h2>' +
					'</div>' +
					'<div class="col-lg-1">' +
					'<h2><a href="/shareOnline/main/getCommPageByType?typeId='+data.list[3].commNameList[index].typeId+'&query=" class="btn btn-warning ediCommBtn" role="button">查看更多</a></h2>' +
					'</div>');
			});
			
			//遍历带有commList的class的div元素
			$("div.commList").each(function(index,current){
				if(!data.list[3].commList[index][0]){
					$(current).append('<h3 style="color:orange;">此类账号还没有人发布过...</h3>');
				}else{
					//遍历更新某类商品列表
					$.each(data.list[3].commList[index], function(index, comment) {
						//遍历更新商品列表
						$(current).append('<div class="col-lg-3">' +
						        '<div class="thumbnail" style="margin: 10px 0;">' +
						        '<a href="#" commId="'+comment.commId+'"> <img src="'+comment.img+'" style="height: 250px;width:250px;">' +
						        '</a>' +
						        '<div class="caption">'+
						        '<h4>【 '+comment.commName+'】</h4>' +
						        '<p style="color:orange;">有效时间：</p>' +
						        '<p>'+comment.availableTime+'</p>' +
						        '<p>价格：'+comment.price+'RMB</p>' +
						        '<p style="color:orange;">编辑时间：'+comment.releaseTime+'</p>' +
						        '</div>' +
						        '</div>')
					});
				}
				
			});
		},
		error : function() {
			alert('主页的商品列表 加载失败！服务器崩溃');
		}
	});
};
//获取商品类型的商品列表
var loadComms = function(comment) {
	$.ajax({
		url : "/shareOnline/main/getIndexCommList",
		contentType : 'application/json;charset=utf-8',
		data : JSON.stringify({
			commListId : comment.typeId
		}),
		dataType : "json",
		type : "POST",
		success : function(data) {
			$.each(data, function(index, comment) {
				$('div[commListId="' + comment.commListId + '"]').append('<div class="col-lg-3">\n' +
					'<div class="thumbnail" style="margin: 10px 0;">\n' +
					'<a href="javascript:void(0)">' +
					'<img src="' + comment.imgUrl + '" style="height: 150px;">' +
					'</a>' +
					'<div class="caption">' +
					'<h3>' + comment.title + '</h3>' +
					'<p>售价:' + comment.price + '</p>' +
					'<p>发布人:' + comment.userName + '</p>' +
					'</div>' +
					'</div>' +
					'</div>');
			});
		},
		error : function() {
			alert('主页的商品列表 加载失败！服务器崩溃');
		}
	});
}
