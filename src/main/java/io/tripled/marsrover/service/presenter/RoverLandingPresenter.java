package io.tripled.marsrover.service.presenter;

import io.tripled.marsrover.service.message.messages.Message;
import io.tripled.marsrover.service.rover.Coordinate;

public interface RoverLandingPresenter extends Presenter {

    Message roverLandedMessage(Coordinate coordinate);

    Message roverLandingErrorMessage(Coordinate coordinate);

    Message roverLandingEmptyCoordinateErrorMessage();
}
