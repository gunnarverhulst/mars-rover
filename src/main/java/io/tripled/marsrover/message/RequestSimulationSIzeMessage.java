package io.tripled.marsrover.message;

public class RequestSimulationSIzeMessage implements Message{

    private final String message = "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
            "[Enter max coordinate] : ";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
