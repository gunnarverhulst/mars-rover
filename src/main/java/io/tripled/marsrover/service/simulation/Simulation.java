package io.tripled.marsrover.service.simulation;

import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Rover;
import io.tripled.marsrover.service.rover.RoverState;

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
