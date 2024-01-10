package io.tripled.marsrover.rover;

import io.tripled.marsrover.input.InputParser;
import io.tripled.marsrover.message.Message;
import io.tripled.marsrover.message.MessagePrinter;

import java.util.Optional;

public class Rover {

    private RoverState roverState = null;


    public Message handleRoverLanding(String input) {

        if(roverState.roverCoordinate() == null) {

            Optional<Coordinate> parsedInput = InputParser.parseInputForCoordinate(input.toLowerCase(), roverState.simulationSize());
            if(parsedInput.isPresent() ){
                Coordinate roverCoordinate = parsedInput.get();
                roverState = new RoverState(roverState.simulationSize(), roverCoordinate);
                return MessagePrinter.landingMessage(roverState.roverCoordinate());
            }

            return MessagePrinter.landingErrorMessage(roverState.roverCoordinate());
        }
        return MessagePrinter.landingAlreadyLandedMessage();

    }

    public Message handleSimulationSize(String input) {
        Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(input);
        if(simulationSizeOptional.isPresent()){
            roverState = new RoverState(simulationSizeOptional.get(), null);

            return MessagePrinter.simulationSizeSetMessage(roverState.simulationSize());
        }
        return MessagePrinter.simulationSizeErrorMessage(input);
    }
    public RoverState getRoverState() {
        return roverState;
    }

    public int getSimulationSize() {
        return roverState.simulationSize();
    }
}
