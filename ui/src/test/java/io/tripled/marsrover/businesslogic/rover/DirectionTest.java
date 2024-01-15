package io.tripled.marsrover.businesslogic.rover;

import org.junit.jupiter.api.Test;

import static io.tripled.marsrover.businesslogic.rover.Direction.DIRECTION;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void whenParseDirectionF_thenGiveDirection(){
        assertEquals(Direction.FORWARD, DIRECTION.parseDirection("f"));
        assertEquals(Direction.FORWARD, DIRECTION.parseDirection("forward"));
    }

    @Test
    void whenParseDirectionL_thenGiveDirection(){
        assertEquals(Direction.LEFT, DIRECTION.parseDirection("l"));
        assertEquals(Direction.LEFT, DIRECTION.parseDirection("left"));
    }

    @Test
    void whenParseDirectionB_thenGiveDirection(){
        assertEquals(Direction.BACKWARD, DIRECTION.parseDirection("b"));
        assertEquals(Direction.BACKWARD, DIRECTION.parseDirection("backward"));
    }

    @Test
    void whenParseDirectionR_thenGiveDirection(){
        assertEquals(Direction.RIGHT, DIRECTION.parseDirection("r"));
        assertEquals(Direction.RIGHT, DIRECTION.parseDirection("right"));
    }

    @Test
    void whenParseDirectionAsText_thenGiveText(){
        assertEquals("forward", Direction.FORWARD.parseDirectionAsText());
        assertEquals("backward", Direction.BACKWARD.parseDirectionAsText());
        assertEquals("left", Direction.LEFT.parseDirectionAsText());
        assertEquals("right", Direction.RIGHT.parseDirectionAsText());
    }

}