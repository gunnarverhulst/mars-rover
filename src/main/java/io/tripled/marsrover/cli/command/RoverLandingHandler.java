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

    public RoverLandingHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
        this.messagePrinter = new MessagePrinter(simulationRepository);
    }

    public Message handleRoverLanding(Coordinate coordinate) {

        if (simulationRepository.getSimulation().getRoverState() == null) {
            setRoverState(coordinate);
            return messagePrinter.landingMessage();
        }
        return messagePrinter.landingErrorMessage();
    }


    private void setRoverState(Coordinate parsedInput) {
        simulationRepository.getSimulation().setRover1State(new RoverState(parsedInput, Heading.NORTH));
    }
}
