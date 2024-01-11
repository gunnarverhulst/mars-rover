package io.tripled.marsrover.cli.input;

import io.tripled.marsrover.cli.command.CommandHandler;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
import io.tripled.marsrover.service.simulation.Simulation;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.Scanner;

public class InputReader {
    public final SimulationRepository simulationRepository;
    public final CommandHandler commandHandler;
    public final MessagePrinter messagePrinter;

    public InputReader() {
        this.simulationRepository = new InMemorySimulationRepository();
        this.commandHandler = new CommandHandler(simulationRepository);
        this.messagePrinter = new MessagePrinter(simulationRepository);
    }

    public void readInput() {
        System.out.println(messagePrinter.requestSimulationSize().messageToBePrinted());
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                input = scanner.nextLine();

                Message output = handleCommand(input);

                System.out.println(output.messageToBePrinted());

            }
            while (!isQuit(input));
        }
        System.out.println("*********END*****************");
    }

    public Message handleCommand(String input) {
        Message output;
        if (!isQuit(input)) {

            if (!isSimulationSizeSet()) {

                output = commandHandler.handlerBeforeSimulationSizeSet(input);

            } else {
                output = commandHandler.handlerAfterSimulationSizeSet(input);
            }

        } else {
            output = commandHandler.handlerAfterSimulationSizeSet(input);
        }
        return output;
    }

    public boolean isSimulationSizeSet() {

        Simulation simulation = commandHandler.getSimulation();
        return simulation != null;
    }


    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }
}