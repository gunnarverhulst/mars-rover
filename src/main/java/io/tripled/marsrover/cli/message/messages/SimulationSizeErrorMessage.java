package io.tripled.marsrover.cli.message.messages;

public class SimulationSizeErrorMessage implements Message{

    private final String message;

    public SimulationSizeErrorMessage(String input) {
        this.message = "[" + input + "] is an invalid Simulation maxCoordinate\n" +
                "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ";
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }
}