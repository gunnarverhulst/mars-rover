package io.tripled.marsrover.cli.message;

import io.tripled.marsrover.MarsRoverApplication;
import io.tripled.marsrover.cli.message.messages.ApiMessage;
import io.tripled.marsrover.cli.message.messages.RequestSimulationSizeMessage;
import io.tripled.marsrover.cli.message.messages.SimulationSizeErrorMessage;
import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
import io.tripled.marsrover.service.simulation.SimulationRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessagePrinterTest {

    private final MarsRoverApplication marsRoverApplication = new MarsRoverApplication();
    private final SimulationRepository simulationRepository;
    private final MessagePrinter messagePrinter;

    public MessagePrinterTest() {
        simulationRepository = new InMemorySimulationRepository();
        messagePrinter = new MessagePrinter(simulationRepository);
    }

    private String input;

    @Test
    void requestSimulationSizeOnBoot(){
        assertEquals(new RequestSimulationSizeMessage().messageToBePrinted(),
                messagePrinter.requestSimulationSize().messageToBePrinted());
    }

    @Test
    void whenInvalidCoordValueEntered_Text_thenMessagePrinterPrints(){
        input = "bad";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                messagePrinter.simulationSizeErrorMessage(input).messageToBePrinted());
    }

    @Test
    void whenInvalidCoordValueEntered_NegativeNumber_thenHandleCommand(){
        input = "-45";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                messagePrinter.simulationSizeErrorMessage(input).messageToBePrinted());
    }

    @Test
    void whenEmptySimulationSizeEntered_thenHandleCommand(){
        input = "";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                messagePrinter.simulationSizeErrorMessage("").messageToBePrinted());
    }

    @Test
    void givenMaxCoordsSet_whenEmptyCommandEntered_thenShowHelpApi(){
        marsRoverApplication.handleCommand("5", marsRoverApplication);
        assertEquals(new ApiMessage().messageToBePrinted(),messagePrinter.apiMessage().messageToBePrinted() );
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        marsRoverApplication.handleCommand("5", marsRoverApplication);
        assertEquals(new ApiMessage().messageToBePrinted(),messagePrinter.apiMessage().messageToBePrinted() );
    }

}