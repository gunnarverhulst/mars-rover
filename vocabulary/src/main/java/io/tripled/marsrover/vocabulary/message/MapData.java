package io.tripled.marsrover.vocabulary.message;


import io.tripled.marsrover.vocabulary.rover.RoverState;

import java.util.List;

public record MapData(int simulationSize, List<RoverState> roverStates) {}
