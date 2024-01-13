package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.messages.RoverLandingMessage;
import io.tripled.marsrover.service.message.messages.Message;
import io.tripled.marsrover.cli.messages.RoverLandingErrorEmptyCoordinateMessage;
import io.tripled.marsrover.cli.messages.RoverLandingErrorMessage;
import io.tripled.marsrover.service.presenter.RoverLandingPresenter;
import io.tripled.marsrover.service.rover.Coordinate;

public class RoverLandingConsolePresenterImpl implements RoverLandingPresenter {
    @Override
    public Message roverLandedMessage(Coordinate coordinate) {
        System.out.println(new RoverLandingMessage(coordinate).messageToBePrinted());
        return new RoverLandingMessage(coordinate);
    }

    @Override
    public Message roverLandingErrorMessage(Coordinate coordinate) {
        System.out.println(new RoverLandingErrorMessage(coordinate).messageToBePrinted());
        return new RoverLandingErrorMessage(coordinate);
    }

    @Override
    public Message roverLandingEmptyCoordinateErrorMessage() {
        System.out.println(new RoverLandingErrorEmptyCoordinateMessage().messageToBePrinted());
        return new RoverLandingErrorEmptyCoordinateMessage();
    }
}
