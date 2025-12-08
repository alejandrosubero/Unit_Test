package com.unitTestGenerator.exception;

import com.unitTestGenerator.exception.model.ErrorResponse;
import com.unitTestGenerator.printers.interfaces.IPrintService;
import com.unitTestGenerator.util.interfaces.IConstantModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler, IPrintService {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String LOG_FILE = "errores-app.log";
    private static final long MAX_FILE_SIZE = 20L * 1024 * 1024; // 20 MB
    private static final String LOG_DIR = "log";

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {


        ExceptionHandler.handle(throwable);

        // 1. Crear respuesta de error estandarizada
        ErrorResponse errorResponse = new ErrorResponse(
                extractErrorCode(throwable),
                throwable.getMessage(),
                thread.getName()
        );

        StringBuffer buffer = new StringBuffer();

        // 2. Loggear en consola con formato
        String margin ="═══════════════════════════════════════════════════════ 🚨 ERROR NO CACHED ═══════════════════════════════════════════════════════";
        buffer.append(margin).append(IConstantModel.BREAK_LINE);
        buffer.append(errorResponse.getErrorCode() ).append(IConstantModel.BREAK_LINE);
        buffer.append(errorResponse.getTimestamp()).append(IConstantModel.BREAK_LINE);
        buffer.append(errorResponse.getMessage()).append(IConstantModel.BREAK_LINE);
        buffer.append(ExceptionHandler.getLastStackTrace()).append(IConstantModel.BREAK_LINE);
        buffer.append(margin).append(IConstantModel.BREAK_LINE);

        this.service().print_DARKGREEN(buffer.toString());

       // TODO: SOMETHING FOR errorResponse SAVE IN MEMORY OR DB errorResponse ?
        // 3. Loggear en archivo
        this.logToFile(errorResponse, throwable);

        // 4. Loggear con SLF4J (para frameworks externos)
       //logger.error("Excepción global capturada: {}", errorResponse, throwable);
        // 5. Opcional: Enviar alerta (email, Slack, etc.)
        // sendAlert(errorResponse);

        // 6. Finalizar aplicación si es crítico
        if (isCritical(throwable)) {
            System.err.println("⚠️  Error crítico detectado. Finalizando aplicación...");
            System.exit(1);
        }
    }

    private String extractErrorCode(Throwable throwable) {
        if (throwable instanceof BusinessException) {
            return ((BusinessException) throwable).getErrorCode();
        }
        if (throwable instanceof TechnicalException) {
            return ((TechnicalException) throwable).getErrorCode();
        }
        return "UNEXPECTED_ERROR";
    }

    private boolean isCritical(Throwable throwable) {
        return throwable instanceof OutOfMemoryError ||
                throwable instanceof StackOverflowError ||
                throwable instanceof TechnicalException;
    }


    private void logToFile(ErrorResponse error, Throwable throwable) {

        try {
            File logFolder = new File(LOG_DIR);
            if (!logFolder.exists()) {
                logFolder.mkdirs();
            }

            File file = new File(logFolder, LOG_FILE);

            if (file.exists() && file.length() >= MAX_FILE_SIZE) {
                file = rotateFile(file);
            }

            try (FileWriter fw = new FileWriter(file, true);
                 PrintWriter pw = new PrintWriter(fw)) {

                pw.println("━━━━━━━━━━━━━━━━━━ ERROR ━━━━━━━━━━━━━━━━━━");
                pw.println("Timestamp     : " + error.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                pw.println("Error Code    : " + error.getErrorCode());
                pw.println("Message       : " + (error.getMessage() != null ? error.getMessage() : "(no message)"));
                pw.println("Thread        : " + error.getThreadName());
                pw.println("Exception     : " + throwable.getClass().getName());
                pw.println("Stack Trace   :");
                StringWriter sw = new StringWriter();
                throwable.printStackTrace(new PrintWriter(sw));
                for (String line : sw.toString().split("\n")) {
                    pw.println("    " + line);
                }
                pw.println("────────────────────────────────────────────────────────\n");


//                pw.println("═══════════════════════════════════════════════════════");
//                pw.println("Timestamp: " + error.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//                pw.println("Error Code: " + error.getErrorCode());
//                pw.println("Message: " + error.getMessage());
//                pw.println("Thread: " + error.getThreadName());
//                pw.println("Stack Trace:");
//                throwable.printStackTrace(pw);
//                pw.println();
//                pw.println("═══════════════════════════════════════════════════════");
            }

        } catch (IOException e) {
            logger.error("No se pudo escribir en archivo de log", e);
        }
    }


    /**
     * Rotación de archivos dentro de la carpeta log/
     */
    private File rotateFile(File originalFile) throws IOException {
        String name = originalFile.getName();
        String parent = originalFile.getParent();

        // Separar el nombre y la extensión
        int dotIdx = name.lastIndexOf('.');
        String baseName = (dotIdx > 0) ? name.substring(0, dotIdx) : name;
        String extension = (dotIdx > 0) ? name.substring(dotIdx) : "";

        int index = 1;
        File newFile;

        // Buscar siguiente nombre disponible
        do {
            String newName = baseName + "_" + index + extension;
            newFile = new File(parent, newName);
            index++;
        } while (newFile.exists());

        // Renombrar el archivo
        if (!originalFile.renameTo(newFile)) {
            throw new IOException("No se pudo rotar el archivo de log");
        }

        // Retornar archivo nuevo con el nombre original
        return new File(originalFile.getAbsolutePath());
    }

}
