/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.util;

/**
 *
 * @author palwal
 */
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

public class SendMail {

    static final Logger logger = Logger.getLogger(SendMail.class);

    public static void sendEmail(String userId, String to) throws EmailException {
        Properties props = Utils.setMailProps();
        String content = "";

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Constant.EMAIL_GMAIL_LOGIN, Constant.EMAIL_GMAIL_PASSWORD);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Constant.EMAIL_FROM_ADDRESS, Constant.EMAIL_FROM_NAME));
            message.setReplyTo(new javax.mail.Address[]{
                        new javax.mail.internet.InternetAddress(Constant.EMAIL_REPLYTO_ADDRESS)
                    });
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("OFTUS: Please activate your account");
            //message.setText("Hello this is not spam. This is a JavaMail test...!");
            content = content + "<html><body><h1>Welcome!</h1>";
            content = content + "<h3>Please click the following link to activate your account</h3><br>";
            content = content + "<h3>http://www.yahoo.com?userId=" + userId + "</h3>";
            content = content + "<br><h2>Thank you!</h2>";
            content = content + "<h2>OFTUS Team.</h2></body></html>";
            message.setContent(content, "text/html");

            Transport.send(message);
            logger.debug("Sent message successfully....");
            Transport.send(message);
            logger.debug("Done:" + content);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
        }
    }

    public static void fileEmail() {
        String to = "alwalputta@gmail.com";
        String from = "alwalputta@gmail.com";
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("This is the Subject Line!");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("This is message body");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "file.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            logger.debug("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
