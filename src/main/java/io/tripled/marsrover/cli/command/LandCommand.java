package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.service.rover.Coordinate;

public record LandCommand(Coordinate coordinate) implements CustomCommand {}
