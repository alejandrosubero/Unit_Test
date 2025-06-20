package com.unitTestGenerator.builders;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.unitTestGenerator.analyzers.services.interfaces.IClassDetailBuilder;
import com.unitTestGenerator.builders.interfaces.IFileManagerDelete;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.printers.interfaces.IPrintService;
import com.unitTestGenerator.uml.sevices.PrintClassToUML;
import com.unitTestGenerator.util.interfaces.IConstantModel;
import org.apache.commons.io.IOUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.*;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Component
public class PDFGenerator implements IPrintService, IFileManagerDelete {


    private String tempNameDirectoryTree = "DirectoryTree.pdf";
    private String tempNameClassTree = "ClassTree.pdf";
    private String tempNameTrees = "tree.pdf";
    private String tempNameTemplate = "Template.pdf";
    private String templateBase = "base.pdf";
    private String templateDependencies = "dependencies.pdf";

    public PDFGenerator() {
    }


    public void converterProjectOrClasInpdf(Project project, Clase classs) {
        if (project == null && classs != null) {
            this.classPdfGeneration(classs);
        }
        if (project != null && classs == null) {
            this.createTempOne(project);
            this.projectPdfGeneration(project);
        }
    }


    private void generateBase(Project project){
        try {
            String pathBase = project.getPathProject() + IConstantModel.Separator;
            String base = pathBase + templateBase;
            List<byte[]> pdfsEnMemoria = new ArrayList<>();
            TemplateBuilder templateBuilder  =  ContextIOC.getInstance().getClassInstance(TemplateBuilder.class);
            pdfsEnMemoria.add(this.convertHtmlToPdfBytes(templateBuilder.coverTemplateGenerate(project.getName())));
            pdfsEnMemoria.add(this.convertHtmlToPdfBytes(templateBuilder.indexTemplateGenerate()));
            pdfsEnMemoria.add(this.convertHtmlToPdfBytes(templateBuilder.projectInfo(project)));
            this.mergePdfBytes(pdfsEnMemoria, base);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void createTempOne(Project project) {
        if (project.getPrinterProject() != null && (project.getPrinterProject().getProjectClassTree() != null || project.getPrinterProject().getProjectDirectoryTree() != null)) {
            String pathBase = project.getPathProject() + IConstantModel.Separator;
            String path1 = pathBase + tempNameDirectoryTree;
            String path2 = pathBase + tempNameClassTree;
            String path3 = pathBase + tempNameTrees;
            this.service().print_BLUE("Starting report generation....");
            this.execute( "Project Directory Tree", project.getPrinterProject().getProjectDirectoryTree(), path1);
            this.execute("Project Class Tree",project.getPrinterProject().getProjectClassTree(), path2);
            appendPdf(path1, path2, path3);
        }
    }

    public void projectDependencies(Project project){
        String pathBase = project.getPathProject() + IConstantModel.Separator;
        String dependenciesPath = pathBase +templateDependencies;
        this.execute( "Project Dependencies", project.getInfo().toStringDependencies(project.getMaven()), dependenciesPath);
    }

    private void projectPdfGeneration(Project project) {
        try {
            List<byte[]> pdfsEnMemoria = new ArrayList<>();
            String pathBase = project.getPathProject() + IConstantModel.Separator;
            String path1 = pathBase + tempNameTrees;
            String path2 = pathBase + tempNameTemplate;
            String basePath = pathBase + templateBase;
            String dependenciesPath = pathBase +templateDependencies;
            String outputPath = pathBase + project.getName() + IConstantModel.PDF_Extention;

            this.generateBase(project);
            this.projectDependencies(project);

            for (Clase classs : project.getClaseList()) {
                pdfsEnMemoria.add(this.convertHtmlToPdfBytes(classs.getClassTemplate()));
            }
            this.mergePdfBytes(pdfsEnMemoria, path2);

            this.combinePdfs(Arrays.asList(basePath, path2, path1, dependenciesPath), outputPath);
            this.service().print_YELLOW("¡successfully generated PDF!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void execute(String title, String text, String outpath) {
        try {
            if(title == null){
                crearPDF(text, outpath);
            }else {
                generatePDF(title,  text,  outpath);
            }
            this.service().print_BLUE("Please wait while generating the report...");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public void createOnepdf(Project project, String text, String title, String nameFile) {
        if (project.getPathProject() != null && text != null) {
            if(nameFile == null){
                nameFile = project.getName()+ IConstantModel.PDF_Extention;
            }
            String pathBase = project.getPathProject() + IConstantModel.Separator;
            String path = pathBase + nameFile;
            this.service().print_BLUE("Starting report generation....");
            this.execute(title,text, path);
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
        } finally {
            document.close();
        }
    }

    public static void generatePDF(String title, String text, String outpath) throws DocumentException, IOException {
        Document document = new Document();
       try {
           PdfWriter.getInstance(document, new FileOutputStream(outpath));
           document.open();
           // font for title: blod and underlined
           Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.UNDERLINE);
           // font for content: normal
           Font fontContent = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
           // create title
           Paragraph paragraphTitle = new Paragraph(title, titleFont);
           paragraphTitle.setSpacingAfter(10);
           // create content
           Paragraph paragraphText = new Paragraph(text, fontContent);
           document.add(paragraphTitle);
           document.add(paragraphText);

       }catch (DocumentException e) {
           throw new RuntimeException(e);
       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       } finally {
           document.close();
       }
    }




    private void classPdfGeneration(Clase classs) {
        if (classs != null) {
            try {
                List<byte[]> pdfsEnMemoria = new ArrayList<>();
                String fileName = classs.getNombre() + IConstantModel.PDF_Extention;
                pdfsEnMemoria.add(this.convertHtmlToPdfBytes(classs.getClassTemplate()));
                this.mergePdfBytes(pdfsEnMemoria, fileName);
                this.service().print_GREEN("¡successfully generated PDF!");
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



    public void combinePdfs1(List<String> pdfPaths, String outputPath) {

        if (pdfPaths == null || pdfPaths.isEmpty()) {
            this.service().print_RED("Error: the list is null or no have paths");
            return;
        }

        PDDocument mainDocument = null;

        try {
            mainDocument = PDDocument.load(new File(pdfPaths.get(0)));
            this.service().print_BLUE("load the first document or base document: " + pdfPaths.get(0));

            for (int i = 1; i < pdfPaths.size(); i++) {
                String currentPdfPath = pdfPaths.get(i);
                this.service().print_BLUE("add pages of : " + currentPdfPath);

                try (PDDocument newDoc = PDDocument.load(new File(currentPdfPath))) {

                    for (int j = 0; j < newDoc.getNumberOfPages(); j++) {
                        mainDocument.addPage(newDoc.getPage(j));
                    }
                }
            }

            mainDocument.save(outputPath);
            this.service().print_BLUE("Please wait while generating the Document......");
            this.deleteTemporalFiles(pdfPaths);

        } catch (IOException e) {
            this.service().print_RED("¡Fail the pdf generated data:");
            this.service().print_RED("PDF out put Path : " + outputPath);
            this.service().print_RED("Error : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Este bloque 'finally' asegura que el 'mainDocument' se cierre SIEMPRE,
            // incluso si ocurre una excepción. Esto es vital para liberar recursos.
            if (mainDocument != null) {
                try {
                    mainDocument.close();
                } catch (IOException e) {
                    this.service().print_RED("Error al cerrar el documento principal: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }


    public void combinePdfs(List<String> pdfPaths, String outputPath) {
        if (pdfPaths == null || pdfPaths.isEmpty()) {
            this.service().print_RED("Error: the list is null or no have paths");
            return;
        }

        PDFMergerUtility merger = new PDFMergerUtility();
        merger.setDestinationFileName(outputPath);

        try {
            for (String path : pdfPaths) {
                this.service().print_BLUE("Adding file to merge: " + path);
                merger.addSource(path);
            }

            this.service().print_BLUE("Please wait while generating the Document......");
            merger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());

            this.deleteTemporalFiles(pdfPaths);

        } catch (IOException e) {
            this.service().print_RED("¡Fail the pdf generated data:");
            this.service().print_RED("PDF output Path : " + outputPath);
            this.service().print_RED("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }





    public void appendPdf(String existingPdfPath, String newPdfPath, String outputPath) {
        try (
                //add existent documents
                PDDocument existingDoc = PDDocument.load(new File(existingPdfPath));
                PDDocument newDoc = PDDocument.load(new File(newPdfPath))
        ) {
            for (int i = 0; i < newDoc.getNumberOfPages(); i++) {
                existingDoc.addPage(newDoc.getPage(i));
            }
            //add content
            existingDoc.save(outputPath);
            this.service().print_BLUE("Please wait while generating the report...");
            this.deleteTemporalFile(existingPdfPath, newPdfPath);

        } catch (IOException e) {
            this.service().print_RED("¡Fail the pdf generated data:");
            this.service().print_RED("PDF out put Path : " + outputPath);
            this.service().print_RED("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void deleteTemporalFile(String existingPdfPath, String newPdfPath) {
        try {
            this.deleteFileIO(existingPdfPath);
            this.deleteFileIO(newPdfPath);
        } catch (Exception e) {
            this.service().print_RED("¡Fail to delete temp file:");
            this.service().print_RED("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void deleteTemporalFiles(List<String> paths ) {
        try {
            for (String existingPdfPath: paths){
                this.deleteFileIO(existingPdfPath);
            }
        } catch (Exception e) {
            this.service().print_RED("¡Fail to delete temp file:");
            this.service().print_RED("Error : " + e.getMessage());
            e.printStackTrace();
        }

    }


    private String ReadResourceFile(String fileName) {
        String contenido = "";
        try (InputStream inputStream = IClassDetailBuilder.class.getClassLoader().getResourceAsStream("templateBase.html")) {
            if (inputStream == null) {
                throw new RuntimeException("file don't fount: " + fileName);
            }
            contenido = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contenido;
    }

}

