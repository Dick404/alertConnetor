package org.rest.alert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class Connector {
    public static void main(String[] args){
        SpringApplication.run(Connector.class, args);
    }
}
