/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.test;

/**
 *
 * @author palwal
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import com.myapp.util.Utils;


public class SendGMail {
    
    static final Logger logger = Logger.getLogger(SendGMail.class);
    
    public static void main(String[] args) {
        Properties props = Utils.setMailProps();
        String content = "";
        String loginLink = "12345566";
        
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("alwalputta", "Mygig123");
                    }
                });
        
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("putta_reddy@yahoo.com", "OFTUS EMAIL"));
            message.setReplyTo(new javax.mail.Address[]{
                        new javax.mail.internet.InternetAddress("putta_reddy@yahoo.com")
                    });
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("alwalputta@yahoo.com"));
            message.setSubject("OFTUS: Please activate your account");
            //message.setText("Hello this is not spam. This is a JavaMail test...!");
            
            content = content + "<html><body><h1>Welcome!</h1>";
            content = content + "<h3>Please click the following link to activate your account</h3><br>";
            content = content + "<h3>http://www.yahoo.com?userId=" + loginLink + "</h3>";
            content = content + "<br><h2>Thank you!</h2>";
            content = content + "<h2>OFTUS Team.</h2></body></html>";
            
            message.setContent(content, "text/html");
            
            Transport.send(message);
            logger.debug("Sent message successfully....");
            
            Transport.send(message);
            
            System.out.println("Done:" + content);
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            
        }
    }
}
