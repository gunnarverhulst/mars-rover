package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.service.simulation.SimulationRepository;

public class CommandHandler {

    private final SimulationRepository simulationRepository;
    private final MessagePrinter messagePrinter;

    public CommandHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
        this.messagePrinter = new MessagePrinter(simulationRepository);
    }

    public Message handlerBeforeSimulationSizeSet(String input) {
        String preppedInput = input.trim().toLowerCase();
        return new SimCreationHandler(simulationRepository).handleSimulationSize(preppedInput);
    }


    public Message handlerAfterSimulationSizeSet(String input) {
        String preparedInput = input.trim().toLowerCase();
        if (preparedInput.equalsIgnoreCase("Q")) {
            return messagePrinter.quitMessage();
        }
        if (preparedInput.isEmpty()) {
            return messagePrinter.apiMessage();
        }
        if (preparedInput.equalsIgnoreCase("P")) {
            return messagePrinter.apiMessage();
        }
        if (preparedInput.equalsIgnoreCase("STATE")) {
            return messagePrinter.stateMessage();
        }
        if (preparedInput.startsWith("land")) {
            return new RoverLandingHandler(simulationRepository).handleRoverLanding(preparedInput);
        }
        if (preparedInput.startsWith("r")) {
            return new RoverDrivingHandler(simulationRepository).handleRoverDriving(preparedInput);
        }
        return messagePrinter.apiMessage();
    }
}
