$(".book-list-content").click(function() {
	var book = $(this).children("#hiddenBookInfo").val();
	console.log(book);

	if ($("#result-bar").css("display") === "none") {
		$("#result-bar").slideDown();
	}

	var title = $(this).find(".book-list-title").text();
	var author = $(this).find(".book-list-author").text();
	var publisher = "퍼블리셔";
	var image = $(this).find("#hidden-image").val();

	$("#result-bar-title").text(title);
	$("#result-bar-author").text(author);

	$("#hidden-title").val(title);
	$("#hidden-author").val(author);
	$("#hidden-publisher").val(publisher);
	$("#hidden-image-url").val(image);
});
