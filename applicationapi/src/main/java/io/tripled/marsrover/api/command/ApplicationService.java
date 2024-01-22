package io.tripled.marsrover.api.command;

import io.tripled.marsrover.api.presenter.Presenter;
import io.tripled.marsrover.vocabulary.rover.RoverState;
import io.tripled.marsrover.vocabulary.simulation.SimulationState;
import org.springframework.stereotype.Component;


public interface ApplicationService {
    void handleCommand(Command command, Presenter presenter);

    SimulationState getSimulation();

    boolean hasSimulation();

    boolean hasRoverState();

    RoverState getRoverSate();

    int getSimulationSize();
}
