package com.bpm.employee.model;

public class RoleModel {

    private Long idRole;

    private String description;

    private String name;

    private String codeRole;

    public RoleModel() {
    }

    public RoleModel(Long idRole, String description, String name, String codeRole) {
        this.idRole = idRole;
        this.description = description;
        this.name = name;
        this.codeRole = codeRole;
    }

    public RoleModel( String description, String name, String codeRole) {
        this.idRole = idRole;
        this.description = description;
        this.name = name;
        this.codeRole = codeRole;
    }
    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeRole() {
        return codeRole;
    }

    public void setCodeRole(String codeRole) {
        this.codeRole = codeRole;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                "idRole=" + idRole +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", codeRole='" + codeRole + '\'' +
                '}';
    }
}
