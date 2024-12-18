package com.unitTestGenerator.services;

public interface ReturnType {


    default String getValorPorDefecto(String tipoRetorno) {
        switch (tipoRetorno) {
            case "int":
                return "0";
            case "long":
                return "0L";
            case "double":
                return "0.0";
            case "float":
                return "0.0f";
            case "boolean":
                return "true";
            case "String":
                return "\"\"";
            case "Integer":
                return "0";
            case "Long":
                return "0L";
            case "Double":
                return "0.0";
            case "Float":
                return "0.0F";
            case "Boolean":
                return "true";
            default:
                return "null";
        }
    }

    default boolean isValidTypeReturn(String typeReturn) {
        switch (typeReturn) {
            case "int":
            case "long":
            case "double":
            case "float":
            case "boolean":
            case "String":
            case "Integer":
            case "Long":
            case "Double":
            case "Float":
            case "Boolean":
            case "Date":
                return true;
            default:
                return false;
        }
    }

    default String getAssertType(String tipoRetorno, String valorDeRetorno, String result) {

        switch (tipoRetorno) {
            case "int":
            case "long":
            case "double":
            case "float":
            case "Integer":
            case "Long":
            case "Double":
            case "Float":
            case "String":
                return "Assertions.assertThat(" + valorDeRetorno + ").isEqualTo("+result+");";
            case "boolean":
                if (valorDeRetorno.equals("true")) {
                    return "Assertions.assertThat(res).isTrue();";
                } else {
                    return "Assertions.assertThat("+result+").isFalse();";
                }
            default:
                if (valorDeRetorno == null) {
                    return "Assertions.assertThat("+result+").isNull();";
                } else {
                    return "Assertions.assertThat("+result+").isNotNull();";
                }
        }
    }


}
/*
	Assertions.assertThat(resultado).isFalse();
	Assertions.assertThat(resultado).isEqualTo(resultado);
	Assertions.assertThat(resultado).isNotEqualTo(resultado);
	Assertions.assertThat(resultado).isNotNull();
	Assertions.assertThat(resultado).isNull();
* */
