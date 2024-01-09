package io.tripled.marsrover.command;

import static io.tripled.marsrover.validators.WorldInitCoordsInputValidator.SIMULATIONSIZE_INPUT_VALIDATOR;

public enum Command {
    QUIT,
    PRINT,
    SIMULATIONSIZE,
    INVALID_VALUE,
    EMPTY_INPUT,
    LAND,
    EMPTY_SIMULATION_SIZE,
    UNKNOWN_COMMAND,
    COMMAND;

    public Command parse(String input){

        if(input.equalsIgnoreCase("Q"))
            return QUIT;
        if(input.isEmpty())
            return EMPTY_INPUT;
        if(input.equalsIgnoreCase("P"))
            return PRINT;
        if(input.toLowerCase().contains("land"))
            return LAND;

        return UNKNOWN_COMMAND;
    }

    public Command parseSimulationSize(String input){

        if(input.isEmpty())
            return EMPTY_SIMULATION_SIZE;
        if(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize(input))
            return SIMULATIONSIZE;
        if(!SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize(input))
            return INVALID_VALUE;

        return UNKNOWN_COMMAND;
    }

}
