package io.tripled.marsrover;

import io.tripled.marsrover.command.Command;
import io.tripled.marsrover.input.InputParser;
import io.tripled.marsrover.message.*;
import io.tripled.marsrover.rover.Coordinate;
import io.tripled.marsrover.rover.RoverState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MarsRoverApplicationTests {

    private String input;

    private final MarsRoverApplication marsRoverApplication = new MarsRoverApplication();

    @BeforeEach
    void init(){
    }

    @Test
    void whenValidSimulationSizeValueEntered_thenParseValue(){

        input = "5";
        Optional<Integer> optionalSimulationSize = InputParser.parseInputForSimulationSize(input);
        if(! optionalSimulationSize.isEmpty()){
            assertEquals(new SimulationSizeSetMessage(optionalSimulationSize.get()).messageToBePrinted(),
                    marsRoverApplication.handleCommand(Command.SIMULATION_SIZE, input).messageToBePrinted());
        }
    }
    @Test
    void whenInvalidCoordValueEntered_Text_thenHandleCommand(){
        input = "bad";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                marsRoverApplication.handleCommand(Command.SIMULATION_SIZE,input).messageToBePrinted());
    }

    @Test
    void whenInvalidCoordValueEntered_NegativeNumber_thenHandleCommand(){
        input = "-45";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                marsRoverApplication.handleCommand(Command.SIMULATION_SIZE,input).messageToBePrinted());
    }

    @Test
    void whenValidCoordValueEntered_thenMaxCoordsIsSet(){
        input = "5";
        marsRoverApplication.handleCommand(Command.SIMULATION_SIZE, input);
        assertTrue(marsRoverApplication.isSimulationSizeSet());
    }

    @Test
    void whenEmptySimulationSizeEntered_thenHandleCommand(){
        input = "";

        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                marsRoverApplication.handleCommand(Command.SIMULATION_SIZE, input).messageToBePrinted());
    }

    @Test
    void givenMaxCoordsSet_whenEmptyCommandEntered_thenShowHelpApi(){
        input = "";
        marsRoverApplication.handleCommand(Command.SIMULATION_SIZE, "5");
        assertEquals(new ApiMessage().messageToBePrinted(),
                marsRoverApplication.handleCommand(Command.EMPTY_INPUT, input).messageToBePrinted());
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        input = "P";
        marsRoverApplication.handleCommand(Command.SIMULATION_SIZE, "5");
        assertEquals(new ApiMessage().messageToBePrinted(),
                marsRoverApplication.handleCommand(Command.PRINT, input).messageToBePrinted());
    }

    @Test
    void whenQCommandEntered_thenQuit(){
        input = "Q";
        assertEquals(new QuitMessage().messageToBePrinted(),
                marsRoverApplication.handleCommand(Command.QUIT, input).messageToBePrinted());

    }

    @Test
    void whenLANDCommandEntered_thenLand(){
        marsRoverApplication.handleCommand(Command.SIMULATION_SIZE, "10");
        input = "Land 5 1";
        assertEquals(new LandingMessage(new Coordinate(5,1)).messageToBePrinted(),
                marsRoverApplication.handleCommand(Command.LAND, input).messageToBePrinted());
    }

    @Test
    void whenStateCommandEntered_thenPrintState(){

        input = "state";
        marsRoverApplication.handleCommand(Command.SIMULATION_SIZE, "10");
        marsRoverApplication.handleCommand(Command.LAND, "land 5 5");

        assertEquals(new StateMessage(10, new RoverState(new Coordinate(5,5))).messageToBePrinted(),
                marsRoverApplication.handleCommand(Command.STATE, input).messageToBePrinted());
    }

}
