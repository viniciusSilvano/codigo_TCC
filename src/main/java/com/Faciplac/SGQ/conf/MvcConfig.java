package com.Faciplac.SGQ.conf;




import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	//Language
	
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(new Locale("pt","BR"));
	    return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	    localeChangeInterceptor.setParamName("language");
	    return localeChangeInterceptor;
	}

	@Bean
	@Description("Thymeleaf template resolver serving HTML 5")
	public ClassLoaderTemplateResolver templateResolver() {

		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

		templateResolver.setPrefix("templates/");
		templateResolver.setCacheable(false);
		templateResolver.setSuffix(".html");        
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCharacterEncoding("UTF-8");

		return templateResolver;
	}

	@Bean
	@Description("Thymeleaf template engine with Spring integration")
	public SpringTemplateEngine templateEngine() {

		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		
		templateEngine.addDialect(new SpringSecurityDialect());
		return templateEngine;
	}

	@Bean
	@Description("Thymeleaf view resolver")
	public ViewResolver viewResolver() {

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");

		return viewResolver;
	}    

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		//Get views
		
		//Login e home
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/home").setViewName("home");
		
		//Prova
		registry.addViewController("/home/cadastrarProva").setViewName("criarprova");
		registry.addViewController("/home/consultarProva").setViewName("consultarProva");
		
		//Questao
		registry.addViewController("/home/cadastrarQuestao").setViewName("criarQuestao");
		registry.addViewController("/home/consultarQuestao").setViewName("consultarQuestao");
		registry.addViewController("/home/homologarQuestao").setViewName("homologarQuestao");
		registry.addViewController("/home/editarQuestao").setViewName("criarQuestao");
		
		//Professor
		registry.addViewController("/home/consultarProfessor").setViewName("consultarProfessor");
		registry.addViewController("/home/cadastrarProfessor").setViewName("criarProfessor");
		
		
		//Disciplina
		registry.addViewController("/home/consultarDisciplina").setViewName("consultarDisciplina");
		registry.addViewController("/home/cadastrarDisciplina").setViewName("criarDisciplina");
		
		//Requisições Post
		
	}

}
