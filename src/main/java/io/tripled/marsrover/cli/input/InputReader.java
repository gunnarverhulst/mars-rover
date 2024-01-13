package io.tripled.marsrover.cli.input;

import io.tripled.marsrover.cli.command.InputController;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.service.simulation.Simulation;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.Scanner;

public class InputReader {
    public final SimulationRepository simulationRepository;
    public final InputController inputController;
    public final MessagePrinter messagePrinter;
    private final InputParser inputParser;

    public InputReader(SimulationRepository simulationRepository) {
        this.simulationRepository  = simulationRepository;
        this.inputController = new InputController(simulationRepository);
        this.messagePrinter = new MessagePrinter(simulationRepository);
        this.inputParser = new InputParser(simulationRepository);
    }

    public void readInput() {

        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                input = scanner.nextLine();

                handleCommand(input);



            }
            while (!isQuit(input));
        }

    }

    public Message handleCommand(String input) {
        System.out.println(messagePrinter.requestSimulationSize().messageToBePrinted());
        Message output;
        if (!isQuit(input)) {

            if (!isSimulationSizeSet()) {

                output = inputController.handlerBeforeSimulationSizeSet(input);

            } else {
                output = inputParser.determineCommand(input);
            }

        } else {
            output = messagePrinter.quitMessage();
        }
        System.out.println(output.messageToBePrinted());
        System.out.println("*********END*****************");
        return output;
    }

    public boolean isSimulationSizeSet() {

        Simulation simulation = inputController.getSimulation();
        return simulation != null;
    }


    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }
}