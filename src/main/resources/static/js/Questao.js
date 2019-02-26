$(document).ready(function(){
	// function enviarAjax(url){
	// $(".dropTipoQuestao").on('change', function(e){
	// if($("option:selected").text == "Objetiva"){
	// $.ajax({
	// url:
	// "/home/cadastrarQuestao/opcoesObjetivas",
	// success: function(data){
	// $('#localDasOpcoes').html(data)
	// }
	// });
	// }
	// });

	// }

	$('#questaoForm').submit(function(event){
		var mensagem = 'valores: ';
		for (i = 0; i < 5; i++) { 
			if($('#radio'+i).is(':checked')){
				$('#radioHidden'+i).val($('#radio'+i).val());
			}
		}
		return;
	});
});