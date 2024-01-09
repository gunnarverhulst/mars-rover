package io.tripled.marsrover;

import io.tripled.marsrover.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MarsRoverApplicationTests {

    @Test
    void helloWorld() {
        assertTrue(true, "A dummy test right here");
    }

    @Test
    void logoIsGunzLogo(){
        assertTrue(MarsRoverApplication.printLogo().contains("Gunz"));
    }

    @Test
    void showWorldInitTextOnBoot(){
        assertEquals("Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n[Enter max coordinate] : ", MarsRoverApplication.showWorldInitText());
    }

    @Test
    void givenWaitingForCoordinateAmount_whenValidValueEntered_thenParseValue(){


        String inputValueString = "5";
        assertEquals(5,MarsRoverApplication.parseCoordinateValue(inputValueString));
    }

    @Test
    void whenValidCoordValueEntered_thenCalculateTotalAmountOfCoords(){
        assertEquals("36", MarsRoverApplication.calculateTotalAmountOfCoords("5"));
        assertEquals("121", MarsRoverApplication.calculateTotalAmountOfCoords("10"));
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
        MarsRoverApplication.handleCommand(Command.COORDS_VALUE, input);
        assertTrue(MarsRoverApplication.maxCoordsHasValue());
    }

    @Test
    void whenEmptyCoordValueEntered_thenHandleCommand(){
        String input = "";

        assertEquals("Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ", MarsRoverApplication.handleCommand(Command.EMPTY_INPUT, input));
    }

    @Test
    void givenMaxCoordsSet_whenEmptyCommandEntered_thenShowHelpApi(){
        String input = "";
        MarsRoverApplication.handleCommand(Command.COORDS_VALUE, "5");
        assertTrue(MarsRoverApplication.handleCommand(Command.EMPTY_INPUT, input).contains("{P}"));
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        String input = "";
        MarsRoverApplication.handleCommand(Command.COORDS_VALUE, "5");
        assertTrue(MarsRoverApplication.handleCommand(Command.PRINT, input).contains("{P}"));
    }

}
