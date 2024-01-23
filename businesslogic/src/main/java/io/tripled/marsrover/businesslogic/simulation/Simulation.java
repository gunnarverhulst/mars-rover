package io.tripled.marsrover.businesslogic.simulation;

import io.tripled.marsrover.businesslogic.rover.Rover;
import io.tripled.marsrover.vocabulary.rover.Coordinate;
import io.tripled.marsrover.vocabulary.rover.Heading;
import io.tripled.marsrover.vocabulary.rover.RoverState;
import io.tripled.marsrover.vocabulary.simulation.SimulationState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Simulation {

    private final int simulationSize ;

    private static int roverCount = 0;

    private final List<Rover> rovers;

    public Simulation(int simulationSize) {
        this.simulationSize = simulationSize;
        rovers = new ArrayList<>();
    }

    public RoverState getRoverState(int roverId){
        return rovers.get(roverId).getRoverState();
    }
    public int getSimulationSize() {
        return simulationSize;
    }

    public Rover getRover(int id){

        return rovers.get(id);
    }

    public SimulationState getSimulationState() {
        return null;
    }

    public int getNumberOfRovers() {
        return rovers.size();
    }
    public List<Rover> getRovers() {
        return rovers;
    }

    public String getRoverId(int roverId) {
        return rovers.get(roverId).getId();
    }

    public String getNewestRoverId() {
        return rovers.getLast().getId();

    }

    public void addNewRover(Coordinate coordinate) {
        roverCount++;
        rovers.add(new Rover(simulationSize, roverCount, new RoverState(""+roverCount, coordinate, Heading.NORTH)));
    }

    public void resetCounter(){
        roverCount = 0;
    }
}
