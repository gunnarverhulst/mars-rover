package io.tripled.marsrover.ui.cli.presenter;

import io.tripled.marsrover.api.message.RoverDrivingMessage;
import io.tripled.marsrover.api.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.vocabulary.rover.Move;

import java.util.List;

public class RoverDrivingConsolePresenterImpl implements RoverDrivingPresenter {
    @Override
    public void roverDriving(List<Move> drivingMoves, RoverDrivingMessage roverDrivingMessage) {
        System.out.println(roverDrivingMessage.messageToBePrinted());
    }
}
