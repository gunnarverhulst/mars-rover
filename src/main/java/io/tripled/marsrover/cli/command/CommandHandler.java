package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.messages.RoverDrivingErrorMessage;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Move;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.List;
import java.util.Optional;

public class CommandHandler {

    private final SimulationRepository simulationRepository;
    private final MessagePrinter messagePrinter;

    private final InputParser inputParser;

    public CommandHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
        this.messagePrinter = new MessagePrinter(simulationRepository);
        this.inputParser = new InputParser(simulationRepository);
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
            Optional<Coordinate> parsedInput = inputParser.parseInputForCoordinate(input.toLowerCase());
            if (parsedInput.isPresent()) {
                return new RoverLandingHandler(simulationRepository).handleRoverLanding(parsedInput.get());
            }
            return messagePrinter.landingErrorMessage();
        }
        if (preparedInput.startsWith("r")) {
            Optional<List<Move>> drivingMoves = InputParser.parseInputForDrivingMoves(preparedInput);

            if(drivingMoves.isPresent()){
                return new RoverDrivingHandler(simulationRepository).handleRoverDriving(drivingMoves.get());
            }
            return new RoverDrivingErrorMessage();
        }
        return messagePrinter.apiMessage();
    }
}
