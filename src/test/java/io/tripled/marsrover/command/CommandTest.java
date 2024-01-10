package io.tripled.marsrover.command;

import org.junit.jupiter.api.Test;

import static io.tripled.marsrover.command.Command.COMMAND;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {

    @Test
    void canParseQuitString(){
        assertEquals(Command.QUIT, COMMAND.parse("q"));
    }

    @Test
    void canParseSimulationSize(){
        assertEquals(Command.SIMULATION_SIZE, COMMAND.parseSimulationSize("5"));
    }

    @Test
    void canDetectInvalidSimulationSizeValue_Text(){
        assertEquals(Command.SIMULATION_SIZE, COMMAND.parseSimulationSize("bad"));
    }

    @Test
    void canDetectInvalidCoordsValue_NegativeNumber(){
        assertEquals(Command.SIMULATION_SIZE, COMMAND.parseSimulationSize("-45"));
    }

    @Test
    void canDetectInvalidCoordsValue_EmptyString(){
        assertEquals(Command.SIMULATION_SIZE, COMMAND.parseSimulationSize(""));
    }

    @Test
    void whenInputP_thenParsedPRINT(){
        assertEquals(Command.PRINT, COMMAND.parse("p"));
        assertEquals(Command.PRINT, COMMAND.parse("P"));
    }

    @Test
    void whenInputLandWithinSim_thenParsedLAND(){

        assertEquals(Command.LAND, COMMAND.parse("lAND 10 10"));
    }

    @Test
    void whenInputState_thenParsedState(){

        assertEquals(Command.STATE, COMMAND.parse("state"));
    }


}