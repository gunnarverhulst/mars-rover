package io.tripled.marsrover;

import io.tripled.marsrover.cli.command.CommandHandler;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.LogoMessage;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.Scanner;


public class MarsRoverApplication {

    private final SimulationRepository simulationRepository;
    private final CommandHandler commandHandler;

    private final MessagePrinter messagePrinter;

    public MarsRoverApplication() {
        simulationRepository = new InMemorySimulationRepository();
        this.commandHandler = new CommandHandler(simulationRepository);
        messagePrinter = new MessagePrinter(simulationRepository);
    }

    public static void main(String[] args) {
        MarsRoverApplication marsRoverApplication = new MarsRoverApplication();

        printLogo();
        marsRoverApplication.readInput();
    }

    public static void printLogo() {
        Message logo = new LogoMessage();
        System.out.println(logo.messageToBePrinted());
    }

    public String readInput() {
        final MarsRoverApplication marsRoverApplication = new MarsRoverApplication();
        System.out.println(messagePrinter.requestSimulationSize().messageToBePrinted());
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                input = scanner.nextLine();

                Message output = marsRoverApplication.handleCommand(input, marsRoverApplication);

                System.out.println(output.messageToBePrinted());

            }
            while (!isQuit(input));
        }
        System.out.println("*********END*****************");
        return input;
    }

    public Message handleCommand(String input, MarsRoverApplication marsRoverApplication) {
        Message output;
        if (!isQuit(input)) {

            if (!marsRoverApplication.isSimulationSizeSet()) {

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
        return simulationRepository.getSimulation() != null;
    }

    //    public Message handleCommand(Command command, String input) {
//
//        return COMMAND_HANDLER.handleCommand(command, input, simulation);
//    }
    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }
}
