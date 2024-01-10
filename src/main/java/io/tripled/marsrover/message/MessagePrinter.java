package io.tripled.marsrover.message;

import io.tripled.marsrover.rover.Coordinate;
import io.tripled.marsrover.rover.RoverState;

public enum MessagePrinter {
    MESSAGE_PRINTER;

    public static String quit(){
        return "Quitting application";
    }

    public static String simulationSizeSetMessage(String input, int simulationSize) {

        return "Simulation with max coordinate [" + input + "] created successfully. Simulation contains [" + calculateTotalNumberOfCoordinates(simulationSize) + "] coordinates\n\n"+
                       "[Please enter a command]";
    }

    public static String requestSimulationSize() {
        return "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ";
    }

    public static String simulationSizeErrorMessage(String input) {

        return "[" + input + "] is an invalid Simulation maxCoordinate\n" +
                "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
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
        if(roverCoordinate != null){
            return "The coordinate [" + roverCoordinate.x() + "," + roverCoordinate.y() + "] is not a valid coordinate for the planet with max coordinate\n\n"+
                    "[Please enter a command]";

        } else
            return"Unable to parse coordinates for landing. Expected two positive numbers [x y]";
    }

    public static String stateMessage(RoverState roverState) {

        if(roverState == null){
            return "Rover has not landed yet.\n\n"+
            "[Please enter a command]";
        }

        return "Simulation has maxCoordinate " + roverState.simulationSize() + " with a total of " + calculateTotalNumberOfCoordinates(roverState.simulationSize()) + " coordinates.\n" +
                "Rover at Coordinates[x=" + roverState.roverCoordinate().x() + ", y=" + roverState.roverCoordinate().y() + "] is facing NORTH";
    }

    private static int calculateTotalNumberOfCoordinates(int simulationSize) {
        return (simulationSize + 1) * (simulationSize + 1);
    }
}
