package io.tripled.marsrover.validators;

import io.tripled.marsrover.LandCoordinates;

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

//        if(!encounteredLandCoordinatesInSim(input, worldInitCoords))
//            return false;

        return true;
    }

    private Optional<LandCoordinates> encounteredLandCoordinatesInSim(String input, int worldInitCoords) {
        Pattern patter = Pattern.compile("\\d+");
        Matcher matcher = patter.matcher(input);



        while(matcher.find()){
            if(Integer.parseInt(matcher.group()) < 1 || Integer.parseInt(matcher.group()) > worldInitCoords)
                return Optional.empty();
        }
        return Optional.empty();
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
