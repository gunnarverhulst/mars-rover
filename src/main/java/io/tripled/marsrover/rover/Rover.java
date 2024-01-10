package io.tripled.marsrover.rover;

public class Rover {

    private RoverState roverState = null;

    public void setRoverState(RoverState roverState) {
        this.roverState = roverState;
    }

    public RoverState getRoverState() {
        return roverState;
    }

    public Coordinate getRoverCoordinates() {
        return roverState.roverCoordinate();
    }
}
