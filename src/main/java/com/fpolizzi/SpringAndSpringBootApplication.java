package com.fpolizzi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAndSpringBootApplication {

    static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(
                SpringAndSpringBootApplication.class,
                args
        );

        // print out all beans that are in the spring container
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        // total number of beans in the spring container
        System.out.println("\nNumber of Beans: " + beanDefinitionNames.length);
    }

    // user-defined bean
    @Bean
    public String readBean() {

        return "Manchester United";
    }

    // execute CommandLineRunner
    @Bean
    CommandLineRunner commandLineRunner() {

        return args -> {

            System.out.println("Hello from CommandLineRunner");
        };
    }
}
