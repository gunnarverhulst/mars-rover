package io.tripled.marsrover.businesslogic.presenter;

import io.tripled.marsrover.ui.cli.messages.RoverDrivingMessage;
import io.tripled.marsrover.businesslogic.rover.Move;

import java.util.List;

public interface RoverDrivingPresenter extends Presenter {

    void roverDriving(List<Move> drivingMoves, RoverDrivingMessage roverDrivingMessage);
}
