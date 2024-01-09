package io.tripled.marsrover.command;

import org.junit.jupiter.api.Test;

import static io.tripled.marsrover.command.Command.COMMAND;
import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void canParseQuitString(){
        assertEquals(Command.QUIT, COMMAND.parse("q",1));
    }

    @Test
    void canParseCoordsValue(){
        assertEquals(Command.COORDS_VALUE, COMMAND.parse("5",0));
    }

    @Test
    void canDetectInvalidValue_Text(){
        assertEquals(Command.INVALID_VALUE, COMMAND.parse("bad",0));
    }

    @Test
    void canDetectInvalidCoordsValue_NegativeNumber(){
        assertEquals(Command.INVALID_VALUE, COMMAND.parse("-45",0));
    }

    @Test
    void canDetectInvalidCoordsValue_EmptyString(){
        assertEquals(Command.EMPTY_INPUT, COMMAND.parse("",0));
    }

    @Test
    void whenInputP_thenParsedPRINT(){
        assertEquals(Command.PRINT, COMMAND.parse("p",1));
        assertEquals(Command.PRINT, COMMAND.parse("P",1));
        assertEquals(Command.INVALID_VALUE, COMMAND.parse("p",0));
        assertEquals(Command.INVALID_VALUE, COMMAND.parse("P",0));
    }

    @Test
    void whenInputLandWithinSim_thenParsedLAND(){
        assertEquals(Command.LAND, COMMAND.parse("land 10 10", 1));
    }

}