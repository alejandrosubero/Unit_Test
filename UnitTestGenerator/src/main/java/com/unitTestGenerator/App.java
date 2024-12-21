package com.unitTestGenerator;

import com.unitTestGenerator.util.AppProjectStarted;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class App {

    public static void main(String[] args) {
        AppProjectStarted.getInstance().start();
    }


}

// class =   EmpleadoServiceImplement
// method =
            // public boolean saveEmpleado(Empleado employee)
            // public boolean updateEmpleado(Empleado employee)
            // public Empleado findById(Long id)
            // public Empleado findByFechaEgreso(Date fechaEgreso)


// path in imac = /Users/user/Documents/TEST_REPOSITORY/Employee1
// path in macbook  = /Users/alejandrosubero/Documents/TESTREPOSITORY/Employee1

// https://www.meta.ai/c/44fd9b43-6c67-44ef-a208-8170eca6a0c2

/*
NOTAS:
1) TOCA PROBAR LOS METOS DE PRUEBAS DONDE FALLA LA GENERACION DEL MOCK POR EL TIPO DE DATO
2) TRABAJAR EN ACTUALIZACION DE FILE ESTO ES CUANDO TIENES LAS PRUEBAS CREADAS Y HACES UNA DE UN METODO Y NO QUIERES ELIMINAR LAS EXISTENTES
TOMAR EN CUENTA UNA AVERTENCIA CUANDO SELECCIONAS ALL QUE SE SOBRE ESCRIBIRAN TODAS, TANBIEN DAR LA OCCION DE SOBRE ESCRIBIR TODAS ASI EL METODO ES DIFERENTE O IGUAL
3) TRABAJAR UANDO NO SE QUIERE USAR MOCK
*/