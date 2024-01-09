package io.tripled.marsrover.validators;

import io.tripled.marsrover.input.InputParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LandInputValidator {
    LAND_INPUT_VALIDATOR;


    private int simulationSize = 0;

    public boolean isValidLandInput(String input){
        input = input.toLowerCase();
        if(!input.startsWith("land"))
            return false;

        if(numberOfEncounteredValues(input) != 2)
            return false;

        if(InputParser.parseInputForXValue(input) <= 0 || InputParser.parseInputForXValue(input) > Math.sqrt(simulationSize))
            return false;

        if(InputParser.parseInputForYValue(input) <= 0 || InputParser.parseInputForYValue(input) > Math.sqrt(simulationSize))
            return false;



        return true;
    }

    private int numberOfEncounteredValues(String input) {
        Pattern patter = Pattern.compile("\\d+");
        Matcher matcher = patter.matcher(input);
        int counter = 0;
        while(matcher.find()){
            counter++;
        }
        return counter;
    }


    public void setSimulationSize(int simulationSize) {
        this.simulationSize = simulationSize;
    }
}
