package io.tripled.marsrover.businesslogic.presenter;


import io.tripled.marsrover.api.message.RoverDrivingMessage;
import io.tripled.marsrover.vocabulary.rover.Move;

import java.util.List;

public interface RoverDrivingPresenter {

    void roverDriving(List<Move> drivingMoves, RoverDrivingMessage roverDrivingMessage);
}
