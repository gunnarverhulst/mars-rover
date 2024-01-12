package io.tripled.marsrover.service.command;

import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.service.businessinterface.Presenter;

public sealed interface ActionHandler<T,P extends Presenter> extends Presenter permits RoverDrivingHandler, RoverLandingHandler, SimCreationHandler {

    Message handle(T command, P p);

}
