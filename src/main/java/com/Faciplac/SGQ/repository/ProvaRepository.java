package com.Faciplac.SGQ.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Faciplac.SGQ.model.Prova;

@Repository
public interface ProvaRepository extends CrudRepository<Prova, Long> {
	
}
