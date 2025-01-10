package com.bpm.employee.pojo;

import java.util.Objects;

public class ComputerSheetPojo {

    private Long idComputerSheet;
    private String userName;
    private String userPassword;
    private Boolean webGranted;
    private Boolean bpmAcceptGranted;
    private String mail;
//    private EmpleadoPojo empleado;

    public ComputerSheetPojo() {
        this.bpmAcceptGranted = false;
    }

    public Long getIdComputerSheet() {
        return idComputerSheet;
    }

    public void setIdComputerSheet(Long idComputerSheet) {
        this.idComputerSheet = idComputerSheet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean getWebGranted() {
        return webGranted;
    }

    public void setWebGranted(Boolean webGranted) {
        this.webGranted = webGranted;
    }

    public Boolean getBpmAcceptGranted() {
        return bpmAcceptGranted;
    }

    public void setBpmAcceptGranted(Boolean bpmAcceptGranted) {
        this.bpmAcceptGranted = bpmAcceptGranted;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

//    public EmpleadoPojo getEmpleado() {
//        return empleado;
//    }
//
//    public void setEmpleado(EmpleadoPojo empleado) {
//        this.empleado = empleado;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerSheetPojo that = (ComputerSheetPojo) o;
        return Objects.equals(idComputerSheet, that.idComputerSheet) && Objects.equals(userName, that.userName) && Objects.equals(userPassword, that.userPassword) && Objects.equals(webGranted, that.webGranted) && Objects.equals(bpmAcceptGranted, that.bpmAcceptGranted) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComputerSheet, userName, userPassword, webGranted, bpmAcceptGranted, mail);
    }
}
