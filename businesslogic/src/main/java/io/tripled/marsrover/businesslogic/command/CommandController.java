package io.tripled.marsrover.businesslogic.command;

import io.tripled.marsrover.api.command.*;
import io.tripled.marsrover.api.presenter.Presenter;
import io.tripled.marsrover.api.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.api.presenter.RoverLandingPresenter;
import io.tripled.marsrover.api.presenter.SimCreationPresenter;
import io.tripled.marsrover.businesslogic.command.commandhandler.CommandHandler;
import io.tripled.marsrover.businesslogic.command.commandhandler.CommandHandlerFactory;
import io.tripled.marsrover.businesslogic.rover.Rover;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;
import io.tripled.marsrover.vocabulary.message.MapData;
import io.tripled.marsrover.vocabulary.rover.Coordinate;
import io.tripled.marsrover.vocabulary.rover.Move;
import io.tripled.marsrover.vocabulary.rover.RoverState;
import io.tripled.marsrover.vocabulary.simulation.SimulationState;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Component
public class CommandController implements ApplicationService {

    private final SimulationRepository simulationRepository;

    public CommandController(SimulationRepository simulationRepository) {
        requireNonNull(simulationRepository);
        this.simulationRepository = simulationRepository;
    }

    @Override
    public SimulationState getSimulation() {
        return simulationRepository.getSimulation().getSimulationState();
    }
    @Override
    public void handleCommand(Command command, Presenter presenter) {
        switch (command) {
            case CreateSimulationCommand createSimulationCommand -> {
                CommandHandler<Integer, SimCreationPresenter> commandHandler = CommandHandlerFactory.COMMAND_HANDLER_FACTORY.createSimulationHandler(simulationRepository);
                commandHandler.handle(createSimulationCommand.simulationSize(), (SimCreationPresenter) presenter);
            }
            case LandCommand landCommand -> {
                CommandHandler<Coordinate, RoverLandingPresenter> commandHandler = CommandHandlerFactory.COMMAND_HANDLER_FACTORY.createRoverLandingHandler(simulationRepository);
                commandHandler.handle(landCommand.coordinate(), (RoverLandingPresenter) presenter);
            }
            case DriveCommand driveCommand -> {
                CommandHandler<List<Move>, RoverDrivingPresenter> commandHandler = CommandHandlerFactory.COMMAND_HANDLER_FACTORY.createRoverDrivingHandler(simulationRepository);
                commandHandler.handle(driveCommand.moves(), (RoverDrivingPresenter) presenter);
            }
        }
    }

    @Override
    public boolean hasSimulation() {
        return simulationRepository.getSimulation() != null;
    }


    @Override
    public boolean hasRoverState(int roverId) {
        return simulationRepository.getSimulation().getRoverState(roverId) != null;
    }

    @Override
    public RoverState getRoverSate(int roverId) {
        return simulationRepository.getSimulation().getRoverState(roverId);
    }

    @Override
    public int getSimulationSize() {
        return simulationRepository.getSimulation().getSimulationSize();
    }

    @Override
    public List<RoverState> createListOfRoverStates() {
        List<RoverState> roverStates = new ArrayList<>();
        for(Rover rover : simulationRepository.getSimulation().getRovers()){
            roverStates.add(rover.getRoverState());
        }
        return roverStates;
    }

    @Override
    public MapData generateMapData() {
        return new MapData(getSimulationSize(), createListOfRoverStates());
    }

    @Override
    public int numberOfRovers() {
        return simulationRepository.getSimulation().getNumberOfRovers();
    }

}
