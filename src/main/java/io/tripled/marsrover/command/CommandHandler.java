package io.tripled.marsrover.command;

import io.tripled.marsrover.input.InputParser;
import io.tripled.marsrover.message.messages.Message;
import io.tripled.marsrover.message.MessagePrinter;
import io.tripled.marsrover.rover.Coordinate;
import io.tripled.marsrover.rover.RoverState;
import io.tripled.marsrover.simulation.Simulation;

import java.util.Optional;

public enum CommandHandler {
    COMMAND_HANDLER;

    public Message handleCommand(Command command, String input, Simulation simulation) {
        return switch (command){
            case QUIT -> MessagePrinter.quitMessage();
            case SIMULATION_SIZE -> handleSimulationSize(input, simulation);
            case EMPTY_INPUT -> MessagePrinter.apiMessage();
            case LAND -> handleRoverLanding(input, simulation);
            case PRINT -> MessagePrinter.apiMessage();
            case STATE -> MessagePrinter.stateMessage(simulation.getSimulationSize(), simulation.getRoverState());
            default -> MessagePrinter.apiMessage();
        };
    }

    public Message handleSimulationSize(String input, Simulation simulation) {
        Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(input);
        if(simulationSizeOptional.isPresent()){
            simulation.setSimulationSize(simulationSizeOptional.get());

            return MessagePrinter.simulationSizeSetMessage(simulation.getSimulationSize());
        }
        return MessagePrinter.simulationSizeErrorMessage(input);
    }

    public Message handleRoverLanding(String input, Simulation simulation) {

        if(simulation.getRoverState() == null) {

            Optional<Coordinate> parsedInput = InputParser.parseInputForCoordinate(input.toLowerCase(), simulation.getSimulationSize());
            if(parsedInput.isPresent() ){
                Coordinate roverCoordinate = parsedInput.get();
                simulation.setRover1State(new RoverState(roverCoordinate));
                return MessagePrinter.landingMessage(simulation.getRover1Coordinates());
            }

            return MessagePrinter.landingErrorMessage(simulation.getRover1Coordinates());
        }
        return MessagePrinter.landingAlreadyLandedMessage();
    }
}
