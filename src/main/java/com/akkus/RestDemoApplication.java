package com.akkus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.boot.SpringApplication;
import static org.springframework.boot.SpringApplication.run;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author akkus
 */
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan("com.akkus.*")
public class RestDemoApplication {

    /**
     *
     */
    public final static Logger logger = getLogger(RestDemoApplication.class);

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        run(RestDemoApplication.class, args);
    }
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(RestDemoApplication.class.getName());

}
