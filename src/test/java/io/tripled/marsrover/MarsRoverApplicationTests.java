package io.tripled.marsrover;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MarsRoverApplicationTests {

    @Test
    void helloWorld() {
        assertTrue(true, "A dummy test right here");
    }

    @Test
    void logoIsGunzLogo(){
        assertTrue(MarsRoverApplication.printLogo().contains("Gunz"));
    }

    @Test
    void showWorldInitTextOnBoot(){

        assertEquals("Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]", MarsRoverApplication.showWorldInitText());
    }

}
