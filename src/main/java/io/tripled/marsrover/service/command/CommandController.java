package io.tripled.marsrover.service.command;

import io.tripled.marsrover.service.message.messages.Message;
import io.tripled.marsrover.service.presenter.Presenter;
import io.tripled.marsrover.service.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.service.presenter.RoverLandingPresenter;
import io.tripled.marsrover.service.presenter.SimConsolePresenter;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Move;
import io.tripled.marsrover.service.simulation.Simulation;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.List;

import static io.tripled.marsrover.service.command.CommandHandlerFactory.ACTION_HANDLER_FACTORY;

public class CommandController {

    private final SimulationRepository simulationRepository;

    public CommandController(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    public Simulation getSimulation() {
        return simulationRepository.getSimulation();
    }
    public Message handleCommand(CustomCommand command, Presenter presenter) {
        switch (command) {
            case CreateSimulationCommand createSimulationCommand -> {
                CommandHandler<Integer, SimConsolePresenter> commandHandler = ACTION_HANDLER_FACTORY.createSimulationHandler(simulationRepository);
                return commandHandler.handle(createSimulationCommand.simulationSize(), (SimConsolePresenter) presenter);
            }
            case LandCommand landCommand -> {
                CommandHandler<Coordinate, RoverLandingPresenter> commandHandler = ACTION_HANDLER_FACTORY.createRoverLandingHandler(simulationRepository);
                return commandHandler.handle(landCommand.coordinate(), (RoverLandingPresenter) presenter);
            }
            case DriveCommand driveCommand -> {
                CommandHandler<List<Move>, RoverDrivingPresenter> commandHandler = ACTION_HANDLER_FACTORY.createRoverDrivingHandler(simulationRepository);
                return commandHandler.handle(driveCommand.moves(), (RoverDrivingPresenter) presenter);
            }
        }
    }
}
