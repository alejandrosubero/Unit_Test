package com.bpm.employee.model;

public class AssignedModel {

    private Long id;
    private String name;
    private String codeEmployee;
    private RoleModel employeeRole;
    private String mail;

    public AssignedModel() {
    }

    public AssignedModel(String name, String codeEmployee, RoleModel employeeRole, String mail) {
        this.name = name;
        this.codeEmployee = codeEmployee;
        this.employeeRole = employeeRole;
        this.mail = mail;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeEmployee() {
        return codeEmployee;
    }

    public void setCodeEmployee(String codeEmployee) {
        this.codeEmployee = codeEmployee;
    }

    public RoleModel getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(RoleModel employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "AssignedModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeEmployee='" + codeEmployee + '\'' +
                ", employeeRole=" + employeeRole +
                ", mail='" + mail + '\'' +
                '}';
    }
}
