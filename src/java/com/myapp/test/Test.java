/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.test;

import com.myapp.util.SendMail;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

/**
 * @author palwal
 */
public class Test {

    static final Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args) {
//        PropertyConfigurator.configure("log4j.properties");
        try {
            System.out.println("Alwal before send email");
            //SendMail.sendEmail();
            System.out.println("Alwal after send email");

            logger.debug("Hello World!");
            logger.info("Sample info message1");
            logger.warn("Sample warn message1");
            logger.error("Sample error message1");
            logger.fatal("Sample fatal message1");
 //       } catch (EmailException e) {
        } catch (Exception e) {
        }
    }
}
