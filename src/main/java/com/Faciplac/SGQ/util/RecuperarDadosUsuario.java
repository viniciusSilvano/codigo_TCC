package com.Faciplac.SGQ.util;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.Faciplac.SGQ.model.Usuario;
import com.Faciplac.SGQ.service.UsuarioService;

@Component
public class RecuperarDadosUsuario {
	//private Object principal;
	
	public RecuperarDadosUsuario() {
		
	}
	
	public String recuperarUserName(Object principal) {
		if (principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		} else {
			return principal.toString();
		}
	}
	
	public String recuperarCargo(UsuarioService service, Object principal) {
		Usuario usuario = service.RecuperarUsuarioPorLogin(((UserDetails)principal).getUsername());
		return usuario.getCargo();
	}
}
