package com.uxpsystems.assignment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;



/**
 * Created by kchaurasia on 4/9/2020.
 */


@SpringBootApplication
@EnableCaching
public class MainApplication {

    private static final Logger logger = LogManager.getLogger(MainApplication.class);
	public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
        logger.info("In main method of MainApplication ");
	}

}
