package io.tripled.marsrover.businesslogic.presenter;

import io.tripled.marsrover.businesslogic.rover.RoverState;

public interface StatePresenter extends Presenter{

    void stateMessage(int simulationSize, RoverState roverState);

    void stateErrorMessage(int simulationSize);
}
