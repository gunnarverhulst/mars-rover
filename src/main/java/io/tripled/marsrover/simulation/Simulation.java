package io.tripled.marsrover.simulation;

import io.tripled.marsrover.input.InputParser;
import io.tripled.marsrover.message.Message;
import io.tripled.marsrover.message.MessagePrinter;
import io.tripled.marsrover.rover.Coordinate;
import io.tripled.marsrover.rover.Rover;
import io.tripled.marsrover.rover.RoverState;

import java.util.Optional;

public class Simulation {
    private int simulationSize = 0;

    private final Rover rover1 = new Rover();

    public Message handleSimulationSize(String input) {
        Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(input);
        if(simulationSizeOptional.isPresent()){
            simulationSize = simulationSizeOptional.get();

            return MessagePrinter.simulationSizeSetMessage(simulationSize);
        }
        return MessagePrinter.simulationSizeErrorMessage(input);
    }

    public Message handleRoverLanding(String input) {

        if(rover1.getRoverState() == null) {

            Optional<Coordinate> parsedInput = InputParser.parseInputForCoordinate(input.toLowerCase(), simulationSize);
            if(parsedInput.isPresent() ){
                Coordinate roverCoordinate = parsedInput.get();
                rover1.setRoverState(new RoverState(roverCoordinate));
                return MessagePrinter.landingMessage(rover1.getRoverState().roverCoordinate());
            }

            return MessagePrinter.landingErrorMessage(rover1.getRoverState().roverCoordinate());
        }
        return MessagePrinter.landingAlreadyLandedMessage();

    }

    public RoverState getRoverState() {
        return rover1.getRoverState();
    }
    public int getSimulationSize() {
        return simulationSize;
    }

}
