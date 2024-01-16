package io.tripled.marsrover.businesslogic.rover;

import io.tripled.marsrover.api.command.ApplicationService;
import io.tripled.marsrover.api.message.TransientMessage;
import io.tripled.marsrover.businesslogic.command.CommandController;
import io.tripled.marsrover.businesslogic.repository.InMemorySimulationRepository;
import io.tripled.marsrover.businesslogic.simulation.Simulation;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;
import io.tripled.marsrover.vocabulary.rover.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverTest {
    private Rover rover;

    @BeforeEach
    void init(){
        SimulationRepository simulationRepository = new InMemorySimulationRepository();
        ApplicationService applicationService = new CommandController(simulationRepository);

        simulationRepository.addSimulation(new Simulation(10, simulationRepository));

        rover = new Rover(10);

    }

    @Test
    void whenMoveRoverUp1WithinBounds_thenYMin1(){

        rover.setRoverState(new RoverState(new Coordinate(5,5), Heading.NORTH));
        String expectedString = "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [5,4]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.FORWARD, 1)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverDown1WithinBounds_thenYPlus1(){
        rover.setRoverState(new RoverState(new Coordinate(5,5), Heading.NORTH));

        String expectedString = "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [5,6]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.BACKWARD, 1)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverLeft(){
        rover.setRoverState(new RoverState(new Coordinate(5,5), Heading.NORTH));

        String expectedString = "Rover R1 is turning left\n" +
                "Rover R1 is now facing WEST\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.LEFT, 1)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverRight(){

        rover.setRoverState(new RoverState(new Coordinate(5,5), Heading.NORTH));

        String expectedString = "Rover R1 is turning right\n" +
                "Rover R1 is now facing EAST\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.RIGHT, 1)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverUp2WithinBounds_thenYMin2(){
        rover.setRoverState(new RoverState(new Coordinate(5,5), Heading.NORTH));
        String expectedString = "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [5,4]\n" +
                "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [5,3]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.FORWARD, 2)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverDown2WithinBounds_thenYPlus2(){
        rover.setRoverState(new RoverState(new Coordinate(5,5), Heading.NORTH));
        String expectedString = "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [5,6]\n" +
                "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [5,7]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.BACKWARD, 2)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverUp2OutOfBounds_thenYLeapsOver(){
        rover.setRoverState(new RoverState(new Coordinate(5,1), Heading.NORTH));

        String expectedString = "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [5,0]\n" +
                "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [5,10]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.FORWARD, 2)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverDown2OutOfBounds_thenYLeapsOver(){
        rover.setRoverState(new RoverState(new Coordinate(5,9), Heading.NORTH));

        String expectedString = "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [5,10]\n" +
                "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [5,0]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.BACKWARD, 2)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverForwardFacingWest2OutOfBounds_thenXLeapsOver(){
        rover.setRoverState(new RoverState(new Coordinate(9,5), Heading.EAST));

        String expectedString = "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [10,5]\n" +
                "Rover R1 is moving forward\n" +
                "Rover R1 is now located at [0,5]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.FORWARD, 2)).messageToBePrinted());
    }

    @Test
    void whenMoveRoverBackwardFacingWest2OutOfBounds_thenYXLeapsOver(){
        rover.setRoverState(new RoverState(new Coordinate(1,5), Heading.EAST));

        String expectedString = "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [0,5]\n" +
                "Rover R1 is moving backward\n" +
                "Rover R1 is now located at [10,5]\n";

        assertEquals(new TransientMessage(expectedString).messageToBePrinted(), rover.moveRover(new Move(Direction.BACKWARD, 2)).messageToBePrinted());
    }

}