package io.tripled.marsrover.businesslogic.command.commandhandler;

import io.tripled.marsrover.businesslogic.presenter.SimCreationPresenter;
import io.tripled.marsrover.businesslogic.simulation.Simulation;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;

public final class SimCreationHandler implements CommandHandler<Integer, SimCreationPresenter> {

    private final SimulationRepository simulationRepository;

    public SimCreationHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    @Override
    public void handle(Integer simulationSize, SimCreationPresenter simCreationPresenter) {
        simulationRepository.addSimulation(new Simulation(simulationSize, simulationRepository));
        simCreationPresenter.simulationCreated(simulationSize);
    }
}
