package com.Faciplac.SGQ.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.Faciplac.SGQ.model.Professor;
import com.Faciplac.SGQ.service.ProfessorService;
import com.Faciplac.SGQ.util.InserePermissao;
import com.Faciplac.SGQ.util.SmtpMailSender;
import com.Faciplac.SGQ.util.StringRandom;

@Controller
public class ProfessorController {
	
	//Envio de email
	@Autowired
	SmtpMailSender smtpMail;
	//Salvar no banco
	@Autowired
	ProfessorService service;
	//Criptografar senha
	
	@Autowired
	InserePermissao inserePermissao;
	//CPF Util
	//@Autowired
	//CPFUtil cpfUtil;
	
	@GetMapping("/home/consultarProfessor")
	public String getConsultarProfessor() {
		return "model/professor/consultarProfessor";
	}
	
	@GetMapping("/home/cadastrarProfessor")
	public String getCadastrarProfessor(Professor professor) {
		return "model/professor/criarProfessor";
	}
	
	@PostMapping("/home/cadastrarProfessor")
	public String setCadastrarProfessor(@Valid Professor professor, BindingResult bindingResult, Model model,RedirectAttributes redir) {
		//Gerar senha aleatoria
		StringRandom sr = new StringRandom();
		professor.setSenha(new BCryptPasswordEncoder().encode(sr.generateRandomString()));
		//professor.setSenha(sr.generateRandomString());
		professor.setAtivo(true);
		
		//Verificar se a model está valida
		if(bindingResult.hasErrors()) {
			model.addAttribute("mensagemErro","erro no formulário");
			return "model/professor/criarProfessor";
			
		}
		
		//Enviar E-mail
		try {
			smtpMail.send(professor.getEmail(),"SGQ- Novo Cadastro" , "Olá, uma conta de professor no SGQ(Sistema de Gerenciamento de Questões) foi criada USUARIO: " + professor.getLogin() + " senha: " + professor.getSenha() +" ");
			professor.setPermissoes(inserePermissao.Professor());
			professor.setCargo("Professor");
			service.CadastrarProfessor(professor);
			redir.addFlashAttribute("mensagemSucesso","Professor cadastrado com sucesso");
			return "redirect:/home/cadastrarProfessor";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("mensagemErro","Erro ao cadastrar");
			//e.printStackTrace();
			return "model/professor/criarProfessor";
		}
		
		
	}
	
}
