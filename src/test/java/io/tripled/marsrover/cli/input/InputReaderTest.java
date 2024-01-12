package io.tripled.marsrover.cli.input;

import io.tripled.marsrover.cli.message.messages.*;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Heading;
import io.tripled.marsrover.service.rover.RoverState;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputReaderTest {

    private final InputReader inputReader;

    private String input = "";


    public InputReaderTest() {
        inputReader = new InputReader();
    }


    @Test
    void whenValidSimulationSizeValueEntered_thenParseValue(){

        input = "5";
        Optional<Integer> optionalSimulationSize = InputParser.parseInputForSimulationSize(input);
        optionalSimulationSize.ifPresent(integer ->
                assertEquals(new SimulationSizeSetMessage(integer).messageToBePrinted(),
                        inputReader.handleCommand(input).messageToBePrinted()));
    }
    @Test
    void whenInvalidCoordValueEntered_Text_thenHandleCommand(){
        input = "bad";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                inputReader.handleCommand(input).messageToBePrinted());
    }

    @Test
    void whenInvalidCoordValueEntered_NegativeNumber_thenHandleCommand(){
        input = "-45";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                inputReader.handleCommand(input).messageToBePrinted());
    }

    @Test
    void whenValidCoordValueEntered_thenMaxCoordsIsSet(){
        input = "5";
        inputReader.handleCommand(input).messageToBePrinted();
        assertTrue(inputReader.isSimulationSizeSet());
    }

    @Test
    void whenEmptySimulationSizeEntered_thenHandleCommand(){
        input = "";

        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                inputReader.handleCommand(input).messageToBePrinted());
    }

    @Test
    void givenMaxCoordsSet_whenEmptyCommandEntered_thenShowHelpApi(){
        input = "";
        inputReader.handleCommand("10").messageToBePrinted();
        assertEquals(new ApiMessage().messageToBePrinted(),
                inputReader.handleCommand(input).messageToBePrinted());
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        input = "P";
        inputReader.handleCommand("5").messageToBePrinted();
        assertEquals(new ApiMessage().messageToBePrinted(),
                inputReader.handleCommand(input).messageToBePrinted());
    }

    @Test
    void whenQCommandEntered_thenQuit(){
        input = "Q";
        assertEquals(new QuitMessage().messageToBePrinted(),
                inputReader.handleCommand(input).messageToBePrinted());

    }

    @Test
    void whenLANDCommandEntered_thenLand(){
        inputReader.handleCommand("10").messageToBePrinted();
        input = "Land 5 1";
        assertEquals(new LandingMessage(new Coordinate(5,1)).messageToBePrinted(),
                inputReader.handleCommand(input).messageToBePrinted());
    }

    @Test
    void whenStateCommandEntered_thenPrintState(){

        input = "state";
        inputReader.handleCommand("10").messageToBePrinted();
        inputReader.handleCommand("land 5 5").messageToBePrinted();

        assertEquals(new StateMessage(10, new RoverState(new Coordinate(5,5), Heading.NORTH)).messageToBePrinted(),
                inputReader.handleCommand(input).messageToBePrinted());
    }

//    @Test
//    void inputReader_readInputV2_Simsize(){
//
//    }

}