package io.tripled.marsrover.input;

import io.tripled.marsrover.rover.Coordinate;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.tripled.marsrover.validators.LandInputValidator.LAND_INPUT_VALIDATOR;
import static io.tripled.marsrover.validators.SimulationSizeInputValidator.SIMULATIONSIZE_INPUT_VALIDATOR;

public enum InputParser {
    INPUT_PARSER;

    public static Optional<Integer> parseInputForSimulationSize(String input) {
        Optional<Integer> optionalSimulationSize = extractSimulationSize(input);
        if(optionalSimulationSize.isPresent() && SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize(input)){
            return optionalSimulationSize;
        }

        return Optional.empty();
    }

    public static Optional<Coordinate> parseInputForCoordinate(String input, int simulationSize) {

        Optional<Coordinate> optionalCoordinate = extractCoordinate(input);
        if(optionalCoordinate.isPresent() && LAND_INPUT_VALIDATOR.isValidCoordinateInput(optionalCoordinate.get(), simulationSize))
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
}
