/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author palwal
 */
public class Test {

    static final Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");

        System.out.println("Alwal 121212");

        logger.debug("Hello World!");
        logger.info("Sample info message1");
        logger.warn("Sample warn message1");
        logger.error("Sample error message1");
        logger.fatal("Sample fatal message1");
    }
}
