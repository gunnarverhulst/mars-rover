package io.tripled.marsrover.service.command;

import io.tripled.marsrover.service.message.messages.Message;
import io.tripled.marsrover.service.presenter.SimConsolePresenter;
import io.tripled.marsrover.service.simulation.Simulation;
import io.tripled.marsrover.service.simulation.SimulationRepository;

public final class SimCreationHandler implements CommandHandler<Integer, SimConsolePresenter> {

    private final SimulationRepository simulationRepository;

    public SimCreationHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    @Override
    public Message handle(Integer simulationSize, SimConsolePresenter simCreationPresenter) {
        simulationRepository.addSimulation(new Simulation(simulationSize, simulationRepository));
        return simCreationPresenter.simulationCreated(simulationSize);
    }
}
