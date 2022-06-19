package com.example.nationspring;

import com.example.nationspring.model.Nation;
import com.example.nationspring.repository.NationRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NationSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(NationSpringApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(NationRepository nationRepository){

        return args -> {


            nationRepository.nationByPopulation(13000).stream().forEach(System.out::println);


        };

    }
}
