package io.tripled.marsrover.data.simulation;

import io.tripled.marsrover.businesslogic.simulation.Simulation;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;

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
