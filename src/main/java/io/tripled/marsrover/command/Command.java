package io.tripled.marsrover.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {
    QUIT,
    PRINT,
    COORDS_VALUE,
    INVALID_VALUE,
    EMPTY_INPUT,
    UNKNOWN_COMMAND,
    COMMAND;

    public Command parse(String input, int worldInitCoords){
        if(input.equalsIgnoreCase("Q"))
            return QUIT;
        if(input.isEmpty())
            return EMPTY_INPUT;
        if(worldInitCoords > 0 && input.equalsIgnoreCase("P"))
            return PRINT;
        if(worldInitCoords == 0 && isValidValue(input))
            return COORDS_VALUE;
        if(worldInitCoords == 0 && !isValidValue(input))
            return INVALID_VALUE;

        return UNKNOWN_COMMAND;
    }

    private boolean isValidValue(String input) {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(input);

        return matcher.find() && Integer.parseInt(input) > 0;
    }
}
