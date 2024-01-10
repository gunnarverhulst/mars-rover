package io.tripled.marsrover.cli.message;

import io.tripled.marsrover.cli.message.messages.*;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.RoverState;

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

    public static Message landingMessage(Coordinate roverCoordinate) {

        return new LandingMessage(roverCoordinate);
    }

    public static Message landingErrorMessage(Coordinate roverCoordinate) {
        if(roverCoordinate != null){
            return new LandingErrorMessage(roverCoordinate);
        } else
            return new LandingErrorEmptyCoordinateMessage();
    }

    public static Message stateMessage(int simulationSize, RoverState roverState) {

        if(roverState == null){
            return new StateErrorMessage();
        }

        return new StateMessage(simulationSize,roverState);
    }

    public static Message landingAlreadyLandedMessage() {
       return new LandingAlreadyLandedMessage();
    }
}
