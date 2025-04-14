package com.pts.pojo;


import java.util.Objects;

public class EmailDataConfigPojo {

    private Long idEmailConfig;
    private String host;
    private String port;
    private String mailUsername;
    private String mailPassword;

//    private Boolean mailSmtpAuth = true;
//    private Integer mailSmtpAonnectiontimeout = 5000;
//    private Integer mailSmtpTimeout = 5000;
//    private Integer mailSmtpWritetimeout = 5000;
//    private Boolean mailDebug = true;
//    private String mailTransportProtocol = "smtp";
//    private Boolean mailSmtpStarttlsEnable = true;


    public EmailDataConfigPojo() {
    }


    public Long getIdEmailConfig() {
        return idEmailConfig;
    }

    public void setIdEmailConfig(Long idEmailConfig) {
        this.idEmailConfig = idEmailConfig;
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
        if (!(o instanceof EmailDataConfigPojo)) return false;
        EmailDataConfigPojo that = (EmailDataConfigPojo) o;
        return Objects.equals(getIdEmailConfig(), that.getIdEmailConfig()) && Objects.equals(getHost(), that.getHost()) && Objects.equals(getPort(), that.getPort()) && Objects.equals(getMailUsername(), that.getMailUsername()) && Objects.equals(getMailPassword(), that.getMailPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdEmailConfig(), getHost(), getPort(), getMailUsername(), getMailPassword());
    }

}
