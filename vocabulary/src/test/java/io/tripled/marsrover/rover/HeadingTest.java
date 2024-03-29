package io.tripled.marsrover.rover;

import io.tripled.marsrover.vocabulary.rover.Heading;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeadingTest {

    @Test
    void whenGetHeading0(){

        assertEquals(Heading.NORTH, Heading.getHeading(0));
    }
    @Test
    void whenGetHeading1(){

        assertEquals(Heading.EAST, Heading.getHeading(1));
    }
    @Test
    void whenGetHeading2(){

        assertEquals(Heading.SOUTH, Heading.getHeading(2));
    }
    @Test
    void whenGetHeading3(){

        assertEquals(Heading.WEST, Heading.getHeading(3));
    }

}