package io.tripled.marsrover;

import io.tripled.marsrover.command.Command;
import io.tripled.marsrover.input.InputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MarsRoverApplicationTests {

    @BeforeEach
    void init(){
        MarsRoverApplication.resetWorld();
    }

    @Test
    void helloWorld() {
        assertTrue(true, "A dummy test right here");
    }

    @Test
    void logoIsGunzLogo(){
        assertTrue(MarsRoverApplication.printLogo().contains("Gunz"));
    }

    @Test
    void givenWaitingForCoordinateAmount_whenValidValueEntered_thenParseValue(){


        String input = "5";
        assertEquals("Simulation with max coordinate [" + input + "] created successfully. Simulation contains [" + InputParser.parseInputForSimulationSize(input) + "] coordinates\n\n"+
                "[Please enter a command]",MarsRoverApplication.handleCommand(Command.SIMULATIONSIZE, input));
    }
    @Test
    void whenInvalidCoordValueEntered_Text_thenHandleCommand(){
        String input = "bad";
        assertEquals("[" + input + "] is an invalid Simulation maxCoordinate\n" +
                        "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                        "[Enter max coordinate] : ", MarsRoverApplication.handleCommand(Command.INVALID_VALUE,input));
    }

    @Test
    void whenInvalidCoordValueEntered_NegativeNumber_thenHandleCommand(){
        String input = "-45";
        assertEquals("[" + input + "] is an invalid Simulation maxCoordinate\n" +
                "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ", MarsRoverApplication.handleCommand(Command.INVALID_VALUE,input));
    }

    @Test
    void whenValidCoordValueEntered_thenMaxCoordsIsSet(){
        String input = "5";
        MarsRoverApplication.handleCommand(Command.SIMULATIONSIZE, input);
        assertTrue(MarsRoverApplication.isSimulationSizeSet());
    }

    @Test
    void whenEmptySimulationSizeEntered_thenHandleCommand(){
        String input = "";

        assertEquals("Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ", MarsRoverApplication.handleCommand(Command.EMPTY_SIMULATION_SIZE, input));
    }

    @Test
    void givenMaxCoordsSet_whenEmptyCommandEntered_thenShowHelpApi(){
        String input = "";
        MarsRoverApplication.handleCommand(Command.SIMULATIONSIZE, "5");
        assertTrue(MarsRoverApplication.handleCommand(Command.EMPTY_INPUT, input).contains("{P}"));
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        String input = "";
        MarsRoverApplication.handleCommand(Command.SIMULATIONSIZE, "5");
        assertTrue(MarsRoverApplication.handleCommand(Command.PRINT, input).contains("{P}"));
    }

    @Test
    void whenQCommandEntered_thenQuit(){
        String input = "Q";
        assertEquals("Quitting application", MarsRoverApplication.handleCommand(Command.QUIT, input));

    }

    @Test
    void whenLANDCommandEntered_thenLand(){
        String input = "Land 5 1";
        assertEquals("Rover R1 landed at (" + 5 + "," + 1 +") and is facing North\n\n[Please enter a command]", MarsRoverApplication.handleCommand(Command.LAND, input));
    }

    @Test
    void whenStateCommandEntered_thenPrintState(){

        String input = "state";
        MarsRoverApplication.handleCommand(Command.SIMULATIONSIZE, "10");
        MarsRoverApplication.handleCommand(Command.LAND, "land 5 5");
        assertEquals("Simulation has maxCoordinate 11.0 with a total of 121 coordinates.\n" +
                "Rover at Coordinates[x=5, y=5] is facing NORTH", MarsRoverApplication.handleCommand(Command.STATE, input));
    }

}
