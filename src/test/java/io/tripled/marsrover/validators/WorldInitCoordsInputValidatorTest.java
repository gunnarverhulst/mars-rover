package io.tripled.marsrover.validators;

import org.junit.jupiter.api.Test;

import static io.tripled.marsrover.validators.WorldInitCoordsInputValidator.SIMULATIONSIZE_INPUT_VALIDATOR;
import static org.junit.jupiter.api.Assertions.*;

class WorldInitCoordsInputValidatorTest {

    @Test
    void checkIfInputValidCoordValue(){
        assertTrue(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("5"));
    }

    @Test
    void checkIfInputWord(){
        assertFalse(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("text"));
        assertFalse(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("teaaxt"));
        assertFalse(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("bad"));
    }

    @Test
    void checkIfInputNegative(){
        assertFalse(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("-45"));
    }

    @Test
    void checkIfInputEmpty(){
        assertFalse(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize(""));
    }



}