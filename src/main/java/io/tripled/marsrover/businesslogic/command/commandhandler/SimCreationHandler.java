package io.tripled.marsrover.businesslogic.command.commandhandler;

import io.tripled.marsrover.businesslogic.presenter.SimConsolePresenter;
import io.tripled.marsrover.businesslogic.simulation.Simulation;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;

public final class SimCreationHandler implements CommandHandler<Integer, SimConsolePresenter> {

    private final SimulationRepository simulationRepository;

    public SimCreationHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    @Override
    public void handle(Integer simulationSize, SimConsolePresenter simCreationPresenter) {
        simulationRepository.addSimulation(new Simulation(simulationSize, simulationRepository));
        simCreationPresenter.simulationCreated(simulationSize);
    }
}
