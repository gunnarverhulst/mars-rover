package io.tripled.marsrover.message;

import io.tripled.marsrover.MarsRoverApplication;
import io.tripled.marsrover.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessagePrinterTest {

    MarsRoverApplication marsRoverApplication = new MarsRoverApplication();

    private String input;

    @Test
    void requestSimulationSizeOnBoot(){
        assertEquals(new RequestSimulationSizeMessage().messageToBePrinted(),
                MessagePrinter.requestSimulationSize().messageToBePrinted());
    }

    @Test
    void whenInvalidCoordValueEntered_Text_thenMessagePrinterPrints(){
        input = "bad";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                MessagePrinter.simulationSizeErrorMessage(input).messageToBePrinted());
    }

    @Test
    void whenInvalidCoordValueEntered_NegativeNumber_thenHandleCommand(){
        input = "-45";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                MessagePrinter.simulationSizeErrorMessage(input).messageToBePrinted());
    }

    @Test
    void whenEmptySimulationSizeEntered_thenHandleCommand(){
        input = "";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                MessagePrinter.simulationSizeErrorMessage("").messageToBePrinted());
    }

    @Test
    void givenMaxCoordsSet_whenEmptyCommandEntered_thenShowHelpApi(){
        marsRoverApplication.handleCommand(Command.SIMULATION_SIZE, "5");
        assertEquals(new ApiMessage().messageToBePrinted(),MessagePrinter.apiMessage().messageToBePrinted() );
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        marsRoverApplication.handleCommand(Command.SIMULATION_SIZE, "5");
        assertEquals(new ApiMessage().messageToBePrinted(),MessagePrinter.apiMessage().messageToBePrinted() );
    }

}