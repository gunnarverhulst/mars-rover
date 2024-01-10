package io.tripled.marsrover.message;

import io.tripled.marsrover.rover.Coordinate;
import io.tripled.marsrover.rover.RoverState;

public enum MessagePrinter {
    MESSAGE_PRINTER;

   public static Message quitMessage(){
        return new QuitMessage();
    }

    public static Message simulationSizeSetMessage( int simulationSize) {
        return new SimulationSizeSetMessage(simulationSize);
    }

    public static Message requestSimulationSize() {
        return new RequestSimulationSIzeMessage();
    }

    public static Message simulationSizeErrorMessage(String input) {

//        return "[" + input + "] is an invalid Simulation maxCoordinate\n" +
//                "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
//                "[Enter max coordinate] : ";

        return new SimulationSIzeErrorMessage(input);
    }

    public static Message apiMessage() {
        return new ApiMessage();
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
