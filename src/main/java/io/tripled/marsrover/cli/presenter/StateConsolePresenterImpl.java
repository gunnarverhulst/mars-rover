package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.service.message.messages.Message;
import io.tripled.marsrover.cli.messages.StateErrorMessage;
import io.tripled.marsrover.cli.messages.StateMessage;
import io.tripled.marsrover.service.presenter.StatePresenter;
import io.tripled.marsrover.service.rover.RoverState;

public class StateConsolePresenterImpl implements StatePresenter {
    @Override
    public Message stateMessage(int simulationSize, RoverState roverState) {
        System.out.println(new StateMessage(simulationSize, roverState).messageToBePrinted());
        return new StateMessage(simulationSize, roverState);
    }

    @Override
    public Message stateErrorMessage() {
        System.out.println(new StateErrorMessage().messageToBePrinted());
        return new StateErrorMessage();
    }
}
