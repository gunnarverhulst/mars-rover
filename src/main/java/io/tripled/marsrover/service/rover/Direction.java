package io.tripled.marsrover.service.rover;

public enum Direction {
    FORWARD,
    LEFT,
    RIGHT,
    BACKWARD,
    UNKNOWN_DIRECTION,
    DIRECTION;

    public Direction parseDirection(String directionString){
        return switch (directionString){
            case "f" -> FORWARD;
            case "l" -> LEFT;
            case "r" -> RIGHT;
            case "b" -> BACKWARD;
            default -> UNKNOWN_DIRECTION;
        };
    }
}
