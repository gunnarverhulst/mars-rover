package io.tripled.marsrover.service.command;

public sealed interface ActionHandler permits RoverDrivingHandler, RoverLandingHandler, SimCreationHandler {



}
