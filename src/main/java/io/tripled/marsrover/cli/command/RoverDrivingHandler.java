package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.messages.RoverDrivingErrorMessage;
import io.tripled.marsrover.cli.message.messages.RoverDrivingMessage;
import io.tripled.marsrover.service.rover.Move;
import io.tripled.marsrover.service.rover.Rover;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.List;
import java.util.Optional;

public final class RoverDrivingHandler implements ActionHandler{

    private final SimulationRepository simulationRepository;


    public RoverDrivingHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    public Message handleRoverDriving(String preparedInput) {
        Optional<List<Move>> drivingMoves = buildDrivingMoves(preparedInput);

        RoverDrivingMessage roverDrivingMessage = new RoverDrivingMessage();
        prepareRoverDrivingMessage(roverDrivingMessage);

        if (drivingMoves.isPresent()) {
            performRoverMoves(drivingMoves.get(), roverDrivingMessage);

            endRoverDrivingMessage(roverDrivingMessage);
            return roverDrivingMessage;
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
