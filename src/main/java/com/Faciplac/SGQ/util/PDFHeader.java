package com.Faciplac.SGQ.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFHeader extends PdfPageEventHelper{
	protected PdfPTable table;
    protected float tableHeight;
    public PDFHeader() {
        table = new PdfPTable(1);
        table.setTotalWidth(523);
        table.setLockedWidth(true);
        table.addCell("Prova criada pelo protótipo QuestPlac");
        table.addCell("Prova criada pelo protótipo QuestPlac");
        table.addCell("Prova criada pelo protótipo QuestPlac");
        tableHeight = table.getTotalHeight();
    }

    public float getTableHeight() {
        return tableHeight;
    }

    public void onEndPage(PdfWriter writer, Document document) {
        table.writeSelectedRows(0, -1,
                document.left(),
                document.top() + ((document.topMargin() + tableHeight) / 2),
                writer.getDirectContent());
    }
}
