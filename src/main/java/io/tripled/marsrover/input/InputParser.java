package io.tripled.marsrover.input;

import io.tripled.marsrover.Coordinate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum InputParser {
    INPUT_PARSER;

    public static int parseInputForSimulationSize(String input) {
        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher(input);
        int simulationSizeWithoutOffset;
        if(matcher.find()){
            simulationSizeWithoutOffset = Integer.parseInt(matcher.group());
            return (simulationSizeWithoutOffset + 1) * (simulationSizeWithoutOffset + 1);
        }
        return 0;
    }

    public static Coordinate parseInputForCoordinate(String input) {
        int x = parseInputForXValue(input);
        int y = parseInputForYValue(input);
        return new Coordinate(x,y);
    }

    private static int parseInputForXValue(String input) {
        Pattern pattern = Pattern.compile("^land (\\d*) ");
        Matcher matcher = pattern.matcher(input.toLowerCase());

        if(matcher.find())
            return Integer.parseInt(matcher.group(1));
        return 0;
    }

    private static int parseInputForYValue(String input) {
        Pattern pattern = Pattern.compile(" (\\d*)$");
        Matcher matcher = pattern.matcher(input.toLowerCase());
        if(matcher.find())
            return Integer.parseInt(matcher.group(1));
        return 0;
    }
}
