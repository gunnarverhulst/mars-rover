package io.tripled.marsrover.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum WorldInitCoordsInputValidator {
    COORDS_INPUT_VALIDATOR;

    public boolean isValidMaxCoordValue(String input) {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(input);

        return matcher.find() && Integer.parseInt(input) > 0;
    }
}
