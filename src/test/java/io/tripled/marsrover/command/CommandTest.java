package io.tripled.marsrover.command;

import io.tripled.marsrover.validators.LandInputValidator;
import org.junit.jupiter.api.Test;

import static io.tripled.marsrover.command.Command.COMMAND;
import static io.tripled.marsrover.validators.LandInputValidator.LAND_INPUT_VALIDATOR;
import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void canParseQuitString(){
        assertEquals(Command.QUIT, COMMAND.parse("q"));
    }

    @Test
    void canParseSimulationSize(){
        assertEquals(Command.SIMULATIONSIZE, COMMAND.parseSimulationSize("5"));
    }

    @Test
    void canDetectInvalidValue_Text(){
        assertEquals(Command.INVALID_VALUE, COMMAND.parseSimulationSize("bad"));
    }

    @Test
    void canDetectInvalidCoordsValue_NegativeNumber(){
        assertEquals(Command.INVALID_VALUE, COMMAND.parseSimulationSize("-45"));
    }

    @Test
    void canDetectInvalidCoordsValue_EmptyString(){
        assertEquals(Command.EMPTY_SIMULATION_SIZE, COMMAND.parseSimulationSize(""));
    }

    @Test
    void whenInputP_thenParsedPRINT(){
        assertEquals(Command.PRINT, COMMAND.parse("p"));
        assertEquals(Command.PRINT, COMMAND.parse("P"));
    }

    @Test
    void whenInputLandWithinSim_thenParsedLAND(){

        LAND_INPUT_VALIDATOR.setSimulationSize(121);
        assertEquals(Command.LAND, COMMAND.parse("lAND 10 10"));
    }


}