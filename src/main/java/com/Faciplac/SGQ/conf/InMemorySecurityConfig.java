package com.Faciplac.SGQ.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

//@Configuration
public class InMemorySecurityConfig {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder
			.inMemoryAuthentication()
			.withUser("058.018.631-84").password("123").roles(
					"PG_CADASTRA_PROVA",
					"PG_CONSULTAR_PROVA",
					"PG_CADASTRA_QUESTAO",
					"PG_CONSULTAR_QUESTAO",
					"PG_CONSULTAR_PROFESSOR",
					"PG_CADASTRAR_PROFESSOR",
					"PG_CONSULTAR_DISCIPLINA",
					"PG_CADASTRAR_DISCIPLINA")
			.and()
			.withUser("058.018.631-83").password("123").roles(
					"PG_CADASTRA_PROVA", 
					"PG_CONSULTAR_PROVA",
					"PG_CADASTRA_QUESTAO",
					"PG_CONSULTAR_QUESTAO");
	}

}
