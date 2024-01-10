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
        return new RequestSimulationSizeMessage();
    }

    public static Message simulationSizeErrorMessage(String input) {

        return new SimulationSizeErrorMessage(input);
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

    public static Message stateMessage(RoverState roverState) {

        if(roverState == null){
            return new StateErrorMessage();
        }

        return new StateMessage(roverState);
    }
}
