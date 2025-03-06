package com.unitTestGenerator.ioc;

public class testIOC {

    public static void main(String[] args) {
        // Crear el contenedor IoC
        ContainerIOC contenedor = new ContainerIOC();

        // Escanear el paquete donde se encuentran las clases anotadas con @Componente
        contenedor.escanearPaquete("com.ejemplo"); // Cambia "com.ejemplo" por tu paquete

        // Obtener una instancia de Cliente (el contenedor inyectará automáticamente las dependencias en el constructor)
        Cliente cliente = contenedor.obtener(Cliente.class);

        // Usar el cliente
        cliente.ejecutarServicio();  // Salida: Ejecutando servicio...
    }


    // Interfaz
    public interface Servicio {
        void ejecutar();
    }

    // Implementación Singleton
    @Componente
    @Singleton
    public class ServicioImpl implements Servicio {
        @Override
        public void ejecutar() {
            System.out.println("Ejecutando servicio...");
        }
    }

    // Clase que usa inyección en el constructor
    @Componente
    public class Cliente {

        private final Servicio servicio;

        // Constructor con dependencias
        public Cliente(Servicio servicio) {
            this.servicio = servicio;
        }

        public void ejecutarServicio() {
            servicio.ejecutar();
        }
    }

}
