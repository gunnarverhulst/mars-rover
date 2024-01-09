package io.tripled.marsrover;

import org.junit.jupiter.api.Test;

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

}
