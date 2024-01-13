package io.tripled.marsrover.service.command;

import io.tripled.marsrover.service.message.messages.Message;
import io.tripled.marsrover.service.presenter.RoverLandingPresenter;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Heading;
import io.tripled.marsrover.service.rover.RoverState;
import io.tripled.marsrover.service.simulation.SimulationRepository;

 public final class RoverLandingHandler implements CommandHandler<Coordinate, RoverLandingPresenter> {

    private final SimulationRepository simulationRepository;


    public RoverLandingHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    private void setRoverState(Coordinate parsedInput) {
        simulationRepository.getSimulation().setRover1State(new RoverState(parsedInput, Heading.NORTH));
    }

    @Override
    public Message handle(Coordinate coordinate,RoverLandingPresenter roverLandingPresenter) {
        if (simulationRepository.getSimulation().getRoverState() == null) {
            setRoverState(coordinate);
            return roverLandingPresenter.roverLandedMessage(coordinate);
        }
        return roverLandingPresenter.roverLandingErrorMessage(coordinate);
    }
}
