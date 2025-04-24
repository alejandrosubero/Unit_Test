package com.unitTestGenerator.builders;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.unitTestGenerator.analyzers.services.interfaces.IClassDetailBuilder;
import com.unitTestGenerator.ioc.anotations.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.printers.IPrintService;
import com.unitTestGenerator.util.IConstantModel;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Component
public class PDFGenerator implements IPrintService {


    private String tempNameDirectoryTree = "DirectoryTree.pdf";
    private String tempNameClassTree = "ClassTree.pdf";
    private String tempNameTrees = "tree.pdf";
    private String tempNameTemplate ="Template.pdf";

    public PDFGenerator() {
    }

    public void converterProjectOrClasInpdf(Project project, Clase classs){
        if(project == null && classs != null){
            this.classPdfGeneration(classs);
        }
        if(project != null && classs == null){
            createTempOne(project);
            this.projectPdfGeneration(project);
        }
    }

    private void createTempOne(Project project){
        if (project.getPrinterProject() != null && (project.getPrinterProject().getProjectClassTree() != null || project.getPrinterProject().getProjectDirectoryTree() != null)) {
            String pathBase = project.getPathProject() + IConstantModel.Separator;
            String path1 = pathBase + tempNameDirectoryTree;
            String path2 = pathBase + tempNameClassTree;
            String path3 = pathBase + tempNameTrees;
            execute (project.getPrinterProject().getProjectDirectoryTree(), path1);
            execute (project.getPrinterProject().getProjectClassTree(), path2);
            appendPdf(path1, path2, path3);
        }
    }



    private void projectPdfGeneration(Project project){
        try {
            String pathBase = project.getPathProject() + IConstantModel.Separator;
            String path1 = pathBase + tempNameTrees;
            String path2 = pathBase + tempNameTemplate;
            String outputPath = pathBase + project.getName()+IConstantModel.PDF_Extention;;

            List<byte[]> pdfsEnMemoria = new ArrayList<>();
            for(Clase classs: project.getClaseList()){
                pdfsEnMemoria.add(this.convertHtmlToPdfBytes(classs.getClassTemplate()));
            }
            this.mergePdfBytes(pdfsEnMemoria, path2);
            appendPdf(path2, path1, outputPath);

            this.service().print_YELLOW("¡successfully generated PDF!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void classPdfGeneration(Clase classs) {
        if (classs != null) {
            try {
                List<byte[]> pdfsEnMemoria = new ArrayList<>();
                String fileName = classs.getNombre() + IConstantModel.PDF_Extention;;
                pdfsEnMemoria.add(this.convertHtmlToPdfBytes(classs.getClassTemplate()));
                this.mergePdfBytes(pdfsEnMemoria, fileName);
                this.service().print_YELLOW("¡successfully generated PDF!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] convertHtmlToPdfBytes(String html) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new IOException("Error al generar PDF en memoria: " + e.getMessage(), e);
        }
    }

    public static void mergePdfBytes(List<byte[]> pdfBytesList, String outputPath) throws IOException {
        PDFMergerUtility merger = new PDFMergerUtility();

        for (byte[] pdfBytes : pdfBytesList) {
            try (InputStream inputStream = new ByteArrayInputStream(pdfBytes)) {
                merger.addSource(inputStream);
            }
        }
        merger.setDestinationFileName(outputPath);
        merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
    }


    private String ReadResourceFile (String fileName){
        String contenido ="";
        try (InputStream inputStream = IClassDetailBuilder.class.getClassLoader().getResourceAsStream("templateBase.html")) {
            if (inputStream == null) {
                throw new RuntimeException("file don't fount: "+fileName);
            }
            contenido = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contenido;
    }


    public void execute (String text, String outpath) {
        try {
            crearPDF(text,outpath);
            this.service().print_BLUE("PDF generated in: " + outpath);
        } catch (DocumentException | IOException e) {
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


    public  void appendPdf(String existingPdfPath, String newPdfPath, String outputPath) {
        try (
                // Cargar documentos existentes
                PDDocument existingDoc = PDDocument.load(new File(existingPdfPath));
                PDDocument newDoc = PDDocument.load(new File(newPdfPath))
        ) {
            // Agregar todas las páginas del nuevo PDF al existente
            for (int i = 0; i < newDoc.getNumberOfPages(); i++) {
                existingDoc.addPage(newDoc.getPage(i));
            }
            // Guardar el PDF combinado
            existingDoc.save(outputPath);

            this.service().print_YELLOW("PDF generated in: " + outputPath);
            this.service().print_YELLOW("¡successfully generated!");

        } catch (IOException e) {
            this.service().print_RED("¡Fail the pdf generated data:" );
            this.service().print_RED("PDF out put Path : " + outputPath);
            this.service().print_RED("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }


}

