$(".list-content-link").click(function() {
	var roomId = $(this).children("#hiddenRoomId").val();

	$.ajax({
		url: ('/book/chat/room/' + roomId + '.do'),
		type: 'GET',
		data: {
			query: 'ajax'
		},
		dataType: 'text',
		beforeSend: function(jqXHR) {
			console.log("ajax 호출전");
		},
		success: function(data) {
			console.log("호출성공");
			console.log(data);
		},
		error: function(jqXHR) {
			console.log("호출실패");
		}
	}).done(function(result) {
		$("#room-list").replaceWith(result);
	});
}); 