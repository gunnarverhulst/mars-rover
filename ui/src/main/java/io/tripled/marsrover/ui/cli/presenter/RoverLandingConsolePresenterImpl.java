package io.tripled.marsrover.ui.cli.presenter;

import io.tripled.marsrover.api.presenter.RoverLandingPresenter;
import io.tripled.marsrover.ui.cli.messages.*;
import io.tripled.marsrover.vocabulary.rover.Coordinate;

public class RoverLandingConsolePresenterImpl implements RoverLandingPresenter {
    @Override
    public void roverLandedMessage(String roverId, Coordinate coordinate) {
        System.out.println(new RoverLandingMessage(roverId, coordinate).messageToBePrinted());
    }

    @Override
    public void roverLandingErrorMessage(Coordinate coordinate) {
        System.out.println(new RoverLandingErrorMessage(coordinate).messageToBePrinted());
    }

    @Override
    public void roverLandingEmptyCoordinateErrorMessage() {
        System.out.println(new RoverLandingErrorEmptyCoordinateMessage().messageToBePrinted());
    }

    @Override
    public void roverLandingErrorOnlyXMessage(String xCoordinate) {
        System.out.println(new RoverLandingErrorOnlyXMessage(xCoordinate).messageToBePrinted());
    }

    @Override
    public void roverLandingErrorXYNotANumberMessage(String xCoordinate, String yCoordinate) {
        System.out.println(new RoverLandingErrorXYNotANumberMessage(xCoordinate, yCoordinate).messageToBePrinted());
    }

    @Override
    public void roverLandingErrorOnlyXNegativeMessage(String xCoordinate) {
        System.out.println(new RoverLandingErrorOnlyXNegativeMessage(xCoordinate).messageToBePrinted());
    }

    @Override
    public void roverLandingErrorXAndOrYNegativeMessage(String xCoordinate, String yCoordinate) {
        System.out.println(new RoverLandingErrorXAndOrYNegativeMessage(xCoordinate, yCoordinate).messageToBePrinted());
    }

    @Override
    public void roverLandingErrorOutOfBounds(int xCoordinate, int yCoordinate, int simulationSize) {
        System.out.println(new RoverLandingErrorOutOfBoundsMessage(xCoordinate, yCoordinate, simulationSize).messageToBePrinted());
    }
}
