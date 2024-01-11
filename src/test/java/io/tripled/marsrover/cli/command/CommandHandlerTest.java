package io.tripled.marsrover.cli.command;

import io.tripled.marsrover.MarsRoverApplication;
import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.messages.*;
import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Heading;
import io.tripled.marsrover.service.rover.RoverState;
import io.tripled.marsrover.service.simulation.SimulationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandHandlerTest {

    private final MarsRoverApplication marsRoverApplication = new MarsRoverApplication();
    private final SimulationRepository simulationRepository;
    private String input;

    private final CommandHandler commandHandler;

    public CommandHandlerTest() {
        simulationRepository = new InMemorySimulationRepository();
        this.commandHandler = new CommandHandler(simulationRepository);
    }

    @BeforeEach
    void init(){
    }

    @Test
    void whenValidSimulationSizeValueEntered_thenParseValue(){

        input = "5";
        Optional<Integer> optionalSimulationSize = InputParser.parseInputForSimulationSize(input);
        optionalSimulationSize.ifPresent(integer ->
                assertEquals(new SimulationSizeSetMessage(integer).messageToBePrinted(),
                    commandHandler.handlerBeforeSimulationSizeSet(input).messageToBePrinted()));
    }

    @Test
    void whenInvalidCoordValueEntered_Text_thenHandleCommand(){
        input = "bad";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                commandHandler.handlerBeforeSimulationSizeSet(input).messageToBePrinted());
    }

    @Test
    void whenInvalidCoordValueEntered_NegativeNumber_thenHandleCommand(){
        input = "-45";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                commandHandler.handlerBeforeSimulationSizeSet(input).messageToBePrinted());
    }

    @Test
    void whenValidCoordValueEntered_thenMaxCoordsIsSet(){
        input = "5";
        commandHandler.handlerBeforeSimulationSizeSet(input).messageToBePrinted();
        assertTrue(simulationRepository.getSimulation() != null);
    }

    @Test
    void whenEmptySimulationSizeEntered_thenHandleCommand(){
        input = "";

        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                commandHandler.handlerBeforeSimulationSizeSet(input).messageToBePrinted());
    }

    @Test
    void givenSimulationSizeSet_whenEmptyCommandEntered_thenShowHelpApi(){
        input = "";
        commandHandler.handlerBeforeSimulationSizeSet("10").messageToBePrinted();
        assertEquals(new ApiMessage().messageToBePrinted(),
                commandHandler.handlerAfterSimulationSizeSet(input).messageToBePrinted());
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        input = "P";
        commandHandler.handlerBeforeSimulationSizeSet("10").messageToBePrinted();
        assertEquals(new ApiMessage().messageToBePrinted(),
                commandHandler.handlerAfterSimulationSizeSet(input).messageToBePrinted());
    }

    @Test
    void whenQCommandEntered_thenQuit(){
        input = "Q";
        assertEquals(new QuitMessage().messageToBePrinted(),
                commandHandler.handlerAfterSimulationSizeSet(input).messageToBePrinted());

    }

    @Test
    void whenLANDCommandEntered_thenLand(){
        input = "Land 5 1";
        commandHandler.handlerBeforeSimulationSizeSet("10").messageToBePrinted();
        assertEquals(new LandingMessage(new Coordinate(5,1)).messageToBePrinted(),
                commandHandler.handlerAfterSimulationSizeSet(input).messageToBePrinted());
    }

    @Test
    void whenStateCommandEntered_thenPrintState(){

        input = "state";
        commandHandler.handlerBeforeSimulationSizeSet("10").messageToBePrinted();
        commandHandler.handlerAfterSimulationSizeSet("land 5 5");

        assertEquals(new StateMessage(10, new RoverState(new Coordinate(5,5), Heading.NORTH)).messageToBePrinted(),
                commandHandler.handlerAfterSimulationSizeSet(input).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_MoveOneForward_thenMoveRover(){
        input = "R1 f1";

        commandHandler.handlerBeforeSimulationSizeSet("10").messageToBePrinted();
        commandHandler.handlerAfterSimulationSizeSet("land 5 5");

        String expectedMessageString = """
                                       Rover R1 received instructions
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [5,6]
                                       Rover R1 executed all instructions. Awaiting new ones...
                                       """;

        assertEquals(expectedMessageString,
                commandHandler.handlerAfterSimulationSizeSet(input).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_MoveOneForward_OneLeft_thenMoveRover(){
        input = "R1 f1 l";

        commandHandler.handlerBeforeSimulationSizeSet("10").messageToBePrinted();
        commandHandler.handlerAfterSimulationSizeSet("land 5 5");

        String expectedMessageString = """
                                       Rover R1 received instructions
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [5,6]
                                       Rover R1 is turning left
                                       Rover R1 is now facing WEST
                                       Rover R1 executed all instructions. Awaiting new ones...
                                       """;

        assertEquals(expectedMessageString,
                commandHandler.handlerAfterSimulationSizeSet(input).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_MoveTwoForward_OneLeft_thenMoveRover(){
        input = "R1 f2 l";

        commandHandler.handlerBeforeSimulationSizeSet("10").messageToBePrinted();
        commandHandler.handlerAfterSimulationSizeSet("land 5 5");

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
                commandHandler.handlerAfterSimulationSizeSet(input).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_Movef4lb2r_thenMoveRover(){
        input = "R1 f4 l b2 r";

        commandHandler.handlerBeforeSimulationSizeSet("10").messageToBePrinted();
        commandHandler.handlerAfterSimulationSizeSet("land 5 5");

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
                commandHandler.handlerAfterSimulationSizeSet(input).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_CheckWorldIsRoundUp_thenMoveRover(){
        input = "R1 f3";

        commandHandler.handlerBeforeSimulationSizeSet("10").messageToBePrinted();
        commandHandler.handlerAfterSimulationSizeSet("land 0 9");

        String expectedMessageString = """
                                       Rover R1 received instructions
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [0,10]
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [0,0]
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [0,1]
                                       Rover R1 executed all instructions. Awaiting new ones...
                                       """;

        assertEquals(expectedMessageString,
                commandHandler.handlerAfterSimulationSizeSet(input).messageToBePrinted());
    }

}