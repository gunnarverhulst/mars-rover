package io.tripled.marsrover.service.command;

import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.messages.RoverDrivingMessage;
import io.tripled.marsrover.service.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.service.rover.Move;
import io.tripled.marsrover.service.rover.Rover;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.List;

public final class RoverDrivingHandler implements CommandHandler<List<Move>, RoverDrivingPresenter> {

    private final SimulationRepository simulationRepository;


    public RoverDrivingHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    public Message execute(List<Move> drivingMoves){
        RoverDrivingMessage roverDrivingMessage = new RoverDrivingMessage();
        prepareRoverDrivingMessage(roverDrivingMessage);
        performRoverMoves(drivingMoves, roverDrivingMessage);

        endRoverDrivingMessage(roverDrivingMessage);
        return roverDrivingMessage;
    }

    private void prepareRoverDrivingMessage(RoverDrivingMessage drivingMessage) {
        drivingMessage.concat("Rover R1 received instructions\n");
    }

    private void performRoverMoves(List<Move> drivingMoves, RoverDrivingMessage drivingMessage) {
        Rover rover = simulationRepository.getSimulation().getRover1();
        drivingMoves.forEach(x -> drivingMessage.concat(rover.singleStepMove(x)));
    }
    private void endRoverDrivingMessage(RoverDrivingMessage drivingMessage) {
        drivingMessage.concat("Rover R1 executed all instructions. Awaiting new ones...\n");
    }

//    @Override
//    public void handle(List<Move> drivingMoves) {
//        RoverDrivingMessage roverDrivingMessage = new RoverDrivingMessage();
//        prepareRoverDrivingMessage(roverDrivingMessage);
//        performRoverMoves(drivingMoves, roverDrivingMessage);
//
//        endRoverDrivingMessage(roverDrivingMessage);
//        return roverDrivingMessage;
//    }

    @Override
    public Message handle(List<Move> moves, RoverDrivingPresenter roverDrivingPresenter) {
        return execute(moves);
    }
}
