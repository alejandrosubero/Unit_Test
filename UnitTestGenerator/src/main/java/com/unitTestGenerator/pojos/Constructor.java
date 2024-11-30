package com.unitTestGenerator.pojos;

import java.util.ArrayList;
import java.util.List;

public class Constructor {

    private List<ParametroMetodo> parametros = new ArrayList<>();
    private boolean isNoneParam;

    public Constructor() {
    }

    public List<ParametroMetodo> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroMetodo> parametros) {
        this.parametros = parametros;
    }

    public boolean isNoneParam() {
        return isNoneParam;
    }

    public void setIsNoneParam(boolean isNoneParam) {
        this.isNoneParam = isNoneParam;
    }

    public Boolean isEmptyParameters(){
        return this.parametros.isEmpty();
    }

}
