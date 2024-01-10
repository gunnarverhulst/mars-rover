package io.tripled.marsrover.simulation;

import io.tripled.marsrover.rover.Coordinate;
import io.tripled.marsrover.rover.Rover;
import io.tripled.marsrover.rover.RoverState;

public class Simulation {

    private int simulationSize = 0;

    private final Rover rover1 = new Rover();

    public RoverState getRoverState() {
        return rover1.getRoverState();
    }
    public int getSimulationSize() {
        return simulationSize;
    }
    public void setSimulationSize(int simulationSize) {
        this.simulationSize = simulationSize;
    }

    public Coordinate getRover1Coordinates(){
        return rover1.getRoverCoordinates();
    }

    public void setRover1State(RoverState roverState){
        rover1.setRoverState(roverState);
    }
}
