package io.tripled.marsrover.message;

import io.tripled.marsrover.MarsRoverApplication;
import io.tripled.marsrover.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessagePrinterTest {

    private String input;

    @Test
    void requestSimulationSizeOnBoot(){
        assertEquals("Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n[Enter max coordinate] : ", MessagePrinter.requestSimulationSize());
    }

    @Test
    void whenInvalidCoordValueEntered_Text_thenMessagePrinterPrints(){
        input = "bad";
        assertEquals("[" + input + "] is an invalid Simulation maxCoordinate\n" +
                "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ", MessagePrinter.simulationSizeErrorMessage(input));
    }

    @Test
    void whenInvalidCoordValueEntered_NegativeNumber_thenHandleCommand(){
        input = "-45";
        assertEquals("[" + input + "] is an invalid Simulation maxCoordinate\n" +
                "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ", MessagePrinter.simulationSizeErrorMessage(input));
    }

    @Test
    void whenEmptySimulationSizeEntered_thenHandleCommand(){
        input = "";
        assertEquals("[" + input + "] is an invalid Simulation maxCoordinate\n" +
                "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ", MessagePrinter.simulationSizeErrorMessage(""));
    }

    @Test
    void givenMaxCoordsSet_whenEmptyCommandEntered_thenShowHelpApi(){
        MarsRoverApplication.handleCommand(Command.SIMULATION_SIZE, "5");
        assertEquals(new ApiMessage().messageToBePrinted(),MessagePrinter.apiMessage().messageToBePrinted() );
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        MarsRoverApplication.handleCommand(Command.SIMULATION_SIZE, "5");
        assertEquals(new ApiMessage().messageToBePrinted(),MessagePrinter.apiMessage().messageToBePrinted() );
    }

}