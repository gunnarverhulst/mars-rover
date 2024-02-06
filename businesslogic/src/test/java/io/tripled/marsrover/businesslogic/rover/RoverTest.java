package io.tripled.marsrover.businesslogic.rover;

import io.tripled.marsrover.api.message.TransientMessage;
import io.tripled.marsrover.businesslogic.TestConfiguration;
import io.tripled.marsrover.businesslogic.repository.InMemorySimulationRepository;
import io.tripled.marsrover.businesslogic.simulation.Simulation;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;
import io.tripled.marsrover.vocabulary.rover.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
class RoverTest {
    private Rover rover;

    private final SimulationRepository simulationRepository;

    public RoverTest(){
        this.simulationRepository = new InMemorySimulationRepository();
        simulationRepository.addSimulation(new Simulation(10));
    }

    @BeforeEach
    void init(){
        simulationRepository.getSimulation().resetCounter();

    }

    @Test
    void whenMoveRoverUp1WithinBounds_thenYPlus1(){

        simulationRepository.getSimulation().addNewRover(new Coordinate(5,5));
        rover = simulationRepository.getSimulation().getRover(0);

        String expectedString = "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [5,6]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.FORWARD, 1)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverDown1WithinBounds_thenYMin1(){
        simulationRepository.getSimulation().addNewRover(new Coordinate(5,5));
        rover = simulationRepository.getSimulation().getRover(0);

        String expectedString = "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [5,4]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.BACKWARD, 1)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverLeft(){
        simulationRepository.getSimulation().addNewRover(new Coordinate(5,5));
        rover = simulationRepository.getSimulation().getRover(0);

        String expectedString = "Rover R1 is turning left\n" +
                "Rover R1 is now facing WEST\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.LEFT, 1)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverRight(){

        simulationRepository.getSimulation().addNewRover(new Coordinate(5,5));
        rover = simulationRepository.getSimulation().getRover(0);

        String expectedString = "Rover R1 is turning right\n" +
                "Rover R1 is now facing EAST\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.RIGHT, 1)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverUp2WithinBounds_thenYPkus2(){

        simulationRepository.getSimulation().addNewRover(new Coordinate(5,5));
        rover = simulationRepository.getSimulation().getRover(0);

        String expectedString = "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [5,6]\n" +
                "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [5,7]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.FORWARD, 2)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverDown2WithinBounds_thenYMin2(){

        simulationRepository.getSimulation().addNewRover(new Coordinate(5,5));
        rover = simulationRepository.getSimulation().getRover(0);

        String expectedString = "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [5,4]\n" +
                "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [5,3]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.BACKWARD, 2)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverUp2OutOfBounds_thenYLeapsOver(){
        simulationRepository.getSimulation().addNewRover(new Coordinate(5,9));
        rover = simulationRepository.getSimulation().getRover(0);

        String expectedString = "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [5,10]\n" +
                "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [5,0]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.FORWARD, 2)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverDown2OutOfBounds_thenYLeapsOver(){
        simulationRepository.getSimulation().addNewRover(new Coordinate(5,1));
        rover = simulationRepository.getSimulation().getRover(0);

        String expectedString = "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [5,0]\n" +
                "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [5,10]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.BACKWARD, 2)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverForwardFacingWest2OutOfBounds_thenXLeapsOver(){
        simulationRepository.getSimulation().addNewRover(new Coordinate(5,5));
        rover = simulationRepository.getSimulation().getRover(0);
        rover.setRoverState(new RoverState(rover.getId(), new Coordinate(9,5), Heading.EAST));

        String expectedString = "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [10,5]\n" +
                "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [0,5]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.FORWARD, 2)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverBackwardFacingWest2OutOfBounds_thenYXLeapsOver(){
        simulationRepository.getSimulation().addNewRover(new Coordinate(5,5));
        rover = simulationRepository.getSimulation().getRover(0);
        rover.setRoverState(new RoverState(rover.getId(),new Coordinate(1,5), Heading.EAST));

        String expectedString = "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [0,5]\n" +
                "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [10,5]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.BACKWARD, 2)).messageToBePrinted());
    }

    @Test
    void when1RoverCreatedHasIdR1(){

        simulationRepository.getSimulation().addNewRover(new Coordinate(5,5));
        rover = simulationRepository.getSimulation().getRover(0);

        assertEquals("R1", simulationRepository.getSimulation().getRoverId(0));
    }

    @Test
    void when2RoversCreated_thenBothHaveIds(){


        simulationRepository.getSimulation().addNewRover(new Coordinate(5,5));
        simulationRepository.getSimulation().addNewRover(new Coordinate(5,4));

        assertEquals("R1", simulationRepository.getSimulation().getRoverId(0));
        assertEquals("R2", simulationRepository.getSimulation().getRoverId(1));
    }

}