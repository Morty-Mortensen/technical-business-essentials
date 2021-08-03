package com.essentials.business.technical;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.logback.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class TechnicalApplication {
    private static final Logger logger = LoggerFactory.getLogger(TechnicalApplication.class);

    public static void main(String[] args) {
        logger.info(String.format("Starting the application on port %d", 8080));
        SpringApplication.run(TechnicalApplication.class, args);

        /**
         * Types of dependency injection objects.
         *
         * Singleton - Single instance (Created on initialization of app)
         * Prototype - Creates new instance everytime (Only initializes when .getBean() is called).
         * Request
         * Session
         * etc...
         */
    }
}
