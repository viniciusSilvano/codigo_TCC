package com.Faciplac.SGQ.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//@Autowired
	//private GpUserDetailsService userDetailsService;
	
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
	            .ignoring()
	                .antMatchers("/js/**","/css/**","/images/**","/webjars/**","/fonts/**");
	    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers("/home/cadastrarProva").hasAnyAuthority("PG_CADASTRA_PROVA")
				.antMatchers("/home/consultarProva").hasAnyAuthority("PG_CONSULTAR_PROVA")
				.antMatchers("/home/cadastrarQuestao").hasAnyAuthority("PG_CADASTRA_QUESTAO")
				.antMatchers("/home/consultarQuestao").hasAnyAuthority("PG_CONSULTAR_QUESTAO")
				.antMatchers("/home/homologarQuestao").hasAnyAuthority("PG_HOMOLOGAR_QUESTAO")
				.antMatchers("/home/consultarProfessor").hasAnyAuthority("PG_CONSULTAR_PROFESSOR")
				.antMatchers("/home/cadastrarProfessor").hasAnyAuthority("PG_CADASTRAR_PROFESSOR")
				.antMatchers("/home/consultarDisciplina").hasAnyAuthority("PG_CONSULTAR_DISCIPLINA")
				.antMatchers("/home/cadastrarDisciplina").hasAnyAuthority("PG_CADASTRAR_DISCIPLINA")
				.anyRequest()
				.authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
			.logout()
				.logoutSuccessUrl("/login?logout")
				.permitAll()
			.and()
			.httpBasic();
		
		
			/*.and()
			.rememberMe()
				.userDetailsService(userDetailsService);*/
	}

}
