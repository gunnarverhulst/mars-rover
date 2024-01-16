package io.tripled.marsrover.api.presenter;


import io.tripled.marsrover.api.message.RoverDrivingMessage;
import io.tripled.marsrover.vocabulary.rover.Move;

import java.util.List;

public interface RoverDrivingPresenter extends Presenter {

    void roverDriving(List<Move> drivingMoves, RoverDrivingMessage roverDrivingMessage);
}
