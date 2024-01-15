package io.tripled.marsrover.businesslogic.message;

import io.tripled.marsrover.businesslogic.rover.RoverState;

public record MapData(int simulationSize, RoverState roverState) {}
