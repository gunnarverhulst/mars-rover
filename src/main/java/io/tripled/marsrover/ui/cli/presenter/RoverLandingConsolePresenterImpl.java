package io.tripled.marsrover.ui.cli.presenter;

import io.tripled.marsrover.ui.cli.messages.RoverLandingErrorEmptyCoordinateMessage;
import io.tripled.marsrover.ui.cli.messages.RoverLandingErrorMessage;
import io.tripled.marsrover.ui.cli.messages.RoverLandingMessage;
import io.tripled.marsrover.businesslogic.presenter.RoverLandingPresenter;
import io.tripled.marsrover.businesslogic.rover.Coordinate;

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
