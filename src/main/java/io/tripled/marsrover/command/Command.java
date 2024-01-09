package io.tripled.marsrover.command;

import static io.tripled.marsrover.validators.WorldInitCoordsInputValidator.COORDS_INPUT_VALIDATOR;

public enum Command {
    QUIT,
    PRINT,
    COORDS_VALUE,
    INVALID_VALUE,
    EMPTY_INPUT,
    LAND,
    UNKNOWN_COMMAND,
    COMMAND;

    public Command parse(String input, int worldInitCoords){
        if(input.equalsIgnoreCase("Q"))
            return QUIT;
        if(input.isEmpty())
            return EMPTY_INPUT;
        if(worldInitCoords > 0 && input.equalsIgnoreCase("P"))
            return PRINT;
        if(worldInitCoords == 0 && COORDS_INPUT_VALIDATOR.isValidMaxCoordValue(input))
            return COORDS_VALUE;
        if(worldInitCoords == 0 && !COORDS_INPUT_VALIDATOR.isValidMaxCoordValue(input))
            return INVALID_VALUE;
        if(worldInitCoords > 0 && input.contains("land"))
            return LAND;

        return UNKNOWN_COMMAND;
    }

}
