package io.tripled.marsrover.service.simulation;

import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Rover;
import io.tripled.marsrover.service.rover.RoverState;

public class Simulation {

    private final int simulationSize ;

    private final SimulationRepository simulationRepository;

    private final Rover rover1;

    public Simulation(int simulationSize, SimulationRepository simulationRepository) {
        this.simulationSize = simulationSize;
        this.simulationRepository = simulationRepository;
        this.rover1 = new Rover(simulationRepository);
    }

    public RoverState getRoverState() {
        return rover1.getRoverState();
    }
    public int getSimulationSize() {
        return simulationSize;
    }

    public Coordinate getRover1Coordinates(){
        return rover1.getRoverCoordinates();
    }

    public void setRover1State(RoverState roverState){
        rover1.setRoverState(roverState);
    }
    public Rover getRover1() {
        return rover1;
    }
}
