package com.unitTestGenerator.builders;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.unitTestGenerator.ioc.anotations.Componente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Componente
public class PDFGenerator {


    public void execute (String text, String outpath) {
        try {
            crearPDF(outpath, text);
            System.out.println("PDF generated in: " + outpath);
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void crearPDF(String text, String outpath) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outpath));
            document.open();
            document.add(new Paragraph(text));

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            document.close();
        }

    }
}

