package com.pts.serviceImplement;

import com.pts.entitys.EmailDataConfig;
import com.pts.pojo.EmailDataConfigPojo;
import com.pts.pojo.EmailPojo;
import com.pts.security.EncryptAES;
import com.pts.service.EmailDataConfigService;
import com.pts.service.MailServices;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Service("EmailService")
public class MailServicesImplment implements MailServices {

    protected static final Log logger = LogFactory.getLog(MailServicesImplment.class);

    //Todo: mejorar la frase de encriptado.
    @Value("${saltAESKey}")
    private String saltAES;

    @Autowired
    private EncryptAES encryptAES;

    @Autowired(required = true)
    private EmailDataConfigService emailDataConfigService;


    @Override
    public Boolean sendMail(EmailPojo email, String portMail) {
        boolean valor = false;
        EmailDataConfigPojo config = null;
        config = emailDataConfigService.findByPort(portMail);

        String pass = encryptAES.decrypt(config.getMailPassword(), saltAES);
        String userName = encryptAES.decrypt(config.getMailUsername(), saltAES);

        Properties props = System.getProperties();
        props.put("mail.smtp.host", config.getHost());
        props.put("mail.smtp.user", userName);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", config.getPort());
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(email.getFrom()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
            message.setSubject(email.getSubject());

            message.setText(email.getContent());

            Transport transport = session.getTransport("smtp");
            transport.connect(config.getHost(), userName, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            valor = true;
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return valor;
    }


    @Override
    public Boolean sendMails(List<EmailPojo> emails, String portMail) {
        int countSendMails = 0;
        for (EmailPojo emailPojo : emails) {
            this.sendMail(emailPojo, portMail);
            countSendMails++;
        }

        if (emails.size() == countSendMails) {
            return true;
        }
        return false;
    }
}



