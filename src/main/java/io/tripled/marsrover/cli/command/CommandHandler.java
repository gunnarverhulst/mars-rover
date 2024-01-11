package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.messages.RoverDrivingErrorMessage;
import io.tripled.marsrover.service.command.ActionHandler;
import io.tripled.marsrover.service.command.RoverDrivingHandler;
import io.tripled.marsrover.service.command.RoverLandingHandler;
import io.tripled.marsrover.service.command.SimCreationHandler;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Move;
import io.tripled.marsrover.service.simulation.Simulation;
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
        Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(preppedInput);


        SimCreationHandler actionHandler = new SimCreationHandler(simulationRepository);

        if (simulationSizeOptional.isPresent()) {
            return actionHandler.execute(simulationSizeOptional.get());

        }
        return messagePrinter.simulationSizeErrorMessage(input);
    }


    public Message handlerAfterSimulationSizeSet(String input) {
        String preparedInput = input.trim().toLowerCase();

        ActionHandler actionHandler;
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
                actionHandler = new RoverLandingHandler(simulationRepository);
                return ((RoverLandingHandler) actionHandler).execute(parsedInput.get());
            }
            return messagePrinter.landingErrorMessage();
        }
        if (preparedInput.startsWith("r")) {
            Optional<List<Move>> drivingMoves = InputParser.parseInputForDrivingMoves(preparedInput);

            if(drivingMoves.isPresent()){
                actionHandler = new RoverDrivingHandler(simulationRepository);
                return ((RoverDrivingHandler) actionHandler).execute(drivingMoves.get());
            }
            return new RoverDrivingErrorMessage();
        }
        return messagePrinter.apiMessage();
    }

    public Simulation getSimulation() {
        return simulationRepository.getSimulation();
    }
}
