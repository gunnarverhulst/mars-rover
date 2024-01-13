package io.tripled.marsrover.service.command;

import io.tripled.marsrover.service.rover.Coordinate;

public record LandCommand(Coordinate coordinate) implements CustomCommand {}
