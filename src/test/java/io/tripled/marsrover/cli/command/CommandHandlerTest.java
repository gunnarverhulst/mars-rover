package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.MarsRoverApplication;
import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.messages.*;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Heading;
import io.tripled.marsrover.service.rover.RoverState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static io.tripled.marsrover.cli.command.CommandHandler.COMMAND_HANDLER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
                    COMMAND_HANDLER.handlerBeforeSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted()));
    }

    @Test
    void whenInvalidCoordValueEntered_Text_thenHandleCommand(){
        input = "bad";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                COMMAND_HANDLER.handlerBeforeSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenInvalidCoordValueEntered_NegativeNumber_thenHandleCommand(){
        input = "-45";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                COMMAND_HANDLER.handlerBeforeSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenValidCoordValueEntered_thenMaxCoordsIsSet(){
        input = "5";
        COMMAND_HANDLER.handlerBeforeSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted();
        assertTrue(marsRoverApplication.isSimulationSizeSet());
    }

    @Test
    void whenEmptySimulationSizeEntered_thenHandleCommand(){
        input = "";

        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                COMMAND_HANDLER.handlerBeforeSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void givenSimulationSizeSet_whenEmptyCommandEntered_thenShowHelpApi(){
        input = "";
        COMMAND_HANDLER.handlerBeforeSimulationSizeSet("10", marsRoverApplication.getSimulation()).messageToBePrinted();
        assertEquals(new ApiMessage().messageToBePrinted(),
                COMMAND_HANDLER.handlerAfterSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        input = "P";
        COMMAND_HANDLER.handlerBeforeSimulationSizeSet("10", marsRoverApplication.getSimulation()).messageToBePrinted();
        assertEquals(new ApiMessage().messageToBePrinted(),
                COMMAND_HANDLER.handlerAfterSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenQCommandEntered_thenQuit(){
        input = "Q";
        assertEquals(new QuitMessage().messageToBePrinted(),
                COMMAND_HANDLER.handlerAfterSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted());

    }

    @Test
    void whenLANDCommandEntered_thenLand(){
        input = "Land 5 1";
        COMMAND_HANDLER.handlerBeforeSimulationSizeSet("10", marsRoverApplication.getSimulation()).messageToBePrinted();
        assertEquals(new LandingMessage(new Coordinate(5,1)).messageToBePrinted(),
                COMMAND_HANDLER.handlerAfterSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenStateCommandEntered_thenPrintState(){

        input = "state";
        COMMAND_HANDLER.handlerBeforeSimulationSizeSet("10", marsRoverApplication.getSimulation()).messageToBePrinted();
        COMMAND_HANDLER.handlerAfterSimulationSizeSet("land 5 5", marsRoverApplication.getSimulation());

        assertEquals(new StateMessage(10, new RoverState(new Coordinate(5,5), Heading.NORTH)).messageToBePrinted(),
                COMMAND_HANDLER.handlerAfterSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_MoveOneForward_thenMoveRover(){
        input = "R1 f1";

        COMMAND_HANDLER.handlerBeforeSimulationSizeSet("10", marsRoverApplication.getSimulation()).messageToBePrinted();
        COMMAND_HANDLER.handlerAfterSimulationSizeSet("land 5 5", marsRoverApplication.getSimulation());

        String expectedMessageString = """
                                       Rover R1 received instructions
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [5,6]
                                       Rover R1 executed all instructions. Awaiting new ones...
                                       """;

        assertEquals(expectedMessageString,
                COMMAND_HANDLER.handlerAfterSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_MoveOneForward_OneLeft_thenMoveRover(){
        input = "R1 f1 l";

        COMMAND_HANDLER.handlerBeforeSimulationSizeSet("10", marsRoverApplication.getSimulation()).messageToBePrinted();
        COMMAND_HANDLER.handlerAfterSimulationSizeSet("land 5 5", marsRoverApplication.getSimulation());

        String expectedMessageString = """
                                       Rover R1 received instructions
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [5,6]
                                       Rover R1 is turning left
                                       Rover R1 is now facing WEST
                                       Rover R1 executed all instructions. Awaiting new ones...
                                       """;

        assertEquals(expectedMessageString,
                COMMAND_HANDLER.handlerAfterSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_MoveTwoForward_OneLeft_thenMoveRover(){
        input = "R1 f2 l";

        COMMAND_HANDLER.handlerBeforeSimulationSizeSet("10", marsRoverApplication.getSimulation()).messageToBePrinted();
        COMMAND_HANDLER.handlerAfterSimulationSizeSet("land 5 5", marsRoverApplication.getSimulation());

        String expectedMessageString = """
                                       Rover R1 received instructions
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [5,6]
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [5,7]
                                       Rover R1 is turning left
                                       Rover R1 is now facing WEST
                                       Rover R1 executed all instructions. Awaiting new ones...
                                       """;

        assertEquals(expectedMessageString,
                COMMAND_HANDLER.handlerAfterSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_Movef4lb2r_thenMoveRover(){
        input = "R1 f4 l b2 r";

        COMMAND_HANDLER.handlerBeforeSimulationSizeSet("10", marsRoverApplication.getSimulation()).messageToBePrinted();
        COMMAND_HANDLER.handlerAfterSimulationSizeSet("land 5 5", marsRoverApplication.getSimulation());

        String expectedMessageString = """
            Rover R1 received instructions
            Rover R1 is moving forward
            Rover R1 is now located at [5,6]
            Rover R1 is moving forward
            Rover R1 is now located at [5,7]
            Rover R1 is moving forward
            Rover R1 is now located at [5,8]
            Rover R1 is moving forward
            Rover R1 is now located at [5,9]
            Rover R1 is turning left
            Rover R1 is now facing WEST
            Rover R1 is moving backward
            Rover R1 is now located at [6,9]
            Rover R1 is moving backward
            Rover R1 is now located at [7,9]
            Rover R1 is turning right
            Rover R1 is now facing NORTH
            Rover R1 executed all instructions. Awaiting new ones...
            """;

        assertEquals(expectedMessageString,
                COMMAND_HANDLER.handlerAfterSimulationSizeSet(input, marsRoverApplication.getSimulation()).messageToBePrinted());
    }




}