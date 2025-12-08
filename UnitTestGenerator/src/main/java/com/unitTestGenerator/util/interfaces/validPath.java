package com.unitTestGenerator.util.interfaces;
import com.unitTestGenerator.App;
import com.unitTestGenerator.core.AppProjectStarted;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.util.PathValidator;

import java.nio.file.Paths;
import java.util.regex.Pattern;

public interface validPath {

    default boolean isValidPathPattern(String pathString){
        // --- Constantes y Patrones (Definidos localmente dentro del método) ---
        final int MAX_PATH_LENGTH = 260;
        final Pattern INVALID_WINDOWS_CHARS = Pattern.compile("[<>:\"|?*\\x00]");
        final Pattern WINDOWS_RESERVED_NAMES = Pattern.compile("^(CON|PRN|AUX|NUL|COM[1-9]|LPT[1-9])(\\..*)?$", Pattern.CASE_INSENSITIVE);
        final String NULL_CHAR = "\0";

        // 1. Verificaciones iniciales
        if (pathString == null || pathString.trim().isEmpty() || pathString.length() > MAX_PATH_LENGTH) {
            return false;
        }

        // 2. Lógica de formato cruzado
        // Determina si el path tiene la estructura de un path Windows (unidad o backslashes).
        boolean isPotentiallyWindows = pathString.matches("^[a-zA-Z]:[/\\\\].*") ||
                (pathString.contains("\\") && !pathString.startsWith("/"));

        // --- Lógica de Validación de Patrón Windows (Integrada) ---
        if (isPotentiallyWindows) {
            try {
                // a) Caracteres prohibidos
                if (INVALID_WINDOWS_CHARS.matcher(pathString).find()) {
                    // Si contiene caracteres prohibidos de Windows, NO es válido en Windows
                    return false;
                }

                // b) Nombres reservados (solo el último componente)
                // Se requiere java.nio.file.Paths
                String fileName = java.nio.file.Paths.get(pathString).getFileName().toString();

                if (!fileName.isEmpty() && WINDOWS_RESERVED_NAMES.matcher(fileName).find()) {
                    return false;
                }

                // c) Puntos o espacios finales
                if (pathString.endsWith(" ") || pathString.endsWith(".")) {
                    return false;
                }

                // Si pasa todas las validaciones de Windows, es válido.
                return true;
            } catch (Exception e) {
                // Si Paths.get falla (ej. ruta demasiado larga o error de sintaxis nativa),
                // no se considera una ruta válida en Windows.
                // Continuamos a la verificación Unix, por si es una ruta relativa o Unix-style.
            }
        }

        // --- Lógica de Validación de Patrón Unix (Integrada) ---
        // Unix/Linux/macOS: Casi cualquier caracter es permitido, excepto el caracter nulo (\0).
        return !pathString.contains(NULL_CHAR);
    }

    default boolean isValidPathAndExists(String pathString){
       return  PathValidator.isValidPathAndExists(pathString);
    }



}
