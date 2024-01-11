package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.messages.RoverDrivingMessage;
import io.tripled.marsrover.service.rover.Move;
import io.tripled.marsrover.service.rover.Rover;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.List;

public final class RoverDrivingHandler implements ActionHandler {

    private final SimulationRepository simulationRepository;


    public RoverDrivingHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    public Message handleRoverDriving(List<Move> drivingMoves) {

        RoverDrivingMessage roverDrivingMessage = new RoverDrivingMessage();
        prepareRoverDrivingMessage(roverDrivingMessage);
        performRoverMoves(drivingMoves, roverDrivingMessage);

        endRoverDrivingMessage(roverDrivingMessage);
        return roverDrivingMessage;
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

}
