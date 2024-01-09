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

    @Test
    void givenWaitingForCoordinateAmount_whenValidValueEntered_thenParseValue(){


        String inputValueString = "5";
        assertEquals(5,MarsRoverApplication.parseCoordinateValue(inputValueString));
    }

    @Test
    void whenValidCoordValueEntered_thenCalculateTotalAmountOfCoords(){
        assertEquals("36", MarsRoverApplication.calculateTotalAmountOfCoords("5"));
        assertEquals("121", MarsRoverApplication.calculateTotalAmountOfCoords("10"));
    }

}
