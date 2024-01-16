package io.tripled.marsrover.businesslogic.simulation;

import org.springframework.stereotype.Repository;

@Repository
public interface SimulationRepository {
    void addSimulation(Simulation simulation);
    Simulation getSimulation();
}
