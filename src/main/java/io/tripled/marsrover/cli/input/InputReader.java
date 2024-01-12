package io.tripled.marsrover.cli.input;

import io.tripled.marsrover.cli.command.Command;
import io.tripled.marsrover.cli.command.InputController;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.presenter.SimCreationConsolePresenterImpl;
import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
import io.tripled.marsrover.service.businessinterface.SimCreationPresenter;
import io.tripled.marsrover.service.command.SimCreationHandler;
import io.tripled.marsrover.service.simulation.Simulation;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.Scanner;

public class InputReader {
    public final SimulationRepository simulationRepository;
    public final InputController inputController;
    public final MessagePrinter messagePrinter;

    public InputReader() {
        this.simulationRepository = new InMemorySimulationRepository();
        this.inputController = new InputController(simulationRepository);
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

    public void readInputV2(){
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                input = scanner.nextLine();

                Command<?> command = InputParser.parse(input);

                inputController.handleCommand(command, new SimCreationConsolePresenterImpl());

            }
            while (!isQuit(input));
        }
    }

    public Message handleCommand(String input) {
        Message output;
        if (!isQuit(input)) {

            if (!isSimulationSizeSet()) {

                output = inputController.handlerBeforeSimulationSizeSet(input);

            } else {
                output = inputController.handlerAfterSimulationSizeSet(input);
            }

        } else {
            output = inputController.handlerAfterSimulationSizeSet(input);
        }
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