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
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Component
public class PDFGenerator implements IPrintService {

    public PDFGenerator() {
    }

    public void converterProjectOrClasInpdf(Project project, Clase classs){
        if(project == null && classs != null){
            this.classPdfGeneration(classs);
        }
        if(project != null && classs == null){
            createTempOne(project);
//            this.projectPdfGeneration(project);
        }
    }

    private void createTempOne(Project project){

        if (project.getPrinterProject() != null && (project.getPrinterProject().getProjectClassTree() != null || project.getPrinterProject().getProjectDirectoryTree() != null)) {

            String pathBase = project.getPathProject() + IConstantModel.Separator;
            String path1 = pathBase + "DirectoryTree.pdf";
            String path2 = pathBase + "ClassTree.pdf";


            execute (project.getPrinterProject().getProjectDirectoryTree(), path1);
            execute (project.getPrinterProject().getProjectClassTree(), path2);
            appendPdf(path1, path2, "tree.pdf");
        }
    }



    private void projectPdfGeneration(Project project){
        try {
            List<byte[]> pdfsEnMemoria = new ArrayList<>();
            String fileName = project.getName()+IConstantModel.PDF_Extention;

            for(Clase classs: project.getClaseList()){
                pdfsEnMemoria.add(this.convertHtmlToPdfBytes(classs.getClassTemplate()));
            }
            String outputPath = project.getPathProject() + IConstantModel.Separator + fileName;
            this.mergePdfBytes(pdfsEnMemoria, outputPath);
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
            crearPDF(outpath, text);
            System.out.println("PDF generated in: " + outpath);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void crearPDF1(String text, String outpath) throws FileNotFoundException, DocumentException {
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


    public static Boolean crearPDF(String text, String outpath) throws IOException, DocumentException {
        if (outpath == null || outpath.trim().isEmpty()) {
            throw new IllegalArgumentException("La ruta no puede estar vacía o nula.");
        }

        Document document = new Document();
        PdfWriter writer = null;
        try {
            File file = new File(outpath);
            File parentDir = file.getParentFile();

            // Validar y crear directorios padre
            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) {
                    throw new IOException("Error al crear directorios para: " + parentDir.getAbsolutePath());
                }
            }

            // Validar si el archivo se puede crear
            if (!file.createNewFile()) {
                throw new IOException("No se pudo crear el archivo: " + outpath);
            }

            writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            document.add(new Paragraph(text));

        } finally {
            if (document != null && document.isOpen()) {
                document.close();
            }
            if (writer != null) {
                writer.close();
            }

            File parentDir = file.getParentFile();
            if (parentDir != null) {
                if (!parentDir.exists() && !parentDir.mkdirs()) {
                    throw new IOException("No se pudo crear el directorio: " + parentDir.getAbsolutePath());
                }
            }

            return ;

        }
    }


    public static void appendPdf(String existingPdfPath, String newPdfPath, String outputPath) {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

