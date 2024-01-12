package io.tripled.marsrover.cli.input;

import io.tripled.marsrover.cli.command.InputController;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.messages.RoverDrivingErrorMessage;
import io.tripled.marsrover.cli.presenter.RoverDrivingPresenterImpl;
import io.tripled.marsrover.cli.presenter.RoverLandingPresenterImpl;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Direction;
import io.tripled.marsrover.service.rover.Move;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.tripled.marsrover.cli.validators.LandInputValidator.LAND_INPUT_VALIDATOR;
import static io.tripled.marsrover.cli.validators.SimulationSizeInputValidator.SIMULATIONSIZE_INPUT_VALIDATOR;

public class InputParser {

    private final MessagePrinter messagePrinter;
    private final SimulationRepository simulationRepository;

    private final InputController inputController;

    public InputParser(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
        this.messagePrinter = new MessagePrinter(simulationRepository);
        this.inputController = new InputController(simulationRepository);
    }

    public static Optional<Integer> parseInputForSimulationSize(String input) {
        Optional<Integer> optionalSimulationSize = extractSimulationSize(input);
        if(optionalSimulationSize.isPresent() && SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize(input)){
            return optionalSimulationSize;
        }

        return Optional.empty();
    }

    public Message determineCommand(String input) {
        String preparedInput = input.trim().toLowerCase();

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
            Optional<Coordinate> coordinate = parseInputForCoordinate(input.toLowerCase());
            if (coordinate.isPresent()) {
                InputController.LandCommand landCommand = new InputController.LandCommand(coordinate.get());
                return inputController.handleCommand(landCommand, new RoverLandingPresenterImpl());
            }
            return messagePrinter.landingErrorMessage();
        }
        if (preparedInput.startsWith("r")) {
            Optional<List<Move>> drivingMoves = parseInputForDrivingMoves(preparedInput);

            if (drivingMoves.isPresent()) {
                List<Move> moves = drivingMoves.get();
                InputController.DriveCommand driveCommand = new InputController.DriveCommand(moves);
                return inputController.handleCommand(driveCommand, new RoverDrivingPresenterImpl());
            }
            return new RoverDrivingErrorMessage();
        }
        return messagePrinter.apiMessage();
    }

    public Optional<Coordinate> parseInputForCoordinate(String input) {

        Optional<Coordinate> optionalCoordinate = extractCoordinate(input);
        if(optionalCoordinate.isPresent() && LAND_INPUT_VALIDATOR.isValidCoordinateInput(optionalCoordinate.get(), simulationRepository.getSimulation().getSimulationSize()))
            return optionalCoordinate;

        return Optional.empty();
    }

    private static Optional<Integer> extractSimulationSize(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()){
            return Optional.of(Integer.parseInt(matcher.group()));
        }
        return Optional.empty();
    }


    private static Optional<Coordinate> extractCoordinate(String input) {
        Pattern pattern = Pattern.compile("^land (\\d+) (\\d+)$");
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()){
            return Optional.of(new Coordinate(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
        }
        return Optional.empty();
    }

    public static Optional<List<Move>> parseInputForDrivingMoves(String input) {
        Pattern pattern = Pattern.compile("( [flrb]\\d*)");
        Matcher matcher = pattern.matcher(input);
        List<Move> drivingMoves = new ArrayList<>();
        while(matcher.find()){

            if(matcher.group(1).contains("f")){
                String forward = matcher.group(1).trim();
                String directionLetter = forward.substring(0,1);
                int directionSteps = forward.length() > 1 ? Integer.parseInt(forward.trim().substring(1)) : 1;
                Direction direction = Direction.DIRECTION.parseDirection(directionLetter);
                drivingMoves.add(new Move(direction, directionSteps));
            }
            if(matcher.group(1).contains("l")){
                String left = matcher.group(1).trim();
                String directionLetter = left.substring(0,1);
                int directionSteps = left.length() > 1 ? Integer.parseInt(left.trim().substring(1)) : 1;
                Direction direction = Direction.DIRECTION.parseDirection(directionLetter);
                drivingMoves.add(new Move(direction, directionSteps));
            }
            if(matcher.group(1).contains("r")){
                String right = matcher.group(1).trim();
                String directionLetter = right.substring(0,1);
                int directionSteps = right.length() > 1 ? Integer.parseInt(right.trim().substring(1)) : 1;
                Direction direction = Direction.DIRECTION.parseDirection(directionLetter);
                drivingMoves.add(new Move(direction, directionSteps));
            }
            if(matcher.group(1).contains("b")){
                String backward = matcher.group(1).trim();
                String directionLetter = backward.substring(0,1);
                int directionSteps = backward.length() > 1 ? Integer.parseInt(backward.trim().substring(1)) : 1;
                Direction direction = Direction.DIRECTION.parseDirection(directionLetter);
                drivingMoves.add(new Move(direction, directionSteps));
            }

        }
        if(drivingMoves.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(drivingMoves);
    }
}
