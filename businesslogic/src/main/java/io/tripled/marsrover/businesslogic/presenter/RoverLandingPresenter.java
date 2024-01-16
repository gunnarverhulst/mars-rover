package io.tripled.marsrover.businesslogic.presenter;


import io.tripled.marsrover.vocabulary.rover.Coordinate;

public interface RoverLandingPresenter {

    void roverLandedMessage(Coordinate coordinate);

    void roverLandingErrorMessage(Coordinate coordinate);

    void roverLandingEmptyCoordinateErrorMessage();

    void roverLandingErrorOnlyXMessage(String xCoordinate);

    void roverLandingErrorXYNotANumberMessage(String xCoordinate, String yCoordinate);

    void roverLandingErrorOnlyXNegativeMessage(String xCoordinate);

    void roverLandingErrorXAndOrYNegativeMessage(String xCoordinate, String yCoordinate);

    void roverLandingErrorOutOfBounds(int xCoordinate, int yCoordinate, int simulationSize);
}
