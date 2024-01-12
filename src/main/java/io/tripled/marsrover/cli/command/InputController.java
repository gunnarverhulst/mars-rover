package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.messages.RoverDrivingErrorMessage;
import io.tripled.marsrover.cli.presenter.RoverLandingPresenterImpl;
import io.tripled.marsrover.cli.presenter.SimCreationConsolePresenterImpl;
import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
import io.tripled.marsrover.service.businessinterface.Presenter;
import io.tripled.marsrover.service.businessinterface.RoverDrivingPresenter;
import io.tripled.marsrover.service.businessinterface.RoverLandingPresenter;
import io.tripled.marsrover.service.businessinterface.SimCreationPresenter;
import io.tripled.marsrover.service.command.*;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Move;
import io.tripled.marsrover.service.simulation.Simulation;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.List;
import java.util.Optional;

public class InputController {

    private final SimulationRepository simulationRepository;
    private final MessagePrinter messagePrinter;

    private final InputParser inputParser;

    public InputController(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
        this.messagePrinter = new MessagePrinter(simulationRepository);
        this.inputParser = new InputParser(simulationRepository);
    }

    public InputController() {
        this.simulationRepository = new InMemorySimulationRepository();
        this.messagePrinter = new MessagePrinter(simulationRepository);
        this.inputParser = new InputParser(simulationRepository);
    }

    public Message handlerBeforeSimulationSizeSet(String input) {
        String preppedInput = input.trim().toLowerCase();
        Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(preppedInput);


        ActionHandler<Integer, SimCreationPresenter> actionHandler = ActionHandlerFactory.ACTION_HANDLER_FACTORY.createSimulationHandler(simulationRepository);

        if (simulationSizeOptional.isPresent()) {
            return actionHandler.handle(simulationSizeOptional.get(), new SimCreationConsolePresenterImpl());
//            return messagePrinter.requestSimulationSize();
        }
        return messagePrinter.simulationSizeErrorMessage(input);
    }


    public Message handlerAfterSimulationSizeSet(String input) {
        String preparedInput = input.trim().toLowerCase();
        //Create ActionHandler -? aparte class
        //ActionHandler . execute -> specifieke actionhandler

        ActionHandler actionHandler;
        if (preparedInput.equalsIgnoreCase("Q")) {
            return messagePrinter.quitMessage();
        }
        if (preparedInput.isEmpty()) {
            return messagePrinter.apiMessage();
        }
        if (preparedInput.equalsIgnoreCase("P")) {
            return messagePrinter.apiMessage();
        }
        if (preparedInput.equalsIgnoreCase("STATE")) {
            return messagePrinter.stateMessage();
        }
        if (preparedInput.startsWith("land")) {
            Optional<Coordinate> coordinate = inputParser.parseInputForCoordinate(input.toLowerCase());
            if (coordinate.isPresent()) {
                actionHandler = new RoverLandingHandler(simulationRepository);
                LandCommand landCommand = new LandCommand(coordinate.get());
                return handleCommand(landCommand, new RoverLandingPresenterImpl());
            }
            return messagePrinter.landingErrorMessage();
        }
        if (preparedInput.startsWith("r")) {
            Optional<List<Move>> drivingMoves = InputParser.parseInputForDrivingMoves(preparedInput);

            if (drivingMoves.isPresent()) {
                List<Move> moves = drivingMoves.get();
                DriveCommand driveCommand = new DriveCommand(moves);
                return handleCommand(driveCommand,null);
            }
            return new RoverDrivingErrorMessage();
        }
        return messagePrinter.apiMessage();
    }

    public Simulation getSimulation() {
        return simulationRepository.getSimulation();
    }


    sealed interface CustomCommand permits DriveCommand, LandCommand{ }


    record LandCommand(Coordinate coordinate) implements CustomCommand{}

    record DriveCommand(List<Move> moves) implements CustomCommand { }
    public Message handleCommand(CustomCommand command, Presenter presenter) {
        switch (command) {
            case DriveCommand driveCommand -> {
                ActionHandler actionHandler = new RoverDrivingHandler(simulationRepository);
                return ((RoverDrivingHandler) actionHandler).handle(driveCommand.moves(), (RoverDrivingPresenter) presenter);
            }
            case LandCommand landCommand -> {
                ActionHandler actionHandler = new RoverLandingHandler(simulationRepository);
                return ((RoverLandingHandler) actionHandler).handle(landCommand.coordinate(), (RoverLandingPresenter) presenter);
            }
        }
    }
}
