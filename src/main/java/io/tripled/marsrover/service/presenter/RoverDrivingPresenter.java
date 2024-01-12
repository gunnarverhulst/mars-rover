package io.tripled.marsrover.service.presenter;

import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.service.rover.Move;

import java.util.List;

public interface RoverDrivingPresenter extends Presenter {

    Message roverDriving(List<Move> drivingMoves);
}
