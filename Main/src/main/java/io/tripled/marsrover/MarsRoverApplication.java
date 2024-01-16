package io.tripled.marsrover;


import io.tripled.marsrover.businesslogic.repository.InMemorySimulationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MarsRoverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarsRoverApplication.class, args);
    }

    @Bean
    public InMemorySimulationRepository createSimulationRepository(){
        return new InMemorySimulationRepository();
    }


}
