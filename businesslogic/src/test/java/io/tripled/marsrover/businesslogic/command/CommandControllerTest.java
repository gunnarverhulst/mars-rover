package io.tripled.marsrover.businesslogic.command;

import io.tripled.marsrover.businesslogic.presenter.DummySimulationCreationPresenter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandControllerTest {

    private final CommandController commandController;

    public CommandControllerTest() {
        commandController = new CommandController();
    }

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