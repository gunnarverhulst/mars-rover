package io.tripled.marsrover;

import io.tripled.marsrover.api.presenter.StatePresenter;
import io.tripled.marsrover.ui.cli.messages.StateMessage;
import io.tripled.marsrover.vocabulary.rover.RoverState;

public class StatePresenterImpl implements StatePresenter {
    @Override
    public void stateMessage(int simulationSize, RoverState roverState) {
        System.out.println(new StateMessage(simulationSize, roverState).messageToBePrinted());
    }

    @Override
    public void stateErrorMessage(int simulationSize) {

    }
}
