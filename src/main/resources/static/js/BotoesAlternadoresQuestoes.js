$(document).ready(function(){
	
	$('#TipoQuestaoCadastro').on('click','#btnAlternaTextoObjetiva-0',function(){
		alert('clicado');
		$('#EnunciadoOpcaoObjetiva-0').remove();
		$('#input-group-0').append('<input id="EnunciadoOpcaoObjetiva-0" name="EnunciadoOpcaoObjetiva" type="text" class="form-control EnunciadoOpcaoObjetiva" value="" placeholder="Enunciado da Opção" aria-describedby="Opção 1">');	
	});
	$('#TipoQuestaoCadastro').on('click','#btnAlternaImagem-0',function(){
		alert('clicado');
		$('#EnunciadoOpcaoObjetiva-0').remove();
		$('#input-group-0').append('<input id="EnunciadoOpcaoObjetiva-0" name="EnunciadoOpcaoObjetiva" type="file" accept="image/*" class="form-control EnunciadoOpcaoObjetiva" aria-describedby="Opção 1">');	
	});
	
});