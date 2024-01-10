package io.tripled.marsrover.cli.input;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.service.rover.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputParserTest {

    private String input;
    private Optional<Coordinate> coordinateReturned;

    @Test
    void whenValidSimulationSize_thenParseSimulationSize(){
        Optional optionalSimulationSize1 = InputParser.parseInputForSimulationSize("5");
        Optional optionalSimulationSize2 = InputParser.parseInputForSimulationSize("10");
        if(!optionalSimulationSize1.isEmpty())
            assertEquals(5, optionalSimulationSize1.get());
        if(!optionalSimulationSize2.isEmpty())
            assertEquals(10, optionalSimulationSize2.get());
    }

        @Test
    void whenInputDoesNotStartWithLand_thenFalse(){

        input = "testland";
        coordinateReturned = InputParser.parseInputForCoordinate(input,10);
        if (coordinateReturned.isPresent()) {
            assertEquals(Optional.empty(), coordinateReturned);
        }
    }

    @Test
    void whenValidLandInputOnlyLand_thenParseCoordinate(){

        input = "land";
        coordinateReturned = InputParser.parseInputForCoordinate(input,10);
        if (coordinateReturned.isPresent()) {
            assertEquals(Optional.empty(), coordinateReturned);
        }
    }

    @Test
    void whenValidLandInputOnlyX_thenParseCoordinate(){

        input = "land 1";

        coordinateReturned = InputParser.parseInputForCoordinate(input,10);
        if (coordinateReturned.isPresent()) {
            assertEquals(Optional.empty(), coordinateReturned);
        }
    }


    @Test
    void whenValidLandInput_thenParseCoordinate(){
        Coordinate coordinate = new Coordinate(5,1);
        input = "Land 5 1";

        coordinateReturned = InputParser.parseInputForCoordinate(input,10);
        if(coordinateReturned.isPresent()){
            assertEquals(coordinate.x(), coordinateReturned.get().x());
            assertEquals(coordinate.y(), coordinateReturned.get().y());

        } else
            assertEquals(Optional.empty(), coordinateReturned);
    }

    @Test
    void whenInputContainsXYZ_thenFalseIfAllOtherChecksFail() {

        input = "land 1 2 3";
        coordinateReturned = InputParser.parseInputForCoordinate(input,10);
        if (coordinateReturned.isPresent()) {
            assertEquals(Optional.empty(), coordinateReturned);
        }
    }

}