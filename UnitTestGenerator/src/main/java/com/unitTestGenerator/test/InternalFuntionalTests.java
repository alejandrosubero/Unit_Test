package com.unitTestGenerator.test;

import com.unitTestGenerator.util.AppProjectStarted;

public class InternalFuntionalTests {

    public static InternalFuntionalTests instance;

    private InternalFuntionalTests() {
    }

    public static InternalFuntionalTests getInstance() {
        if (instance == null) {
            instance = new InternalFuntionalTests();
        }
        return instance;
    }

    public static void executeTest() {

        Boolean isAnalisis = false;
        Boolean useMock = false;
//        String pathProject = "/Users/alejandrosubero/Documents/TESTREPOSITORY/Employee1";
        String pathProject = "/Users/user/Documents/TEST_REPOSITORY/Employee1";
        String nombreClase = "EmpleadoServiceImplement";
        String method = "updateEmpleado";
//        String method ="findById";
//        String method ="findByFechaEgreso";
//        String method = "findByTotalHorasFeriadoYear";
        AppProjectStarted.getInstance().executeTest(pathProject, isAnalisis, nombreClase, method, useMock);

    }

    public static void main(String[] args) {
        executeTest();
    }
}


// path in imac = /Users/user/Documents/TEST_REPOSITORY/Employee1
// path in macbook  = /Users/alejandrosubero/Documents/TESTREPOSITORY/Employee1

/*
NOTAS:
0) a un crea los long con Mock hay que corregir y que cambie el valor en el mok y la prueba
1) TOCA PROBAR LOS METOS DE PRUEBAS DONDE FALLA LA GENERACION DEL MOCK POR EL TIPO DE DATO
TOMAR EN CUENTA UNA AVERTENCIA CUANDO SELECCIONAS ALL QUE SE SOBRE ESCRIBIRAN TODAS, TANBIEN DAR LA OCCION DE SOBRE ESCRIBIR TODAS ASI EL METODO ES DIFERENTE O IGUAL
3) TRABAJAR cUANDO NO SE QUIERE USAR MOCK
4) hay una falla si se tiene la variable que el debe de costruir no adiciona los metodos y falla cuando esta en mock add;


<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>test</scope>
</dependency>





*/