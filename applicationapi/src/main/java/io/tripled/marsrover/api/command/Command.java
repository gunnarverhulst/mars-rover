package io.tripled.marsrover.api.command;


public sealed interface Command permits CreateSimulationCommand, DriveCommand, LandCommand {
}