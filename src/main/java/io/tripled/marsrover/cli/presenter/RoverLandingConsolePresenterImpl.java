package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.messages.RoverLandingErrorEmptyCoordinateMessage;
import io.tripled.marsrover.cli.messages.RoverLandingErrorMessage;
import io.tripled.marsrover.cli.messages.RoverLandingMessage;
import io.tripled.marsrover.service.presenter.RoverLandingPresenter;
import io.tripled.marsrover.service.rover.Coordinate;

public class RoverLandingConsolePresenterImpl implements RoverLandingPresenter {
    @Override
    public void roverLandedMessage(Coordinate coordinate) {
        System.out.println(new RoverLandingMessage(coordinate).messageToBePrinted());
    }

    @Override
    public void roverLandingErrorMessage(Coordinate coordinate) {
        System.out.println(new RoverLandingErrorMessage(coordinate).messageToBePrinted());
    }

    @Override
    public void roverLandingEmptyCoordinateErrorMessage() {
        System.out.println(new RoverLandingErrorEmptyCoordinateMessage().messageToBePrinted());
    }
}
