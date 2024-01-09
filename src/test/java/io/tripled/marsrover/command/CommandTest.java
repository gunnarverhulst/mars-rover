package io.tripled.marsrover.command;

import org.junit.jupiter.api.Test;

import static io.tripled.marsrover.command.Command.COMMAND;
import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void canParseQuitString(){
        assertEquals(Command.QUIT, COMMAND.parse("q"));
    }

    @Test
    void canParseSimulationSize(){
        assertEquals(Command.COORDS_VALUE, COMMAND.parseSimulationSize("5"));
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
        assertEquals(Command.EMPTY_INPUT, COMMAND.parseSimulationSize(""));
    }

    @Test
    void whenInputP_thenParsedPRINT(){
        assertEquals(Command.PRINT, COMMAND.parse("p"));
        assertEquals(Command.PRINT, COMMAND.parse("P"));
    }

    @Test
    void whenInputLandWithinSim_thenParsedLAND(){
        assertEquals(Command.LAND, COMMAND.parse("land 10 10"));
    }

}