package io.tripled.marsrover.validators;

import org.junit.jupiter.api.Test;

import static io.tripled.marsrover.validators.SimulationSizeInputValidator.SIMULATIONSIZE_INPUT_VALIDATOR;
import static org.junit.jupiter.api.Assertions.*;

class SimulationSizeInputValidatorTest {

    @Test
    void checkForValidSimulationSize(){
        assertTrue(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("5"));
        assertTrue(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("0"));
        assertTrue(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("100"));
    }

    @Test
    void checkIfInputWord(){
        assertFalse(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("text"));
        assertFalse(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("teaaxt"));
        assertFalse(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("bad"));
        assertFalse(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("b564ad"));
    }

    @Test
    void checkIfInputNegativeOr0(){
        assertFalse(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("-45"));
        assertFalse(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("-1"));
    }

    @Test
    void checkIfInputEmpty(){
        assertFalse(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize(""));
    }

    @Test
    void checkForValidSimulationSize_whenInputTooBig(){
        assertFalse(SIMULATIONSIZE_INPUT_VALIDATOR.isValidSimulationSize("101"));
    }

}