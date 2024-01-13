//package io.tripled.marsrover.cli.input;
//
//import io.tripled.marsrover.cli.message.messages.*;
//import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
//import io.tripled.marsrover.service.rover.Coordinate;
//import io.tripled.marsrover.service.rover.Heading;
//import io.tripled.marsrover.service.rover.RoverState;
//import io.tripled.marsrover.service.simulation.SimulationRepository;
//import org.junit.jupiter.api.Test;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class InputReaderTest {
//
//    private final SimulationRepository simulationRepository;
//    private final InputReader inputReader;
//
//    private String input = "";
//
//
//    public InputReaderTest() {
//        simulationRepository = new InMemorySimulationRepository();
//        inputReader = new InputReader(simulationRepository);
//    }
//
//    @Test
//    void whenInvalidCoordValueEntered_Text_thenHandleCommand(){
//        input = "bad";
//        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
//                inputReader.handleCommand(input).messageToBePrinted());
//    }
//
//    @Test
//    void whenInvalidCoordValueEntered_NegativeNumber_thenHandleCommand(){
//        input = "-45";
//        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
//                inputReader.handleCommand(input).messageToBePrinted());
//    }
//
//    @Test
//    void whenEmptySimulationSizeEntered_thenHandleCommand(){
//        input = "";
//
//        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
//                inputReader.handleCommand(input).messageToBePrinted());
//    }
//
//    @Test
//    void givenMaxCoordsSet_whenEmptyCommandEntered_thenShowHelpApi(){
//        input = "";
//        inputReader.handleCommand("10").messageToBePrinted();
//        assertEquals(new ApiMessage().messageToBePrinted(),
//                inputReader.handleCommand(input).messageToBePrinted());
//    }
//
//    @Test
//    void whenPCommandEntered_thenShowHelpApi(){
//        input = "P";
//        inputReader.handleCommand("5").messageToBePrinted();
//        assertEquals(new ApiMessage().messageToBePrinted(),
//                inputReader.handleCommand(input).messageToBePrinted());
//    }
//
//    @Test
//    void whenQCommandEntered_thenQuit(){
//        input = "Q";
//        assertEquals(new QuitMessage().messageToBePrinted(),
//                inputReader.handleCommand(input).messageToBePrinted());
//
//    }
//
//    @Test
//    void whenLANDCommandEntered_thenLand(){
//        inputReader.handleCommand("10").messageToBePrinted();
//        input = "Land 5 1";
//        assertEquals(new RoverLandingMessage(new Coordinate(5,1)).messageToBePrinted(),
//                inputReader.handleCommand(input).messageToBePrinted());
//    }
//
//    @Test
//    void whenStateCommandEntered_thenPrintState(){
//
//        input = "state";
//        inputReader.handleCommand("10").messageToBePrinted();
//        inputReader.handleCommand("land 5 5").messageToBePrinted();
//
//        assertEquals(new StateMessage(10, new RoverState(new Coordinate(5,5), Heading.NORTH)).messageToBePrinted(),
//                inputReader.handleCommand(input).messageToBePrinted());
//    }
//
////    @Test
////    void inputReader_readInputV2_Simsize(){
////
////    }
//
//}