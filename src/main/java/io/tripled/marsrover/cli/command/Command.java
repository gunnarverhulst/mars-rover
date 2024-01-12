package io.tripled.marsrover.cli.command;

public record Command<T>(CommandType commandType, T commandData) { }
