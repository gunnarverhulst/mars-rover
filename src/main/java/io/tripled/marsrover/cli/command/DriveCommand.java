package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.service.rover.Move;

import java.util.List;

public record DriveCommand(List<Move> moves) implements CustomCommand { }
