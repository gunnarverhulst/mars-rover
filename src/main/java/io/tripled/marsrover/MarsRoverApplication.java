package io.tripled.marsrover;

import io.tripled.marsrover.cli.input.InputReader;
import io.tripled.marsrover.cli.message.messages.LogoMessage;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.presenter.ProgramPresenterImpl;
import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
import io.tripled.marsrover.service.presenter.ProgramPresenter;
import io.tripled.marsrover.service.simulation.SimulationRepository;


public class MarsRoverApplication {

    private final InputReader inputReader;

    private final SimulationRepository simulationRepository;

    public MarsRoverApplication() {
        this.simulationRepository = new InMemorySimulationRepository();
        this.inputReader = new InputReader(simulationRepository);
    }

    public static void main(String[] args) {
        MarsRoverApplication marsRoverApplication = new MarsRoverApplication();

        ProgramPresenter programPresenter = new ProgramPresenterImpl();
        programPresenter.printLogo();
        marsRoverApplication.getInputReader().readInput();
        programPresenter.printEndMessage();
    }

    public InputReader getInputReader() {
        return inputReader;
    }

}
