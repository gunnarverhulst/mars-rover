package io.tripled.marsrover.command;

public enum Command {
    QUIT,
    PRINT,
    SIMULATION_SIZE,
    EMPTY_INPUT,
    LAND,
    STATE,
    UNKNOWN_COMMAND,
    COMMAND;

    public Command parse(String input){

        if(input.equalsIgnoreCase("Q"))
            return QUIT;
        if(input.isEmpty())
            return EMPTY_INPUT;
        if(input.equalsIgnoreCase("P"))
            return PRINT;
        if(input.equalsIgnoreCase("STATE"))
            return STATE;
        if(input.toLowerCase().startsWith("land"))
            return LAND;

        return UNKNOWN_COMMAND;
    }

    public Command parseSimulationSize(String input){
        return SIMULATION_SIZE;
    }

}
