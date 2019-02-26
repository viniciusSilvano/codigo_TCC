package com.Faciplac.SGQ.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.DocumentException;

//https://stackoverflow.com/questions/5936003/write-html-file-using-java
@Component
public class GerarEnunciadoQuestaoEmHTML {
	@Autowired
	StringRandom random;
	
	public GerarEnunciadoQuestaoEmHTML() {
		
	}
	
	public GerarEnunciadoQuestaoEmHTML(String HTML) throws IOException, DocumentException {
		criarEnunciadoEmHTML(HTML);
	}
	
	public String criarEnunciadoEmHTML(String HTML) throws IOException {
		
		// usando o template de arquivo html
		File htmlTemplateFile = new File("results/Template HTML/template.html");
		String htmlString = FileUtils.readFileToString(htmlTemplateFile);
		String title = "New Page";
		String body = HTML;
		htmlString = htmlString.replace("$title", title);
		htmlString = htmlString.replace("$body", body);
		
		//criando enunciado em arquivo html
		String nome =  "enunciado" + random.generateRandomString() + ".html";
		File newHtmlFile = new File("results/EnunciadosHTML/" + nome);
		while(newHtmlFile.exists() && !newHtmlFile.isDirectory()) {
			nome = "enunciado" + random.generateRandomString() + random.getRandomNumber() + ".html";
			newHtmlFile = new File("results/EnunciadosHTML/" + nome);
		}
		FileUtils.writeStringToFile(newHtmlFile, htmlString);
		return nome;
			
	}
	
	public boolean excluirEnunciadoEmHTML(String enunciado) {
		File file = new File("results/EnunciadosHTML/" + enunciado);

		return file.delete();
		
	}
	
	public void reescreverArquivoHtml(String HTML, String enunciado) throws IOException {
		// usando o template de arquivo html
		File htmlTemplateFile = new File("results/Template HTML/template.html");
		String htmlString = FileUtils.readFileToString(htmlTemplateFile);
		String title = "New Page";
		String body = HTML;
		htmlString = htmlString.replace("$title", title);
		htmlString = htmlString.replace("$body", body);
		
		File newHtmlFile = new File("results/EnunciadosHTML/" + enunciado);
		FileUtils.writeStringToFile(newHtmlFile, htmlString);
		
	}  
	
}
