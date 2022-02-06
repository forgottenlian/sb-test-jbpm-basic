package com.forgottenlian.test.sbtestjbpmbasic;

import com.forgottenlian.test.sbtestjbpmbasic.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    @Value("${app.name}")
    private String appName;

    @Autowired
    private FirstService s1;
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            System.out.println(String.format("%s ...", appName));
            s1.deployService();
            s1.testSimple_startProcess();
            System.out.println(String.format("STOP %s ...", appName));
        };
    }
}
