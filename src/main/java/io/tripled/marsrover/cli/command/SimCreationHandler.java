package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.service.simulation.Simulation;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.Optional;

public final class SimCreationHandler implements ActionHandler {

    private final SimulationRepository simulationRepository;

    private final MessagePrinter messagePrinter;

    public SimCreationHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
        messagePrinter = new MessagePrinter(simulationRepository);
    }

    public Message handleSimulationSize(String input) {
        Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(input);
        if (simulationSizeOptional.isPresent()) {
            simulationRepository.addSimulation(new Simulation(simulationSizeOptional.get(), simulationRepository));

            return messagePrinter.simulationSizeSetMessage(simulationRepository.getSimulation().getSimulationSize());
        }
        return messagePrinter.simulationSizeErrorMessage(input);
    }
}
