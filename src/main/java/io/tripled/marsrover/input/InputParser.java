package io.tripled.marsrover.input;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum InputParser {
    INPUT_PARSER;

    public static int parseInputForSimulationSize(String input) {
        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher(input);
        int simulationSizeWithoutOffset = 0;
        if(matcher.find()){
            simulationSizeWithoutOffset = Integer.parseInt(matcher.group());
            return (simulationSizeWithoutOffset + 1) * (simulationSizeWithoutOffset + 1);
        }
        return 0;
    }
}
