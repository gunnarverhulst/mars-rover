package io.tripled.marsrover.businesslogic.presenter;

import io.tripled.marsrover.businesslogic.rover.RoverState;

public interface MapPresenter extends Presenter{

    void mapMessage(int simulationSize, RoverState roverstate);

    void mapMessage(int simulationSize);
}
