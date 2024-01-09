package io.tripled.marsrover.validators;

import io.tripled.marsrover.input.InputParser;
import org.junit.jupiter.api.Test;

import static io.tripled.marsrover.validators.LandInputValidator.LAND_INPUT_VALIDATOR;
import static org.junit.jupiter.api.Assertions.*;

class LandInputValidatorTest {

    @Test
    void whenInputDoesNotStartWithLand_thenFalse(){

        String input = "testland";
        assertFalse(LAND_INPUT_VALIDATOR.isValidLandInput(input));
    }

    @Test
    void whenInputContainsLand_thenFalseIfAllOtherChecksFail(){

        String input = "land";
        assertFalse(LAND_INPUT_VALIDATOR.isValidLandInput(input));
    }

    @Test
    void whenInputContainsOnlyX_thenFalseIfAllOtherChecksFail(){

        String input = "land 1";
        assertFalse(LAND_INPUT_VALIDATOR.isValidLandInput(input));
    }

    @Test
    void whenInputContainsXYZ_thenFalseIfAllOtherChecksFail(){

        String input = "land 1 2 3";
        assertFalse(LAND_INPUT_VALIDATOR.isValidLandInput(input));
    }

    @Test
    void whenInputContainsXBiggerThanSqrtSimulationSize_thenFalseIfAllOtherChecksFail(){
        LAND_INPUT_VALIDATOR.setSimulationSize(121);
        String input = "land 12 2";
        assertFalse(LAND_INPUT_VALIDATOR.isValidLandInput(input));
    }

    @Test
    void whenInputContainsXSmallerThan1_thenFalseIfAllOtherChecksFail(){
        LAND_INPUT_VALIDATOR.setSimulationSize(121);
        String input = "land 0 2";
        assertFalse(LAND_INPUT_VALIDATOR.isValidLandInput(input));
    }

    @Test
    void whenInputContainsYBiggerThanSqrtSimulationSize_thenFalseIfAllOtherChecksFail(){
        LAND_INPUT_VALIDATOR.setSimulationSize(121);
        String input = "land 5 20";
        assertFalse(LAND_INPUT_VALIDATOR.isValidLandInput(input));
    }

    @Test
    void whenInputContainsYSmallerThan1_thenFalseIfAllOtherChecksFail(){
        LAND_INPUT_VALIDATOR.setSimulationSize(121);
        String input = "land 10 -5";
        assertFalse(LAND_INPUT_VALIDATOR.isValidLandInput(input));
    }

    @Test
    void whenInputHasValidCoordinates_thenFalseIfAllOtherChecksFail(){
        LAND_INPUT_VALIDATOR.setSimulationSize(121);
        String input = "land 10 5";
        assertTrue(LAND_INPUT_VALIDATOR.isValidLandInput(input));
    }

}