package io.tripled.marsrover.service.command;

import io.tripled.marsrover.service.rover.Move;

import java.util.List;

public record DriveCommand(List<Move> moves) implements Command { }
