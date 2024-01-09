package io.tripled.marsrover.message;

import io.tripled.marsrover.Coordinate;

public enum MessagePrinter {
    MESSAGE_PRINTER;

    public static String quit(){
        return "Quitting application";
    }

    public static String simulationSizeSetMessage(String input, int simulationSize) {

        return "Simulation with max coordinate [" + input + "] created successfully. Simulation contains [" + simulationSize + "] coordinates\n\n"+
                       "[Please enter a command]";
    }

    public static String invalidValue(String input) {
        return "[" + input + "] is an invalid Simulation maxCoordinate\n" +
                simulationSizeErrorMessage();
    }

    public static String simulationSizeErrorMessage() {

        return "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ";
    }

    public static String apiMessage() {
        return """
                ***************************************************************************************************************************************************
                *   Print state of simulation     | {state}                                                   | ex: state                                         *
                *   Land a new rover              | {land {x} {y}}                                            | ex: land 1 5                                      *
                *   Quit the application          | {Q}                                                                                                           *
                *   Print API overview            | {P}                                                                                                           *
                ***************************************************************************************************************************************************\n\n""" +
                "[Please enter a command] : ";
    }


    public static String landingMessage(Coordinate roverCoordinate) {
        
        return "Rover R1 landed at (" + roverCoordinate.x() + "," + roverCoordinate.y() + ") and is facing North\n\n" +
                "[Please enter a command]";
    }

    public static String landingErrorMessage(Coordinate roverCoordinate) {
        return "The coordinate [" + roverCoordinate.x() + "," + roverCoordinate.y() + "] is not a valid coordinate for the planet with max coordinate\n\n"+
                "[Please enter a command]";
    }
}
