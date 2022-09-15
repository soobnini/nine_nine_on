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
/* mouse hover menu */
const brandButton = document.querySelector('#brand-button');
const brandTooltip = document.querySelector('#brand-tooltip');
const bookButton = document.querySelector('#book-button');
const bookTooltip = document.querySelector('#book-tooltip');
const communityButton = document.querySelector('#community-button');
const communityTooltip = document.querySelector('#community-tooltip');

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
 *  다른 곳 클릭하면 팝업창 사라지는 함수  (이벤트들 너무 하드코딩돼서 추후 수정하겠슴..)
 */
function clearBox(targetId, newId) {
	$(targetId).html("")
	var newDiv = document.createElement("div");
	newDiv.setAttribute("id", newId);
	newDiv.setAttribute("class", "tooltip-content");
	document.querySelector(targetId).appendChild(newDiv);
}

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

$("#brand-button").hover(function() {
	$("#book-tooltip").slideUp();
	clearBox("#book-tooltip", "book-tooltip-content");
	$("#community-tooltip").slideUp();
	clearBox("#community-tooltip", "community-tooltip-content");

	loadAjaxFromUrl("/book/brand/menu.do", "GET", "#brand-tooltip-content");
	$("#brand-tooltip").slideDown();
	setUpTooltip(brandButton, brandTooltip);
});

$("#book-button").hover(function() {
	$("#brand-tooltip").slideUp();
	clearBox("#brand-tooltip", "brand-tooltip-content");
	$("#community-tooltip").slideUp();
	clearBox("#community-tooltip", "community-tooltip-content");

	loadAjaxFromUrl("/book/menu.do", "GET", "#book-tooltip-content");
	$("#book-tooltip").slideDown();
	setUpTooltip(bookButton, bookTooltip);
});

$("#community-button").hover(function() {
	$("#book-tooltip").slideUp();
	clearBox("#book-tooltip", "book-tooltip-content");
	$("#brand-tooltip").slideUp();
	clearBox("#brand-tooltip", "brand-tooltip-content");

	loadAjaxFromUrl("/book/community/menu.do", "GET", "#community-tooltip-content");
	$("#community-tooltip").slideDown();
	setUpTooltip(communityButton, communityTooltip);
});

$("#book-dm-button").click(function(e) {
	e.preventDefault();
	$("html, body").animate({
		scrollTop: 0
	}, 200);
	
	
	var otherMemberId = $('#hidden-register-id').val();
	var bookId = $('#hidden-book-id').val();
	
	loadAjaxAboutDM("/book/chat/create.do", otherMemberId, bookId, "#dm-tooltip-content")
	$("#dm-tooltip").slideDown();
	setUpTooltip(dmButton, dmTooltip);
});

$("body")
	.on(
		"click",
		function(e) {
			var $tgPoint = $(e.target);
			// DM창일 경우
			var $popCallBtn = ((($tgPoint.attr('id') === "dm-button") || ($tgPoint.attr('id') === "book-dm-button")) ? true
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

			// 브랜드창일 경우
			$popCallBtn = (($tgPoint.attr('id') === "brand-button") ? true
				: false);
			$popArea = (($tgPoint.closest("#brand-tooltip")
				.attr('id') === "brand-tooltip") ? true
				: false);
			console.log($popCallBtn + ", " + $popArea);
			if (!$popCallBtn && !$popArea) {
				$("#brand-tooltip").slideUp();
				clearBox("#brand-tooltip", "brand-tooltip-content");
			}

			// 이웃책장창일 경우
			$popCallBtn = (($tgPoint.attr('id') === "book-button") ? true
				: false);
			$popArea = (($tgPoint.closest("#book-tooltip")
				.attr('id') === "book-tooltip") ? true
				: false);
			console.log($popCallBtn + ", " + $popArea);
			if (!$popCallBtn && !$popArea) {
				$("#book-tooltip").slideUp();
				clearBox("#book-tooltip", "book-tooltip-content");
			}

			// 커뮤니티창일 경우
			$popCallBtn = (($tgPoint.attr('id') === "community-button") ? true
				: false);
			$popArea = (($tgPoint.closest("#community-tooltip")
				.attr('id') === "community-tooltip") ? true
				: false);
			console.log($popCallBtn + ", " + $popArea);
			if (!$popCallBtn && !$popArea) {
				$("#community-tooltip").slideUp();
				clearBox("#community-tooltip", "community-tooltip-content");
			}
		});

/**
 * 검색차		
 */
 $("#search-bar").keyup(function(e) {
	if (e.keyCode == 13) {
		searchForm.submit();
	}
});