package io.tripled.marsrover.rover;

import io.tripled.marsrover.input.InputParser;
import io.tripled.marsrover.message.Message;
import io.tripled.marsrover.message.MessagePrinter;

import java.util.Optional;

public class Rover {


    private int simulationSize = 0;
    private Coordinate roverCoordinate = null;


    private RoverState roverState = null;


    public Message handleRoverLanding(String input) {

        if(roverCoordinate == null) {

            Optional<Coordinate> parsedInput = InputParser.parseInputForCoordinate(input.toLowerCase(), simulationSize);
            if(parsedInput.isPresent() ){
                roverCoordinate = parsedInput.get();
                if(roverState == null){
                    roverState = new RoverState(simulationSize, roverCoordinate);
                    return MessagePrinter.landingMessage(roverCoordinate);
                }
            }

            return MessagePrinter.landingErrorMessage(roverCoordinate);
        }
        return MessagePrinter.landingAlreadyLandedMessage();

    }

    public Message handleSimulationSize(String input) {
        Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(input);
        if(simulationSizeOptional.isPresent()){
            simulationSize = simulationSizeOptional.get();

            return MessagePrinter.simulationSizeSetMessage(simulationSize);
        }
        return MessagePrinter.simulationSizeErrorMessage(input);
    }
    public RoverState getRoverState() {
        return roverState;
    }

    public int getSimulationSize() {
        return simulationSize;
    }
}
