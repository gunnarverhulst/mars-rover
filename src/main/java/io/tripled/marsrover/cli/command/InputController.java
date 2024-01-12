package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.presenter.SimCreationConsolePresenterImpl;
import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
import io.tripled.marsrover.service.command.ActionHandler;
import io.tripled.marsrover.service.command.RoverDrivingHandler;
import io.tripled.marsrover.service.command.RoverLandingHandler;
import io.tripled.marsrover.service.command.SimCreationHandler;
import io.tripled.marsrover.service.presenter.Presenter;
import io.tripled.marsrover.service.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.service.presenter.RoverLandingPresenter;
import io.tripled.marsrover.service.presenter.SimCreationPresenter;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Move;
import io.tripled.marsrover.service.simulation.Simulation;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.List;
import java.util.Optional;

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
            return handleCommand(createSimulationCommand, new SimCreationConsolePresenterImpl());
        }
        return messagePrinter.simulationSizeErrorMessage(input);
    }


    public Message handlerAfterSimulationSizeSet(String input) {
//        String preparedInput = input.trim().toLowerCase();
//        //Create ActionHandler -? aparte class
//        //ActionHandler . execute -> specifieke actionhandler
//
//        ActionHandler actionHandler;
//        if (preparedInput.equalsIgnoreCase("Q")) {
//            return messagePrinter.quitMessage();
//        }
//        if (preparedInput.isEmpty()) {
//            return messagePrinter.apiMessage();
//        }
//        if (preparedInput.equalsIgnoreCase("P")) {
//            return messagePrinter.apiMessage();
//        }
//        if (preparedInput.equalsIgnoreCase("STATE")) {
//            return messagePrinter.stateMessage();
//        }
//        if (preparedInput.startsWith("land")) {
//            Optional<Coordinate> coordinate = inputParser.parseInputForCoordinate(input.toLowerCase());
//            if (coordinate.isPresent()) {
////                actionHandler = new RoverLandingHandler(simulationRepository);
//                LandCommand landCommand = new LandCommand(coordinate.get());
//                return handleCommand(landCommand, new RoverLandingPresenterImpl());
//            }
//            return messagePrinter.landingErrorMessage();
//        }
//        if (preparedInput.startsWith("r")) {
//            Optional<List<Move>> drivingMoves = InputParser.parseInputForDrivingMoves(preparedInput);
//
//            if (drivingMoves.isPresent()) {
//                List<Move> moves = drivingMoves.get();
//                DriveCommand driveCommand = new DriveCommand(moves);
//                return handleCommand(driveCommand, new RoverDrivingPresenterImpl());
//            }
//            return new RoverDrivingErrorMessage();
//        }
//        return messagePrinter.apiMessage();

        return null;
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
                ActionHandler actionHandler = new SimCreationHandler(simulationRepository);
                return ((SimCreationHandler) actionHandler).handle(createSimulationCommand.simulationSize(), (SimCreationPresenter) presenter);
            }
            case LandCommand landCommand -> {
                ActionHandler actionHandler = new RoverLandingHandler(simulationRepository);
                return ((RoverLandingHandler) actionHandler).handle(landCommand.coordinate(), (RoverLandingPresenter) presenter);
            }
            case DriveCommand driveCommand -> {
                ActionHandler actionHandler = new RoverDrivingHandler(simulationRepository);
                return ((RoverDrivingHandler) actionHandler).handle(driveCommand.moves(), (RoverDrivingPresenter) presenter);
            }
        }
    }
}
