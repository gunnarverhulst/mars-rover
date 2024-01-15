package io.tripled.marsrover.businesslogic.rover;

public enum Direction {
    FORWARD,
    LEFT,
    RIGHT,
    BACKWARD,
    DIRECTION;

    public Direction parseDirection(String directionAsText){
        return switch (directionAsText){
            case "f", "forward" -> FORWARD;
            case "l", "left" -> LEFT;
            case "r", "right" -> RIGHT;
            case "b", "backward" -> BACKWARD;
            default -> FORWARD;
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
