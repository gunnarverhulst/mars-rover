package io.tripled.marsrover.service.command;

public sealed interface Command permits CreateSimulationCommand, DriveCommand, LandCommand {
}