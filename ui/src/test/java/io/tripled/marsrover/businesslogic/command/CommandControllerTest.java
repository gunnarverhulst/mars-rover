package io.tripled.marsrover.businesslogic.command;

import io.tripled.marsrover.businesslogic.presenter.DummySimulationCreationPresenter;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;
import io.tripled.marsrover.businesslogic.repository.InMemorySimulationRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandControllerTest {

    private final CommandController commandController;

    public CommandControllerTest() {
        commandController = new CommandController();
    }

//    @BeforeEach

    @Test
    void createSimulationWithValidSize(){

        DummySimulationCreationPresenter dummySimulationCreationPresenter = new DummySimulationCreationPresenter();
        commandController.handleCommand(new CreateSimulationCommand(10), dummySimulationCreationPresenter);
        assertEquals(10, commandController.getSimulationSize());
        assertEquals(10, dummySimulationCreationPresenter.returnSimCreationSize());
    }

    @Test
    void landRoverWithValidCoordinate(){

    }

}