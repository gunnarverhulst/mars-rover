package io.tripled.marsrover.businesslogic.command.commandhandler;

import io.tripled.marsrover.api.presenter.RoverLandingPresenter;
import io.tripled.marsrover.vocabulary.rover.Heading;
import io.tripled.marsrover.vocabulary.rover.RoverState;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;
import io.tripled.marsrover.vocabulary.rover.Coordinate;

public final class RoverLandingHandler implements CommandHandler<Coordinate, RoverLandingPresenter> {

    private final SimulationRepository simulationRepository;

    public RoverLandingHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    @Override
    public void handle(Coordinate coordinate,RoverLandingPresenter roverLandingPresenter) {
            landRover(coordinate);
            roverLandingPresenter.roverLandedMessage(simulationRepository.getSimulation().getNewestRoverId(), coordinate);
    }

    private void landRover(Coordinate coordinate) {
        simulationRepository.getSimulation().addNewRover(coordinate);
    }

}
