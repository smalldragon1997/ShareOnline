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
	//时间插件的初始化
	$('.datetimepicker').datetimepicker({
		format : "Y-m-d H:i", //格式化日期
		timepicker : true, //关闭时间选项
		yearStart : new Date().getFullYear(), //设置最小年份
		yearEnd : 2050, //设置最大年份
		todayButton : true, //关闭选择今天按钮
	});
	$.datetimepicker.setLocale('ch'); //设置显示中文

	//时间选择
	$('#startBtn').click(function() {
		$('#startInput').focus();
	});
	//时间选择
	$('#endBtn').click(function() {
		$('#endInput').focus();
	});
	//发布表单验证
	var releaseForm = $('#reviseFrom');
	releaseForm.validate({
		rules : { //name
			commTitle : {
				required : true,
				rangelength : [2,10]
			},
			commPrice : {
				range : [ 0.1, 999999 ],
				required : true
			},
			startTime : {
				required : true,
				//date : true
			},
			endTime : {
				required : true,
				//date : true
			},
			introduce : {
				required : true,
				rangelength : [10,255]
			}
		},
		messages : {
			commTitle : {
				required : "请输入商品标题",
				rangelength : "商品标题长度1~10个字"
			},
			commPrice : {
				range : "请输入0~999999范围",
				required : "请输入商品价格"
			},
			startTime : {
				required : "请选择开始时间",
				//date : "请重新选择时间"
			},
			endTime : {
				required : "请选择结束时间",
				//date : "请重新选择时间"
			},
			introduce : {
				required : "请输入商品介绍",
				rangelength : "介绍长度10~255字符"
			}
		}
	}
	);
	//提交验证
	$('#reviseSubmitBtn').click(function(){
		if(releaseForm.valid()){
			if($('#startInput').val() >= $('#endInput').val()){
				$('#timeSelect').text('日期错误：结束时间不得早于开始时间');
				return false;
			}
			$('#timeSelect').text('');
			//防止重复提交
			$(this).attr("disabled", "true").text('正在修改...'); //设置变灰按钮
			var data = $('#reviseFrom').serializeJson();
			alert(JSON.stringify(data));
			$.ajax({
				url : "/shareOnline/comm/reviseComm",
				contentType : 'application/json;charset=utf-8',
				dataType : "json",
				data : JSON.stringify(data),
				type : "POST",
				success : function(data) {
					if (data.state) {
						/*这里使用的异步请求，当请求登录成功的时候，重新定位到跳转界面*/
						window.location.href = data.list[3].page;
					} else {
						alert(data.msg);
						setTimeout("$('#submitBtn').removeAttr('disabled').text('确认修改')", 1000); //设置1秒后提交按钮 显示
					}
				},
				error : function() {
					alert('发布失败！服务器崩溃');
					setTimeout("$('#submitBtn').removeAttr('disabled').text('确认修改')", 1000); //设置1秒后提交按钮 显示
				}
			});
		}
	});
});

function uploadImg(input) { //用于进行图片上传，返回地址
	var fileName = $(input).val(); //获得图片的伪路径和名字后缀名
	if (fileName == null || fileName == undefined || fileName == '') { //若为空不进行操作
		return false;
	}
	if (!/\.(?:png|jpg|PNG|JPG)$/.test(fileName)) //校验文件格式
	{
		alert("类型必须是图片(.png|jpg|PNG|JPG)");
		$(input).val('');
		return false;
	}
	var data = new FormData(); // 封装图片数据

	$.each($(input)[0].files, function(i, file) { //只有一张图片
		data.append('imgUpload', file); // 封装
	});
	$.ajax({
		type : "POST",
		url : "/shareOnline/comm/uploadImg",
		data : data,
		cache : false,
		contentType : false, //不可缺
		processData : false, //不可缺
		dataType : "json",
		success : function(date) {
			if (date.state) {
				$("#imgShow").attr("src", date.imgUrl + '?time=' + new Date().getTime()); //显示图片
				$('#imgName').val(date.imgName);
			} else {
				alert(date.msg);
				$(input).val('');
			}
		},
		error : function() {
			alert("上传失败，服务器崩溃");
			$(input).val('');
		}
	});
}
