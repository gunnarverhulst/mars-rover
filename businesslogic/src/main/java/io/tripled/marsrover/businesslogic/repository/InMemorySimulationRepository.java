package io.tripled.marsrover.businesslogic.repository;

import io.tripled.marsrover.businesslogic.simulation.Simulation;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemorySimulationRepository implements SimulationRepository {

    private Simulation simulation;
    @Override
    public void addSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public Simulation getSimulation() {
        return simulation;
    }
}
