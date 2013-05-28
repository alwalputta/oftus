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
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

public class SendEmail {

    static final Logger logger = Logger.getLogger(SendEmail.class);

    public static void sendEmail() {
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

            message.setText("This is actual message");

            Transport.send(message);
            logger.debug("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void sendHTMLEmail() {
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
            message.setContent("<h1>This is actual message</h1>", "text/html");

            Transport.send(message);
            logger.debug("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
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
