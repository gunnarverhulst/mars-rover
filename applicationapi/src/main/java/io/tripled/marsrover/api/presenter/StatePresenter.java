package io.tripled.marsrover.api.presenter;


import io.tripled.marsrover.vocabulary.rover.RoverState;

public interface StatePresenter extends Presenter {

    void stateMessage(int simulationSize, RoverState roverState);

    void stateErrorMessage(int simulationSize);
}
