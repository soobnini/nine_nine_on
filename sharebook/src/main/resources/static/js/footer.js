$("#category-link").click(function(e) {
	e.preventDefault();
	$("html, body").animate({
		scrollTop: 0
	}, 200);

	loadAjaxFromUrl("/book/category.do", "GET", "#category-tooltip-content");
	$("#category-tooltip").slideDown();
	setUpTooltip(categoryButton, categoryTooltip);
});

$("#brand-link").click(function(e) {
	e.preventDefault();
	$("html, body").animate({
		scrollTop: 0
	}, 200);

	$("#book-tooltip").slideUp();
	clearBox("#book-tooltip", "book-tooltip-content");
	$("#community-tooltip").slideUp();
	clearBox("#community-tooltip", "community-tooltip-content");

	loadAjaxFromUrl("/book/brand/menu.do", "GET", "#brand-tooltip-content");
	$("#brand-tooltip").slideDown();
	setUpTooltip(brandButton, brandTooltip);
});

$("#book-link").click(function(e) {
	e.preventDefault();
	$("html, body").animate({
		scrollTop: 0
	}, 200);

	$("#brand-tooltip").slideUp();
	clearBox("#brand-tooltip", "brand-tooltip-content");
	$("#community-tooltip").slideUp();
	clearBox("#community-tooltip", "community-tooltip-content");

	loadAjaxFromUrl("/book/menu.do", "GET", "#book-tooltip-content");
	$("#book-tooltip").slideDown();
	setUpTooltip(bookButton, bookTooltip);
});

$("#community-link").click(function(e) {
	e.preventDefault();
	$("html, body").animate({
		scrollTop: 0
	}, 200);

	$("#book-tooltip").slideUp();
	clearBox("#book-tooltip", "book-tooltip-content");
	$("#brand-tooltip").slideUp();
	clearBox("#brand-tooltip", "brand-tooltip-content");

	loadAjaxFromUrl("/book/community/menu.do", "GET", "#community-tooltip-content");
	$("#community-tooltip").slideDown();
	setUpTooltip(communityButton, communityTooltip);
});