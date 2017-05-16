package com.jychan.notbad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * not-bad 管理平台
 *
 */
@ComponentScan({ "com.jychan.notbad" })
@EnableAutoConfiguration
@EnableConfigurationProperties
@SpringBootApplication
public class StartUp {

    public static void main( String[] args ) {
        System.out.println( "Welcome to start not-bad-admin!" );
        SpringApplication.run(StartUp.class, args);
    }
}
