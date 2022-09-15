$("#list-bar").keyup(function(e) {
	if (e.keyCode == 13) {
		$("#list-recommend-box").css("display", "none");
		loadAjaxFromAPI($("#list-bar").val(), "/book/search.do", "POST", "#book-list-result");
	}
});

$("#list-icon-box").click(function(e) {
	loadAjaxFromAPI($("#search-bar").val(), "/book/search.do", "POST", "#book-list-result");
});

$("#popular-sort").click(function(e) {
	$("#popular-sort").css("color", "#1C1B1F");
	$("#recent-sort").css("color", "#9A9A9A");
	loadAjaxFromAPI("2", "/book/list/sort.do", "POST", "#book-list-result");
});

$("#recent-sort").click(function(e) {
	$("#popular-sort").css("color", "#9A9A9A");
	$("#recent-sort").css("color", "#1C1B1F");
	loadAjaxFromAPI("3", "/book/list/sort.do", "POST", "#book-list-result");
});

$(".condition-label.region").click(function() {
	var conditionValue = $(this).find(".condition-checkbox").val();
	loadAjaxFromAPI(conditionValue, "/book/list/condition/region.do", "POST", "#book-list-result");
}); 
