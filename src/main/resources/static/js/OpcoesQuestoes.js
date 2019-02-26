	$(document).ready(function(){
		
		LimparTabela();
		InserirEnunciadoTipoObjetiva();
		InserirCamposDoCadastroTipoObjetiva();
		
		$('.dropTipoQuestao').on('change', function(){
			if(this.value == 'Objetiva'){
				//alert('objetiva foi selecionado');
				LimparTabela();
				InserirEnunciadoTipoObjetiva();
				InserirCamposDoCadastroTipoObjetiva();
			}
			
			if(this.value == 'Verdadeiro Ou Falso'){
				//alert('verdadeiro ou falso selecionado');
				LimparTabela();			
				InserirEnunciadoTipoVerdOuFalso();
				InserirCamposDoCadastroTipoVerdOuFalso();
			}
			
			if(this.value == 'Por que..'){
				//alert('Por que selecionado');
				LimparTabela();
				InserirEnunciadoTipoPorque();
				InserirCamposDoCadastroTipoPorque();
			}
		});
		
		$('#TipoQuestaoCadastroEnunciado').on('click','button.btnInserirOpcaoObjetiva',function(){
			InserirOpcaoObjetiva();
			
		});

		$('#TipoQuestaoCadastroEnunciado').on('click','button.btnRemoverOpcaoObjetiva',function(){
			RemoverOpcaoObjetiva();
			
		});
		
		$('#TipoQuestaoCadastroEnunciado').on('click','button.btnInserirOpcaoVerdOuFalso',function(){
			InserirOpcaoVerdOuFalso();
		});

		$('#TipoQuestaoCadastroEnunciado').on('click','button.btnRemoverOpcaoVerdOuFalso',function(){
			RemoverOpcaoVerdOuFalso();
		});
		
		function LimparTabela(){
			$('#TipoQuestaoCadastroEnunciado').children().remove();
			$('#TipoQuestaoCadastro').children().remove();
		}
		
		function InserirEnunciadoTipoObjetiva(){
			$('#TipoQuestaoCadastroEnunciado').append('\
			<tr>\
				<th>Opção\
				</th>\
				<th>Opção Correta </th>\
			</tr>');
		}
		
		function InserirCamposDoCadastroTipoObjetiva(){
			for(i = 0; i < 5; i++){
				$('#TipoQuestaoCadastro').append('\
				<tr>\
					<td>\
						<div id="input-group" class="input-group">\
							<span class="input-group-addon" id="basic-addon1">' + i + ')</span>\
							<input id="EnunciadoOpcaoObjetiva-0" name="EnunciadoOpcaoObjetiva" type="text" class="form-control EnunciadoOpcaoObjetiva" value="" placeholder="Enunciado da Opção" aria-describedby="Opção 1">\
						</div>\
					</td>\
					<td><input name="OpcaoCorreta" type="radio" class="form-control"></td>\
				</tr>');
			}
		}

		function InserirEnunciadoTipoVerdOuFalso(){
			  $('#TipoQuestaoCadastroEnunciado').append('\
				<tr>\
					<th>Opção\
					</th>\
					<th>Opção Correta </th>\
				</tr>')
		}

		function InserirCamposDoCadastroTipoVerdOuFalso(){
			for(i = 0; i < 5; i++){
				$('#TipoQuestaoCadastro').append('\
					<tr>\
						<td>\
							<div class="input-group">\
								<span class="input-group-addon" id="basic-addon1">' + i + ')</span>\
								<input name="EnunciadoOpcao1" type="text" class="form-control EnunciadoOpcaoVerdOuFalso" value="" placeholder="Enunciado da Opção" aria-describedby="Opção 1">\
							</div>\
						</td>\
						<td>\
							<div class="input-group">\
								<span class="input-group-addon">\
									<input type="radio" name="OpcaoVerdadeiroOuFalso1" aria-label="...">\
								</span>\
								<input type="text" disabled value="Verdadeiro" class="form-control" aria-label="...">\
							</div>\
							<div class="input-group">\
								<span class="input-group-addon">\
									<input type="radio" name="OpcaoVerdadeiroOuFalso1" aria-label="...">\
								</span>\
								<input type="text" disabled value="Falso" class="form-control" aria-label="...">\
							</div>\
						</td>\
					</tr>');
			}
		}
		
		function InserirEnunciadoTipoPorque(){
			$('#TipoQuestaoCadastroEnunciado').append('\
				<tr>\
					<th>Opção\
					</th>\
					<th>\
						Opção Correta\
					</th>\
				</tr>');
		}
	
		function InserirCamposDoCadastroTipoPorque(){
			$('#TipoQuestaoCadastroEnunciado').append('\
				<tr>\
					<td>\
						<div class="input-group">\
							<span class="input-group-addon" id="basic-addon1">I</span>\
							<input name="EnunciadoOpcao1" type="text" class="form-control" value="" placeholder="Afirmação" aria-describedby="Opção 1">\
							<span class="input-group-addon" id="basic-addon1">Por Que</span>\
							<span class="input-group-addon" id="basic-addon1">II</span>\
							<input name="EnunciadoOpcao1" type="text" class="form-control" value="" placeholder="Justificativa" aria-describedby="Opção 1">\
						</div>\
					</td>\
					<td>\
					</td>\
				</tr>\
				<tr>\
					<td>\
						<div class="form-group">\
							<input type="text" class="form-control" value="As asserções I e II são proposições verdadeiras, e a II é uma justificativa correta da I."></input>\
						</div>\
					</td>\
					<td>\
						<input type="radio" class="form-control" name="alternativa correta">\
					</td>\
				</tr>\
				<tr>\
					<td>\
						<div class="form-group">\
							<input type="text" class="form-control" value="As asserções I e II são proposições verdadeiras, mas a II não é uma justificativa correta da I."></input>\
						</div>\
					</td>\
					<td>\
						<input type="radio" class="form-control" name="alternativa correta">\
					</td>\
				</tr>\
				<tr>\
					<td>\
						<div class="form-group">\
							<input type="text" class="form-control" value="A asserção I é uma proposição verdadeira, e a II é uma proposição falsa."></input>\
						</div>\
					</td>\
					<td>\
						<input type="radio" class="form-control" name="alternativa correta">\
					</td>\
				</tr>\
				<tr>\
					<td>\
						<div class="form-group">\
							<input type="text" class="form-control" value="A asserção I é uma proposição falsa, e a II é uma proposição verdadeira."></input>\
						</div>\
					</td>\
					<td>\
						<input type="radio" class="form-control" name="alternativa correta">\
					</td>\
				</tr>\
				<tr>\
					<td>\
						<div class="form-group">\
							<input type="text" class="form-control" value="As asserções I e II são proposições falsas."></input>\
						</div>\
					</td>\
					<td>\
						<input type="radio" class="form-control" name="alternativa correta">\
					</td>\
				</tr>');
		}
		
		/*function InserirOpcaoObjetiva(){
			var n  = $('#TipoQuestaoCadastro input.EnunciadoOpcaoObjetiva').length;
			if(n != 5){
				var letra = letraDaOpcao(n);
				$('#TipoQuestaoCadastro').append('\
					<tr>\
						<td>\
							<div class="input-group">\
								<span class="input-group-addon" id="basic-addon1">'+ letra + ')</span>\
								<input ' + 'id="EnunciadoOpcaoObjetiva-' + n + '" name="EnunciadoOpcaoObjetiva" type="text" class="form-control EnunciadoOpcaoObjetiva" value="" placeholder="Enunciado da Opção" aria-describedby="Opção 1">\
							</div>\
						</td>\
						<td><input name="OpcaoCorreta" type="radio" class="form-control"></td>\
					</tr>\
				');
			}else{
				alert('5 é o limite máximo');
			}
			
		}

		function RemoverOpcaoObjetiva(){
			var n  = $('#TipoQuestaoCadastro input.EnunciadoOpcaoObjetiva').length;
			if(n > 1){
				$('tr:last-child','#TipoQuestaoCadastro').remove();
			}
			
		}
	
		function InserirOpcaoVerdOuFalso(){
			var n  = $('#TipoQuestaoCadastro input.EnunciadoOpcaoVerdOuFalso').length;
			alert('preparando para criar opcao VerdOuFalso tendo' + n);
			if(n != 5){
				var letra = letraDaOpcao(n);
				
				$('#TipoQuestaoCadastro').append('\
				<tr>\
					<td>\
						<div class="input-group">\
							<span class="input-group-addon" id="basic-addon1">' + letra + ')</span>\
							<input name="EnunciadoOpcao1" type="text" class="form-control EnunciadoOpcaoVerdOuFalso" value="" placeholder="Enunciado da Opção" aria-describedby="Opção 1">\
						</div>\
					</td>\
					<td>\
						<div class="input-group">\
							<span class="input-group-addon">\
								<input type="radio" name="OpcaoVerdadeiroOuFalso1" aria-label="...">\
							</span>\
							<input type="text" disabled value="Verdadeiro" class="form-control" aria-label="...">\
						</div>\
						<div class="input-group">\
							<span class="input-group-addon">\
								<input type="radio" name="OpcaoVerdadeiroOuFalso1" aria-label="...">\
							</span>\
							<input type="text" disabled value="Falso" class="form-control" aria-label="...">\
						</div>\
					</td>\
				</tr>\
				');
			}else{
				alert('5 é o limite máximo');
			}
		}

		function RemoverOpcaoVerdOuFalso(){
			var n  = $('#TipoQuestaoCadastro input.EnunciadoOpcaoVerdOuFalso').length;
			if(n > 1){
				$('tr:last-child','#TipoQuestaoCadastro').remove();
			}	
		}

		//Util

		function letraDaOpcao(n){
			var letra = 'B';
			switch(n) {
					case 1:
						letra = 'B'
						break;
				    case 2:
				        letra = 'C'
				        return letra;
				        break;
				    case 3:
				        letra = 'D'
				        break;
				    case 4:
				        letra = 'E'
				        break;  
			}

			return letra;

		}*/
	});
