package com.unitTestGenerator.analyzers.services;

import com.unitTestGenerator.pojos.Clase;

public interface DynamicPackageRootFinder {

    public static String getName(Clase classs) {
        try {
            String className = classs.getPaquete() + "." + classs.getNombre();
            String packageRoot = findPackageRoot(className);
            return packageRoot;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String findPackageRoot(String className) {
        try {

            Class<?> clazz = Class.forName(className);
            String packageName = clazz.getPackage().getName();
            String[] parts = packageName.split("\\.");

            String prefijo = "";

            // ( "com", "org", "net", etc.)
            if (parts.length >= 2) {
                prefijo = parts[0] ;
            }

            for (String part : parts) {
                if (!part.equals(prefijo)) {
                    return part;
                }
            }
            return "NotFound";
        } catch (ClassNotFoundException e) {
            return "Class Not Found: " + className;
        }
    }
}
