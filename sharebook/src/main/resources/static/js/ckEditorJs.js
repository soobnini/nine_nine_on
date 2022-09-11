ClassicEditor.create(document.querySelector('#content'), {
	language: "ko",
	removePlugins: ['MediaEmbed'],
	ckfinder: { uploadUrl: 'http://localhost:8080/book/images/imgUpload.do' }
})
	.then(
		editor => {
			console.log(editor);
		})
	.catch(error => { console.error(error); });
