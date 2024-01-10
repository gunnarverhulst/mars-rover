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

    @BeforeEach
    void init(){
        MarsRoverApplication.resetWorld();
    }

    @Test
    void whenValidSimulationSizeValueEntered_thenParseValue(){

        input = "5";
        Optional<Integer> optionalSimulationSize = InputParser.parseInputForSimulationSize(input);
        if(! optionalSimulationSize.isEmpty()){
            assertEquals(new SimulationSizeSetMessage(optionalSimulationSize.get()).messageToBePrinted(),
                    MarsRoverApplication.handleCommand(Command.SIMULATION_SIZE, input).messageToBePrinted());
        }
    }
    @Test
    void whenInvalidCoordValueEntered_Text_thenHandleCommand(){
        input = "bad";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                MarsRoverApplication.handleCommand(Command.SIMULATION_SIZE,input).messageToBePrinted());
    }

    @Test
    void whenInvalidCoordValueEntered_NegativeNumber_thenHandleCommand(){
        input = "-45";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                MarsRoverApplication.handleCommand(Command.SIMULATION_SIZE,input).messageToBePrinted());
    }

    @Test
    void whenValidCoordValueEntered_thenMaxCoordsIsSet(){
        input = "5";
        MarsRoverApplication.handleCommand(Command.SIMULATION_SIZE, input);
        assertTrue(MarsRoverApplication.isSimulationSizeSet());
    }

    @Test
    void whenEmptySimulationSizeEntered_thenHandleCommand(){
        input = "";

        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                MarsRoverApplication.handleCommand(Command.SIMULATION_SIZE, input).messageToBePrinted());
    }

    @Test
    void givenMaxCoordsSet_whenEmptyCommandEntered_thenShowHelpApi(){
        input = "";
        MarsRoverApplication.handleCommand(Command.SIMULATION_SIZE, "5");
        assertEquals(new ApiMessage().messageToBePrinted(),
                MarsRoverApplication.handleCommand(Command.EMPTY_INPUT, input).messageToBePrinted());
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        input = "P";
        MarsRoverApplication.handleCommand(Command.SIMULATION_SIZE, "5");
        assertEquals(new ApiMessage().messageToBePrinted(),
                MarsRoverApplication.handleCommand(Command.PRINT, input).messageToBePrinted());
    }

    @Test
    void whenQCommandEntered_thenQuit(){
        input = "Q";
        assertEquals(new QuitMessage().messageToBePrinted(),
                MarsRoverApplication.handleCommand(Command.QUIT, input).messageToBePrinted());

    }

    @Test
    void whenLANDCommandEntered_thenLand(){
        MarsRoverApplication.handleCommand(Command.SIMULATION_SIZE, "10");
        input = "Land 5 1";
//        assertEquals("Rover R1 landed at (" + 5 + "," + 1 +") and is facing North\n\n[Please enter a command]", MarsRoverApplication.handleCommand(Command.LAND, input));
        assertEquals(new LandingMessage(new Coordinate(5,1)).messageToBePrinted(),
                MarsRoverApplication.handleCommand(Command.LAND, input).messageToBePrinted());
    }

    @Test
    void whenStateCommandEntered_thenPrintState(){

        input = "state";
        MarsRoverApplication.handleCommand(Command.SIMULATION_SIZE, "10");
        MarsRoverApplication.handleCommand(Command.LAND, "land 5 5");

        assertEquals(new StateMessage(new RoverState(10, new Coordinate(5,5))).messageToBePrinted(),
                MarsRoverApplication.handleCommand(Command.STATE, input).messageToBePrinted());
    }

}
