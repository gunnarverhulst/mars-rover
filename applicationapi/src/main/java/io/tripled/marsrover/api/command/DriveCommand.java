package io.tripled.marsrover.api.command;


import io.tripled.marsrover.vocabulary.rover.Move;

import java.util.List;

public record DriveCommand(List<Move> moves) implements Command { }
