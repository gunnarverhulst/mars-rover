package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.RoverState;
import io.tripled.marsrover.service.simulation.Simulation;

import java.util.Optional;

public enum CommandHandler {
    COMMAND_HANDLER;

    public Message handlerBeforeSimulationSizeSet(String input, Simulation simulation){
        return handleSimulationSize(input, simulation);
    }

    public Message handlerAfterSimulationSizeSet(String input, Simulation simulation){
        String inputConvertedToLowercase = input.toLowerCase();
        if(inputConvertedToLowercase.equalsIgnoreCase("Q")){
            return MessagePrinter.quitMessage();
        }
        if(inputConvertedToLowercase.isEmpty()){
            return MessagePrinter.apiMessage();
        }
        if(inputConvertedToLowercase.equalsIgnoreCase("P")){
            return MessagePrinter.apiMessage();
        }
        if(inputConvertedToLowercase.equalsIgnoreCase("STATE")){
            return MessagePrinter.stateMessage(simulation.getSimulationSize(), simulation.getRoverState());
        }
        if(inputConvertedToLowercase.startsWith("land")){
            return handleRoverLanding(inputConvertedToLowercase, simulation);
        }
        return MessagePrinter.apiMessage();
    }

    public Message handleSimulationSize(String input, Simulation simulation) {
        String trimmedInput = input.trim();
        Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(trimmedInput);
        if(simulationSizeOptional.isPresent()){
            simulation.setSimulationSize(simulationSizeOptional.get());

            return MessagePrinter.simulationSizeSetMessage(simulation.getSimulationSize());
        }
        return MessagePrinter.simulationSizeErrorMessage(trimmedInput);
    }

    public Message handleRoverLanding(String input, Simulation simulation) {

        if(simulation.getRoverState() == null) {

            Optional<Coordinate> parsedInput = InputParser.parseInputForCoordinate(input.toLowerCase(), simulation.getSimulationSize());
            if(parsedInput.isPresent() ){
                Coordinate roverCoordinate = parsedInput.get();
                simulation.setRover1State(new RoverState(roverCoordinate));
                return MessagePrinter.landingMessage(simulation.getRover1Coordinates());
            }

                return MessagePrinter.landingErrorMessage(simulation.getRoverState());
        }
        return MessagePrinter.landingAlreadyLandedMessage();
    }
}
