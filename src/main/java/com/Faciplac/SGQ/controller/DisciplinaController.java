package com.Faciplac.SGQ.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Faciplac.SGQ.model.Disciplina;
import com.Faciplac.SGQ.service.DisciplinaService;
//https://spring.io/guides/gs/validating-form-input/
//https://stackoverflow.com/questions/36744655/for-each-operator-in-thymeleaf
//https://www.caelum.com.br/apostila-java-orientacao-objetos/collections-framework/#listas-javautillist

@Controller
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService service;
	
	//Consultar
	@GetMapping("home/consultarDisciplina")
	public String ConsultarDisciplina(Model model){
		List<Disciplina> disciplinas = service.BuscarTodasAsDisciplinas();
		model.addAttribute("disciplinas", disciplinas);
		return "model/disciplina/consultarDisciplina";
	}
	
	//Excluir
	@GetMapping("home/consultarDisciplina/DeletarDisciplina/{idDisciplina}")
	public String ModalExcluirDisciplina(@PathVariable("idDisciplina") String idDisciplina, Model model) {
		model.addAttribute("tituloModal","Deseja Excluir?");
		model.addAttribute("tituloBotaoModal","Excluir");
		model.addAttribute("urlBotaoModal","/home/consultarDisciplina/ConfirmarExclusaoDisciplina/" + idDisciplina);
		model.addAttribute("tituloBotaoDismissModal","Cancelar");
		//model.addAttribute("idDisciplina",idDisciplina);
		return "util/modal :: modal";
	}
	
	@GetMapping("home/consultarDisciplina/ConfirmarExclusaoDisciplina/{idDisciplina}")
	public String ExcluirDisciplina(@PathVariable("idDisciplina") String idDisciplina,Model model) {
		service.ExcluirDisciplina(Long.parseLong(idDisciplina));
		return "redirect:/home/consultarDisciplina";
	}
	
	//Cadastrar
	@GetMapping("home/cadastrarDisciplina")
	public String GetCadastrarDisciplina(Disciplina disciplina, Model model) {
		model.addAttribute("action", "/home/cadastrarDisciplina");
		model.addAttribute("titulo","Cadastrar - Disciplina");
		return "model/disciplina/criarDisciplina";
	}
		
	@PostMapping("home/cadastrarDisciplina")
	public String PostCadastrarDisciplina(@Valid Disciplina disciplina, BindingResult bindingResult, Model model,RedirectAttributes redir) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("mensagemErro","erro no formulário");
			return "model/disciplina/criarDisciplina";
			
		}
		
		service.CadastrarDisciplina(disciplina);
		redir.addFlashAttribute("mensagemSucesso","Disciplina criada com sucesso");
		
		return "redirect:/home/cadastrarDisciplina";
		
	}
	
	//Editar
	@GetMapping("home/consultarDisciplina/editarDisciplina/{idDisciplina}")
	public String GetEditarDisciplina(Disciplina disciplina, @PathVariable Long idDisciplina, Model model) {
		disciplina = service.BuscarPorId(idDisciplina);
		model.addAttribute("action", "/home/consultarDisciplina/editarDisciplina");
		model.addAttribute("titulo","Editar - Disciplina");
		model.addAttribute("disciplina", disciplina);
		return "model/disciplina/criarDisciplina";
	}
	
	@PostMapping("home/consultarDisciplina/editarDisciplina")
	public String PostEditarDisciplina(@Valid Disciplina disciplina, BindingResult bindingResult, Model model,RedirectAttributes redir) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("mensagemErro","erro no formulário");
			return "model/disciplina/criarDisciplina";
			
		}
		try {
			service.CadastrarDisciplina(disciplina);
			redir.addFlashAttribute("mensagemSucesso","Disciplina editada com sucesso");
		}catch(Exception e) {
			model.addAttribute("mensagemErro","erro no servidor");
			return "model/disciplina/criarDisciplina";
		}
		
		return "redirect:/home/cadastrarDisciplina";
	}
}
