package io.tripled.marsrover;

import io.tripled.marsrover.api.message.RoverDrivingMessage;
import io.tripled.marsrover.api.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.vocabulary.rover.Move;

import java.util.List;

public class RoverDrivingPresenterImpl implements RoverDrivingPresenter {

    @Override
    public void roverDriving(List<Move> drivingMoves, RoverDrivingMessage roverDrivingMessage) {
        System.out.println("Rover R1 moved in direction " + drivingMoves.getFirst().direction() + " for " + drivingMoves.getFirst().steps() + "steps");

    }
}