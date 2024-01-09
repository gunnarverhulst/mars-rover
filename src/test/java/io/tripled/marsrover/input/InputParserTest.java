package io.tripled.marsrover.input;

import io.tripled.marsrover.MarsRoverApplication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {
    @Test
    void whenValidCoordValueEntered_thenCalculateTotalAmountOfCoords(){
        assertEquals(36, InputParser.parseInputForSimulationSize("5"));
        assertEquals(121, InputParser.parseInputForSimulationSize("10"));
    }


}