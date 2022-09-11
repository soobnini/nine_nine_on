/*
 * 파일 업로드 관련 JS 
*/

$(document).ready(function() {
	const uploadButton = $('#form-upload-image');
	const visibleInput = $('#form-upload-file-name');

	uploadButton.on("change", function() {
		if (window.FileReader) {
			var filename = $(this)[0].files[0].name;
		}
		else {
			// 옛날 IE 사용할 경우
			var filename = $(this).val().split('/').pop().split('\\').pop();
		}

		visibleInput.val(filename);
	});
});