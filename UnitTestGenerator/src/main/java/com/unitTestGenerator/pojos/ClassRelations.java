package com.unitTestGenerator.pojos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassRelations {

    private String className;
    private List<String> implementsList;
    private String classExtends;
    private String classType;


    public ClassRelations() {
        this.implementsList = new ArrayList<>();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getImplementsList() {
        return implementsList;
    }

    public void setImplementsList(List<String> implementsList) {
        this.implementsList = implementsList;
    }

    public String getClassExtends() {
        return classExtends;
    }

    public void setClassExtends(String classExtends) {
        this.classExtends = classExtends;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassRelations that = (ClassRelations) o;
        return Objects.equals(className, that.className) && Objects.equals(implementsList, that.implementsList) && Objects.equals(classExtends, that.classExtends) && Objects.equals(classType, that.classType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, implementsList, classExtends, classType);
    }

    public static Builder builder() {
        return new Builder();
    }

    public interface ClassRelationesBuilder {
        public Builder className(String className);
        public Builder implementsList(List<String> implementsList);
        public Builder classExtends(String classExtends);
        public Builder classType(String classType);

        public ClassRelations build();
    }

    public static class Builder implements ClassRelationesBuilder {
        private String className;
        private List<String> implementsList;
        private String classExtends;
        private String classType;

        @Override
        public Builder className(String className) {
            this.className = className;
            return this;
        }

        @Override
        public Builder implementsList(List<String> implementsList) {
            this.implementsList = implementsList;
            return this;
        }

        @Override
        public Builder classExtends(String classExtends) {
            this.classExtends = classExtends;
            return this;
        }

        @Override
        public Builder classType(String classType) {
            this.classType = classType;
            return this;
        }

        @Override
        public ClassRelations build() {
            ClassRelations classrelationes = new ClassRelations();
            classrelationes.setClassName(this.className);
            classrelationes.setImplementsList(this.implementsList);
            classrelationes.setClassExtends(this.classExtends);
            classrelationes.setClassType(this.classType);
            return classrelationes;
        }
    }

}
