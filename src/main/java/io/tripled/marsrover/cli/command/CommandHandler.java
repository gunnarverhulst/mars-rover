package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.RoverDrivingErrorMessage;
import io.tripled.marsrover.cli.message.messages.RoverDrivingMessage;
import io.tripled.marsrover.service.rover.*;
import io.tripled.marsrover.service.simulation.Simulation;

import java.util.List;
import java.util.Optional;

public enum CommandHandler {
    COMMAND_HANDLER;

    public Message handlerBeforeSimulationSizeSet(String input, Simulation simulation){
        String inputConvertedToLowercase = input.trim().toLowerCase();
        return handleSimulationSize(inputConvertedToLowercase, simulation);
    }

    public Message handlerAfterSimulationSizeSet(String input, Simulation simulation){
        String preparedInput = input.trim().toLowerCase();
        if(preparedInput.equalsIgnoreCase("Q")){
            return MessagePrinter.quitMessage();
        }
        if(preparedInput.isEmpty()){
            return MessagePrinter.apiMessage();
        }
        if(preparedInput.equalsIgnoreCase("P")){
            return MessagePrinter.apiMessage();
        }
        if(preparedInput.equalsIgnoreCase("STATE")){
            return MessagePrinter.stateMessage(simulation.getSimulationSize(), simulation.getRoverState());
        }
        if(preparedInput.startsWith("land")){
            return handleRoverLanding(preparedInput, simulation);
        }
        if(preparedInput.startsWith("r")){
            return handleRoverDriving(preparedInput, simulation);
        }
        return MessagePrinter.apiMessage();
    }


    private Message handleSimulationSize(String input, Simulation simulation) {
        Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(input);
        if(simulationSizeOptional.isPresent()){
            simulation.setSimulationSize(simulationSizeOptional.get());

            return MessagePrinter.simulationSizeSetMessage(simulation.getSimulationSize());
        }
        return MessagePrinter.simulationSizeErrorMessage(input);
    }

    private Message handleRoverLanding(String input, Simulation simulation) {

        if(simulation.getRoverState() == null) {

            return performRoverLanding(input, simulation);
        }
        return MessagePrinter.landingAlreadyLandedMessage();
    }

    private Message performRoverLanding(String input, Simulation simulation) {
        Optional<Coordinate> parsedInput = InputParser.parseInputForCoordinate(input.toLowerCase(), simulation.getSimulationSize());
        if(parsedInput.isPresent() ){
            setRoverState(simulation, parsedInput.get());
            return MessagePrinter.landingMessage(simulation.getRover1Coordinates());
        }

        return MessagePrinter.landingErrorMessage(simulation.getRoverState());
    }

    private static void setRoverState(Simulation simulation, Coordinate parsedInput) {
        simulation.setRover1State(new RoverState(parsedInput, Heading.NORTH));
    }

    private Message handleRoverDriving(String preparedInput, Simulation simulation) {
        Optional<List<Move>> drivingMoves = buildDrivingMoves(preparedInput);

        RoverDrivingMessage drivingMessage = new RoverDrivingMessage();
        prepareRoverDrivingMessage(drivingMessage);

        if(drivingMoves.isPresent()){
            performRoverMoves(simulation, drivingMoves.get(), drivingMessage);

            endRoverDrivingMessage(drivingMessage);
            return drivingMessage;
        }

        return new RoverDrivingErrorMessage();
    }

    private static void endRoverDrivingMessage(RoverDrivingMessage drivingMessage) {
        drivingMessage.concat("Rover R1 executed all instructions. Awaiting new ones...\n");
    }

    private static void prepareRoverDrivingMessage(RoverDrivingMessage drivingMessage) {
        drivingMessage.concat("Rover R1 received instructions\n");
    }

    private static void performRoverMoves(Simulation simulation, List<Move> drivingMoves, RoverDrivingMessage drivingMessage) {
        Rover rover = simulation.getRover1();
        drivingMoves.forEach(x -> drivingMessage.concat(rover.move(x, simulation.getSimulationSize())));
    }

    private static Optional<List<Move>> buildDrivingMoves(String preparedInput) {
        return InputParser.parseInputForDrivingMoves(preparedInput);
    }
}
