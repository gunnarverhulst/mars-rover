package io.tripled.marsrover;

import io.tripled.marsrover.api.presenter.RoverLandingPresenter;
import io.tripled.marsrover.vocabulary.rover.Coordinate;

public class RoverLandingPresenterImpl implements RoverLandingPresenter {
    @Override
    public void roverLandedMessage(String roverId, Coordinate coordinate) {
        System.out.println("the rover " + roverId + " has landed on [" + coordinate.x() + "-" + coordinate.y() + "]");
    }

    @Override
    public void roverLandingErrorMessage(Coordinate coordinate) {

    }

    @Override
    public void roverLandingEmptyCoordinateErrorMessage() {

    }

    @Override
    public void roverLandingErrorOnlyXMessage(String xCoordinate) {

    }

    @Override
    public void roverLandingErrorXYNotANumberMessage(String xCoordinate, String yCoordinate) {

    }

    @Override
    public void roverLandingErrorOnlyXNegativeMessage(String xCoordinate) {

    }

    @Override
    public void roverLandingErrorXAndOrYNegativeMessage(String xCoordinate, String yCoordinate) {

    }

    @Override
    public void roverLandingErrorOutOfBounds(int xCoordinate, int yCoordinate, int simulationSize) {

    }
}
