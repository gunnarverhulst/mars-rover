package io.tripled.marsrover.businesslogic.command.commandhandler;


public sealed interface CommandHandler<T,P> permits RoverDrivingHandler, RoverLandingHandler, SimCreationHandler {

    void handle(T command, P p);

}
