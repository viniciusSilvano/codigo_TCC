package com.Faciplac.SGQ.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Faciplac.SGQ.model.Disciplina;
import com.Faciplac.SGQ.model.Questao;
import com.Faciplac.SGQ.model.Usuario;
import com.Faciplac.SGQ.repository.QuestaoRepository;
import com.Faciplac.SGQ.util.TaxonomiaDeBloomEnum;

@Service
@Transactional
public class QuestaoService {
	
	@Autowired
	QuestaoRepository repository;
	
	@Autowired
	DisciplinaService disciplinaService;
	
	int countQuestoesFaceis = 0;
	int countQuestoesMedias = 0;
	int countQuestoesDificeis = 0;
	
	int countBloomConhecimento = 0;
	int countBloomCompreensao = 0;
	int countBloomAplicacao = 0;
	int countBloomAnalise = 0;
	int countBloomSintese = 0;
	int countBloomAvaliacao = 0;
	
	
	public void CadastrarQuestao(Questao questao) {
		repository.save(questao);
	}
	
	public List<Questao> BuscarTodasAsQuestoes(){
		return repository.findAll();
	}
	public List<Questao> BuscarQuestoesPorUsuario(Usuario usuario){
		return repository.findAllByUsuario(usuario);
	}

	
	public List<Disciplina> BuscarQuestoesPorDisciplina() {
		
		List<Disciplina> listaDisciplinas = disciplinaService.BuscarTodasAsDisciplinas();
		
		countQuestoesFaceis = 0;
		countQuestoesMedias = 0;
		countQuestoesDificeis = 0;
		
		listaDisciplinas.forEach(disciplina->{
		
			List<Questao> listaQuestoes = repository.findAllByDisciplina(disciplina);
			
			listaQuestoes.forEach(questao ->{
				switch(questao.getDificuldade()) {
					case "facil":
						countQuestoesFaceis++;
						break;
					case "media":
						countQuestoesMedias++;
						break;
					case "dificil":
						countQuestoesDificeis++;
						break;
				}
			});
			
			disciplina.setQntQuestoesFaceis(countQuestoesFaceis);
			disciplina.setQntQuestoesMedias(countQuestoesMedias);
			disciplina.setQntQuestoesDificeis(countQuestoesDificeis);
			disciplina.setQuestoes(listaQuestoes);
			
			//disciplina.setQuestoes();
		});
		return listaDisciplinas;
	}
	
	public List<Questao> BuscarQuestoesPorTaxonomiaDeBloom(TaxonomiaDeBloomEnum classificacaoTaxonomiaBloom){
		return repository.findAllByClassificacaoTaxonomiaBloom(classificacaoTaxonomiaBloom);
		
	}
	
	public List<Questao> BuscarTop15QuestoesPelaTaxonomiaDeBloomESituacaoEDisciplinaOrdenadoPelaDataDeUso(TaxonomiaDeBloomEnum classificacaoTaxonomiaBloom, String situacao, Disciplina disciplina){
		return repository.findTop15ByClassificacaoTaxonomiaBloomAndSituacaoAndDisciplinaOrderByDataDeUsoAsc(classificacaoTaxonomiaBloom, situacao, disciplina);
	}
	
	public List<Disciplina> ContarQuestoesPorTaxonomiaDeBloomEDisciplina() {
		
		List<Disciplina> listaDisciplinas = disciplinaService.BuscarTodasAsDisciplinas();
		
		
		listaDisciplinas.forEach(disciplina->{
			countBloomConhecimento = 0;
			countBloomCompreensao = 0;
			countBloomAplicacao = 0;
			countBloomAnalise = 0;
			countBloomSintese = 0;
			countBloomAvaliacao = 0;
			
			List<Questao> listaQuestoes = repository.findAllByDisciplinaAndSituacao(disciplina, "Minuta");
			
			listaQuestoes.forEach(questao ->{
				switch(questao.getClassificacaoTaxonomiaBloom().getValor()) {
					case "conhecimento":
						countBloomConhecimento++;
						break;
					case "compreensão":
						countBloomCompreensao++;
						break;
					case "aplicação":
						countBloomAplicacao++;
						break;
					case "análise":
						countBloomAnalise++;
						break;
					case "síntese":
						countBloomSintese++;
						break;
					case "avaliação":
						countBloomAvaliacao++;
						break;
				}
			});
			
			disciplina.setQntBloomConhecimento(countBloomConhecimento);
			disciplina.setQntBloomCompreensao(countBloomCompreensao);
			disciplina.setQntBloomAplicacao(countBloomAplicacao);
			disciplina.setQntBloomAnalise(countBloomAnalise);
			disciplina.setQntBloomSintese(countBloomSintese);
			disciplina.setQntBloomAvaliacao(countBloomAvaliacao);
			disciplina.setQuestoes(listaQuestoes);
			
			
			
			//disciplina.setQuestoes();
		});
		return listaDisciplinas;
	}
	
	public List<Questao> BuscarQuestaoPorSituacao(String situacao){
		return repository.findAllBySituacao(situacao);
	}
	
	public Questao BuscarQuestaoPorId(Long id) {
		return repository.findOne(id);
	}

}
