package io.tripled.marsrover.businesslogic.command;

import io.tripled.marsrover.businesslogic.rover.Move;

import java.util.List;

public record DriveCommand(List<Move> moves) implements Command { }
