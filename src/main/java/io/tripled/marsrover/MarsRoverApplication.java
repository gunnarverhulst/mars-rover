package io.tripled.marsrover;

import io.tripled.marsrover.cli.input.InputReader;
import io.tripled.marsrover.cli.message.messages.LogoMessage;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
import io.tripled.marsrover.service.simulation.SimulationRepository;


public class MarsRoverApplication {

    private final InputReader inputReader;


    public MarsRoverApplication() {
        SimulationRepository simulationRepository = new InMemorySimulationRepository();
        this.inputReader = new InputReader(simulationRepository);
    }

    public static void main(String[] args) {
        MarsRoverApplication marsRoverApplication = new MarsRoverApplication();

        printLogo();
        System.out.println(marsRoverApplication.getInputReader().readInput());
    }

    public static void printLogo() {
        Message logo = new LogoMessage();
        System.out.println(logo.messageToBePrinted());
    }
    public InputReader getInputReader() {
        return inputReader;
    }

}
