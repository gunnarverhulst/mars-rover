package io.tripled.marsrover.cli.input;

import io.tripled.marsrover.service.command.CreateSimulationCommand;
import io.tripled.marsrover.service.command.DriveCommand;
import io.tripled.marsrover.service.command.CommandController;
import io.tripled.marsrover.service.command.LandCommand;
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

    private final CommandController commandController;

    public InputParser(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
        this.commandController = new CommandController(simulationRepository);
    }

    public Message determineCommand(String input) {
        String preparedInput = input.trim().toLowerCase();
        boolean isSimulationSizeSet = simulationRepository.getSimulation() != null;

        if(!isSimulationSizeSet){
            return parseInputIfSimulationSizeNotSet(input, preparedInput);
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
            return parseState();
        }

        if (preparedInput.startsWith("land")) {
            return parseLandInput(input);
        }

        if (preparedInput.startsWith("r")) {
            return parseDriveInput(preparedInput);
        }
        return new ApiConsolePresenterImpl().apiMessage();

    }

    public static Optional<Integer> parseInputForSimulationSize(String input) {
        Optional<Integer> optionalSimulationSize = extractSimulationSize(input);
        if(optionalSimulationSize.isPresent() && SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize(input)){
            return optionalSimulationSize;
        }

        return Optional.empty();
    }

    public Optional<Coordinate> parseInputForCoordinate(String input) {

        Optional<Coordinate> optionalCoordinate = extractCoordinate(input);
        if(optionalCoordinate.isPresent() && LAND_INPUT_VALIDATOR.isValidCoordinateInput(optionalCoordinate.get(), getSimulationSize()))
            return optionalCoordinate;

        return Optional.empty();
    }

    private static Optional<List<Move>> parseInputForDrivingMoves(String input) {
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

    private Message parseInputIfSimulationSizeNotSet(String input, String preparedInput) {
        if (preparedInput.equalsIgnoreCase("Q")) {
            return new QuitConsolePresenterImpl().quitMessage();
        }

        String preppedInput = input.trim().toLowerCase();
        Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(preppedInput);

        if (simulationSizeOptional.isPresent()) {

            int simulationSize = simulationSizeOptional.get();
            CreateSimulationCommand createSimulationCommand = new CreateSimulationCommand(simulationSize);
            return commandController.handleCommand(createSimulationCommand, new SimulationConsolePresenterImpl());
        } else
            return new SimulationConsolePresenterImpl().simulationSizeError(input);
    }

    private Message parseState() {
        if(simulationRepository.getSimulation().getRoverState() != null)
            return new StateConsolePresenterImpl().stateMessage(getSimulationSize(), simulationRepository.getSimulation().getRoverState());
        else
            return new StateConsolePresenterImpl().stateErrorMessage();
    }

    private Message parseLandInput(String input) {
        Optional<Coordinate> coordinate = parseInputForCoordinate(input.toLowerCase());
        if (coordinate.isPresent()) {
            LandCommand landCommand = new LandCommand(coordinate.get());
            return commandController.handleCommand(landCommand, new RoverLandingConsolePresenterImpl());
        }

        return new RoverLandingConsolePresenterImpl().roverLandingEmptyCoordinateErrorMessage();
    }

    private Message parseDriveInput(String preparedInput) {
        Optional<List<Move>> drivingMoves = parseInputForDrivingMoves(preparedInput);

        if (drivingMoves.isPresent()) {
            List<Move> moves = drivingMoves.get();
            DriveCommand driveCommand = new DriveCommand(moves);
            return commandController.handleCommand(driveCommand, new RoverDrivingConsolePresenterImpl());
        }
        return new RoverDrivingErrorMessage();
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

    private static void addDrivingMove(Matcher matcher, String matchString, List<Move> drivingMoves) {
        if (matcher.group(1).contains(matchString)) {
            String backward = matcher.group(1).trim();
            String directionLetter = backward.substring(0, 1);
            int directionSteps = backward.length() > 1 ? Integer.parseInt(backward.trim().substring(1)) : 1;
            Direction direction = Direction.DIRECTION.parseDirection(directionLetter);
            drivingMoves.add(new Move(direction, directionSteps));
        }
    }

    private int getSimulationSize() {
        return simulationRepository.getSimulation().getSimulationSize();
    }
}
