package io.tripled.marsrover.businesslogic.simulation;

public interface SimulationRepository {
    void addSimulation(Simulation simulation);
    Simulation getSimulation();
}
