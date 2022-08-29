$(document).ready(function() {
	// 나중에 따로 함수로 빼서 click에도 붙이자
	$.ajax({
		url: '/book/chat/rooms.do',
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
		$("#tooltip-content").replaceWith(result);
	});
});

$("#button").click(function() {
	$("#tooltip").slideDown();
});

$("body")
	.on(
		"click",
		function(e) {
			var $tgPoint = $(e.target);
			console.log($tgPoint);
			var $popCallBtn = (($tgPoint.attr('id') === "button") ? true
				: false);
			console.log($popCallBtn);
			var $popArea = (($tgPoint.closest("#tooltip")
				.attr('id') === "tooltip") ? true
				: false);
			console.log($popArea);
			console.log($tgPoint.closest("#tooltip").attr(
				'id'));
			if (!$popCallBtn && !$popArea) {
				$("#tooltip").slideUp();
			}
		});