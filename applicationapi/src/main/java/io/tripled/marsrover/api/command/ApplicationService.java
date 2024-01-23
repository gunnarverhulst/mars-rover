package io.tripled.marsrover.api.command;

import io.tripled.marsrover.api.presenter.Presenter;
import io.tripled.marsrover.vocabulary.message.MapData;
import io.tripled.marsrover.vocabulary.rover.RoverState;
import io.tripled.marsrover.vocabulary.simulation.SimulationState;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ApplicationService {
    void handleCommand(Command command, Presenter presenter);

    SimulationState getSimulation();

    boolean hasSimulation();

    boolean hasRoverState(int roverId);

    RoverState getRoverSate(int roverId);

    int getSimulationSize();

    List<RoverState> createListOfRoverStates();

    MapData generateMapData();

    int numberOfRovers();
}
