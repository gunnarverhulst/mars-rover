package io.tripled.marsrover.cli.messages;

import io.tripled.marsrover.service.message.messages.Message;

public class SimulationSizeSetMessage implements Message {

    private final String message;


    public SimulationSizeSetMessage(int simulationSize) {
        message = "Simulation with max coordinate [" + simulationSize + "] created successfully. Simulation contains [" + calculateTotalNumberOfCoordinates(simulationSize) + "] coordinates\n\n"+
                "[Please enter a command]";
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }

    private static int calculateTotalNumberOfCoordinates(int simulationSize) {
        return (simulationSize + 1) * (simulationSize + 1);
    }
}
