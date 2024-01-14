package io.tripled.marsrover.ui.cli.input;

import io.tripled.marsrover.businesslogic.command.CommandController;
import io.tripled.marsrover.businesslogic.command.CreateSimulationCommand;
import io.tripled.marsrover.businesslogic.command.DriveCommand;
import io.tripled.marsrover.businesslogic.command.LandCommand;
import io.tripled.marsrover.businesslogic.rover.Coordinate;
import io.tripled.marsrover.businesslogic.rover.Direction;
import io.tripled.marsrover.businesslogic.rover.Move;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;
import io.tripled.marsrover.ui.cli.messages.RoverDrivingErrorMessage;
import io.tripled.marsrover.ui.cli.presenter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.tripled.marsrover.ui.cli.validators.LandInputValidator.LAND_INPUT_VALIDATOR;
import static io.tripled.marsrover.ui.cli.validators.SimulationSizeInputValidator.SIMULATIONSIZE_INPUT_VALIDATOR;

public class InputParser {

    private final SimulationRepository simulationRepository;

    private final CommandController commandController;

    public InputParser(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
        this.commandController = new CommandController(simulationRepository);
    }

    public void determineCommand(String input) {
        String preparedInput = input.trim().toLowerCase();
        boolean isSimulationSizeSet = simulationRepository.getSimulation() != null;

        if(!isSimulationSizeSet){
            parseInputIfSimulationSizeNotSet(input, preparedInput);
        } else if (preparedInput.equalsIgnoreCase("Q")) {
            new QuitConsolePresenterImpl().quitMessage();
        } else if (preparedInput.isEmpty()) {
            new ApiConsolePresenterImpl().apiMessage();
        } else if (preparedInput.equalsIgnoreCase("P")) {
            new ApiConsolePresenterImpl().apiMessage();
        } else if (preparedInput.equalsIgnoreCase("STATE")) {
            parseState();
        } else if (preparedInput.startsWith("land")) {
            parseLandInput(input);
        } else if (preparedInput.startsWith("r")) {
            parseDriveInput(preparedInput);
        } else
            new ApiConsolePresenterImpl().apiMessage();
    }

    public static Optional<Integer> parseInputForSimulationSize(String input) {
        Optional<Integer> optionalSimulationSize = extractSimulationSize(input);
        if(optionalSimulationSize.isPresent() && SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize(input)){
            return optionalSimulationSize;
        }

        return Optional.empty();
    }

    public Optional<Coordinate> parseInputForCoordinate(String input) {

        return extractCoordinate(input);
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

    private void parseInputIfSimulationSizeNotSet(String input, String preparedInput) {

        if (preparedInput.equalsIgnoreCase("Q")) {
            new QuitConsolePresenterImpl().quitMessage();
        } else {
            String preppedInput = input.trim().toLowerCase();
            Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(preppedInput);

            if (simulationSizeOptional.isPresent()) {

                int simulationSize = simulationSizeOptional.get();
                CreateSimulationCommand createSimulationCommand = new CreateSimulationCommand(simulationSize);
                commandController.handleCommand(createSimulationCommand, new SimulationConsolePresenterImpl());
            } else
                new SimulationConsolePresenterImpl().simulationSizeError(input);
        }
    }

    private void parseState() {
        if(simulationRepository.getSimulation().getRoverState() != null)
            new StateConsolePresenterImpl().stateMessage(getSimulationSize(), simulationRepository.getSimulation().getRoverState());
        else
            new StateConsolePresenterImpl().stateErrorMessage(getSimulationSize());

    }

    private void parseLandInput(String input) {
        String prepperdInput = input.toLowerCase();
        Optional<Coordinate> coordinate = parseInputForCoordinate(prepperdInput);
        if (coordinate.isPresent() ) {
            int x = coordinate.get().x();
            int y = coordinate.get().y();
            int simulationSize = simulationRepository.getSimulation().getSimulationSize();
            if(x <= simulationSize && y <= simulationSize){
                LandCommand landCommand = new LandCommand(coordinate.get());
                commandController.handleCommand(landCommand, new RoverLandingConsolePresenterImpl());
            } else {
                new RoverLandingConsolePresenterImpl().roverLandingErrorOutOfBounds(x, y, simulationSize);
            }

        } else
            parseInvalidCoordinateInput(prepperdInput);

    }

    private void parseInvalidCoordinateInput(String input) {
        Pattern invalidPatternNoCoordinates = Pattern.compile("^land$");
        Matcher matcherNoCoordinates = invalidPatternNoCoordinates.matcher(input);
        if(matcherNoCoordinates.find()){
            new RoverLandingConsolePresenterImpl().roverLandingEmptyCoordinateErrorMessage();
            return;
        }

        Pattern invalidPatternXAndOrYNotANumber = Pattern.compile("land ([0-9a-zA-Z]+) ([0-9a-zA-Z]+)");
        Matcher matcherXAndOrYNotANumber = invalidPatternXAndOrYNotANumber.matcher(input);
        if (matcherXAndOrYNotANumber.find()){
            new RoverLandingConsolePresenterImpl().roverLandingErrorXYNotANumberMessage(matcherXAndOrYNotANumber.group(1), matcherXAndOrYNotANumber.group(2));
            return;
        }

        Pattern invalidPatternXAndOrYNegative = Pattern.compile("land (-?[0-9a-zA-Z]+) (-?[0-9a-zA-Z]+)");
        Matcher matcherXAndOrYNegative = invalidPatternXAndOrYNegative.matcher(input);
        if (matcherXAndOrYNegative.find()){
            new RoverLandingConsolePresenterImpl().roverLandingErrorXAndOrYNegativeMessage(matcherXAndOrYNegative.group(1), matcherXAndOrYNegative.group(2));
            return;
        }

        Pattern invalidPatternOnlyX = Pattern.compile("land ([0-9a-zA-Z]+)");
        Matcher matcherOnlyX = invalidPatternOnlyX.matcher(input);
        if (matcherOnlyX.find()){
            new RoverLandingConsolePresenterImpl().roverLandingErrorOnlyXMessage(matcherOnlyX.group(1));
            return;
        }

        Pattern invalidPatternOnlyXNegative = Pattern.compile("land (-[0-9a-zA-Z]+)");
        Matcher matcherOnlyXNegative = invalidPatternOnlyXNegative.matcher(input);
        if (matcherOnlyXNegative.find()){
            new RoverLandingConsolePresenterImpl().roverLandingErrorOnlyXNegativeMessage(matcherOnlyXNegative.group(1));
            return;
        }


    }

    private void parseDriveInput(String preparedInput) {
        Optional<List<Move>> drivingMoves = parseInputForDrivingMoves(preparedInput);

        if (drivingMoves.isPresent()) {
            List<Move> moves = drivingMoves.get();
            DriveCommand driveCommand = new DriveCommand(moves);
            commandController.handleCommand(driveCommand, new RoverDrivingConsolePresenterImpl());
        } else
            new RoverDrivingErrorMessage();
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
