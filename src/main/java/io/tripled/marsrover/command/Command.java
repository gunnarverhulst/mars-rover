package io.tripled.marsrover.command;

import io.tripled.marsrover.validators.LandInputValidator;

import static io.tripled.marsrover.validators.LandInputValidator.LAND_INPUT_VALIDATOR;
import static io.tripled.marsrover.validators.WorldInitCoordsInputValidator.SIMULATIONSIZE_INPUT_VALIDATOR;

public enum Command {
    QUIT,
    PRINT,
    SIMULATIONSIZE,
    INVALID_VALUE,
    EMPTY_INPUT,
    LAND,
    EMPTY_SIMULATION_SIZE,
    INVALID_LANDING,
    UNKNOWN_COMMAND,
    COMMAND;

    public Command parse(String input){



        if(input.equalsIgnoreCase("Q"))
            return QUIT;
        if(input.isEmpty())
            return EMPTY_INPUT;
        if(input.equalsIgnoreCase("P"))
            return PRINT;
        if(LAND_INPUT_VALIDATOR.isValidLandInput(input))
            return LAND;
        if(!LAND_INPUT_VALIDATOR.isValidLandInput(input))
            return INVALID_LANDING;

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
