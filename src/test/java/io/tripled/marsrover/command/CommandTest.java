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

}