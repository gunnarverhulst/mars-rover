package io.tripled.marsrover.cli.message;

import io.tripled.marsrover.cli.message.messages.*;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.RoverState;
import io.tripled.marsrover.service.simulation.SimulationRepository;

public class MessagePrinter {

    private final SimulationRepository simulationRepository;

    public MessagePrinter(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    public Message quitMessage(){
        return new QuitMessage();
    }

    public Message simulationSizeSetMessage( int simulationSize) {
        return new SimulationSizeSetMessage(simulationSize);
    }

    public Message requestSimulationSize() {
        return new RequestSimulationSizeMessage();
    }

    public Message simulationSizeErrorMessage(String input) {

        return new SimulationSizeErrorMessage(input);
    }

    public Message apiMessage() {
        return new ApiMessage();
    }

    public Message landingMessage() {

        return new LandingMessage(simulationRepository.getSimulation().getRover1Coordinates());
    }

    public Message landingErrorMessage() {
        if( simulationRepository.getSimulation().getRoverState() != null){
            return new LandingErrorMessage(simulationRepository.getSimulation().getRoverState().roverCoordinate());
        } else
            return new LandingErrorEmptyCoordinateMessage();
    }

    public Message stateMessage() {

        if(simulationRepository.getSimulation().getRoverState() == null){
            return new StateErrorMessage();
        }

        return new StateMessage(simulationRepository.getSimulation().getSimulationSize(),simulationRepository.getSimulation().getRoverState());
    }

    public Message landingAlreadyLandedMessage() {
       return new LandingAlreadyLandedMessage();
    }
}
