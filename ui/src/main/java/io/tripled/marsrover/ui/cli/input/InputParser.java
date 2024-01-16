package io.tripled.marsrover.ui.cli.input;

import io.tripled.marsrover.api.command.ApplicationService;
import io.tripled.marsrover.api.command.CreateSimulationCommand;
import io.tripled.marsrover.api.command.DriveCommand;
import io.tripled.marsrover.api.command.LandCommand;
import io.tripled.marsrover.ui.cli.messages.RoverDrivingErrorMessage;
import io.tripled.marsrover.ui.cli.presenter.*;
import io.tripled.marsrover.vocabulary.message.MapData;
import io.tripled.marsrover.vocabulary.rover.Coordinate;
import io.tripled.marsrover.vocabulary.rover.Direction;
import io.tripled.marsrover.vocabulary.rover.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.tripled.marsrover.ui.cli.validators.SimulationSizeInputValidator.SIMULATIONSIZE_INPUT_VALIDATOR;

class InputParser {

    private final ApplicationService applicationService;

    InputParser(ApplicationService applicationService) { // cc meegeven
        this.applicationService = applicationService;
    }

    public void determineCommand(String input) {
        String preparedInput = input.trim().toLowerCase();

        if (!applicationService.hasSimulation()) {
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
        } else if(preparedInput.equalsIgnoreCase("m")){
            if(applicationService.hasRoverState()){
                MapData mapData = new MapData(getSimulationSize(), applicationService.getRoverSate());
                new MapConsolePresenterImpl().mapMessage(mapData);
            } else{
                MapData mapData = new MapData(getSimulationSize(), null);
                new MapConsolePresenterImpl().mapMessage(mapData);
            }
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
                applicationService.handleCommand(createSimulationCommand, new SimulationCreationPresenterImpl());
            } else
                new SimulationCreationPresenterImpl().simulationSizeError(input);
        }
    }

    private void parseState() {
        if(applicationService.getRoverSate() != null)
            new StateConsolePresenterImpl().stateMessage(getSimulationSize(), applicationService.getRoverSate());
        else
            new StateConsolePresenterImpl().stateErrorMessage(getSimulationSize());

    }

    private void parseLandInput(String input) {
        String preppedInput = input.toLowerCase();
        Optional<Coordinate> coordinate = parseInputForCoordinate(preppedInput);
        if (coordinate.isPresent() ) {
            int x = coordinate.get().x();
            int y = coordinate.get().y();
            int simulationSize = applicationService.getSimulationSize();
            if(x <= simulationSize && y <= simulationSize){
                LandCommand landCommand = new LandCommand(coordinate.get());
                applicationService.handleCommand(landCommand, new RoverLandingConsolePresenterImpl());
            } else {
                new RoverLandingConsolePresenterImpl().roverLandingErrorOutOfBounds(x, y, simulationSize);
            }

        } else
            parseInvalidCoordinateInput(preppedInput);

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
            applicationService.handleCommand(driveCommand, new RoverDrivingConsolePresenterImpl());
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
        return applicationService.getSimulationSize();
    }
}
