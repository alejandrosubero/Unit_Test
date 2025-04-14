package com.pts.entitys;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EmailDataConfig")
public class EmailDataConfig {

    @Id
    @GeneratedValue(generator = "sequence_id_generator")
    @SequenceGenerator(name="sequence_id_generator", initialValue= 2, allocationSize=1000)
    @Column(name = "idEmailConfig", updatable = true, nullable = false, length = 25)
    private Long idEmailConfig;

    @Column(name = "host", updatable = true, nullable = true, length = 200)
    private String host;

    @Column(name = "port", updatable = true, nullable = true, length = 200)
    private String port;

    @Column(name = "mailUsername", updatable = true, nullable = true, length = 200)
    private String mailUsername;

    @Column(name = "mailPassword", updatable = true, nullable = true, length = 200)
    private String mailPassword;

//    private Boolean mailSmtpAuth = true;
//    private Integer mailSmtpAonnectiontimeout = 5000;
//    private Integer mailSmtpTimeout = 5000;
//    private Integer mailSmtpWritetimeout = 5000;
//    private Boolean mailDebug = true;
//    private String mailTransportProtocol = "smtp";
//    private Boolean mailSmtpStarttlsEnable = true;


    public EmailDataConfig() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public void setMailUsername(String mailUsername) {
        this.mailUsername = mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailDataConfig)) return false;
        EmailDataConfig that = (EmailDataConfig) o;
        return Objects.equals(getHost(), that.getHost()) && Objects.equals(getPort(), that.getPort()) && Objects.equals(getMailUsername(), that.getMailUsername()) && Objects.equals(getMailPassword(), that.getMailPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHost(), getPort(), getMailUsername(), getMailPassword());
    }
}
