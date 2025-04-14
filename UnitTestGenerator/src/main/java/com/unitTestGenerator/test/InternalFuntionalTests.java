package com.unitTestGenerator.test;


import com.unitTestGenerator.core.AppProjectStarted;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.EndebleIOC;


@EndebleIOC("com.unitTestGenerator")
public class InternalFuntionalTests {
    
    private InternalFuntionalTests() {
    }

    public static void executeTest() {
//        String pathProject ="D:\\TEST_REPOSITORIES\\Employee1";
//        String nombreClase = "EmpleadoServiceImplement";
//        String method = "updateEmpleado";
//        String method ="findById";
//        String method ="findByFechaEgreso";
//        String method = "findByTotalHorasFeriadoYear";
//        AppProjectStarted.getInstance().executeTest(pathProject, isAnalisis, nombreClase, method, useMock);

//        String me ="/Users/user/Documents/TEST_REPOSITORY/UnitTestGenerator";
//        String me ="/Users/alejandrosubero/Documents/TESTREPOSITORY/UnitTestGenerator";

//        String pathProject = "/Users/user/Documents/TEST_REPOSITORY/Employee1";
        String pathProject = "/Users/alejandrosubero/Documents/TESTREPOSITORY/Employee1";


        ContextIOC con = ContextIOC.getInstance(InternalFuntionalTests.class);
        Boolean isAnalisis = false;
        Boolean useMock = false;

        String nombreClase = "CargoServiceImplement";
        String method = "updateCargo";
        con.getClassInstance(AppProjectStarted.class).analizedTest(pathProject, isAnalisis, nombreClase, method, useMock);;
    }

    public static void main(String[] args) {
        executeTest();
    }
}

/*
NOTAS:
hay que usar la clase main para leer la anotacion y detectar si estamos en precencia de un proyecto sprintboot y cuando se escribe
@SpringBootTest hay que escribir es: @SpringBootTest(classes = Main_Class.class)
para evitar el error mas si estamos usando security

'org.projectlombok', name: 'lombok', version: '1.18.34'

*/

// https://mvnrepository.com/artifact/commons-io/commons-io
// https://github.com/vinayakbagal7/HibernateSqlite/blob/master/HibernateHelloWorld/src/main/java/com/srccodes/example/hibernate/App.java
// https://github.com/xerial/sqlite-jdbc?tab=readme-ov-file

