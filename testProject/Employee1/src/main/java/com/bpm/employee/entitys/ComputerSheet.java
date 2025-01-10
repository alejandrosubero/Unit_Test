package com.bpm.employee.entitys;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "computerSheet")
public class ComputerSheet {

    @Id
    @GeneratedValue(generator = "sequence_computerSheet_generator")
    @SequenceGenerator(name = "sequence_computerSheet_generator", initialValue = 1, allocationSize = 1000)
    @Column(name = "idComputerSheet", updatable = true, nullable = false, length = 25)
    private Long idComputerSheet;

    @Column(name = "userName", updatable = true, nullable = true, length = 200)
    private String userName;

    @Column(name = "userPassword", updatable = true, nullable = true, length = 200)
    private String userPassword;

    @Column(name = "webGranted", updatable = true, nullable = true, length = 200)
    private Boolean webGranted;

    @Column(name = "bpmAcceptGranted", updatable = true, nullable = true, length = 200)
    private Boolean bpmAcceptGranted;

    @Column(name = "mail", updatable = true, nullable = true, length = 200)
    private String mail;

//    @OneToOne
//    @JoinColumn(name = "empleado_id")
//    private Empleado empleado;


    public ComputerSheet() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerSheet that = (ComputerSheet) o;
        return Objects.equals(idComputerSheet, that.idComputerSheet) && Objects.equals(userName, that.userName) && Objects.equals(userPassword, that.userPassword) && Objects.equals(webGranted, that.webGranted) && Objects.equals(bpmAcceptGranted, that.bpmAcceptGranted) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComputerSheet, userName, userPassword, webGranted, bpmAcceptGranted, mail);
    }
}
