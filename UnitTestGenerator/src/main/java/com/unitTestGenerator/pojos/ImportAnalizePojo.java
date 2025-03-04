package com.unitTestGenerator.pojos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImportAnalizePojo {

    private List<String> externalImports = new ArrayList<>();
    private List<String>  projectImports = new ArrayList<>();


    public ImportAnalizePojo() {
    }

    public ImportAnalizePojo(List<String> externalImports, List<String> projectImports) {
        if(externalImports != null && !externalImports.equals("")){
            this.externalImports = externalImports;
        }
        if(projectImports != null && !projectImports.equals("")) {
            this.projectImports = projectImports;
        }
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

    public List<String> getExternalImports() {
        return externalImports;
    }

    public void setExternalImports(List<String> externalImports) {
        this.externalImports = externalImports;
    }

    public List<String> getProjectImports() {
        return projectImports;
    }

    public void setProjectImports(List<String> projectImports) {
        this.projectImports = projectImports;
    }

    public static Builder builder() {
        return new Builder();
    }

    public interface ImportAnalizePojoBuilder {
        public Builder externalImports(List<String> externalImports);
        public Builder projectImports(List<String> projectImports);

        public ImportAnalizePojo build();
    }

    public static class Builder implements ImportAnalizePojoBuilder {
        private List<String> externalImports;
        private List<String> projectImports;

        @Override
        public Builder externalImports(List<String> externalImports) {
            this.externalImports = externalImports;
            return this;
        }

        @Override
        public Builder projectImports(List<String> projectImports) {
            this.projectImports = projectImports;
            return this;
        }

        @Override
        public ImportAnalizePojo build() {
            ImportAnalizePojo importanalizepojo = new ImportAnalizePojo();
            importanalizepojo.setExternalImports(this.externalImports);
            importanalizepojo.setProjectImports(this.projectImports);
            return importanalizepojo;
        }
    }

}
