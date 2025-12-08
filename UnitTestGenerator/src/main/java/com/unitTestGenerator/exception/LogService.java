package com.unitTestGenerator.exception;

import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.ioc.anotations.Singleton;
import com.unitTestGenerator.printers.interfaces.IPrintService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Singleton
public class LogService implements IPrintService {

    private final Path logDir = Paths.get("log");

    public List<String> listLogFiles() {
        if (!Files.exists(logDir)) {
            System.out.println("La carpeta /log no existe todavía.");
            return Collections.emptyList();
        }

        try (Stream<Path> stream = Files.list(logDir)) {
            return stream.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch ( IOException e) {
            System.out.println("Error listando logs: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void printLogFile(String fileName) {
        Path file = logDir.resolve(fileName);

        if (!Files.exists(file)) {
            System.out.println("Archivo no encontrado: " + fileName);
            return;
        }

        try {
            Files.lines(file).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error leyendo log: " + e.getMessage());
        }
    }
}

