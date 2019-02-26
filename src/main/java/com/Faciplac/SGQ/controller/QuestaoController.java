package com.Faciplac.SGQ.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.Faciplac.SGQ.model.Questao;
import com.Faciplac.SGQ.model.Usuario;
import com.Faciplac.SGQ.service.DisciplinaService;
import com.Faciplac.SGQ.service.QuestaoService;
import com.Faciplac.SGQ.service.UsuarioService;
import com.Faciplac.SGQ.util.GerarEnunciadoQuestaoEmHTML;
import com.Faciplac.SGQ.util.RecuperarDadosUsuario;
import com.Faciplac.SGQ.util.SmtpMailSender;
import com.Faciplac.SGQ.util.TaxonomiaDeBloomEnum;

//https://stackoverflow.com/questions/36500731/how-to-bind-an-object-list-with-thymeleaf
//https://stackoverflow.com/questions/40717777/thymeleaf-edit-list-of-objects-for-form
//https://stackoverflow.com/questions/11780766/setcontent-of-an-textarea-with-tinymce
@Controller
public class QuestaoController{
	
	@Autowired
	private DisciplinaService disciplinaService;
	@Autowired
	private QuestaoService service;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private GerarEnunciadoQuestaoEmHTML enunciadoEmHTML;
	@Autowired
	private SmtpMailSender mailSender;
	/*@Autowired
	private OpcaoQuestaoObjetivaService opcaoObjetivaService;*/
	@Autowired
	private RecuperarDadosUsuario recuperarDadosUsuario;
	
	@GetMapping("/home/cadastrarQuestao")
	public String getCadastrarQuestao(Questao questao, Model model) {
		model.addAttribute("disciplinas",disciplinaService.BuscarTodasAsDisciplinas());
		model.addAttribute("action", "/home/cadastrarQuestao");
		model.addAttribute("listaTaxonomiaDeBloom", TaxonomiaDeBloomEnum.values());
		questao.setConteudoEnunciado("");
		//questao.setOpcoesObjetivas(CriarListaOpcoesObjetiva());
		return "model/questao/CriarQuestao";
	}
	
	@GetMapping("/home/visualizarQuestao/{id}")
	public String visualizarQuestao(@PathVariable Long id, Model model) {
		Questao questao = service.BuscarQuestaoPorId(id);
		model.addAttribute("tituloModal","Questão \"" + questao.getDescricao() + "\"");
		model.addAttribute("tituloBotaoDismissModal","OK");
		String fileName = "results/EnunciadosHTML/" + questao.getEnunciado();
		try {
			Document doc = Jsoup.parse(new File(fileName), "utf-8");
			Element divTag = doc.getElementById("conteudoEnunciado"); 
			model.addAttribute("conteudoBodyModal", divTag);
			model.addAttribute("questao", questao);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "model/questao/consultarQuestao";
		} 
		return "util/modalMostrarQuestao :: modal";
	}
	
	@GetMapping("/home/consultarQuestao")
	public String getConsultarQuestao(Model model) {
		//RecuperarDadosUsuario dadosUsuario = new RecuperarDadosUsuario(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String username = recuperarDadosUsuario.recuperarUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal());//dadosUsuario.recuperarUserName();
		List<Questao> questoes;
		Usuario usuario = usuarioService.RecuperarUsuarioPorLogin(username);
		if(usuario.getCargo() == "Professor") {
			questoes = service.BuscarQuestoesPorUsuario(usuario);
		}else {
			questoes = service.BuscarTodasAsQuestoes();
		}
		model.addAttribute("questoes",questoes);
		model.addAttribute("cargo",usuario.getCargo());
		return "model/questao/consultarQuestao";
	}
	
	//, @ModelAttribute  ArrayList<OpcaoQuestaoObjetiva> opcoesObjetivas
	@PostMapping("/home/cadastrarQuestao")
	public String cadastrarQuestao(@Valid Questao questao, BindingResult bindingResult, Model model,RedirectAttributes redir) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("mensagemErro","erro no formulário");
			model.addAttribute("disciplinas",disciplinaService.BuscarTodasAsDisciplinas());
			return "model/questao/CriarQuestao";
			
		}
		
		redir.addFlashAttribute("disciplinas",disciplinaService.BuscarTodasAsDisciplinas());
		try {
			/*if(questao.getEnunciado() == null && (!questao.getEnunciado().isEmpty())) {
				String nomeArquivoHtml = enunciadoEmHTML.criarEnunciadoEmHTML(questao.getConteudoEnunciado());
				questao.setEnunciado(nomeArquivoHtml);
			}else {
				enunciadoEmHTML.reescreverArquivoHtml(questao.getConteudoEnunciado(), questao.getEnunciado());
			}*/
			String nomeArquivoHtml = enunciadoEmHTML.criarEnunciadoEmHTML(questao.getConteudoEnunciado());
			questao.setEnunciado(nomeArquivoHtml);
			/*List<OpcaoQuestaoObjetiva> lista = questao.getOpcoesObjetivas();
			for(OpcaoQuestaoObjetiva opcao : lista) {
				opcao.setQuestao(questao);
			}
		
			questao.setOpcoesObjetivas(lista);*/
			questao.setSituacao("Aguardando Validação");
			//inserindo a dificuldade com base na taxonomia de bloom:
			String dificuldade = "";
			if(questao.getClassificacaoTaxonomiaBloom() == TaxonomiaDeBloomEnum.n1 || questao.getClassificacaoTaxonomiaBloom() == TaxonomiaDeBloomEnum.n2)
				dificuldade = "Fácil";
			if(questao.getClassificacaoTaxonomiaBloom() == TaxonomiaDeBloomEnum.n3 || questao.getClassificacaoTaxonomiaBloom() == TaxonomiaDeBloomEnum.n4)
				dificuldade = "Média";
			if(questao.getClassificacaoTaxonomiaBloom() == TaxonomiaDeBloomEnum.n5 || questao.getClassificacaoTaxonomiaBloom() == TaxonomiaDeBloomEnum.n6)
				dificuldade = "Difícil";
			questao.setDificuldade(dificuldade);
			String username = recuperarDadosUsuario.recuperarUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal());//dadosUsuario.recuperarUserName();
			questao.setUsuario(usuarioService.RecuperarUsuarioPorLogin(username));
			questao.setDataCriacao(LocalDateTime.now());
			questao.setDataDeUso(LocalDateTime.now());
			service.CadastrarQuestao(questao);
			redir.addFlashAttribute("mensagemSucesso","Questão criada com sucesso");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/home/cadastrarQuestao";
	}
	
	@PostMapping("/home/editarQuestao")
	public String postEditarQuestao(@Valid Questao questao, Model model,RedirectAttributes redir) {
		
		Questao questaoParaEditar = service.BuscarQuestaoPorId(questao.getIdQuestao());
		questaoParaEditar.setDescricao(questao.getDescricao());
		//questaoParaEditar.setDificuldade(questao.getDificuldade());
		questaoParaEditar.setDisciplina(questao.getDisciplina());
		
		String username = recuperarDadosUsuario.recuperarUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal());//dadosUsuario.recuperarUserName();
		questaoParaEditar.setUsuario(usuarioService.RecuperarUsuarioPorLogin(username));
		questaoParaEditar.setDataCriacao(LocalDateTime.now());
		try {
			enunciadoEmHTML.reescreverArquivoHtml(questao.getConteudoEnunciado(), questaoParaEditar.getEnunciado());
			questaoParaEditar.setConteudoEnunciado(questao.getConteudoEnunciado());
			/*opcaoObjetivaService.ExcluirOpcaoQuestaoObjetivaPorQuestao(questaoParaEditar);
			questaoParaEditar.setOpcoesObjetivas(questao.getOpcoesObjetivas());
			List<OpcaoQuestaoObjetiva> lista = questaoParaEditar.getOpcoesObjetivas();
			for(OpcaoQuestaoObjetiva opcao : lista) {
				opcao.setQuestao(questaoParaEditar);
			}
			questaoParaEditar.setOpcoesObjetivas(lista);*/
			questaoParaEditar.setClassificacaoTaxonomiaBloom(questao.getClassificacaoTaxonomiaBloom());
			String dificuldade = "";
			if(questao.getClassificacaoTaxonomiaBloom() == TaxonomiaDeBloomEnum.n1 || questao.getClassificacaoTaxonomiaBloom() == TaxonomiaDeBloomEnum.n2)
				dificuldade = "Fácil";
			if(questao.getClassificacaoTaxonomiaBloom() == TaxonomiaDeBloomEnum.n3 || questao.getClassificacaoTaxonomiaBloom() == TaxonomiaDeBloomEnum.n4)
				dificuldade = "Média";
			if(questao.getClassificacaoTaxonomiaBloom() == TaxonomiaDeBloomEnum.n5 || questao.getClassificacaoTaxonomiaBloom() == TaxonomiaDeBloomEnum.n6)
				dificuldade = "Difícil";
			
			questaoParaEditar.setDificuldade(dificuldade);
			questaoParaEditar.setSituacao("Aguardando Validação");
			questaoParaEditar.setTipoQuestao(questao.getTipoQuestao());
			service.CadastrarQuestao(questaoParaEditar);
			redir.addFlashAttribute("mensagemSucesso","Questão Editada com sucesso");
			return "redirect:/home/consultarQuestao";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("mensagemErro","erro ao editar questão");
		return "/home/consultarQuestao";
	}
	
	@GetMapping("/home/editarQuestao/{id}")
	public String getEditarQuestao(Questao questao,@PathVariable Long id, Model model) {
		model.addAttribute("disciplinas",disciplinaService.BuscarTodasAsDisciplinas());
		questao = service.BuscarQuestaoPorId(id);
		
		try {
			String fileName = "results/EnunciadosHTML/" + questao.getEnunciado();
			Document doc = Jsoup.parse(new File(fileName), "utf-8");
			Element divTag = doc.getElementById("conteudoEnunciado"); 
			model.addAttribute("html",divTag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("mensagemErro","erro ao editar questão");
			return "model/questao/consultarQuestao";
		}
		model.addAttribute("idQuestaoEdit",questao.getIdQuestao());
		model.addAttribute("enunciadoEdit",questao.getEnunciado());
		model.addAttribute("questao",questao);
		model.addAttribute("listaTaxonomiaDeBloom", TaxonomiaDeBloomEnum.values());
		model.addAttribute("action", "/home/editarQuestao");
		return "model/questao/CriarQuestao";
	}
	
	@GetMapping("/home/homologarQuestao")
	public String getHomologarQuestao(Model model) {
		List<Questao> questoes = service.BuscarQuestaoPorSituacao("Aguardando Validação");
		model.addAttribute("questoes",questoes);
		return "model/questao/homologarQuestao";
	}
	
	@GetMapping("/home/homologarQuestao/{acao}/{id}")
	public String preencherModal(@PathVariable String acao,@PathVariable Long id, Model model) {
		switch(acao) {
		 	case "Homologar":
		 		model.addAttribute("tituloModal","Deseja Homologar A Questão?");
				model.addAttribute("tituloBotaoModal","Homologar");
				model.addAttribute("urlBotaoModal","/home/confirmarHomologarQuestao/" + id);
				model.addAttribute("tituloBotaoDismissModal","Cancelar");
				break;
		 	case "Revisao":
		 		model.addAttribute("tituloModal","Deseja enviar a questão para revisão?");
				model.addAttribute("tituloBotaoModal","Enviar");
				model.addAttribute("urlBotaoModal","/home/confirmarRevisaoQuestao/" + id);
				model.addAttribute("tituloBotaoDismissModal","Cancelar");
				break;
		}
		
		return "util/modal :: modal";
	}
	
	@GetMapping("/home/confirmarHomologarQuestao/{id}")
	public String confirmarHomologarQuestao(@PathVariable Long id, RedirectAttributes redir, Model model) {
		try {
			Questao questao = service.BuscarQuestaoPorId(id);
			questao.setSituacao("Minuta");
			service.CadastrarQuestao(questao);
			if(questao.getUsuario().getCargo().equals("Professor")) {
				mailSender.send(questao.getUsuario().getEmail(),"SGQ - Questão Homologada", "A questao de enunciado: \"" + questao.getEnunciado() + "\" foi homologada");
			}
			redir.addFlashAttribute("mensagemSucesso","Questão homologada com sucesso");
			return"redirect:/home/homologarQuestao";
		}catch(Exception e) {
			model.addAttribute("mensagemErro","erro ao homologar questão");
			return "model/questao/homologarQuestao";
		}
		
	}
	
	@GetMapping("/home/confirmarRevisaoQuestao/{id}")
	public String confirmarRevisaoQuestao(@PathVariable Long id, RedirectAttributes redir, Model model) {
		try {
			Questao questao = service.BuscarQuestaoPorId(id);
			questao.setSituacao("Para revisao");
			service.CadastrarQuestao(questao);
			if(questao.getUsuario().getCargo().equals("Professor")) {
				mailSender.send(questao.getUsuario().getEmail(),"SGQ - Questão Para Revisão", "A questao de enunciado: \"" + questao.getEnunciado() + "\" necessita de revisão");
			}
			redir.addFlashAttribute("mensagemSucesso","Questão enviada para revisão");
			return"redirect:/home/homologarQuestao";
		}catch(Exception e) {
			model.addAttribute("mensagemErro","erro ao enviar a questão para revisao");
			return "model/questao/homologarQuestao";
		}
	}
	
	/*public ArrayList<OpcaoQuestaoObjetiva> CriarListaOpcoesObjetiva(){
		ArrayList<OpcaoQuestaoObjetiva> opcoesObjetivas = new ArrayList<OpcaoQuestaoObjetiva>();
		for(int i = 0; i < 5;i++) {
			if(i == 0) {
				opcoesObjetivas.add(new OpcaoQuestaoObjetiva("enunciado" + i,true));
			}else {
				opcoesObjetivas.add(new OpcaoQuestaoObjetiva("enunciado" + i,false));
			}
		}
		
		return opcoesObjetivas;
	}*/
	
	
}
