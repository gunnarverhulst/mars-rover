package io.tripled.marsrover.command;

import io.tripled.marsrover.message.Message;
import io.tripled.marsrover.message.MessagePrinter;
import io.tripled.marsrover.simulation.Simulation;

public enum CommandHandler {
    COMMAND_HANDLER;

    private Simulation simulation;


    public Message handleCommand(Command command, String input) {
        return switch (command){
            case QUIT -> MessagePrinter.quitMessage();
            case SIMULATION_SIZE -> simulation.handleSimulationSize(input);
            case EMPTY_INPUT -> MessagePrinter.apiMessage();
            case LAND -> simulation.handleRoverLanding(input);
            case PRINT -> MessagePrinter.apiMessage();
            case STATE -> MessagePrinter.stateMessage(simulation.getSimulationSize(), simulation.getRoverState());
            default -> MessagePrinter.apiMessage();
        };
    }
    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
