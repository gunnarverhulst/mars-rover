package io.tripled.marsrover.businesslogic.simulation;

import io.tripled.marsrover.businesslogic.rover.Rover;
import io.tripled.marsrover.businesslogic.rover.RoverState;

public class Simulation {

    private final int simulationSize ;
    private final Rover rover1;

    public Simulation(int simulationSize, SimulationRepository simulationRepository) {
        this.simulationSize = simulationSize;
        this.rover1 = new Rover(simulationRepository);
    }

    public RoverState getRoverState() {
        return rover1.getRoverState();
    }
    public int getSimulationSize() {
        return simulationSize;
    }


    public void setRover1State(RoverState roverState){
        rover1.setRoverState(roverState);
    }
    public Rover getRover1() {
        return rover1;
    }
}