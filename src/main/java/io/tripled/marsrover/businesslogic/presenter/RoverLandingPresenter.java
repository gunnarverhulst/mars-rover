package io.tripled.marsrover.businesslogic.presenter;

import io.tripled.marsrover.businesslogic.rover.Coordinate;

public interface RoverLandingPresenter extends Presenter {

    void roverLandedMessage(Coordinate coordinate);

    void roverLandingErrorMessage(Coordinate coordinate);

    void roverLandingEmptyCoordinateErrorMessage();
}
