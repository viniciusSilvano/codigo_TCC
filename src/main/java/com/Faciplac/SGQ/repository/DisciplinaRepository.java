package com.Faciplac.SGQ.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Faciplac.SGQ.model.Disciplina;

@Repository
public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {

}
