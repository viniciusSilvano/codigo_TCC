package com.Faciplac.SGQ.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.Faciplac.SGQ.model.Disciplina;
import com.Faciplac.SGQ.model.Prova;
import com.Faciplac.SGQ.model.Questao;
import com.Faciplac.SGQ.service.QuestaoService;
import com.Faciplac.SGQ.util.GerarPDF;
import com.Faciplac.SGQ.util.TaxonomiaDeBloomEnum;

@Controller
public class ProvaController {
	
	@Autowired
	QuestaoService questaoService;
	
	List<Questao> questoesDaProva;
	
	
	
	@GetMapping("/home/cadastrarProva")
	public String getCriarProva(Model model, Prova prova) {
		//List<Disciplina> listaDisciplinas = questaoService.BuscarQuestoesPorDisciplina();
		List<Disciplina> listaDisciplinas = questaoService.ContarQuestoesPorTaxonomiaDeBloomEDisciplina();
		
		PrepararQuestoesDaProva(prova);
		
		model.addAttribute("disciplinas", listaDisciplinas);
		return "model/prova/criarProva";
		
	}
	
	//https://stackoverflow.com/questions/16652760/return-generated-pdf-using-spring-mvc
	//https://stackoverflow.com/questions/858980/file-to-byte-in-java
	@PostMapping("/home/cadastrarProva")
	public String postCriarProva(Model model, @Valid Prova prova) {
		
		

		List<String> Enunciados = new ArrayList<String>();
		try {
			
			prova.setQuestoes(PrepararQuestoesDaProva(prova));
			List<Disciplina> listaDisciplinas = questaoService.ContarQuestoesPorTaxonomiaDeBloomEDisciplina();
			
			for(Questao questao : prova.getQuestoes()) {
				Enunciados.add("results/EnunciadosHTML/" + questao.getEnunciado());
			}
			
			new GerarPDF(Enunciados,"results/objects/" + prova.getDescricao() + ".pdf",prova.getDisciplina().getNomeDisciplina());
			model.addAttribute("disciplinas", listaDisciplinas);
			return "model/prova/criarProva";
			
		}catch(Exception e) {
			List<Disciplina> listaDisciplinas = questaoService.ContarQuestoesPorTaxonomiaDeBloomEDisciplina();
			model.addAttribute("disciplinas", listaDisciplinas);
			return "model/prova/criarProva";
			
		}
	}
	
	@GetMapping("/home/consultarProva")
	public String getConsultarProva() {
		return "model/prova/consultarProva";
	}
	
	public List<Questao> PrepararQuestoesDaProva(Prova prova) {
		questoesDaProva = new ArrayList<Questao>();
		
		GerarQuestoesAleatoriamente(questaoService.BuscarTop15QuestoesPelaTaxonomiaDeBloomESituacaoEDisciplinaOrdenadoPelaDataDeUso(TaxonomiaDeBloomEnum.n1, "Minuta", prova.getDisciplina()), prova.getQntBloomConhecimento());
		GerarQuestoesAleatoriamente(questaoService.BuscarTop15QuestoesPelaTaxonomiaDeBloomESituacaoEDisciplinaOrdenadoPelaDataDeUso(TaxonomiaDeBloomEnum.n2, "Minuta", prova.getDisciplina()), prova.getQntBloomCompreensao());
		GerarQuestoesAleatoriamente(questaoService.BuscarTop15QuestoesPelaTaxonomiaDeBloomESituacaoEDisciplinaOrdenadoPelaDataDeUso(TaxonomiaDeBloomEnum.n3, "Minuta", prova.getDisciplina()), prova.getQntBloomAplicacao());
		GerarQuestoesAleatoriamente(questaoService.BuscarTop15QuestoesPelaTaxonomiaDeBloomESituacaoEDisciplinaOrdenadoPelaDataDeUso(TaxonomiaDeBloomEnum.n4, "Minuta", prova.getDisciplina()), prova.getQntBloomAnalise());
		GerarQuestoesAleatoriamente(questaoService.BuscarTop15QuestoesPelaTaxonomiaDeBloomESituacaoEDisciplinaOrdenadoPelaDataDeUso(TaxonomiaDeBloomEnum.n5, "Minuta", prova.getDisciplina()), prova.getQntBloomSintese());
		GerarQuestoesAleatoriamente(questaoService.BuscarTop15QuestoesPelaTaxonomiaDeBloomESituacaoEDisciplinaOrdenadoPelaDataDeUso(TaxonomiaDeBloomEnum.n6, "Minuta", prova.getDisciplina()), prova.getQntBloomAvaliacao());
		
		return questoesDaProva;
	}
	public void GerarQuestoesAleatoriamente(List<Questao> questoes, int numeroQuestoes) {
		Random randomGenerator = new Random();
		if(questoes.size() != 0 ) {
			for(int i = 0; i < numeroQuestoes; i++) {
				Questao questao;
				int index = randomGenerator.nextInt(questoes.size());
				questao = questoes.get(index);
				questao.setDataDeUso(LocalDateTime.now());
				questoesDaProva.add(questao);
			}
		}

	}
	
}
