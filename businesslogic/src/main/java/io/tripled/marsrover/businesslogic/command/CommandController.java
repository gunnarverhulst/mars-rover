package io.tripled.marsrover.businesslogic.command;

import io.tripled.marsrover.businesslogic.command.commandhandler.CommandHandler;
import io.tripled.marsrover.businesslogic.presenter.Presenter;
import io.tripled.marsrover.businesslogic.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.businesslogic.presenter.RoverLandingPresenter;
import io.tripled.marsrover.businesslogic.presenter.SimCreationPresenter;
import io.tripled.marsrover.businesslogic.repository.InMemorySimulationRepository;
import io.tripled.marsrover.businesslogic.rover.Coordinate;
import io.tripled.marsrover.businesslogic.rover.Move;
import io.tripled.marsrover.businesslogic.rover.RoverState;
import io.tripled.marsrover.businesslogic.simulation.Simulation;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;

import java.util.List;

import static io.tripled.marsrover.businesslogic.command.commandhandler.CommandHandlerFactory.COMMAND_HANDLER_FACTORY;

public class CommandController {

    private final SimulationRepository simulationRepository;

    public CommandController() {
        this.simulationRepository = new InMemorySimulationRepository();
    }

    public Simulation getSimulation() {
        return simulationRepository.getSimulation();
    }
    public void handleCommand(Command command, Presenter presenter) {
        switch (command) {
            case CreateSimulationCommand createSimulationCommand -> {
                CommandHandler<Integer, SimCreationPresenter> commandHandler = COMMAND_HANDLER_FACTORY.createSimulationHandler(simulationRepository);
                commandHandler.handle(createSimulationCommand.simulationSize(), (SimCreationPresenter) presenter);
            }
            case LandCommand landCommand -> {
                CommandHandler<Coordinate, RoverLandingPresenter> commandHandler = COMMAND_HANDLER_FACTORY.createRoverLandingHandler(simulationRepository);
                commandHandler.handle(landCommand.coordinate(), (RoverLandingPresenter) presenter);
            }
            case DriveCommand driveCommand -> {
                CommandHandler<List<Move>, RoverDrivingPresenter> commandHandler = COMMAND_HANDLER_FACTORY.createRoverDrivingHandler(simulationRepository);
                commandHandler.handle(driveCommand.moves(), (RoverDrivingPresenter) presenter);
            }
        }
    }

    public boolean hasSimulation() {
        return simulationRepository.getSimulation() != null;
    }

    public boolean hasRoverState() {
        return simulationRepository.getSimulation().getRoverState() != null;
    }

    public RoverState getRoverSate() {
        return simulationRepository.getSimulation().getRoverState();
    }

    public int getSimulationSize() {
        return simulationRepository.getSimulation().getSimulationSize();
    }
}
