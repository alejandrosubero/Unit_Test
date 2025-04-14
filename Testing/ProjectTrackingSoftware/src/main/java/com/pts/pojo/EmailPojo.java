package com.pts.pojo;

import java.util.Arrays;
import java.util.Objects;

public class EmailPojo {

    private String to;
    private String from;
    private String subject;
    private String content;
    private byte[] adjunto;

    public EmailPojo() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(byte[] adjunto) {
        this.adjunto = adjunto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailPojo)) return false;
        EmailPojo emailPojo = (EmailPojo) o;
        return Objects.equals(getTo(), emailPojo.getTo()) && Objects.equals(getFrom(), emailPojo.getFrom()) && Objects.equals(getSubject(), emailPojo.getSubject()) && Objects.equals(getContent(), emailPojo.getContent()) && Arrays.equals(getAdjunto(), emailPojo.getAdjunto());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getTo(), getFrom(), getSubject(), getContent());
        result = 31 * result + Arrays.hashCode(getAdjunto());
        return result;
    }
}
