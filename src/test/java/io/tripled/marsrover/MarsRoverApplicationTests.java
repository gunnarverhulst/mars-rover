package io.tripled.marsrover;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.messages.*;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Heading;
import io.tripled.marsrover.service.rover.RoverState;
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
        optionalSimulationSize.ifPresent(integer ->
                assertEquals(new SimulationSizeSetMessage(integer).messageToBePrinted(),
                marsRoverApplication.handleCommand(input, marsRoverApplication).messageToBePrinted()));
    }
    @Test
    void whenInvalidCoordValueEntered_Text_thenHandleCommand(){
        input = "bad";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                marsRoverApplication.handleCommand(input, marsRoverApplication).messageToBePrinted());
    }

    @Test
    void whenInvalidCoordValueEntered_NegativeNumber_thenHandleCommand(){
        input = "-45";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                marsRoverApplication.handleCommand(input, marsRoverApplication).messageToBePrinted());
    }

    @Test
    void whenValidCoordValueEntered_thenMaxCoordsIsSet(){
        input = "5";
        marsRoverApplication.handleCommand(input, marsRoverApplication).messageToBePrinted();
        assertTrue(marsRoverApplication.isSimulationSizeSet());
    }

    @Test
    void whenEmptySimulationSizeEntered_thenHandleCommand(){
        input = "";

        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                marsRoverApplication.handleCommand(input, marsRoverApplication).messageToBePrinted());
    }

    @Test
    void givenMaxCoordsSet_whenEmptyCommandEntered_thenShowHelpApi(){
        input = "";
        marsRoverApplication.handleCommand("10", marsRoverApplication).messageToBePrinted();
        assertEquals(new ApiMessage().messageToBePrinted(),
                marsRoverApplication.handleCommand(input, marsRoverApplication).messageToBePrinted());
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        input = "P";
        marsRoverApplication.handleCommand("5", marsRoverApplication).messageToBePrinted();
        assertEquals(new ApiMessage().messageToBePrinted(),
                marsRoverApplication.handleCommand(input, marsRoverApplication).messageToBePrinted());
    }

    @Test
    void whenQCommandEntered_thenQuit(){
        input = "Q";
        assertEquals(new QuitMessage().messageToBePrinted(),
                marsRoverApplication.handleCommand(input, marsRoverApplication).messageToBePrinted());

    }

    @Test
    void whenLANDCommandEntered_thenLand(){
        marsRoverApplication.handleCommand("10", marsRoverApplication).messageToBePrinted();
        input = "Land 5 1";
        assertEquals(new LandingMessage(new Coordinate(5,1)).messageToBePrinted(),
                marsRoverApplication.handleCommand(input, marsRoverApplication).messageToBePrinted());
    }

    @Test
    void whenStateCommandEntered_thenPrintState(){

        input = "state";
        marsRoverApplication.handleCommand("10", marsRoverApplication).messageToBePrinted();
        marsRoverApplication.handleCommand("land 5 5", marsRoverApplication).messageToBePrinted();

        assertEquals(new StateMessage(10, new RoverState(new Coordinate(5,5), Heading.NORTH)).messageToBePrinted(),
                marsRoverApplication.handleCommand(input, marsRoverApplication).messageToBePrinted());
    }

}
