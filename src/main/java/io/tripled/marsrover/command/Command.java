package io.tripled.marsrover.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {
    QUIT,
    UNKNOWN_COMMAND,
    COORDS_VALUE,
    INVALID_VALUE,
    EMPTY_INPUT,
    COMMAND;


    public Command parse(String input){
        if(input.equalsIgnoreCase("Q"))
            return QUIT;
        if(input.isEmpty())
            return EMPTY_INPUT;
        if(isValidValue(input))
            return COORDS_VALUE;
        else
            return INVALID_VALUE;

//        return UNKNOWN_COMMAND;
    }

    private boolean isValidValue(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        return matcher.find() && Integer.parseInt(input) > 0;
    }
}
