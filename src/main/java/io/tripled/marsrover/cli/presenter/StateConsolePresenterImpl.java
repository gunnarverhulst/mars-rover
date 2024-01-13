package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.service.message.messages.Message;
import io.tripled.marsrover.cli.message.messages.StateErrorMessage;
import io.tripled.marsrover.cli.message.messages.StateMessage;
import io.tripled.marsrover.service.presenter.StatePresenter;
import io.tripled.marsrover.service.rover.RoverState;

public class StateConsolePresenterImpl implements StatePresenter {
    @Override
    public Message stateMessage(int simulationSize, RoverState roverState) {
        return new StateMessage(simulationSize, roverState);
    }

    @Override
    public Message stateErrorMessage() {
        return new StateErrorMessage();
    }
}
