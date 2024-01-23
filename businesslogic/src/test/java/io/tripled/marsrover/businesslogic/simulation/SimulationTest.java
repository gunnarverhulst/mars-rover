package io.tripled.marsrover.businesslogic.simulation;

import io.tripled.marsrover.businesslogic.TestConfiguration;
import io.tripled.marsrover.vocabulary.rover.Coordinate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
class SimulationTest {

    private final Simulation simulationUnderTest;

    public SimulationTest() {
        this.simulationUnderTest = new Simulation(10);
    }

    @Test
    void givenCreationOf2Rovers_BothShouldHaveDifferentIds(){


        simulationUnderTest.addNewRover(new Coordinate(5,5));
        simulationUnderTest.addNewRover(new Coordinate(5,5));

        assertEquals(2, simulationUnderTest.getNumberOfRovers());
    }

}