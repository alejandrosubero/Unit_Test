package com.unitTestGenerator.test;
import com.unitTestGenerator.persistence.DataDAO;
import com.unitTestGenerator.persistence.model.Data;

import java.util.List;


public class AppTestDatabase {
    public static void main(String[] args) {
        DataDAO dataDAO = new DataDAO();
        Data newData = new Data("Ehhh", "Descripción h", "Datos h");
        dataDAO.save(newData);

        List<Data> all = dataDAO.findAll();

        Data foundData = dataDAO.findByName("Ehhh");

        if (foundData != null) {
            System.out.println("Data saved and found: " + foundData);
        } else {
            System.out.println("Data not saved or not found.");
        }
    }
//    public static void main(String[] args) {
//        DataDAO dao = new DataDAO();
//
//        // Guardar un nuevo dato
//        Data newData = new Data("Ejemplo", "Descripción de prueba", "Datos importantes");
//        dao.save(newData);
//
//        // Obtener por nombre
//        Data foundData = dao.findByName("Ejemplo");
//        if (foundData != null) {
//            System.out.println("Encontrado: " + foundData);
//        }
//
//        // Obtener todos
//        List<Data> allData = dao.findAll();
//        System.out.println("Todos los datos:");
//        allData.forEach(System.out::println);
//
//        // Actualizar
//        if (foundData != null) {
//            foundData.setDescripcion("Descripción actualizada");
//            dao.update(foundData);
//            System.out.println("Dato actualizado: " + dao.findByName("Ejemplo"));
//        }
//
//        // Borrar
//        if (foundData != null) {
//            dao.delete(foundData.getId());
//            System.out.println("Dato eliminado");
//        }
//
//        // Mostrar datos después de borrar
//        System.out.println("Datos restantes:");
//        dao.findAll().forEach(System.out::println);
//    }
    }
