package com.Faciplac.SGQ.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Faciplac.SGQ.model.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {
	public Professor findOneByLogin(String login);
}
