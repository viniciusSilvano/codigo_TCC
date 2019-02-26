package com.Faciplac.SGQ.util;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFFooterEHeader extends PdfPageEventHelper{
	
	String disciplina;
	
	public PDFFooterEHeader() {}
	
	public PDFFooterEHeader(String disciplina) {
		this.disciplina = disciplina;
	}

	public void onEndPage(PdfWriter writer, Document document) {
		Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
		
        PdfContentByte cb = writer.getDirectContent();
        Phrase header = new Phrase("" + disciplina, ffont);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	Date date = new Date();
        Phrase footer = new Phrase(dateFormat.format(date), ffont);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                header,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.top() + 10, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 10, 0);
    }
	
}
