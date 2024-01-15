package io.tripled.marsrover;

import io.tripled.marsrover.ui.cli.input.InputReader;
import io.tripled.marsrover.ui.cli.presenter.ProgramConsolePresenterImpl;
import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
import io.tripled.marsrover.businesslogic.presenter.ProgramPresenter;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;


public class MarsRoverApplication {

    private final InputReader inputReader;

    private final SimulationRepository simulationRepository;

    public MarsRoverApplication() {
        this.simulationRepository = new InMemorySimulationRepository();
        this.inputReader = new InputReader(simulationRepository);
    }

    public static void main(String[] args) {
        MarsRoverApplication marsRoverApplication = new MarsRoverApplication();

        ProgramPresenter programPresenter = new ProgramConsolePresenterImpl();
        programPresenter.logo();
        programPresenter.startupMessage();


        marsRoverApplication.getInputReader().readInput();

        programPresenter.endMessage();
    }

    public InputReader getInputReader() {
        return inputReader;
    }

}
