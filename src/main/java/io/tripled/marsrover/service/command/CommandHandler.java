package io.tripled.marsrover.service.command;

import io.tripled.marsrover.service.message.messages.Message;
import io.tripled.marsrover.service.presenter.Presenter;

public sealed interface CommandHandler<T,P extends Presenter> extends Presenter permits RoverDrivingHandler, RoverLandingHandler, SimCreationHandler {

    Message handle(T command, P p);

}
