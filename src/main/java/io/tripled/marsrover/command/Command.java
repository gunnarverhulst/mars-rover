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

    public Command parse(String input){

        if(input.equalsIgnoreCase("Q"))
            return QUIT;
        if(input.isEmpty())
            return EMPTY_INPUT;
        if(input.equalsIgnoreCase("P"))
            return PRINT;
        if(input.contains("land"))
            return LAND;

        return UNKNOWN_COMMAND;
    }

    public Command parseSimulationSize(String input){

        if(input.isEmpty())
            return EMPTY_INPUT;
        if(COORDS_INPUT_VALIDATOR.isValidMaxCoordValue(input))
            return COORDS_VALUE;
        if(!COORDS_INPUT_VALIDATOR.isValidMaxCoordValue(input))
            return INVALID_VALUE;

        return UNKNOWN_COMMAND;
    }

}
