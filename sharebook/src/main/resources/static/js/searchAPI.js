$("#search-bar").keyup(function(e) {
	if (e.keyCode == 13) {
		loadAjaxFromAPI($("#search-bar").val(), "/bookapi", "POST", "#book-list-result");
	}
});

$("#search-icon-box").click(function(e) {
	loadAjaxFromAPI($("#search-bar").val(), "/bookapi", "POST", "#book-list-result");
});