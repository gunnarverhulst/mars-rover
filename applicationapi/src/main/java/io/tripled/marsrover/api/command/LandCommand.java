package io.tripled.marsrover.api.command;


import io.tripled.marsrover.vocabulary.rover.Coordinate;

public record LandCommand(Coordinate coordinate) implements Command {}
