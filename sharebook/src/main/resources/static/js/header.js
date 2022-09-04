import {
	computePosition,
	flip,
	shift,
	offset,
} from 'https://cdn.skypack.dev/@floating-ui/dom@0.2.0';

const button = document.querySelector('#button');
const tooltip = document.querySelector('#tooltip');

function setUpTooltip() {
	computePosition(button, tooltip, {
		placement: 'bottom',
		middleware: [offset(8), flip(), shift({ padding: 5 })],
	}).then(({ x, y }) => {
		Object.assign(tooltip.style, {
			left: `${x}px`,
			top: `${y}px`,
		});
	});
}

function update() {
	computePosition(button, tooltip, {

	}).then(({ x, y, placement, middlewareData }) => {

	});

	console.log("위치 update 성공");
}


/**
 *  AJAX 관련 JS
 */
$("#button").click(function() {
	loadAjaxFromUrl("/book/chat/rooms.do", "GET", "#tooltip-content");

	setUpTooltip();
	update();
	$("#tooltip").slideDown();
});

/**
 *  다른 곳 클릭하면 팝업창 사라지는 함수
 */
function clearBox(targetId) {
	$(targetId).html("")
	console.log("tooltip 지우기 완료");
	
	var newDiv = document.createElement("div");
	newDiv.setAttribute("id", "tooltip-content");
	document.querySelector(targetId).appendChild(newDiv);
	console.log("tooltip 생성 완료");
}

$("body")
	.on(
		"click",
		function(e) {
			var $tgPoint = $(e.target);
			var $popCallBtn = ((($tgPoint.attr('id') === "button") || $tgPoint.attr('id') === "dm-icon") ? true
				: false);
			var $popArea = (($tgPoint.closest("#tooltip")
				.attr('id') === "tooltip") ? true
				: false);
			console.log($tgPoint.attr('id') + " " + $popCallBtn + " " + $popArea);
			if (!$popCallBtn && !$popArea) {
				$("#tooltip").slideUp();
				clearBox("#tooltip");
			}
		});