package io.tripled.marsrover.businesslogic.command;

import io.tripled.marsrover.api.command.ApplicationService;
import io.tripled.marsrover.businesslogic.command.CommandController;
import io.tripled.marsrover.businesslogic.repository.InMemorySimulationRepository;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandControllerTest {

    private final ApplicationService applicationService;
    private final SimulationRepository simulationRepository;

    public CommandControllerTest() {
        this.simulationRepository = new InMemorySimulationRepository();
        applicationService = new CommandController(simulationRepository);
    }

//    @Test
//    void createSimulationWithValidSize(){
//
//        DummySimulationCreationPresenter dummySimulationCreationPresenter = new DummySimulationCreationPresenter();
//        commandController.handleCommand(new CreateSimulationCommand(10), dummySimulationCreationPresenter);
//        assertEquals(10, commandController.getSimulationSize());
//        assertEquals(10, dummySimulationCreationPresenter.returnSimCreationSize());
//    }

//    @Test
//    void createSimulationWithInValidSize(){
//
//        DummySimulationCreationPresenter dummySimulationCreationPresenter = new DummySimulationCreationPresenter();
//        commandController.handleCommand(new CreateSimulationCommand(10), dummySimulationCreationPresenter);
//        assertEquals(10, commandController.getSimulationSize());
//        assertEquals(10, dummySimulationCreationPresenter.returnSimCreationSize());
//    }

    @Test
    void landRoverWithValidCoordinate(){
//        DummyRoverLandingPresenter dummyRoverLandingPresenter = new DummyRoverLandingPresenter();
//        DummySimulationCreationPresenter dummySimulationCreationPresenter = new DummySimulationCreationPresenter();
//        commandController.handleCommand(new CreateSimulationCommand(10), dummySimulationCreationPresenter);
//        commandController.handleCommand(new LandCommand(new Coordinate(5,5)), dummyRoverLandingPresenter);
//
//        Coordinate expectedCoordinate = new Coordinate(5,5);
//        assertEquals(expectedCoordinate.x(), commandController.getRoverSate().roverCoordinate().x());
//        assertEquals(expectedCoordinate.y(), commandController.getRoverSate().roverCoordinate().y());
//
//
//        assertEquals(expectedCoordinate.x(), dummyRoverLandingPresenter.getCoordinate().x());
//        assertEquals(expectedCoordinate.y(), dummyRoverLandingPresenter.getCoordinate().y());

    }

}