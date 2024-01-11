package io.tripled.marsrover.data.simulation;

import io.tripled.marsrover.service.simulation.Simulation;
import io.tripled.marsrover.service.simulation.SimulationRepository;

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
