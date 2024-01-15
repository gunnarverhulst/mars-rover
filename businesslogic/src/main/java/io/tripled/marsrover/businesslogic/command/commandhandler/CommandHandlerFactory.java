package io.tripled.marsrover.businesslogic.command.commandhandler;

import io.tripled.marsrover.businesslogic.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.businesslogic.presenter.RoverLandingPresenter;
import io.tripled.marsrover.businesslogic.presenter.SimCreationPresenter;
import io.tripled.marsrover.businesslogic.rover.Coordinate;
import io.tripled.marsrover.businesslogic.rover.Move;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;

import java.util.List;

public enum CommandHandlerFactory {
    COMMAND_HANDLER_FACTORY;

    public CommandHandler<Integer, SimCreationPresenter> createSimulationHandler(SimulationRepository simulationRepository){
        return new SimCreationHandler(simulationRepository);
    }

    public CommandHandler<Coordinate, RoverLandingPresenter> createRoverLandingHandler(SimulationRepository simulationRepository){
        return new RoverLandingHandler(simulationRepository);
    }

    public CommandHandler<List<Move>, RoverDrivingPresenter> createRoverDrivingHandler(SimulationRepository simulationRepository){
        return new RoverDrivingHandler(simulationRepository);
    }

}
