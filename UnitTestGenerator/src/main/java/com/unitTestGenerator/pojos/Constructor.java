package com.unitTestGenerator.pojos;

import com.unitTestGenerator.ioc.anotations.Componente;

import java.util.ArrayList;
import java.util.List;

@Componente
public class Constructor {

    private List<ParametroMetodo> parametros = new ArrayList<>();
    private boolean isNoneParam;
    private String costructorSignature;
    private String costructorContent;



    public Constructor() {
    }

    public String getCostructorContent() {
        return costructorContent;
    }

    public void setCostructorContent(String costructorContent) {
        this.costructorContent = costructorContent;
    }

    public String getCostructorSignature() {
        return costructorSignature;
    }

    public void setCostructorSignature(String costructorSignature) {
        this.costructorSignature = costructorSignature;
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
