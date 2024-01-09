package io.tripled.marsrover.input;

import io.tripled.marsrover.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {
    @Test
    void whenValidSimulationSize_thenParseSimulationSize(){
        assertEquals(36, InputParser.parseInputForSimulationSize("5"));
        assertEquals(121, InputParser.parseInputForSimulationSize("10"));
    }

    @Test
    void whenValidLandInput_thenParseCoordinate(){
        Coordinate coordinate = new Coordinate(5,1);
        String input = "Land 5 1";
        assertEquals(coordinate.x(), InputParser.parseInputForCoordinate(input).get().x());
        assertEquals(coordinate.y(), InputParser.parseInputForCoordinate(input).get().y());
    }


}