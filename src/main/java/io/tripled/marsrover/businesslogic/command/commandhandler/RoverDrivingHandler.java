package io.tripled.marsrover.businesslogic.command.commandhandler;

import io.tripled.marsrover.ui.cli.messages.RoverDrivingMessage;
import io.tripled.marsrover.businesslogic.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.businesslogic.rover.Move;
import io.tripled.marsrover.businesslogic.rover.Rover;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;

import java.util.List;

public final class RoverDrivingHandler implements CommandHandler<List<Move>, RoverDrivingPresenter> {

    private final SimulationRepository simulationRepository;


    public RoverDrivingHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    @Override
    public void handle(List<Move> drivingMoves, RoverDrivingPresenter roverDrivingPresenter) {

        RoverDrivingMessage roverDrivingMessage = new RoverDrivingMessage();
        prepareRoverDrivingMessage(roverDrivingMessage);
        performRoverMoves(drivingMoves, roverDrivingMessage);

        endRoverDrivingMessage(roverDrivingMessage);

        roverDrivingPresenter.roverDriving(drivingMoves, roverDrivingMessage);
    }

    private void prepareRoverDrivingMessage(RoverDrivingMessage drivingMessage) {
        drivingMessage.concat("Rover R1 received instructions\n");
    }

    private void performRoverMoves(List<Move> drivingMoves, RoverDrivingMessage drivingMessage) {
        Rover rover = simulationRepository.getSimulation().getRover1();
        drivingMoves.forEach(x -> drivingMessage.concat(rover.moveRover(x).messageToBePrinted()));
    }
    private void endRoverDrivingMessage(RoverDrivingMessage drivingMessage) {
        drivingMessage.concat("Rover R1 executed all instructions. Awaiting new ones...\n\n[Please enter a command] : ");
    }

}