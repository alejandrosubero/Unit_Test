package com.unitTestGenerator.test;


import com.unitTestGenerator.core.AppProjectStarted;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.EndebleIOC;


@EndebleIOC("com.unitTestGenerator")
public class InternalFuntionalTests {
    
    private InternalFuntionalTests() {
    }

    public static void executeTest() {
//        String pathProject = "/Users/alejandrosubero/Documents/TESTREPOSITORY/Employee1";
//        String pathProject ="D:\\TEST_REPOSITORIES\\Employee1";
//        String me ="/Users/user/Documents/TEST_REPOSITORY/UnitTestGenerator";
//        String nombreClase = "EmpleadoServiceImplement";
//        String method = "updateEmpleado";
//        String method ="findById";
//        String method ="findByFechaEgreso";
//        String method = "findByTotalHorasFeriadoYear";
//        AppProjectStarted.getInstance().executeTest(pathProject, isAnalisis, nombreClase, method, useMock);
//        String pathProject = "/Users/user/Documents/TEST_REPOSITORY/Employee1";
        ContextIOC con = ContextIOC.getInstance(InternalFuntionalTests.class);
        Boolean isAnalisis = false;
        Boolean useMock = false;
        String me ="/Users/alejandrosubero/Documents/TESTREPOSITORY/UnitTestGenerator";
        String nombreClase = "CargoServiceImplement";
        String method = "updateCargo";
        con.getClassInstance(AppProjectStarted.class).analizedTest(me, isAnalisis, nombreClase, method, useMock);;
    }

    public static void main(String[] args) {
        executeTest();
    }
}

// https://mvnrepository.com/artifact/commons-io/commons-io
// https://github.com/vinayakbagal7/HibernateSqlite/blob/master/HibernateHelloWorld/src/main/java/com/srccodes/example/hibernate/App.java
// https://github.com/xerial/sqlite-jdbc?tab=readme-ov-file

// path in imac = /Users/user/Documents/TEST_REPOSITORY/Employee1
// path in macbook  = /Users/alejandrosubero/Documents/TESTREPOSITORY/Employee1

/*
NOTAS:
0) a un crea los long con Mock hay que corregir y que cambie el valor en el mok y la prueba
1) TOCA PROBAR LOS METOS DE PRUEBAS DONDE FALLA LA GENERACION DEL MOCK POR EL TIPO DE DATO
TOMAR EN CUENTA UNA AVERTENCIA CUANDO SELECCIONAS ALL QUE SE SOBRE ESCRIBIRAN TODAS, TANBIEN DAR LA OCCION DE SOBRE ESCRIBIR TODAS ASI EL METODO ES DIFERENTE O IGUAL
3) TRABAJAR cUANDO NO SE QUIERE USAR MOCK
4) hay una falla si se tiene la variable que el debe de costruir no adiciona los metodos y falla cuando esta en mock add;

'org.projectlombok', name: 'lombok', version: '1.18.34'

*/