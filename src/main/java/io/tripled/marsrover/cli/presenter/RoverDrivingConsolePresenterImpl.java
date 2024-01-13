package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.service.message.messages.Message;
import io.tripled.marsrover.cli.messages.RoverDrivingMessage;
import io.tripled.marsrover.service.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.service.rover.Move;

import java.util.List;

public class RoverDrivingConsolePresenterImpl implements RoverDrivingPresenter {
    @Override
    public Message roverDriving(List<Move> drivingMoves, RoverDrivingMessage roverDrivingMessage) {
        System.out.println(roverDrivingMessage.messageToBePrinted());
        return roverDrivingMessage;
    }
}
