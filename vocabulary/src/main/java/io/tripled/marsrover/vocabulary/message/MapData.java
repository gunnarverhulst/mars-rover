package io.tripled.marsrover.vocabulary.message;


import io.tripled.marsrover.vocabulary.rover.RoverState;

public record MapData(int simulationSize, RoverState roverState) {}
