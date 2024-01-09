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
    void canParseCoordsValue(){
        assertEquals(Command.COORDS_VALUE, COMMAND.parse("5"));
    }

    @Test
    void canDetectInvalidValue_Text(){
        assertEquals(Command.INVALID_VALUE, COMMAND.parse("bad"));
    }

    @Test
    void canDetectInvalidCoordsValue_NegativeNumber(){
        assertEquals(Command.INVALID_VALUE, COMMAND.parse("-45"));
    }

    @Test
    void canDetectInvalidCoordsValue_EmptyString(){
        assertEquals(Command.EMPTY_INPUT, COMMAND.parse(""));
    }


}