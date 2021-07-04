package com.essentials.business.technical;

import com.essentials.business.technical.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TechnicalApplication {

    public static void main(String[] args) {
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
