package io.tripled.marsrover.service.presenter;

import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.service.rover.RoverState;

public interface StatePresenter extends Presenter{

    Message stateMessage(int simulationSize, RoverState roverState);

    Message stateErrorMessage();
}
