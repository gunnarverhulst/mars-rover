package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.businesslogic.message.Message;

public class SimulationSizeErrorMessage implements Message {

    private final String message;

    public SimulationSizeErrorMessage(String input) {
        this.message = "[" + input + "] is an invalid Simulation size\n\n" +
                "Determine the maxCoordinate of the simulation by setting the maximum size [0-100]\n\n" +
                "[Enter max coordinate] : ";
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }
}
