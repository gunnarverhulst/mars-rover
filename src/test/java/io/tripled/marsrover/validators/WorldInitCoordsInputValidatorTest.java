package io.tripled.marsrover.validators;

import org.junit.jupiter.api.Test;

import static io.tripled.marsrover.validators.WorldInitCoordsInputValidator.COORDS_INPUT_VALIDATOR;
import static org.junit.jupiter.api.Assertions.*;

class WorldInitCoordsInputValidatorTest {

    @Test
    void checkIfInputValidCoordValue(){
        assertTrue(COORDS_INPUT_VALIDATOR.isValidMaxCoordValue("5"));
    }

    @Test
    void checkIfInputWord(){
        assertFalse(COORDS_INPUT_VALIDATOR.isValidMaxCoordValue("text"));
        assertFalse(COORDS_INPUT_VALIDATOR.isValidMaxCoordValue("teaaxt"));
        assertFalse(COORDS_INPUT_VALIDATOR.isValidMaxCoordValue("bad"));
    }

    @Test
    void checkIfInputNegative(){
        assertFalse(COORDS_INPUT_VALIDATOR.isValidMaxCoordValue("-45"));
    }

    @Test
    void checkIfInputEmpty(){
        assertFalse(COORDS_INPUT_VALIDATOR.isValidMaxCoordValue(""));
    }



}