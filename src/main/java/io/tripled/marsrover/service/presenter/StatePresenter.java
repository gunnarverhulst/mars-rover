package io.tripled.marsrover.service.presenter;

import io.tripled.marsrover.service.rover.RoverState;

public interface StatePresenter extends Presenter{

    void stateMessage(int simulationSize, RoverState roverState);

    void stateErrorMessage();
}
