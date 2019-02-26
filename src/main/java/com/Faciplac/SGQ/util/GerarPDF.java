package com.Faciplac.SGQ.util;


import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;

import com.itextpdf.tool.xml.XMLWorkerHelper;


public class GerarPDF {
	public static final String DEST= "results/objects/";
	public static final String IMGCapa = "src/main/resources/static/images/faciplacLogoProva.jpg";
	/*public static final String[] HTML = {
	        "resources/xml/page1.html",
	        "resources/xml/page2.html",
	        "resources/xml/page3.html"
	    };*/
	public GerarPDF(List<String> HTML, String nomePDF, String disciplina)   throws IOException, DocumentException {
		File file = new File(nomePDF);
        file.getParentFile().mkdirs();
        this.createPdf(nomePDF, HTML.toArray(new String[0]),disciplina);
        //this.createPdf2(DEST, HTML);
	}
	
	public void createPdf(String file, String[] HTML, String disciplina) throws IOException, DocumentException {
        Document document = new Document();
//        PdfCopy copy = new PdfCopy(document, new FileOutputStream(file));
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
       // PDFHeader headerEvent = new PDFHeader();
        PDFFooterEHeader footerEvent = new PDFFooterEHeader(disciplina);
       // writer.setPageEvent(headerEvent);
        
        document.open();
       
        criarConteudoCapaDaProva(document,disciplina);
        writer.setPageEvent(footerEvent);
        String css = readCSS();
        //com.itextpdf.text.List listaConteudo = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
        int numeroQuestao = 1;
        for (String htmlfile : HTML) {
           
        	String html = Utilities.readFileToString(htmlfile);
            ElementList list = XMLWorkerHelper.parseToElementList(html, css);
            Paragraph paragraph = new Paragraph("Questão " + numeroQuestao + "");
            paragraph.setSpacingBefore(10);
            paragraph.setSpacingAfter(0);
            Boolean VariavelSetada = false;
            for (Element e : list) {	
            	if(!VariavelSetada) {
            		document.add(paragraph);
            		VariavelSetada = true;
            	}
            	document.add(e);
            }
            numeroQuestao++;
            //document.newPage();
        }
        document.close();
    }
	private String readCSS() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        InputStream is = XMLWorkerHelper.class.getResourceAsStream("/default.css");
        while ((length = is.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return new String(baos.toByteArray());
    }
	
	private void criarConteudoCapaDaProva(Document document,String disciplina) throws BadElementException, MalformedURLException, DocumentException, IOException {
		 Image imagemCapa = Image.getInstance(IMGCapa);
		 imagemCapa.setAlignment(Element.ALIGN_CENTER);
		 document.add(imagemCapa);
		 Paragraph paragraph1 = new Paragraph(disciplina);
		 paragraph1.setAlignment(Element.ALIGN_CENTER);
		 document.add(paragraph1);
	     paragraph1 = new Paragraph("Vinicius Silvano");
	     paragraph1.setAlignment(Element.ALIGN_CENTER);
	     document.add(paragraph1);	     
	     paragraph1 = new Paragraph("LEIA ATENTAMENTE AS INSTRUÇÕES ABAIXO");
	     paragraph1.setAlignment(Element.ALIGN_CENTER);
	     paragraph1.setSpacingAfter(6);
	     paragraph1.setFont(new Font(FontFamily.TIMES_ROMAN,18));
	     document.add(paragraph1);
	     paragraph1 = null;
	     criarInstrucoesParaProva(document);
	     document.newPage();
	}

	public void criarInstrucoesParaProva(Document document) throws DocumentException {
		com.itextpdf.text.List listaInstrucoes = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
		Paragraph paragraph = new Paragraph("Verifique se, além deste caderno de questões, você recebeu a Folha de Respostas"
				+ ", destinado à transcrição das respostas das questões de múltipla escolha (objetivas) e das questões dissertativas.");
		ListItem itemDaLista = new ListItem(paragraph);
		itemDaLista.setSpacingAfter(6);
		listaInstrucoes.add(itemDaLista);
		
		paragraph = new Paragraph("As questões e seus respectivos pesos dessa avaliação estão distribuídas em:");
		itemDaLista = new ListItem(paragraph);
		PdfPTable tabela = new PdfPTable(3);
		tabela.addCell("Parte");
		tabela.addCell("Quantide de questões");
		tabela.addCell("Peso individual");
		for(int i = 0; i < 6; i++) {
			tabela.addCell("");
		}
		itemDaLista.add(tabela);
		itemDaLista.setSpacingAfter(6);
		listaInstrucoes.add(itemDaLista);
		
		paragraph = new Paragraph("Leia atentamente cada questão antes de iniciar o teste, pois questões com marcações incorretas"
				+ "ou rasuras não serão consideradas.");
		itemDaLista = new ListItem(paragraph);
		itemDaLista.setSpacingAfter(6);
		listaInstrucoes.add(itemDaLista);
		
		paragraph = new Paragraph("Use caneta esferográfica de tinta preta ou azul, tanto para marcar as respostas das questões"
				+ "objetivas quanto para escrever as respostas das questões discursivas. Lápis ou outros meios de marcação serão"
				+ "desconsiderados.");
		itemDaLista = new ListItem(paragraph);
		itemDaLista.setSpacingAfter(6);
		listaInstrucoes.add(itemDaLista);
		
		paragraph = new Paragraph("Responda cada questão discursiva em, no máximo, 15 linhas. Qualquer texto que ultrapasse o espaço"
				+ "destinado à resposta será desconsiderado.");
		itemDaLista = new ListItem(paragraph);
		itemDaLista.setSpacingAfter(6);
		listaInstrucoes.add(itemDaLista);
		
		paragraph = new Paragraph("Não serão permitidos o uso de quaisquer equipamentos eletrônicos, incluindo aparelhos celulares"
				+ "tablets e notebooks. Eles devem permanecer desligados durante toda a avaliação.");
		itemDaLista = new ListItem(paragraph);
		itemDaLista.setSpacingAfter(6);
		listaInstrucoes.add(itemDaLista);
		
		paragraph = new Paragraph("Não se comunique com os demais estudantes nem troque material com eles; não consulte material"
				+ "bibliográfico, cadernos ou anotações de qualquer espécie.");
		itemDaLista = new ListItem(paragraph);
		itemDaLista.setSpacingAfter(6);
		listaInstrucoes.add(itemDaLista);
		
		paragraph = new Paragraph("Observe as instruções de marcação das respostas das questões de múltipla escolha (apenas uma res"
				+ "posta por questão), expressas na Folha de Respostas.");
		itemDaLista = new ListItem(paragraph);
		itemDaLista.setSpacingAfter(6);
		listaInstrucoes.add(itemDaLista);
		
		paragraph = new Paragraph("Ao terminar a avaliação, entregue sua Folha de Respostas ao responsável pela aplicação da prova.");
		itemDaLista = new ListItem(paragraph);
		itemDaLista.setSpacingAfter(6);
		listaInstrucoes.add(itemDaLista);
		
		paragraph = new Paragraph("Para o correto preenchimento das questões objetivas na Folha de Respostas, informe apenas a letra"
				+ "da opção desejada, lembrando que cada questão objetiva permite apenas uma única marcação e que mais de uma indica"
				+ "ção anulará a questão");
		itemDaLista = new ListItem(paragraph);
		itemDaLista.setSpacingAfter(6);
		listaInstrucoes.add(itemDaLista);
		document.add(listaInstrucoes);
		itemDaLista = null;
		listaInstrucoes = null;
	
	}
	
	
	/*public void createPdf2(String file, String HTML) throws IOException, DocumentException {
		 Document document = new Document();
		 PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
		 document.open();
		 String interHTML = HTML;
		 HTML = "<html><head><title>Test PDF</title></head><body>" + interHTML + "</body></html>";
		 
		 //HTML = "<html><head><title>Test PDF</title></head><body>" + HTML + "</body></html>";
		// CSS
		 	CSSResolver cssResolver =
	                XMLWorkerHelper.getInstance().getDefaultCssResolver(true); 
		 // HTML
	        HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
	        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
	        htmlContext.setImageProvider(new Base64ImageProvider());
	        
	     // Pipelines
	        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
	        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
	        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);   
	        
	      // XML Worker
	        XMLWorker worker = new XMLWorker(css, true);
	        XMLParser p = new XMLParser(worker);
	        p.parse(new ByteArrayInputStream(HTML.getBytes()));
	        //XMLWorkerHelper.getInstance().parseXHtml(writer, document,
	        		//new ByteArrayInputStream(HTML.getBytes()),new ByteArrayInputStream(HTML.getBytes()), Charset.forName("UTF-8"));
	        // step 5
	        document.close();   
		 
	 }*/
}
