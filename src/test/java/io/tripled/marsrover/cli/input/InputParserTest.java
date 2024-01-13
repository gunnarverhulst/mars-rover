package io.tripled.marsrover.cli.input;

import io.tripled.marsrover.cli.messages.*;
import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
import io.tripled.marsrover.service.rover.Coordinate;
import io.tripled.marsrover.service.rover.Heading;
import io.tripled.marsrover.service.rover.RoverState;
import io.tripled.marsrover.service.simulation.SimulationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InputParserTest {

    private final SimulationRepository simulationRepository;
    private String input;

    private final InputParser inputParser;

    public InputParserTest() {
        simulationRepository = new InMemorySimulationRepository();
        inputParser = new InputParser(simulationRepository);
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
                    inputParser.determineCommand(input).messageToBePrinted()));
    }

    @Test
    void whenInvalidSimulationSizeEntered_Text_thenHandleCommand(){
        input = "bad";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void whenInvalidSimulationSizeEntered_NegativeNumber_thenHandleCommand(){
        input = "-45";
        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void whenValidSimulationSIzeEntered_thenMaxSimulationSizeIsSet(){
        input = "5";
        inputParser.determineCommand(input).messageToBePrinted();
        assertNotNull(simulationRepository.getSimulation());
    }

    @Test
    void whenEmptySimulationSizeEntered_thenHandleCommand(){
        input = "";

        assertEquals(new SimulationSizeErrorMessage(input).messageToBePrinted(),
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void givenSimulationSizeSet_whenEmptyCommandEntered_thenShowHelpApi(){
        input = "";
        inputParser.determineCommand("10").messageToBePrinted();
        assertEquals(new ApiMessage().messageToBePrinted(),
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void whenPCommandEntered_thenShowHelpApi(){
        input = "P";
        inputParser.determineCommand("10").messageToBePrinted();
        assertEquals(new ApiMessage().messageToBePrinted(),
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void whenQCommandEntered_thenQuit(){
        input = "Q";
        assertEquals(new QuitMessage().messageToBePrinted(),
                inputParser.determineCommand(input).messageToBePrinted());

    }

    @Test
    void whenInputDoesNotStartWithLand_thenFalse(){

        input = "testland";
        Optional<Coordinate> coordinateReturned = inputParser.parseInputForCoordinate(input);
        if (coordinateReturned.isPresent()) {
            assertEquals(Optional.empty(), coordinateReturned);
        }
    }

    @Test
    void whenValidLandInputOnlyLand_thenParseCoordinate(){

        input = "land";
        Optional<Coordinate> coordinateReturned = inputParser.parseInputForCoordinate(input);
        if (coordinateReturned.isPresent()) {
            assertEquals(Optional.empty(), coordinateReturned);
        }
    }

    @Test
    void whenValidLandInputOnlyX_thenParseCoordinate(){

        input = "land 1";

        Optional<Coordinate> coordinateReturned = inputParser.parseInputForCoordinate(input);
        if (coordinateReturned.isPresent()) {
            assertEquals(Optional.empty(), coordinateReturned);
        }
    }


    @Test
    void whenValidLandInput_thenParseCoordinate(){
        Coordinate coordinate = new Coordinate(5,1);
        input = "Land 5 1";

        Optional<Coordinate> coordinateReturned = inputParser.parseInputForCoordinate(input);
        if(coordinateReturned.isPresent()){
            assertEquals(coordinate.x(), coordinateReturned.get().x());
            assertEquals(coordinate.y(), coordinateReturned.get().y());

        } else
            assertEquals(Optional.empty(), coordinateReturned);
    }

    @Test
    void whenInputContainsXYZ_thenFalseIfAllOtherChecksFail() {

        input = "land 1 2 3";
        Optional<Coordinate> coordinateReturned = inputParser.parseInputForCoordinate(input);
        if (coordinateReturned.isPresent()) {
            assertEquals(Optional.empty(), coordinateReturned);
        }
    }

    @Test
    void whenLANDCommandEntered_thenLand(){
        input = "Land 5 1";
        inputParser.determineCommand("10").messageToBePrinted();
        assertEquals(new RoverLandingMessage(new Coordinate(5,1)).messageToBePrinted(),
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void whenStateCommandEntered_thenPrintState(){

        input = "state";
        inputParser.determineCommand("10").messageToBePrinted();
        inputParser.determineCommand("land 5 5");

        assertEquals(new StateMessage(10, new RoverState(new Coordinate(5,5), Heading.NORTH)).messageToBePrinted(),
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_MoveOneForward_thenMoveRover(){
        input = "R1 f1";

        inputParser.determineCommand("10").messageToBePrinted();
        inputParser.determineCommand("land 5 5");

        String expectedMessageString = """
                                       Rover R1 received instructions
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [5,6]
                                       Rover R1 executed all instructions. Awaiting new ones...
                                       """;

        assertEquals(expectedMessageString,
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_MoveOneForward_OneLeft_thenMoveRover(){
        input = "R1 f1 l";

        inputParser.determineCommand("10").messageToBePrinted();
        inputParser.determineCommand("land 5 5");

        String expectedMessageString = """
                                       Rover R1 received instructions
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [5,6]
                                       Rover R1 is turning left
                                       Rover R1 is now facing WEST
                                       Rover R1 executed all instructions. Awaiting new ones...
                                       """;

        assertEquals(expectedMessageString,
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_MoveTwoForward_OneLeft_thenMoveRover(){
        input = "R1 f2 l";

        inputParser.determineCommand("10").messageToBePrinted();
        inputParser.determineCommand("land 5 5");

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
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_Movef4lb2r_thenMoveRover(){
        input = "R1 f4 l b2 r";

        inputParser.determineCommand("10").messageToBePrinted();
        inputParser.determineCommand("land 5 5");

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
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_CheckWorldIsRoundUp_thenMoveRover(){
        input = "R1 f3";

        inputParser.determineCommand("10").messageToBePrinted();
        inputParser.determineCommand("land 0 9");

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
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_CheckWorldIsRoundDown_thenMoveRover(){
        input = "R1 b3";

        inputParser.determineCommand("10").messageToBePrinted();
        inputParser.determineCommand("land 0 2");

        String expectedMessageString = """
                                       Rover R1 received instructions
                                       Rover R1 is moving backward
                                       Rover R1 is now located at [0,1]
                                       Rover R1 is moving backward
                                       Rover R1 is now located at [0,0]
                                       Rover R1 is moving backward
                                       Rover R1 is now located at [0,10]
                                       Rover R1 executed all instructions. Awaiting new ones...
                                       """;

        assertEquals(expectedMessageString,
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_CheckWorldIsRoundLeft_thenMoveRover(){
        input = "R1 l f3";

        inputParser.determineCommand("10");
        inputParser.determineCommand("land 1 2");

        String expectedMessageString = """
                                       Rover R1 received instructions
                                       Rover R1 is turning left
                                       Rover R1 is now facing WEST
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [0,2]
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [10,2]
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [9,2]
                                       Rover R1 executed all instructions. Awaiting new ones...
                                       """;

        assertEquals(expectedMessageString,
                inputParser.determineCommand(input).messageToBePrinted());
    }

    @Test
    void whenValidDriveCommandEntered_CheckWorldIsRoundRight_thenMoveRover(){
        input = "R1 r f3";

        inputParser.determineCommand("10").messageToBePrinted();
        inputParser.determineCommand("land 9 2");

        String expectedMessageString = """
                                       Rover R1 received instructions
                                       Rover R1 is turning right
                                       Rover R1 is now facing EAST
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [10,2]
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [0,2]
                                       Rover R1 is moving forward
                                       Rover R1 is now located at [1,2]
                                       Rover R1 executed all instructions. Awaiting new ones...
                                       """;

        assertEquals(expectedMessageString,
                inputParser.determineCommand(input).messageToBePrinted());
    }

}