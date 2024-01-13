package io.tripled.marsrover.ui.cli.validators;

import io.tripled.marsrover.businesslogic.rover.Coordinate;
import org.junit.jupiter.api.Test;

import static io.tripled.marsrover.ui.cli.validators.LandInputValidator.LAND_INPUT_VALIDATOR;
import static org.junit.jupiter.api.Assertions.*;

class LandInputValidatorTest {

    @Test
    void whenInputContainsXBiggerThanSqrtSimulationSize_thenFalseIfAllOtherChecksFail(){

        Coordinate coordinate = new Coordinate(12,2);
        assertFalse(LAND_INPUT_VALIDATOR.isValidCoordinateInput(coordinate, 10));
    }

    @Test
    void whenInputContainsXSmallerThan1_thenFalseIfAllOtherChecksFail(){

        Coordinate coordinate = new Coordinate(0,2);
        assertTrue(LAND_INPUT_VALIDATOR.isValidCoordinateInput(coordinate, 10));
    }

    @Test
    void whenInputContainsYBiggerThanSqrtSimulationSize_thenFalseIfAllOtherChecksFail(){

        Coordinate coordinate = new Coordinate(5,20);
        assertFalse(LAND_INPUT_VALIDATOR.isValidCoordinateInput(coordinate,10));
    }

    @Test
    void whenInputContainsYSmallerThan1_thenFalseIfAllOtherChecksFail(){

        Coordinate coordinate = new Coordinate(10,-5);
        assertFalse(LAND_INPUT_VALIDATOR.isValidCoordinateInput(coordinate, 10));
    }

    @Test
    void whenInputHasValidCoordinates_thenFalseIfAllOtherChecksFail(){

        Coordinate coordinate = new Coordinate(10,5);
        assertTrue(LAND_INPUT_VALIDATOR.isValidCoordinateInput(coordinate, 10));
    }

}