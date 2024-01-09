package io.tripled.marsrover.message;

import io.tripled.marsrover.MarsRoverApplication;
import io.tripled.marsrover.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessagePrinterTest {

    @Test
    void showWorldInitTextOnBoot(){
        assertEquals("Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n[Enter max coordinate] : ", MessagePrinter.simulationSizeErrorMessage());
    }

    @Test
    void whenInvalidCoordValueEntered_Text_thenMessagePrinterPrints(){
        String input = "bad";
        assertEquals("[" + input + "] is an invalid Simulation maxCoordinate\n" +
                "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ", MessagePrinter.invalidValue(input));
    }

    @Test
    void whenInvalidCoordValueEntered_NegativeNumber_thenHandleCommand(){
        String input = "-45";
        assertEquals("[" + input + "] is an invalid Simulation maxCoordinate\n" +
                "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ", MessagePrinter.invalidValue(input));
    }

    @Test
    void whenEmptyCoordValueEntered_thenHandleCommand(){

        assertEquals("Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ", MessagePrinter.simulationSizeErrorMessage());
    }

    @Test
    void givenMaxCoordsSet_whenEmptyCommandEntered_thenShowHelpApi(){
        MarsRoverApplication.handleCommand(Command.COORDS_VALUE, "5");
        assertTrue(MessagePrinter.apiMessage().contains("{P}"));
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        MarsRoverApplication.handleCommand(Command.COORDS_VALUE, "5");
        assertTrue(MessagePrinter.apiMessage().contains("{P}"));
    }

}