$(document).ready(function() {
	// 스크롤 자동 생성 
	const $messageArea = $('msgArea');
	$messageArea.scrollTop($messageArea[0].scrollHeight);


	console.log("STOMP랑 SockJS 준비");
	var roomId = [[${ roomId }]];
	var memberId = [[${ member.member_id }]];
	var memberName = [[${ member.nickname }]];

	console.log(roomId + ", " + memberName);

	var sockJs = new SockJS("/stomp/chat");
	//1. SockJS를 내부에 들고있는 stomp를 내어줌
	var stomp = Stomp.over(sockJs);

	//2. connection이 맺어지면 실행
	stomp.connect({}, function() {
		console.log("STOMP Connection")

		//4. subscribe(path, callback)으로 메세지를 받을 수 있음
		stomp.subscribe("/topic/book/chat/room/" + roomId + ".do", function(chat) {
			console.log("메시지 받기 시작");
			var message = JSON.parse(chat.body);

			var writer = message.member.nickname;
			var str = '';

			if (writer === memberName) {
				str = "<div class='col-6'>";
				str += "<div class='alert alert-secondary' style='color:blue'>";
				str += "<b>" + writer + " : " + message.content + "</b>";
				str += "</div></div>";
			}
			else {
				str = "<div class='col-6'>";
				str += "<div class='alert alert-warning' style='color:black'>";
				str += "<b>" + writer + " : " + message.content + "</b>";
				str += "</div></div>";
			}

			$("#msgArea").append(str);
			console.log("메시지 받기 완료");
		});
	});

	$("#button-send").on("click", function(e) {
		console.log("전송 시작");
		var msg = document.getElementById("msg");
		var now = new Date();
		console.log([[${ member }]]);
		var member = [[${ member }]];

		console.log([[${ member.nickname }]] + ":" + msg.value);
		stomp.send('/app/book/chat/message.do', {}, JSON.stringify({ sent_time: now, content: msg.value, chatRoom: [[${ room }]], member: member }));
		msg.value = '';
		console.log("전송 완료");
	});

	$("#prev-icon").on("click", function(e) {
		loadAjaxFromUrl("/book/chat/rooms.do", "GET", "#dm-tooltip-content");
	});
});
