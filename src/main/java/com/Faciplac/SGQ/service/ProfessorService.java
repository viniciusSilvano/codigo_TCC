package com.Faciplac.SGQ.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Faciplac.SGQ.model.Professor;
import com.Faciplac.SGQ.repository.ProfessorRepository;

@Service
@Transactional
public class ProfessorService {
	@Autowired
	ProfessorRepository repository;
	
	public void CadastrarProfessor(Professor professor) {
		repository.save(professor);
	}
	
	public Professor RecuperarProfessorPorLogin(String login) {
		return repository.findOneByLogin(login);
	}
}
