package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.message.messages.LandingMessage;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.service.businessinterface.RoverLandingPresenter;
import io.tripled.marsrover.service.rover.Coordinate;

public class RoverLandingPresenterImpl implements RoverLandingPresenter {
    @Override
    public Message roverLanded(Coordinate coordinate) {
        return new LandingMessage(coordinate);
    }
}
