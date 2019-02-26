package com.Faciplac.SGQ.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Faciplac.SGQ.model.Usuario;
import com.Faciplac.SGQ.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario RecuperarUsuarioPorLogin(String login) {
		return repository.findByLogin(login);
	}
}
