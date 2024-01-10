package io.tripled.marsrover.cli.validators;

public enum SimulationSizeInputValidator {
    SIMULATIONSIZE_INPUT_VALIDATOR;

    public boolean isValidSimulationSize(String input) {

        if(input.isEmpty())
            return false;

        if(input.matches("([a-zA-Z]+(\\w+)[a-zA-Z]*)||([a-zA-Z]*(\\w+)[a-zA-Z]+)"))
            return false;

        if(input.matches("\\[a-zA-Z]+"))
            return false;

        return Integer.parseInt(input) >= 0 && Integer.parseInt(input) <= 100;
    }
}
