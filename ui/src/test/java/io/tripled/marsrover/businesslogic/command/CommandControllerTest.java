package io.tripled.marsrover.businesslogic.command;

import io.tripled.marsrover.businesslogic.presenter.DummySimulationCreationPresenter;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;
import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandControllerTest {

    private final CommandController commandController;
    private final SimulationRepository simulationRepository;

    public CommandControllerTest() {
        this.simulationRepository = new InMemorySimulationRepository();
        commandController = new CommandController(simulationRepository);
    }

//    @BeforeEach

    @Test
    void createSimulationWithValidSize(){

        DummySimulationCreationPresenter dummySimulationCreationPresenter = new DummySimulationCreationPresenter();
        commandController.handleCommand(new CreateSimulationCommand(10), dummySimulationCreationPresenter);
        assertEquals(10, simulationRepository.getSimulation().getSimulationSize());
        assertEquals(10, dummySimulationCreationPresenter.returnSimCreationSize());
    }

    @Test
    void landRoverWithValidCoordinate(){

    }

}