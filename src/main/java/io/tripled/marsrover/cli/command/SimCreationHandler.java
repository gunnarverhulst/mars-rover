package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.service.simulation.Simulation;
import io.tripled.marsrover.service.simulation.SimulationRepository;

public final class SimCreationHandler implements ActionHandler {

    private final SimulationRepository simulationRepository;

    private final MessagePrinter messagePrinter;

    public SimCreationHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
        messagePrinter = new MessagePrinter(simulationRepository);
    }

    public Message handleSimulationSize(int simulationSize) {
        simulationRepository.addSimulation(new Simulation(simulationSize, simulationRepository));

        return messagePrinter.simulationSizeSetMessage(simulationRepository.getSimulation().getSimulationSize());
    }
}
