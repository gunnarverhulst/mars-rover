package io.tripled.marsrover.validators;

import io.tripled.marsrover.Coordinate;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LandInputValidator {
    LAND_INPUT_VALIDATOR;

    public boolean isValidLandInput(String input, int worldInitCoords){
        if(!input.startsWith("land"))
            return false;

        if(numberOfEncounteredValues(input) != 2)
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
}
