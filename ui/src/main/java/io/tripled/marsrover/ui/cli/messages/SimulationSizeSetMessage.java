package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.api.message.Message;

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
        return (int) Math.pow((simulationSize+1), 2);
    }
}
