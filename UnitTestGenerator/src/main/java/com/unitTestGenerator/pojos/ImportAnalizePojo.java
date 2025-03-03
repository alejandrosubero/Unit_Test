package com.unitTestGenerator.pojos;

import java.util.Objects;

public class ImportAnalizePojo {

    private String externalImports;
    private String projectImports;


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

    public static Builder builder() {
        return new Builder();
    }

    public interface ImportAnalizePojoBuilder {
        public Builder externalImports(String externalImports);
        public Builder projectImports(String projectImports);

        public ImportAnalizePojo build();
    }

    public static class Builder implements ImportAnalizePojoBuilder {
        private String externalImports;
        private String projectImports;

        @Override
        public Builder externalImports(String externalImports) {
            this.externalImports = externalImports;
            return this;
        }

        @Override
        public Builder projectImports(String projectImports) {
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
