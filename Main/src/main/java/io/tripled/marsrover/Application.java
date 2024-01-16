package io.tripled.marsrover;

import io.tripled.marsrover.api.presenter.ProgramPresenter;
import io.tripled.marsrover.businesslogic.command.CommandController;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;
import io.tripled.marsrover.ui.cli.input.InputReader;
import io.tripled.marsrover.ui.cli.presenter.ProgramConsolePresenterImpl;
import org.springframework.stereotype.Component;

@Component
public class Application {

    private InputReader inputReader;

    private SimulationRepository simulationRepository;

    public Application(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
        this.inputReader = new InputReader(new CommandController(simulationRepository));

        ProgramPresenter programPresenter = new ProgramConsolePresenterImpl();
        programPresenter.logo();
        programPresenter.startupMessage();


        inputReader.readInput();

        programPresenter.endMessage();
    }
}
