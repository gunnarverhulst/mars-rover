package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.RoverDrivingErrorMessage;
import io.tripled.marsrover.cli.message.messages.RoverDrivingMessage;
import io.tripled.marsrover.service.rover.*;
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
        String inputConvertedToLowercase = input.trim().toLowerCase();
        return handleSimulationSize(inputConvertedToLowercase);
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
            return handleRoverLanding(preparedInput);
        }
        if (preparedInput.startsWith("r")) {
            return handleRoverDriving(preparedInput);
        }
        return messagePrinter.apiMessage();
    }


    private Message handleSimulationSize(String input) {
        Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(input);
        if (simulationSizeOptional.isPresent()) {
            simulationRepository.addSimulation(new Simulation(simulationSizeOptional.get(), simulationRepository));

            return messagePrinter.simulationSizeSetMessage(simulationRepository.getSimulation().getSimulationSize());
        }
        return messagePrinter.simulationSizeErrorMessage(input);
    }

    private Message handleRoverLanding(String input) {

        if (simulationRepository.getSimulation().getRoverState() == null) {

            return performRoverLanding(input);
        }
        return messagePrinter.landingAlreadyLandedMessage();
    }

    private Message performRoverLanding(String input) {
        Optional<Coordinate> parsedInput = inputParser.parseInputForCoordinate(input.toLowerCase());
        if (parsedInput.isPresent()) {
            setRoverState(parsedInput.get());
            return messagePrinter.landingMessage();
        }

        return messagePrinter.landingErrorMessage();
    }

    private void setRoverState(Coordinate parsedInput) {
        simulationRepository.getSimulation().setRover1State(new RoverState(parsedInput, Heading.NORTH));
    }

    private Message handleRoverDriving(String preparedInput) {
        Optional<List<Move>> drivingMoves = buildDrivingMoves(preparedInput);

        RoverDrivingMessage drivingMessage = new RoverDrivingMessage();
        prepareRoverDrivingMessage(drivingMessage);

        if (drivingMoves.isPresent()) {
            performRoverMoves(drivingMoves.get(), drivingMessage);

            endRoverDrivingMessage(drivingMessage);
            return drivingMessage;
        }

        return new RoverDrivingErrorMessage();
    }

    private void endRoverDrivingMessage(RoverDrivingMessage drivingMessage) {
        drivingMessage.concat("Rover R1 executed all instructions. Awaiting new ones...\n");
    }

    private void prepareRoverDrivingMessage(RoverDrivingMessage drivingMessage) {
        drivingMessage.concat("Rover R1 received instructions\n");
    }

    private void performRoverMoves(List<Move> drivingMoves, RoverDrivingMessage drivingMessage) {
        Rover rover = simulationRepository.getSimulation().getRover1();
        drivingMoves.forEach(x -> drivingMessage.concat(rover.move(x)));
    }

    private Optional<List<Move>> buildDrivingMoves(String preparedInput) {
        return InputParser.parseInputForDrivingMoves(preparedInput);
    }
}
