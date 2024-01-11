package io.tripled.marsrover.cli.command;

public sealed interface ActionHandler permits RoverDrivingHandler, RoverLandingHandler, SimCreationHandler {
}
