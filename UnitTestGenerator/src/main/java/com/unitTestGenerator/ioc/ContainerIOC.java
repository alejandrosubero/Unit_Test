package com.unitTestGenerator.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;

public class ContainerIOC {


        private Map<String, Object> instanciasSingleton = new HashMap<>();
        private Map<String, Class<?>> clasesRegistradas = new HashMap<>();

        // Escanear un paquete y registrar automáticamente las clases anotadas con @Componente
        public void escanearPaquete(String nombrePaquete) {
            Reflections reflections = new Reflections(nombrePaquete);
            // Obtener todas las clases anotadas con @Componente
            Set<Class<?>> clases = reflections.getTypesAnnotatedWith(Componente.class);
            for (Class<?> clase : clases) {
                registrar(clase);
            }
        }

        // Registrar una clase en el contenedor
        public void registrar(Class<?> clase) {
            // Almacenar la clase en el mapa de clases registradas
            clasesRegistradas.put(clase.getSimpleName().toLowerCase(), clase);
            // Si es un Singleton, crear una instancia y almacenarla
            if (clase.isAnnotationPresent(Singleton.class)) {
                Object instancia = crearInstancia(clase);
                instanciasSingleton.put(clase.getSimpleName().toLowerCase(), instancia);
            }
        }

        // Crear una instancia de una clase y resolver sus dependencias
        private Object crearInstancia(Class<?> clase) {
            try {
                // Obtener el constructor con dependencias
                Constructor<?> constructor = obtenerConstructorConDependencias(clase);
                // Resolver las dependencias de los parámetros del constructor
                Object[] parametros = resolverParametrosConstructor(constructor);
                // Crear la instancia usando el constructor
                Object instancia = constructor.newInstance(parametros);
                // Inyectar dependencias en los campos (si es necesario)
                inyectarDependencias(instancia);
                return instancia;
            } catch (Exception e) {
                throw new RuntimeException("Error al crear la instancia de " + clase.getName(), e);
            }
        }

        // Obtener el constructor con dependencias (el que tiene parámetros)
        private Constructor<?> obtenerConstructorConDependencias(Class<?> clase) {
            // Obtener todos los constructores públicos
            Constructor<?>[] constructores = clase.getDeclaredConstructors();
            // Si hay más de un constructor, seleccionar el que tiene parámetros
            for (Constructor<?> constructor : constructores) {
                if (constructor.getParameterCount() > 0) {
                    return constructor;
                }
            }
            // Si no hay constructores con parámetros, usar el constructor por defecto
            try {
                return clase.getDeclaredConstructor();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("No se encontró un constructor válido en " + clase.getName(), e);
            }
        }

        // Resolver los parámetros del constructor
        private Object[] resolverParametrosConstructor(Constructor<?> constructor) {
            Class<?>[] tiposParametros = constructor.getParameterTypes();
            Object[] parametros = new Object[tiposParametros.length];
            for (int i = 0; i < tiposParametros.length; i++) {
                parametros[i] = obtener(tiposParametros[i].getSimpleName().toLowerCase(), tiposParametros[i]);
            }
            return parametros;
        }

        // Inyectar dependencias en los campos de un objeto
        private void inyectarDependencias(Object objeto) {
            // Obtener todos los campos de la clase
            for (Field campo : objeto.getClass().getDeclaredFields()) {
                // Verificar si el campo está anotado con @Inyectar
                if (campo.isAnnotationPresent(Inyect.class)) {
                    // Obtener el tipo del campo
                    Class<?> tipoCampo = campo.getType();
                    // Buscar la instancia correspondiente en el contenedor
                    Object instancia = obtener(tipoCampo.getSimpleName().toLowerCase(), tipoCampo);
                    // Hacer accesible el campo (incluso si es privado)
                    campo.setAccessible(true);
                    try {
                        // Asignar la instancia al campo
                        campo.set(objeto, instancia);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Error al inyectar dependencia en el campo " + campo.getName(), e);
                    }
                }
            }
        }

        // Obtener una instancia del contenedor
        public <T> T obtener(String nombre, Class<T> tipo) {
            // Verificar si es un Singleton
            if (instanciasSingleton.containsKey(nombre)) {
                return tipo.cast(instanciasSingleton.get(nombre));
            }
            // Verificar si la clase está registrada
            if (clasesRegistradas.containsKey(nombre)) {
                Class<?> clase = clasesRegistradas.get(nombre);
                // Crear una nueva instancia para Prototype
                Object instancia = crearInstancia(clase);
                return tipo.cast(instancia);
            }
            throw new RuntimeException("No se encontró la instancia con nombre " + nombre);
        }

        // Obtener una instancia de una clase registrada
        public <T> T obtener(Class<T> tipo) {
            return obtener(tipo.getSimpleName().toLowerCase(), tipo);
        }
    }