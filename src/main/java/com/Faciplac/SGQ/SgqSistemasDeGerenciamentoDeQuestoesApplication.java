package com.Faciplac.SGQ;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Faciplac.SGQ.util.TaxonomiaDeBloomEnum;

@SpringBootApplication
public class SgqSistemasDeGerenciamentoDeQuestoesApplication {
//https://moelholm.com/2016/11/09/spring-boot-controlling-timezones-with-hibernate/
// https://www.mkyong.com/java/java-how-to-get-current-date-time-date-and-calender/
	@PostConstruct
	  void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	  }
	
	public static void main(String[] args) {
		String teste = TaxonomiaDeBloomEnum.valueOf("n1").getValor();
		for (TaxonomiaDeBloomEnum valor : TaxonomiaDeBloomEnum.values()) {
			valor.getValor();
		}
		TaxonomiaDeBloomEnum.n1.getValor();
		System.out.println(teste);
		SpringApplication.run(SgqSistemasDeGerenciamentoDeQuestoesApplication.class, args);
	}
}
