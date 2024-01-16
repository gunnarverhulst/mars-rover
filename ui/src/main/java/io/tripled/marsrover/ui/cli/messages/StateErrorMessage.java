package io.tripled.marsrover.ui.cli.messages;


import io.tripled.marsrover.api.message.Message;

public class StateErrorMessage implements Message {

    private final String message;

    public StateErrorMessage(int simulationSize) {
        message = "Simulation has max coordinate [" + simulationSize + "] with a total of " + ((simulationSize + 1) + (simulationSize + 1)) + " coordinates.\n\n" +
                "[Please enter a command] : ";
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }
}
