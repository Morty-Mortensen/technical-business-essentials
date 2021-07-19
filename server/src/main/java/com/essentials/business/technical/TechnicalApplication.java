package com.essentials.business.technical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class TechnicalApplication {

    /**
     * Task, see if there is a way to make less update if either an Interceptor is created or a new exception is created.
     * <p>
     * If a new interceptor is created:
     * 1. Make sure that the error handling urls (error/401, error/403) are added.
     * 2. Forward calls to that error handler and return false.
     * <p>
     * If new exception is created:
     * 1. Update appropriate middleware that might use it.
     * 2. Update ErrorType enum
     * 3. Update /utils/ExceptionHandler to add new enum to switch/case operation
     * 4. Update /controller/exception/controller/ErrorHandlerController to return ErrorInfo object and appropriate status code.
     * 5. Update /controller/exception/controller/RedirectErrorController to map to the new url (e.g. "error/501") and throw newly created exception.
     */

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
