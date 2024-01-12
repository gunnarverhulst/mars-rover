package io.tripled.marsrover.service.command;

import io.tripled.marsrover.service.businessinterface.RoverDrivingPresenter;
import io.tripled.marsrover.service.businessinterface.RoverLandingPresenter;
import io.tripled.marsrover.service.businessinterface.SimCreationPresenter;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Move;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.List;

public enum ActionHandlerFactory {
    ACTION_HANDLER_FACTORY;

    public ActionHandler<Integer, SimCreationPresenter> createSimulationHandler(SimulationRepository simulationRepository){
        return new SimCreationHandler(simulationRepository);
    }

    ActionHandler<Coordinate, RoverLandingPresenter> createRoverLandingHandler(){
        return null;
    }

    ActionHandler<List<Move>, RoverDrivingPresenter> createRoverDrivingHandler(){
        return null;
    }

}
