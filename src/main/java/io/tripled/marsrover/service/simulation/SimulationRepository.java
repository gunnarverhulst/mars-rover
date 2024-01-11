package io.tripled.marsrover.service.simulation;

public interface SimulationRepository {
    void addSimulation(Simulation simulation);
    Simulation getSimulation();
}
