import {
	computePosition,
	flip,
	shift,
	offset
} from 'https://cdn.skypack.dev/@floating-ui/dom@0.2.0';

const dmButton = document.querySelector('#dm-button');
const dmTooltip = document.querySelector('#dm-tooltip');
const categoryButton = document.querySelector('#category-button');
const categoryTooltip = document.querySelector('#category-tooltip');

function setUpTooltip(button, tooltip) {
	computePosition(button, tooltip, {
		placement: 'bottom',
		middleware: [offset(8), shift()]
	}).then(({ x, y }) => {
		Object.assign(tooltip.style, {
			left: `${x}px`,
			top: `${y}px`
		});
	});
}

/**
 *  AJAX 관련 JS
 */
$("#dm-button").click(function() {
	loadAjaxFromUrl("/book/chat/rooms.do", "GET", "#dm-tooltip-content");

	$("#dm-tooltip").slideDown();
	setUpTooltip(dmButton, dmTooltip);
});

$("#category-button").click(function() {
	loadAjaxFromUrl("/book/category.do", "GET", "#category-tooltip-content");

	$("#category-tooltip").slideDown();
	setUpTooltip(categoryButton, categoryTooltip);
});

/**
 *  다른 곳 클릭하면 팝업창 사라지는 함수
 */
function clearBox(targetId, newId) {
	$(targetId).html("")
	console.log("tooltip 지우기 완료");

	var newDiv = document.createElement("div");
	newDiv.setAttribute("id", newId);
	document.querySelector(targetId).appendChild(newDiv);
	console.log("tooltip 생성 완료");
}

$("body")
	.on(
		"click",
		function(e) {
			var $tgPoint = $(e.target);
			// DM창일 경우
			var $popCallBtn = ((($tgPoint.attr('id') === "dm-button")) ? true
				: false);
			var $popArea = (($tgPoint.closest("#dm-tooltip")
				.attr('id') === "dm-tooltip") ? true
				: false);
			if (!$popCallBtn && !$popArea) {
				$("#dm-tooltip").slideUp();
				clearBox("#dm-tooltip", "dm-tooltip-content");
			}

			// 카테고리창일 경우
			$popCallBtn = ((($tgPoint.attr('id') === "category-button") || $tgPoint.attr('id') === "category-icon") ? true
				: false);
			$popArea = (($tgPoint.closest("#category-tooltip")
				.attr('id') === "category-tooltip") ? true
				: false);
			console.log($popCallBtn + ", " + $popArea);
			if (!$popCallBtn && !$popArea) {
				$("#category-tooltip").slideUp();
				clearBox("#category-tooltip", "category-tooltip-content");
			}
		});