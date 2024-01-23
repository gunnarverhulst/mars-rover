package io.tripled.marsrover.businesslogic;

import io.tripled.marsrover.api.command.ApplicationService;
import io.tripled.marsrover.businesslogic.command.CommandController;
import io.tripled.marsrover.businesslogic.repository.InMemorySimulationRepository;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public SimulationRepository getSimulationRepository(){
        return new InMemorySimulationRepository();
    }

    @Bean
    public ApplicationService getCommandController(){
        return new CommandController(getSimulationRepository());
    }
}
