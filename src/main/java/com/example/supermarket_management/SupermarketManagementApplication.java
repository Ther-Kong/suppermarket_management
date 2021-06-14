package com.example.supermarket_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SupermarketManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupermarketManagementApplication.class, args);
    }

}
