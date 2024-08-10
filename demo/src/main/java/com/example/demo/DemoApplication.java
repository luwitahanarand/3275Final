package com.example.demo;

import com.example.demo.entities.Sales;
import com.example.demo.repositories.SalesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
//This class is the entry point to the application
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(SalesRepository salesRepository) {
        return args -> {
            salesRepository.save(new Sales(null, "Jessica Lam", "Washing machine", 5000.00, new Date()));
            salesRepository.save(new Sales(null, "Janno San", "Refrigerator", 5000.00, new Date()));
            salesRepository.save(new Sales(null, "Jessica Lam", "Music system", 5000.00, new Date()));
            salesRepository.findAll().forEach(p->{
                System.out.println(p.getId());
            });

        };
    }

}
