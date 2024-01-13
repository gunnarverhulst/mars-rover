package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.input.InputParser;
import io.tripled.marsrover.cli.message.messages.SimulationSizeSetMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SimCreationConsolePresenterImplTest {
    private String input;

    private SimulationConsolePresenterImpl simCreationConsolePresenter;

    @BeforeEach
    void init(){
        simCreationConsolePresenter = new SimulationConsolePresenterImpl();
    }

    @Test
    void whenValidSimulationSizeValueEntered_thenParseValue(){

        input = "5";
        Optional<Integer> optionalSimulationSize = InputParser.parseInputForSimulationSize(input);
        optionalSimulationSize.ifPresent(integer ->
                assertEquals(new SimulationSizeSetMessage(integer).messageToBePrinted(),
                        simCreationConsolePresenter.simulationCreated(Integer.parseInt(input)).messageToBePrinted()));
    }

}