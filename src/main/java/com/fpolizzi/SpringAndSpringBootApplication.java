package com.fpolizzi;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            // System.out.println(beanDefinitionName);
        }

        // total number of beans in the spring container
        System.out.println("\nNumber of Beans: " + beanDefinitionNames.length);
    }

    // user-defined bean
    @Bean
    public String redBean() {

        return "Manchester United";
    }

    @Bean
    public String blueBean() {

        return "Chelsea";
    }

    // execute CommandLineRunner
    @Bean
    CommandLineRunner commandLineRunner(String redBean,
                                        String blueBean,
                                        UserService userService) {

        return args -> {

            System.out.println("\nHello from CommandLineRunner");
            System.out.println(redBean());
            System.out.println(blueBean());
            System.out.println(userService.getUsers());
            System.out.println(userService.getUserById(1));
            System.out.println(userService.getUserById(3));
            System.out.println();
        };
    }

    public record User(
            int id,
            String name
    ) {
    }

    @Service
    public class UserService {

        @PostConstruct
        public void init() {

            System.out.println("Before bean creation..");
        }

        @PreDestroy
        public void tearDown() {

            System.out.println("Before bean destroying..");
        }


        public List<User> getUsers() {

            return List.of(
                    new User(1, "John Doe"),
                    new User(2, "Jane Doe")
            );
        }

        public Optional<User> getUserById(int id) {

            return getUsers().stream()
                    .filter(user -> user.id == id)
                    .findFirst();
        }
    }
}
