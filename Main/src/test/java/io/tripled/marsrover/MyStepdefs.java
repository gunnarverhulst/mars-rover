package io.tripled.marsrover;

import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import io.tripled.marsrover.api.command.ApplicationService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@CucumberContextConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MarsRoverApplication.class})
@SpringBootTest
public class MyStepdefs {

    @Autowired
    private ApplicationService applicationService;

    public MyStepdefs(ApplicationService applicationService){
        this.applicationService = applicationService;
    }

    @Given("A simulation of size {int}")
    public void aSimulationOfSize(int simulationSize){
    }
}
