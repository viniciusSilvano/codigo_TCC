$(document).ready(function(){
				tinyMCE.init({
					oninit : "setPlainText",
					selector: 'textarea#tinymce',
					height: 500,
 					menubar: true,
					menubar: 'insert',
					image_caption: true,
					image_advtab: true,
					image_title: true,
					automatic_uploads: true,
 					file_picker_types: 'image', 
 					file_picker_callback: function(cb, value, meta) {
 						var input = document.createElement('input');
 						input.setAttribute('type', 'file');
 						input.setAttribute('accept', 'image/*');
					

 						input.onchange = function() {
 							var file = this.files[0];
						  
 							var reader = new FileReader();
 							reader.onload = function () {
 								var id = 'blobid' + (new Date()).getTime();
								var blobCache =  tinymce.activeEditor.editorUpload.blobCache;
								var base64 = reader.result.split(',')[1];
 								var blobInfo = blobCache.create(id, file, base64);
								blobCache.add(blobInfo);

								cb(blobInfo.blobUri(), { title: file.name });
 							};
							reader.readAsDataURL(file);
 						};
						
						input.click();
					},
				
 					plugins: [
 				    'advlist autolink lists link image charmap print preview anchor textcolor',
 				    'searchreplace visualblocks code fullscreen',
 				    'insertdatetime media table contextmenu paste code help imagetools',
 				    'table',
 				    'codesample',
 				    'paste'
 					],
 					theme_advanced_fonts : "Andale Mono=andale mono,times;"+
 	                "Arial=arial,helvetica,sans-serif;"+
 	                "Arial Black=arial black,avant garde;"+
 	                "Book Antiqua=book antiqua,palatino;"+
 	                "Comic Sans MS=comic sans ms,sans-serif;"+
 	                "Courier New=courier new,courier;"+
 	                "Georgia=georgia,palatino;"+
 	                "Helvetica=helvetica;"+
 	                "Impact=impact,chicago;"+
 	                "Symbol=symbol;"+
 	                "Tahoma=tahoma,arial,helvetica,sans-serif;"+
 	                "Terminal=terminal,monaco;"+
 	                "Times New Roman=times new roman,times;"+
 	                "Trebuchet MS=trebuchet ms,geneva;"+
 	                "Verdana=verdana,geneva;"+
 	                "Webdings=webdings;"+
 	                "Wingdings=wingdings,zapf dingbats",
 	                fontsize_formats: '8pt 10pt 12pt 14pt 18pt 24pt 36pt',
 	                insertdatetime_dateformat: "%d-%m-%Y",
 	                // formatselect
 					toolbar: 'insert |codesample|table | equationeditor | undo redo | fontselect | fontsizeselect | bold italic backcolor  | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat | help',
 					paste_data_images: true,
 					paste_as_text: true,
 					language_url: 'langs/pt_BR.js' });
				
				
				function setPlainText() {
			        var ed = tinyMCE.get('elm1');

			        ed.pasteAsPlainText = true;  

			        //adding handlers crossbrowser
			        if (tinymce.isOpera || /Firefox\/2/.test(navigator.userAgent)) {
			            ed.onKeyDown.add(function (ed, e) {
			                if (((tinymce.isMac ? e.metaKey : e.ctrlKey) && e.keyCode == 86) || (e.shiftKey && e.keyCode == 45))
			                    ed.pasteAsPlainText = true;
			            });
			        } else {            
			            ed.onPaste.addToTop(function (ed, e) {
			                ed.pasteAsPlainText = true;
			            });
			        }
			    }
});