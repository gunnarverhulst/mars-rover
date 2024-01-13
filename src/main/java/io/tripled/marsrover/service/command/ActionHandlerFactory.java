package io.tripled.marsrover.service.command;

import io.tripled.marsrover.service.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.service.presenter.RoverLandingPresenter;
import io.tripled.marsrover.service.presenter.SimConsolePresenter;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Move;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.List;

public enum ActionHandlerFactory {
    ACTION_HANDLER_FACTORY;

    public ActionHandler<Integer, SimConsolePresenter> createSimulationHandler(SimulationRepository simulationRepository){
        return new SimCreationHandler(simulationRepository);
    }

    public ActionHandler<Coordinate, RoverLandingPresenter> createRoverLandingHandler(SimulationRepository simulationRepository){

        return new RoverLandingHandler(simulationRepository);
    }

    public ActionHandler<List<Move>, RoverDrivingPresenter> createRoverDrivingHandler(SimulationRepository simulationRepository){
        return new RoverDrivingHandler(simulationRepository);
    }

}
