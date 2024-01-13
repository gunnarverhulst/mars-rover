package io.tripled.marsrover.businesslogic.command;

import io.tripled.marsrover.businesslogic.rover.Coordinate;

public record LandCommand(Coordinate coordinate) implements Command {}
