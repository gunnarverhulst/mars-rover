package io.tripled.marsrover.cli.command;

public sealed interface CustomCommand permits CreateSimulationCommand, DriveCommand, LandCommand {
}