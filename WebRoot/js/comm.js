$(document).ready(function() {

	//加载已发布商品
	loadReleasedComm();
//	//删除商品
//	$('a').click(function(){
//		var commId = $(this).attr('commId');
//		alert(commId);
//		$.ajax({
//			url : "/shareOnline/comm/delComm?commId="+commId,
//			dataType : "json",
//			type : "POST",
//			success : function(data) {
//				if(data.state){
//					alert('删除成功！');
//					/*这里使用的异步请求，当请求成功的时候，重新定位到index界面*/
//					window.location.href = data.list[3].page;
//				}else{
//					alert(data.msg);
//					/*这里使用的异步请求，当请求成功的时候，重新定位到index界面*/
//					window.location.href = data.list[3].page;
//				}
//			},
//			error : function() {
//				alert('删除失败！服务器崩溃');
//			}
//		});
//	});
});
//加载已发布商品
var loadReleasedComm = function() {
	//更新商品
	$.ajax({
		url : "/shareOnline/comm/getReleasedComm",
		dataType : "json",
		type : "POST",
		success : function(data) {
			if(data.state){
				$.each(data.list[3].comms, function(index, comment) {
					//对每一个列表名结果进行遍历
					$('#comms').append('<div class="col-lg-3">' +
					        '<div class="thumbnail" style="margin: 10px 0;">' +
					        '<h4>【'+comment.typeName+'】</h4>' +
					        '<a href="#"> <img src="'+comment.img+'" style="height: 250px;width:250px;">' +
					        '</a>' +
					        '<div class="caption">'+
					        '<h4 style="color:orange;">【 '+comment.commName+'】</h4>' +
					        '<p style="color:orange;">有效时间：</p>' +
					        '<p>'+comment.availableTime+'</p>' +
					        '<p>价格：'+comment.price+'RMB</p>' +
					        '<p style="color:orange;">编辑时间：'+comment.releaseTime+'</p>' +
					        '</div>' +
					        '<a href="/shareOnline/main/reviseComm?commId='+comment.commId+'" class="btn btn-warning ediCommBtn" role="button">编辑</a> ' +
					        '<a href="/shareOnline/comm/delComm?commId='+comment.commId+'" class="btn btn-warning delCommBtn" role="button">删除</a>' +
					        '</div>');
				});
			}else{
				$('#comms').append('<h3 style="color:orange;">'+data.msg+'</h3>')
			}
		},
		error : function() {
			alert('已发布页面 商品列表加载失败！服务器崩溃');
		}
	});
};