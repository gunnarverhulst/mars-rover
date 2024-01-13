package io.tripled.marsrover.service.command;

public sealed interface CustomCommand permits CreateSimulationCommand, DriveCommand, LandCommand {
}