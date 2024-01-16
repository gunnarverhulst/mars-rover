package io.tripled.marsrover.businesslogic.command.commandhandler;

import io.tripled.marsrover.businesslogic.presenter.Presenter;

public sealed interface CommandHandler<T,P extends Presenter> permits RoverDrivingHandler, RoverLandingHandler, SimCreationHandler {

    void handle(T command, P p);

}
