package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.message.messages.LandingMessage;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.messages.RoverLandingErrorEmptyCoordinateMessage;
import io.tripled.marsrover.cli.message.messages.RoverLandingErrorMessage;
import io.tripled.marsrover.service.presenter.RoverLandingPresenter;
import io.tripled.marsrover.service.rover.Coordinate;

public class RoverLandingConsolePresenterImpl implements RoverLandingPresenter {
    @Override
    public Message roverLandedMessage(Coordinate coordinate) {
        return new LandingMessage(coordinate);
    }

    @Override
    public Message roverLandingErrorMessage(Coordinate coordinate) {
        return new RoverLandingErrorMessage(coordinate);
    }

    @Override
    public Message roverLandingEmptyCoordinateErrorMessage() {
        return new RoverLandingErrorEmptyCoordinateMessage();
    }
}
