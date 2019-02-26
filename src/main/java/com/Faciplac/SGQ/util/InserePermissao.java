package com.Faciplac.SGQ.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.Faciplac.SGQ.model.Permissao;

@Component
public class InserePermissao {
	List<Permissao> permissoes;
	
	public InserePermissao() {
		permissoes = new ArrayList<Permissao>();
	}
	
	public List<Permissao> Professor(){
		Permissao cadastrarProva = new Permissao();
		Permissao consultarProva = new Permissao();
		Permissao cadastrarQuestao = new Permissao();
		Permissao consultarQuestao = new Permissao();
		
		cadastrarProva.setNome("PG_CADASTRA_PROVA");
		consultarProva.setNome("PG_CONSULTAR_PROVA");
		cadastrarQuestao.setNome("PG_CADASTRA_QUESTAO");
		consultarQuestao.setNome("PG_CONSULTAR_QUESTAO");
		
		permissoes.add(cadastrarProva);
		permissoes.add(consultarProva);
		permissoes.add(cadastrarQuestao);
		permissoes.add(consultarQuestao);
		
		
		return permissoes;
	}
}
