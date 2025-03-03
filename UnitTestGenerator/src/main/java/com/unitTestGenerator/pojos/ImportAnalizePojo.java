package com.unitTestGenerator.pojos;

import java.util.Objects;

public class ImportAnalizePojo {

    String externalImports;
    String projectImports;


    public ImportAnalizePojo() {
    }

    public ImportAnalizePojo(String externalImports, String projectImports) {
        if(externalImports != null && !externalImports.equals("")){
            this.externalImports = externalImports;
        }
        if(projectImports != null && !projectImports.equals("")) {
            this.projectImports = projectImports;
        }
    }

    public String getExternalImports() {
        return externalImports;
    }

    public void setExternalImports(String externalImports) {
        this.externalImports = externalImports;
    }

    public String getProjectImports() {
        return projectImports;
    }

    public void setProjectImports(String projectImports) {
        this.projectImports = projectImports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportAnalizePojo that = (ImportAnalizePojo) o;
        return Objects.equals(externalImports, that.externalImports) && Objects.equals(projectImports, that.projectImports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(externalImports, projectImports);
    }
}
