package io.tripled.marsrover.businesslogic.presenter;

import io.tripled.marsrover.businesslogic.rover.Coordinate;

public class DummyRoverLandingPresenter implements RoverLandingPresenter{

    private Coordinate coordinate;

    @Override
    public void roverLandedMessage(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate(){
        return coordinate;
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
