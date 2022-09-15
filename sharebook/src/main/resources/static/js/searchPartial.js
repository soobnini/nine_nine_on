$(".book-list-content").click(function() {
	var book = $(this).children("#hiddenBookInfo").val();
	console.log(book);

	if ($("#result-bar").css("display") === "none") {
		$("#result-bar").slideDown();
	}

	var title = $(this).find(".book-list-title").text();
	var author = $(this).find(".book-list-author").text();
	var publisher = $(this).find(".book-list-publisher").text();
	var image = $(this).find("#hidden-image").val();
	var publishYear = $(this).find(".book-list-year").text();
	var isbn = $(this).find("#hidden-api-isbn").val();

	$("#result-bar-title").text(title);
	$("#result-bar-author").text(author);
	$("#result-bar-publisher").text(" / " + publisher);
	$("#result-bar-year").text(publishYear);

	$("#hidden-title").val(title);
	$("#hidden-author").val(author);
	$("#hidden-publisher").val(publisher);
	$("#hidden-image-url").val(image);
	$("#hidden-publish-year").val(publishYear);
	$("#hidden-isbn").val(isbn);
}); 
