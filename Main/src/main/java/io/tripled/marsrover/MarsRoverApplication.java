package io.tripled.marsrover;


import io.tripled.marsrover.api.presenter.ProgramPresenter;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;
import io.tripled.marsrover.ui.cli.input.InputReader;
import io.tripled.marsrover.ui.cli.presenter.ProgramConsolePresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class MarsRoverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarsRoverApplication.class);
        ApplicationContext MarsroverContext = new AnnotationConfigApplicationContext("io.tripled.marsrover");
        InputReader inputReader= MarsroverContext.getBean(InputReader.class);

        ProgramPresenter programPresenter = new ProgramConsolePresenterImpl();
        programPresenter.logo();
        programPresenter.startupMessage();

        inputReader.readInput();

        programPresenter.endMessage();

    }




}
