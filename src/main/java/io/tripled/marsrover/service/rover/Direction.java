package io.tripled.marsrover.service.rover;

public enum Direction {
    FORWARD,
    LEFT,
    RIGHT,
    BACKWARD,
    UNKNOWN_DIRECTION,
    DIRECTION;

    public Direction parseDirection(String directionAsText){
        return switch (directionAsText){
            case "f", "forward" -> FORWARD;
            case "l", "left" -> LEFT;
            case "r", "right" -> RIGHT;
            case "b", "backward" -> BACKWARD;
            default -> UNKNOWN_DIRECTION;
        };
    }

    public String parseDirectionAsText(){

        return switch (this){
            case LEFT -> "left";
            case RIGHT -> "right";
            case BACKWARD -> "backward";
            default -> "forward";
        };
    }
}
