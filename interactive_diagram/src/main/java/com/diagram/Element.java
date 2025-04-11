package com.diagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Element {


    public static List<DiagramElement> geDdiagramElementList(){

        List<DiagramElement> elementos = new ArrayList<>();

//        elementos.add(new DiagramElement("inicio", "Valor_17", Arrays.asList("var_17"), Arrays.asList("met_17"), Arrays.asList("proceso"), Arrays.asList(new ElementConnection(null, false)), 250, 170));
//        elementos.add(new DiagramElement("proceso", "Valor_18", Arrays.asList("var_18"), Arrays.asList("met_18"), Arrays.asList("Subproceso"), Arrays.asList(new ElementConnection(null, true)), 256, 170));
//        elementos.add(new DiagramElement("Subproceso", "Valor_19", Arrays.asList("var_19"), Arrays.asList("met_19"), Arrays.asList("inicio"), Arrays.asList(new ElementConnection(null, true)), 257, 170));


        // Bloque 1 (Filas 0-9)
        elementos.add(new DiagramElement("Inicio_0", "Valor_0", Arrays.asList("var_0"), Arrays.asList("met_0"), Arrays.asList("Proceso_1"), Arrays.asList(new ElementConnection(null, false)), 50, 80));
        elementos.add(new DiagramElement("Inicio_1", "Valor_1", Arrays.asList("var_1"), Arrays.asList("met_1"), Arrays.asList("Decisión_2", "Tarea_3"), Arrays.asList(new ElementConnection(null, true), new ElementConnection(null, false)), 170, 80));
        elementos.add(new DiagramElement("Inicio_2", "Valor_2", Arrays.asList("var_2"), Arrays.asList("met_2"), Arrays.asList("Proceso_1", "Fin_4"), Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, true)), 290, 80));
        elementos.add(new DiagramElement("Inicio_3", "Valor_3", Arrays.asList("var_3"), Arrays.asList("met_3"), Arrays.asList("Proceso_1"), Arrays.asList(new ElementConnection(null, false)), 410, 80));
        elementos.add(new DiagramElement("Inicio_4", "Valor_4", Arrays.asList("var_4"), Arrays.asList("met_4"), new ArrayList<>(), new ArrayList<>(), 530, 80));
        elementos.add(new DiagramElement("Entrada_1", "Valor_5", Arrays.asList("var_5"), Arrays.asList("met_5"), Arrays.asList("Proceso_6"), Arrays.asList(new ElementConnection(null, true)), 650, 80));
        elementos.add(new DiagramElement("Entrada_2", "Valor_6", Arrays.asList("var_6"), Arrays.asList("met_6"), Arrays.asList("Tarea_7", "Decisión_2"), Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, true)), 770, 80));
        elementos.add(new DiagramElement("Entrada_3", "Valor_7", Arrays.asList("var_7"), Arrays.asList("met_7"), Arrays.asList("Proceso_1"), Arrays.asList(new ElementConnection(null, false)), 890, 80));
        elementos.add(new DiagramElement("Entrada_4", "Valor_8", Arrays.asList("var_8"), Arrays.asList("met_8"), Arrays.asList("BaseDatos_9"), Arrays.asList(new ElementConnection(null, true)), 1010, 80));
        elementos.add(new DiagramElement("Entrada_5", "Valor_9", Arrays.asList("var_9"), Arrays.asList("met_9"), new ArrayList<>(), new ArrayList<>(), 1130, 80));

        // Bloque 2 (Filas 10-19)
        elementos.add(new DiagramElement("Proceso_1", "Valor_10", Arrays.asList("var_10"), Arrays.asList("met_10"), Arrays.asList("Proceso_11"), Arrays.asList(new ElementConnection(null, false)), 50, 170));
        elementos.add(new DiagramElement("Proceso_2", "Valor_11", Arrays.asList("var_11"), Arrays.asList("met_11"), Arrays.asList("Decisión_12", "Tarea_13"), Arrays.asList(new ElementConnection(null, true), new ElementConnection(null, false)), 170, 170));
        elementos.add(new DiagramElement("Proceso_3", "Valor_12", Arrays.asList("var_12"), Arrays.asList("met_12"), Arrays.asList("Proceso_11", "Fin_14"), Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, true)), 290, 170));
        elementos.add(new DiagramElement("Proceso_4", "Valor_13", Arrays.asList("var_13"), Arrays.asList("met_13"), Arrays.asList("Proceso_11"), Arrays.asList(new ElementConnection(null, false)), 410, 170));
        elementos.add(new DiagramElement("Proceso_5", "Valor_14", Arrays.asList("var_14"), Arrays.asList("met_14"), new ArrayList<>(), new ArrayList<>(), 530, 170));
        elementos.add(new DiagramElement("Proceso_6", "Valor_15", Arrays.asList("var_15"), Arrays.asList("met_15"), Arrays.asList("Proceso_16"), Arrays.asList(new ElementConnection(null, true)), 650, 170));
        elementos.add(new DiagramElement("Proceso_7", "Valor_16", Arrays.asList("var_16"), Arrays.asList("met_16"), Arrays.asList("Tarea_17", "Decisión_12"), Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, true)), 770, 170));
        elementos.add(new DiagramElement("Subproceso_1", "Valor_17", Arrays.asList("var_17"), Arrays.asList("met_17"), Arrays.asList("Proceso_11"), Arrays.asList(new ElementConnection(null, false)), 890, 170));
        elementos.add(new DiagramElement("Subproceso_17", "Valor_18", Arrays.asList("var_18"), Arrays.asList("met_18"), Arrays.asList("BaseDatos_19"), Arrays.asList(new ElementConnection(null, true)), 1010, 170));
        elementos.add(new DiagramElement("Subproceso_17", "Valor_19", Arrays.asList("var_19"), Arrays.asList("met_19"), new ArrayList<>(), new ArrayList<>(), 1130, 170));

        // Bloque 3 (Filas 20-29)
        elementos.add(new DiagramElement("Inicio_20", "Valor_20", Arrays.asList("var_20"), Arrays.asList("met_20"), Arrays.asList("Proceso_21"), Arrays.asList(new ElementConnection(null, false)), 50, 260));
        elementos.add(new DiagramElement("Proceso_21", "Valor_21", Arrays.asList("var_21"), Arrays.asList("met_21"), Arrays.asList("Decisión_22", "Tarea_23"), Arrays.asList(new ElementConnection(null, true), new ElementConnection(null, false)), 170, 260));
        elementos.add(new DiagramElement("Decisión_22", "Valor_22", Arrays.asList("var_22"), Arrays.asList("met_22"), Arrays.asList("Proceso_21", "Fin_24"), Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, true)), 290, 260));
        elementos.add(new DiagramElement("Tarea_23", "Valor_23", Arrays.asList("var_23"), Arrays.asList("met_23"), Arrays.asList("Proceso_21"), Arrays.asList(new ElementConnection(null, false)), 410, 260));
        elementos.add(new DiagramElement("Fin_24", "Valor_24", Arrays.asList("var_24"), Arrays.asList("met_24"), new ArrayList<>(), new ArrayList<>(), 530, 260));
        elementos.add(new DiagramElement("Entrada_25", "Valor_25", Arrays.asList("var_25"), Arrays.asList("met_25"), Arrays.asList("Proceso_26"), Arrays.asList(new ElementConnection(null, true)), 650, 260));
        elementos.add(new DiagramElement("Salida_26", "Valor_26", Arrays.asList("var_26"), Arrays.asList("met_26"), Arrays.asList("Tarea_27", "Decisión_22"), Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, true)), 770, 260));
        elementos.add(new DiagramElement("Subproceso_27", "Valor_27", Arrays.asList("var_27"), Arrays.asList("met_27"), Arrays.asList("Proceso_21"), Arrays.asList(new ElementConnection(null, false)), 890, 260));
        elementos.add(new DiagramElement("Documento_28", "Valor_28", Arrays.asList("var_28"), Arrays.asList("met_28"), Arrays.asList("BaseDatos_29"), Arrays.asList(new ElementConnection(null, true)), 1010, 260));
        elementos.add(new DiagramElement("BaseDatos_29", "Valor_29", Arrays.asList("var_29"), Arrays.asList("met_29"), new ArrayList<>(), new ArrayList<>(), 1130, 260));

        // Bloque 4 (Filas 30-39)
        elementos.add(new DiagramElement("Inicio_30", "Valor_30", Arrays.asList("var_30"), Arrays.asList("met_30"), Arrays.asList("Proceso_31"), Arrays.asList(new ElementConnection(null, false)), 50, 350));
        elementos.add(new DiagramElement("Proceso_31", "Valor_31", Arrays.asList("var_31"), Arrays.asList("met_31"), Arrays.asList("Decisión_32", "Tarea_33"), Arrays.asList(new ElementConnection(null, true), new ElementConnection(null, false)), 170, 350));
        elementos.add(new DiagramElement("Decisión_32", "Valor_32", Arrays.asList("var_32"), Arrays.asList("met_32"), Arrays.asList("Proceso_31", "Fin_34"), Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, true)), 290, 350));
        elementos.add(new DiagramElement("Tarea_33", "Valor_33", Arrays.asList("var_33"), Arrays.asList("met_33"), Arrays.asList("Proceso_31"), Arrays.asList(new ElementConnection(null, false)), 410, 350));
        elementos.add(new DiagramElement("Fin_34", "Valor_34", Arrays.asList("var_34"), Arrays.asList("met_34"), new ArrayList<>(), new ArrayList<>(), 530, 350));
        elementos.add(new DiagramElement("Entrada_35", "Valor_35", Arrays.asList("var_35"), Arrays.asList("met_35"), Arrays.asList("Proceso_36"), Arrays.asList(new ElementConnection(null, true)), 650, 350));
        elementos.add(new DiagramElement("Salida_36", "Valor_36", Arrays.asList("var_36"), Arrays.asList("met_36"), Arrays.asList("Tarea_37", "Decisión_32"), Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, true)), 770, 350));
        elementos.add(new DiagramElement("Subproceso_37", "Valor_37", Arrays.asList("var_37"), Arrays.asList("met_37"), Arrays.asList("Proceso_31"), Arrays.asList(new ElementConnection(null, false)), 890, 350));
        elementos.add(new DiagramElement("Documento_38", "Valor_38", Arrays.asList("var_38"), Arrays.asList("met_38"), Arrays.asList("BaseDatos_39"), Arrays.asList(new ElementConnection(null, true)), 1010, 350));
        elementos.add(new DiagramElement("BaseDatos_39", "Valor_39", Arrays.asList("var_39"), Arrays.asList("met_39"), new ArrayList<>(), new ArrayList<>(), 1130, 350));

        // Bloque 5 (Filas 40-49)
        elementos.add(new DiagramElement("Inicio_40", "Valor_40", Arrays.asList("var_40"), Arrays.asList("met_40"), Arrays.asList("Proceso_41"), Arrays.asList(new ElementConnection(null, false)), 50, 440));
        elementos.add(new DiagramElement("Proceso_41", "Valor_41", Arrays.asList("var_41"), Arrays.asList("met_41"), Arrays.asList("Decisión_42", "Tarea_43"), Arrays.asList(new ElementConnection(null, true), new ElementConnection(null, false)), 170, 440));
        elementos.add(new DiagramElement("Decisión_42", "Valor_42", Arrays.asList("var_42"), Arrays.asList("met_42"), Arrays.asList("Proceso_41", "Fin_44"), Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, true)), 290, 440));
        elementos.add(new DiagramElement("Tarea_43", "Valor_43", Arrays.asList("var_43"), Arrays.asList("met_43"), Arrays.asList("Proceso_41"), Arrays.asList(new ElementConnection(null, false)), 410, 440));
        elementos.add(new DiagramElement("Fin_44", "Valor_44", Arrays.asList("var_44"), Arrays.asList("met_44"), new ArrayList<>(), new ArrayList<>(), 530, 440));
        elementos.add(new DiagramElement("Entrada_45", "Valor_45", Arrays.asList("var_45"), Arrays.asList("met_45"), Arrays.asList("Proceso_46"), Arrays.asList(new ElementConnection(null, true)), 650, 440));
        elementos.add(new DiagramElement("Salida_46", "Valor_46", Arrays.asList("var_46"), Arrays.asList("met_46"), Arrays.asList("Tarea_47", "Decisión_42"), Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, true)), 770, 440));
        elementos.add(new DiagramElement("Subproceso_47", "Valor_47", Arrays.asList("var_47"), Arrays.asList("met_47"), Arrays.asList("Proceso_41"), Arrays.asList(new ElementConnection(null, false)), 890, 440));
        elementos.add(new DiagramElement("Documento_48", "Valor_48", Arrays.asList("var_48"), Arrays.asList("met_48"), Arrays.asList("BaseDatos_49"), Arrays.asList(new ElementConnection(null, true)), 1010, 440));
        elementos.add(new DiagramElement("BaseDatos_49", "Valor_49", Arrays.asList("var_49"), Arrays.asList("met_49"), new ArrayList<>(), new ArrayList<>(), 1130, 440));

        // Bloque 6 (Filas 50-59)
        elementos.add(new DiagramElement("Inicio_50", "Valor_50", Arrays.asList("var_50"), Arrays.asList("met_50"), Arrays.asList("Proceso_51"), Arrays.asList(new ElementConnection(null, false)), 50, 530));
        elementos.add(new DiagramElement("Proceso_51", "Valor_51", Arrays.asList("var_51"), Arrays.asList("met_51"), Arrays.asList("Decisión_52", "Tarea_53"), Arrays.asList(new ElementConnection(null, true), new ElementConnection(null, false)), 170, 530));
        elementos.add(new DiagramElement("Decisión_52", "Valor_52", Arrays.asList("var_52"), Arrays.asList("met_52"), Arrays.asList("Proceso_51", "Fin_54"), Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, true)), 290, 530));

        return elementos;
    }
}
