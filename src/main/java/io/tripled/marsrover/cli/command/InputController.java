package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.presenter.SimulationConsolePresenterImpl;
import io.tripled.marsrover.service.command.*;
import io.tripled.marsrover.service.presenter.Presenter;
import io.tripled.marsrover.service.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.service.presenter.RoverLandingPresenter;
import io.tripled.marsrover.service.presenter.SimConsolePresenter;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Move;
import io.tripled.marsrover.service.simulation.Simulation;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.List;
import java.util.Optional;

import static io.tripled.marsrover.service.command.ActionHandlerFactory.ACTION_HANDLER_FACTORY;

public class InputController {

    private final SimulationRepository simulationRepository;
    private final MessagePrinter messagePrinter;

    public InputController(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
        this.messagePrinter = new MessagePrinter(simulationRepository);
    }

    public Message handlerBeforeSimulationSizeSet(String input) {
        String preppedInput = input.trim().toLowerCase();
        Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(preppedInput);

        if (simulationSizeOptional.isPresent()) {

            int simulationSize = simulationSizeOptional.get();
            CreateSimulationCommand createSimulationCommand = new CreateSimulationCommand(simulationSize);
            return handleCommand(createSimulationCommand, new SimulationConsolePresenterImpl());
        }
        return messagePrinter.simulationSizeErrorMessage(input);
    }


    public Simulation getSimulation() {
        return simulationRepository.getSimulation();
    }



    sealed interface CustomCommand permits CreateSimulationCommand, DriveCommand, LandCommand { }

    public record CreateSimulationCommand(int simulationSize) implements CustomCommand{}

    public record LandCommand(Coordinate coordinate) implements CustomCommand{}

    public record DriveCommand(List<Move> moves) implements CustomCommand { }
    public Message handleCommand(CustomCommand command, Presenter presenter) {
        switch (command) {
            case CreateSimulationCommand createSimulationCommand -> {
                ActionHandler actionHandler = ACTION_HANDLER_FACTORY.createSimulationHandler(simulationRepository);
                return ((SimCreationHandler) actionHandler).handle(createSimulationCommand.simulationSize(), (SimConsolePresenter) presenter);
            }
            case LandCommand landCommand -> {
                ActionHandler actionHandler = ACTION_HANDLER_FACTORY.createRoverLandingHandler(simulationRepository);
                return ((RoverLandingHandler) actionHandler).handle(landCommand.coordinate(), (RoverLandingPresenter) presenter);
            }
            case DriveCommand driveCommand -> {
                ActionHandler actionHandler = ACTION_HANDLER_FACTORY.createRoverDrivingHandler(simulationRepository);
                return ((RoverDrivingHandler) actionHandler).handle(driveCommand.moves(), (RoverDrivingPresenter) presenter);
            }
        }
    }
}
