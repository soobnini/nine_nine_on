$(document).ready(function() {
	loadAjaxFromUrl("/book/header.do", "GET", "#header");
	loadAjaxFromUrl("/book/footer.do", "GET", "#footer");
});