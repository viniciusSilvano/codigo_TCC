package com.Faciplac.SGQ.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Faciplac.SGQ.model.Disciplina;
import com.Faciplac.SGQ.repository.DisciplinaRepository;

@Service
@Transactional
public class DisciplinaService {
	@Autowired
	private DisciplinaRepository repository;
	
	public List<Disciplina> BuscarTodasAsDisciplinas() {
		List<Disciplina> disciplinas = new ArrayList<>();
		repository.findAll().forEach(disciplinas::add);
		return disciplinas;
	}
	
	public void CadastrarDisciplina(Disciplina disciplina) {
		repository.save(disciplina);
	}
	
	public void ExcluirDisciplina(Long idDisciplina) {
		repository.delete(idDisciplina);
	}
	
	public Disciplina BuscarPorId(Long id) {
		return repository.findOne(id);
	}
	
}
