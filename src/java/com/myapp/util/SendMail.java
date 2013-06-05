/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.util;

/**
 *
 * @author palwal
 */
import com.myapp.admin.User;
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
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class SendMail {

    static final Logger logger = Logger.getLogger(SendMail.class);

    public static void sendActivateEmail(HttpServletRequest request, User user, String emailTo) throws EmailException {
        Properties props = Utils.setMailProps();
        String content = "";

        logger.debug("userId:" + user.getUserId());
        logger.debug("emailTo:" + emailTo);
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        logger.debug("serverName1");
                        return new PasswordAuthentication(Constant.EMAIL_GMAIL_LOGIN, Constant.EMAIL_GMAIL_PASSWORD);
                    }
                });
        try {

            logger.debug("serverName1");
            String serverName = request.getServerName();
            String contextPath = request.getContextPath();
            String requestURL = request.getRequestURL().toString();
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();

            logger.debug("serverName1");
            int last = requestURL.lastIndexOf("/");
            requestURL = requestURL.substring(0, last) + "/activate_profile?userId=" + user.getUserId();

            logger.debug("serverName1");
            if (request.getQueryString() != null) {
                requestURL = requestURL + request.getQueryString();
            }
            String completeURL = requestURL.toString();

            logger.debug("serverName:" + serverName);
            logger.debug("contextPath:" + contextPath);
            logger.debug("requestURL:" + requestURL);
            logger.debug("url:" + url);
            logger.debug("completeURL:" + completeURL);
            logger.debug("email:" + emailTo);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Constant.EMAIL_FROM_ADDRESS, Constant.EMAIL_FROM_NAME));
            message.setReplyTo(new javax.mail.Address[]{
                        new javax.mail.internet.InternetAddress(Constant.EMAIL_REPLYTO_ADDRESS)
                    });
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject("Oftus: Please Activate Your Account");
            //message.setText("Hello this is not spam. This is a JavaMail test...!");
            content = content + "<html><body><h1>Welcome " + user.getFirstName() + ",</h1>";
            content = content + "<h3>Please click the following link to activate your account.</h3><br>";
            content = content + "<h3>" + completeURL + "</h3>";
            content = content + "<h3><a href=" + completeURL + ">" + completeURL + "</a></h3>";
            content = content + "<br><h2>Thank you!</h2>";
            content = content + "<h2>OFTUS Team.</h2></body></html>";
            //message.setContent(content, "text/html; charset=utf-8");
            message.setContent(content, "text/html");

            Transport.send(message);
            logger.debug("Sent message successfully....");
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
