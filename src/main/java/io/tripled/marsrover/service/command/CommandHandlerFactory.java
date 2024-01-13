package io.tripled.marsrover.service.command;

import io.tripled.marsrover.service.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.service.presenter.RoverLandingPresenter;
import io.tripled.marsrover.service.presenter.SimConsolePresenter;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Move;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.List;

public enum CommandHandlerFactory {
    ACTION_HANDLER_FACTORY;

    public CommandHandler<Integer, SimConsolePresenter> createSimulationHandler(SimulationRepository simulationRepository){
        return new SimCreationHandler(simulationRepository);
    }

    public CommandHandler<Coordinate, RoverLandingPresenter> createRoverLandingHandler(SimulationRepository simulationRepository){
        return new RoverLandingHandler(simulationRepository);
    }

    public CommandHandler<List<Move>, RoverDrivingPresenter> createRoverDrivingHandler(SimulationRepository simulationRepository){
        return new RoverDrivingHandler(simulationRepository);
    }

}
