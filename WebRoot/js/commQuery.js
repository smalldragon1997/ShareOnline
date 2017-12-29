$(document).ready(function() {

	//搜索的商品
	loadQueryComm();

});
//加载搜索的商品
var loadQueryComm = function() {
	//更新商品
	$.ajax({
		url : "/shareOnline/comm/queryComm",
		dataType : "json",
		type : "POST",
		success : function(data) {
			$('div.typeName').append('<h2>'+data.list[3].typeName+'</h2>');
			if(data.state){
				$.each(data.list[3].comms, function(index, comment) {
					//对每一个列表名结果进行遍历
					$('#commsByType').append('<div class="col-lg-3">' +
					        '<div class="thumbnail" style="margin: 10px 0;">' +
					        '<h4>【'+comment.typeName+'】</h4>' +
					        '<a href="#"> <img src="'+comment.img+'" style="height: 250px;width:250px;">' +
					        '</a>' +
					        '<div class="caption">'+
					        '<h4>【 '+comment.commName+'】</h4>' +
					        '<p style="color:orange;">有效时间：</p>' +
					        '<p>'+comment.availableTime+'</p>' +
					        '<p>价格：'+comment.price+'RMB</p>' +
					        '<p style="color:orange;">编辑时间：'+comment.releaseTime+'</p>' +
					        '</div>' + '</div>');
				});
			}else{
				$('#commsByType').append('<h3 style="color:orange;">'+data.msg+'</h3>')
			}
		},
		error : function() {
			alert('已发布页面 商品列表加载失败！服务器崩溃');
		}
	});
};
