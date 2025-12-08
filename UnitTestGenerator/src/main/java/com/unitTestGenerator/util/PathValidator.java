package com.unitTestGenerator.util;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * Utilidad para validar paths de archivos y directorios de forma segura
 * y compatible con múltiples sistemas operativos (Windows, macOS, Linux).
 */
public class PathValidator {


    // Límite de longitud para rutas en Windows (incluyendo terminador nulo)
    private static final int MAX_PATH_LENGTH = 260;

    // Caracteres inválidos para nombres de archivo en Windows
    private static final Pattern INVALID_WINDOWS_CHARS = Pattern.compile("[<>:\"|?*\\x00]");

    // Nombres reservados en Windows (CON, PRN, AUX, NUL, COM1-9, LPT1-9)
    private static final Pattern WINDOWS_RESERVED_NAMES =
            Pattern.compile("^(CON|PRN|AUX|NUL|COM[1-9]|LPT[1-9])(\\..*)?$",
                    Pattern.CASE_INSENSITIVE);

    /**
     * Constructor privado para evitar instanciación de clase utilitaria
     */
    private PathValidator() {
        throw new AssertionError("No se permite instanciar PathValidator");
    }

    /**
     * Valida si un path es sintácticamente válido para el sistema operativo actual.
     * NO verifica si el archivo/directorio existe en el sistema de archivos.
     *
     * @param pathString El path a validar (puede ser absoluto o relativo)
     * @return true si el path tiene sintaxis válida, false en caso contrario
     */
    public static boolean isValidPath(String pathString) {
        if (pathString == null || pathString.trim().isEmpty()) {
            return false;
        }

        // Verificar longitud excesiva
        if (pathString.length() > MAX_PATH_LENGTH) {
            return false;
        }

        try {
            Path path = Paths.get(pathString);

            // Validaciones específicas por sistema operativo
            if (isWindows()) {
                return isValidWindowsPath(pathString, path);
            }

            // Para Unix-like (Linux/macOS)
            return isValidUnixPath(pathString);

        } catch (InvalidPathException e) {
            return false;
        }
    }

    /**
     * Valida el path y además verifica si el archivo/directorio existe físicamente.
     * Requiere permisos de lectura en el sistema de archivos.
     *
     * @param pathString El path a validar y verificar existencia
     * @return true si el path es válido y existe, false en caso contrario
     */
    public static boolean isValidPathAndExists(String pathString) {
        if (!isValidPath(pathString)) {
            return false;
        }

        try {
            Path path = Paths.get(pathString);
            return Files.exists(path);
        } catch (SecurityException e) {
            // No se tienen permisos para acceder al path
            return false;
        } catch (Exception e) {
            // Cualquier otra excepción (por ejemplo, problemas de E/S)
            return false;
        }
    }

    /**
     * Valida un path sin importar el sistema operativo actual.
     * Acepta tanto formatos Windows como Unix/Linux/macOS.
     * Útil cuando se almacenan rutas de diferentes sistemas.
     *
     * @param pathString El path a validar
     * @return true si el path es válido en algún sistema operativo soportado
     */
    public static boolean isValidPathCrossPlatform(String pathString) {
        if (pathString == null || pathString.trim().isEmpty() ||
                pathString.length() > MAX_PATH_LENGTH) {
            return false;
        }

        // Intentar validación como Windows
        if (isPotentiallyWindowsPath(pathString)) {
            try {
                if (isValidWindowsPath(pathString, null)) {
                    return true;
                }
            } catch (Exception e) {
                // Continuar con validación Unix
            }
        }

        // Intentar validación como Unix
        return isValidUnixPath(pathString);
    }

    /**
     * Verifica si el string podría ser una ruta Windows basándose en característicos
     * como unidades (C:\\) o uso de backslash como separador principal
     */
    private static boolean isPotentiallyWindowsPath(String pathString) {
        return pathString.matches("^[a-zA-Z]:[/\\\\].*") ||
                (pathString.contains("\\") && !pathString.startsWith("/"));
    }

    /**
     * Valida sintaxis Unix/Linux/macOS
     */
    private static boolean isValidUnixPath(String pathString) {
        // Único caracter inválido en Unix es el nulo
        return !pathString.contains("\0");
    }

    /**
     * Valida sintaxis Windows con reglas específicas
     */
    private static boolean isValidWindowsPath(String pathString, Path path) throws InvalidPathException {
        // Verificar caracteres inválidos
        if (INVALID_WINDOWS_CHARS.matcher(pathString).find()) {
            return false;
        }

        // Verificar nombres reservados (CON, PRN, AUX, etc.)
        String fileName = (path != null) ? path.getFileName().toString() :
                Paths.get(pathString).getFileName().toString();

        if (!fileName.isEmpty() && WINDOWS_RESERVED_NAMES.matcher(fileName).find()) {
            return false;
        }

        // Verificar que no termine con espacio o punto (problemático en Windows)
        if (pathString.endsWith(" ") || pathString.endsWith(".")) {
            return false;
        }

        return true;
    }

    /**
     * Detecta si el sistema operativo actual es Windows
     */
    private static boolean isWindows() {
        return System.getProperty("os.name", "").toLowerCase().contains("win");
    }


}
