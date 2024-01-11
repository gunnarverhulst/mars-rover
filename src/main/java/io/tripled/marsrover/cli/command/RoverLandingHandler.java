package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Heading;
import io.tripled.marsrover.service.rover.RoverState;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.Optional;

public final class RoverLandingHandler implements ActionHandler{

    private final SimulationRepository simulationRepository;

    private final MessagePrinter messagePrinter;

    private final InputParser inputParser;

    public RoverLandingHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
        this.messagePrinter = new MessagePrinter(simulationRepository);
        this.inputParser = new InputParser(simulationRepository);
    }

    public Message handleRoverLanding(String input) {

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
}
