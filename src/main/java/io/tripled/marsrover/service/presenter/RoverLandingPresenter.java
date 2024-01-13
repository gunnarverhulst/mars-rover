package io.tripled.marsrover.service.presenter;

import io.tripled.marsrover.service.rover.Coordinate;

public interface RoverLandingPresenter extends Presenter {

    void roverLandedMessage(Coordinate coordinate);

    void roverLandingErrorMessage(Coordinate coordinate);

    void roverLandingEmptyCoordinateErrorMessage();
}
