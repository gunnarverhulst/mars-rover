package io.tripled.marsrover.ui.cli.presenter;

import io.tripled.marsrover.businesslogic.message.RoverDrivingMessage;
import io.tripled.marsrover.businesslogic.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.businesslogic.rover.Move;

import java.util.List;

public class RoverDrivingConsolePresenterImpl implements RoverDrivingPresenter {
    @Override
    public void roverDriving(List<Move> drivingMoves, RoverDrivingMessage roverDrivingMessage) {
        System.out.println(roverDrivingMessage.messageToBePrinted());
    }
}
