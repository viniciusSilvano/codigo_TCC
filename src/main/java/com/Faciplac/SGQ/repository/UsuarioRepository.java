package com.Faciplac.SGQ.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Faciplac.SGQ.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	public Usuario findByLogin(String login);
}
