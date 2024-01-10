package io.tripled.marsrover.command;

import io.tripled.marsrover.MarsRoverApplication;
import io.tripled.marsrover.input.InputParser;
import io.tripled.marsrover.message.messages.*;
import io.tripled.marsrover.rover.Coordinate;
import io.tripled.marsrover.rover.RoverState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static io.tripled.marsrover.command.CommandHandler.COMMAND_HANDLER;
import static org.junit.jupiter.api.Assertions.*;

class CommandHandlerTest {

    private String input;

    private final MarsRoverApplication marsRoverApplication = new MarsRoverApplication();

    @BeforeEach
    void init(){
    }

    @Test
    void whenValidSimulationSizeValueEntered_thenParseValue(){

        input = "5";
        Optional<Integer> optionalSimulationSize = InputParser.parseInputForSimulationSize(input);
        optionalSimulationSize.ifPresent(integer ->
                assertEquals(new SimulationSizeSetMessage(integer).messageToBePrinted(),
                        COMMAND_HANDLER.handleCommand(Command.SIMULATION_SIZE, input, marsRoverApplication.getSimulation()).messageToBePrinted()));
    }

    @Test
    void whenInvalidCoordValueEntered_Text_thenHandleCommand(){
        input = "bad";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                COMMAND_HANDLER.handleCommand(Command.SIMULATION_SIZE,input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenInvalidCoordValueEntered_NegativeNumber_thenHandleCommand(){
        input = "-45";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                COMMAND_HANDLER.handleCommand(Command.SIMULATION_SIZE,input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenValidCoordValueEntered_thenMaxCoordsIsSet(){
        input = "5";
        COMMAND_HANDLER.handleCommand(Command.SIMULATION_SIZE, input, marsRoverApplication.getSimulation());
        assertTrue(marsRoverApplication.isSimulationSizeSet());
    }

    @Test
    void whenEmptySimulationSizeEntered_thenHandleCommand(){
        input = "";

        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                COMMAND_HANDLER.handleCommand(Command.SIMULATION_SIZE, input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void givenMaxCoordsSet_whenEmptyCommandEntered_thenShowHelpApi(){
        input = "";
        COMMAND_HANDLER.handleCommand(Command.SIMULATION_SIZE, "5", marsRoverApplication.getSimulation());
        assertEquals(new ApiMessage().messageToBePrinted(),
                COMMAND_HANDLER.handleCommand(Command.EMPTY_INPUT, input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        input = "P";
        COMMAND_HANDLER.handleCommand(Command.SIMULATION_SIZE, "5", marsRoverApplication.getSimulation());
        assertEquals(new ApiMessage().messageToBePrinted(),
                COMMAND_HANDLER.handleCommand(Command.PRINT, input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenQCommandEntered_thenQuit(){
        input = "Q";
        assertEquals(new QuitMessage().messageToBePrinted(),
                COMMAND_HANDLER.handleCommand(Command.QUIT, input, marsRoverApplication.getSimulation()).messageToBePrinted());

    }

    @Test
    void whenLANDCommandEntered_thenLand(){
        COMMAND_HANDLER.handleCommand(Command.SIMULATION_SIZE, "10", marsRoverApplication.getSimulation());
        input = "Land 5 1";
        assertEquals(new LandingMessage(new Coordinate(5,1)).messageToBePrinted(),
                COMMAND_HANDLER.handleCommand(Command.LAND, input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenStateCommandEntered_thenPrintState(){

        input = "state";
        COMMAND_HANDLER.handleCommand(Command.SIMULATION_SIZE, "10", marsRoverApplication.getSimulation());
        COMMAND_HANDLER.handleCommand(Command.LAND, "land 5 5", marsRoverApplication.getSimulation());

        assertEquals(new StateMessage(10, new RoverState(new Coordinate(5,5))).messageToBePrinted(),
                COMMAND_HANDLER.handleCommand(Command.STATE, input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

}