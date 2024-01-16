package io.tripled.marsrover.businesslogic.command;


public sealed interface Command permits CreateSimulationCommand, DriveCommand, LandCommand {
}