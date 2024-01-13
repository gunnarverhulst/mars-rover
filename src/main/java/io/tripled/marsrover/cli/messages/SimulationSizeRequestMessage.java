package io.tripled.marsrover.cli.messages;

import io.tripled.marsrover.service.message.messages.Message;

public class SimulationSizeRequestMessage implements Message {

    private final String message = """
            Determine the maxCoordinate of the simulation by setting the maximum size [0-100]
          
            "[Enter max coordinate] :""";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
