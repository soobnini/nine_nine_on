/**
 *  Controller URL로 페이지 가져올 때
 */
function loadAjaxFromUrl(thisUrl, thisType, changeElement) {
	$.ajax({
		url: thisUrl,
		type: thisType,
		data: {
			query: 'ajax'
		},
		dataType: 'text',
		beforeSend: function(jqXHR) {
			console.log("ajax 호출전");
		},
		success: function(data) {
			console.log("호출성공");
			console.log(data);
		},
		error: function(jqXHR) {
			console.log("호출실패");
		}
	}).done(function(result) {
		$(changeElement).replaceWith(result);
	});
}

/**
 *  HTML과 같은 정적인 파일 가져올 때 (쓸 예정이었는데 안 써서, 예비용으로...)
 */
function loadAjaxFromHtml(thisUrl, thisDataType, changeElement) {
	$.ajax({
		url: thisUrl,
		dataType: thisDataType,
		success: function(data) {
			$(changeElement).html(data);
		}
	});
}