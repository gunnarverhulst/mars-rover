package io.tripled.marsrover.cli.input;

import io.tripled.marsrover.cli.command.InputController;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.messages.RoverDrivingErrorMessage;
import io.tripled.marsrover.cli.presenter.*;
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

    private final SimulationRepository simulationRepository;

    private final InputController inputController;

    public InputParser(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
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
        boolean isSimulationSizeSet = simulationRepository.getSimulation() != null;

        if(!isSimulationSizeSet){
            if (preparedInput.equalsIgnoreCase("Q")) {
                return new QuitConsolePresenterImpl().quitMessage();
            }

            String preppedInput = input.trim().toLowerCase();
            Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(preppedInput);

            if (simulationSizeOptional.isPresent()) {

                int simulationSize = simulationSizeOptional.get();
                InputController.CreateSimulationCommand createSimulationCommand = new InputController.CreateSimulationCommand(simulationSize);
                 return inputController.handleCommand(createSimulationCommand, new SimulationConsolePresenterImpl());
            } else
                return new SimulationConsolePresenterImpl().simulationSizeError(input);
        }

        if (preparedInput.equalsIgnoreCase("Q")) {
            return new QuitConsolePresenterImpl().quitMessage();
        }
        if (preparedInput.isEmpty()) {
            return new ApiConsolePresenterImpl().apiMessage();
        }
        if (preparedInput.equalsIgnoreCase("P")) {
            return new ApiConsolePresenterImpl().apiMessage();
        }
        if (preparedInput.equalsIgnoreCase("STATE")) {
            if(simulationRepository.getSimulation().getRoverState() != null)
                return new StateConsolePresenterImpl().stateMessage(simulationRepository.getSimulation().getSimulationSize(), simulationRepository.getSimulation().getRoverState());
            else
                return new StateConsolePresenterImpl().stateErrorMessage();
        }
        if (preparedInput.startsWith("land")) {
            Optional<Coordinate> coordinate = parseInputForCoordinate(input.toLowerCase());
            if (coordinate.isPresent()) {
                InputController.LandCommand landCommand = new InputController.LandCommand(coordinate.get());
                return inputController.handleCommand(landCommand, new RoverLandingConsolePresenterImpl());
            }

            return new RoverLandingConsolePresenterImpl().roverLandingEmptyCoordinateErrorMessage();
        }
        if (preparedInput.startsWith("r")) {
            Optional<List<Move>> drivingMoves = parseInputForDrivingMoves(preparedInput);

            if (drivingMoves.isPresent()) {
                List<Move> moves = drivingMoves.get();
                InputController.DriveCommand driveCommand = new InputController.DriveCommand(moves);
                return inputController.handleCommand(driveCommand, new RoverDrivingConsolePresenterImpl());
            }
            return new RoverDrivingErrorMessage();
        }
        return new ApiConsolePresenterImpl().apiMessage();

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

            addDrivingMove(matcher, "f", drivingMoves);
            addDrivingMove(matcher, "l", drivingMoves);
            addDrivingMove(matcher, "r", drivingMoves);
            addDrivingMove(matcher, "b", drivingMoves);

        }
        if(drivingMoves.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(drivingMoves);
    }

    private static void addDrivingMove(Matcher matcher, String matchString, List<Move> drivingMoves) {
        if (matcher.group(1).contains(matchString)) {
            String backward = matcher.group(1).trim();
            String directionLetter = backward.substring(0, 1);
            int directionSteps = backward.length() > 1 ? Integer.parseInt(backward.trim().substring(1)) : 1;
            Direction direction = Direction.DIRECTION.parseDirection(directionLetter);
            drivingMoves.add(new Move(direction, directionSteps));
        }
    }
}
