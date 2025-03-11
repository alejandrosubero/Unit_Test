package com.unitTestGenerator.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.unitTestGenerator.ioc.anotations.*;
import org.reflections.Reflections;

    public class ContextIOC {

        private static ContextIOC instance;
        private  static Class<?> nainClass;
        private Map<String, Object> singletonInstances = new HashMap<>();
        private Map<String, Class<?>> registeredClasses = new HashMap<>();

        public ContextIOC() {
        }

        public static ContextIOC getInstance(){
            if(instance == null){
                instance = new ContextIOC();
            }
            return instance;
        }

        public static ContextIOC getInstance(String packge){
            instance = getInstance();
            instance.scanPackage(packge);
            return instance;
        }


        public static ContextIOC getInstance(Class<?> clazzI) {
            try {
                instance = getInstance();
                nainClass = clazzI;
                if (nainClass.isAnnotationPresent(EndebleIOC.class)) {
                    EndebleIOC anotation = nainClass.getAnnotation(EndebleIOC.class);
                    String packge = anotation.value();
                    instance.scanPackage(packge);
                } else {
                    System.out.println("Class does not have the EndebleIOC annotation.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred while trying to read the package annotation... ERROR:.."+e);
                e.printStackTrace();
                return getInstance();
            }
            return instance;
        }


        // Scan a package and automatically register classes annotated with @Componente
        public void scanPackage(String packageName) {
            Reflections reflections = new Reflections(packageName);
            // Get all classes annotated with @Componente
            Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Componente.class);
            for (Class<?> clazz : classes) {
                register(clazz);
            }
        }


        // Register a class in the container
        public void register(Class<?> clazz) {
            String name = clazz.getSimpleName().toLowerCase();
            // If it implements an interface, use the interface name instead of the class name
            Class<?>[] interfaces = clazz.getInterfaces();
            if (interfaces.length > 0) {
                name = interfaces[0].getSimpleName().toLowerCase();
            }
            registeredClasses.put(name, clazz);
            if (clazz.isAnnotationPresent(Singleton.class)) {
                Object instance = createInstance(clazz);
                singletonInstances.put(name, instance);
            }
            System.out.println("Registered: " + name + " -> " + clazz.getName());  // Debug
        }

        // Create an instance of a class and resolve its dependencies
        private Object createInstance(Class<?> clazz) {
            try {
                // Get the constructor with dependencies
                Constructor<?> constructor = getConstructorWithDependencies(clazz);
                // Resolve the dependencies of the constructor parameters
                Object[] parameters = resolveConstructorParameters(constructor);
                // Create the instance using the constructor
                Object instance = constructor.newInstance(parameters);
                // Inject dependencies into fields (if necessary)
                injectDependencies(instance);
                return instance;
            } catch (Exception e) {
                throw new RuntimeException("Error creating instance of " + clazz.getName(), e);
            }
        }

        // Get the constructor with dependencies (the one with parameters)
        private Constructor<?> getConstructorWithDependencies(Class<?> clazz) {
            // Get all public constructors
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            // If there is more than one constructor, select the one with parameters
            for (Constructor<?> constructor : constructors) {
                if (constructor.getParameterCount() > 0) {
                    return constructor;
                }
            }
            // If there are no constructors with parameters, use the default constructor
            try {
                return clazz.getDeclaredConstructor();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("No valid constructor found in " + clazz.getName(), e);
            }
        }

        // Resolve the parameters of the constructor
        private Object[] resolveConstructorParameters(Constructor<?> constructor) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object[] parameters = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                String name = parameterTypes[i].getSimpleName().toLowerCase();
                parameters[i] = getClassInstance(name, parameterTypes[i]);
            }
            return parameters;
        }

        // Inject dependencies into an object's fields
        private void injectDependencies(Object object) {
            // Get all fields of the class
            for (Field field : object.getClass().getDeclaredFields()) {
                // Check if the field is annotated with @Inject
                if (field.isAnnotationPresent(Inyect.class)) {
                    // Get the field type
                    Class<?> fieldType = field.getType();
                    // Find the corresponding instance in the container
                    Object instance = getClassInstance(fieldType.getSimpleName().toLowerCase(), fieldType);
                    // Make the field accessible (even if it's private)
                    field.setAccessible(true);
                    try {
                        // Assign the instance to the field
                        field.set(object, instance);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Error injecting dependency into field " + field.getName(), e);
                    }
                }
            }
        }

        // Get an instance from the container
        public <T> T getClassInstance(String name, Class<T> type) {
            // Check if it is a Singleton
            if (singletonInstances.containsKey(name)) {
                return type.cast(singletonInstances.get(name));
            }
            // Check if the class is registered
            if (registeredClasses.containsKey(name)) {
                Class<?> clazz = registeredClasses.get(name);
                // Create a new instance for Prototype
                Object instance = createInstance(clazz);
                return type.cast(instance);
            }
            throw new RuntimeException("Instance with name " + name + " not found");
        }

        // Get an instance of a registered class
        public <T> T getClassInstance(Class<T> type) {
            return getClassInstance(type.getSimpleName().toLowerCase(), type);
        }


        public Class<?> getClassAnnotation(String name) {
            try {
                Class<?> clazz = nainClass;
                if (clazz.isAnnotationPresent(ClassName.class)) {
                    ClassName classNameAnnotation = clazz.getAnnotation(ClassName.class);
                    String className = classNameAnnotation.value();
                    Class<?> dynamicClass = Class.forName(className);
                    // Instanciar la clase dinámicamente y llamar a un método
                    Object instance = dynamicClass.getDeclaredConstructor().newInstance();
                    // Obtener el método saludo() de la clase Cliente (o cualquier clase)
                    Method saludoMethod = dynamicClass.getMethod(name);

                    if (saludoMethod == null) {
                        System.out.println("Método no encontrado: " + name);
                    } else {
                        saludoMethod.invoke(instance);
                    }
                    // Invocar el método saludo() en la instancia creada


                } else {
                    System.out.println("Class does not have the ClassName annotation.");
                }
            } catch (ClassNotFoundException e) {
                System.out.println("An error occurred while trying to read the package annotation: " + e.getMessage());
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return null;
        }
    }

