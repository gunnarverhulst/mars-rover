package io.tripled.marsrover.vocabulary.rover;

import io.tripled.marsrover.vocabulary.rover.Direction;

public record Move (Direction direction, int steps){}
