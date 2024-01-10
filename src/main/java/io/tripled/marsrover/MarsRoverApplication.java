package io.tripled.marsrover;

import io.tripled.marsrover.command.Command;
import io.tripled.marsrover.message.messages.LogoMessage;
import io.tripled.marsrover.message.messages.Message;
import io.tripled.marsrover.message.MessagePrinter;
import io.tripled.marsrover.simulation.Simulation;

import java.util.Scanner;

import static io.tripled.marsrover.command.Command.COMMAND;
import static io.tripled.marsrover.command.CommandHandler.COMMAND_HANDLER;

public class MarsRoverApplication {

    private final Simulation simulation = new Simulation();

    public static void main(String[] args) {
        printLogo();
        readInput();
    }

    public static void printLogo() {
        Message logo = new LogoMessage();
        System.out.println(logo.messageToBePrinted());
    }

    public static String readInput() {
        final MarsRoverApplication marsRoverApplication = new MarsRoverApplication();
        System.out.println(MessagePrinter.requestSimulationSize().messageToBePrinted());
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                input = scanner.nextLine();

                Message output;
                if (!isQuit(input)){

                    if(!marsRoverApplication.isSimulationSizeSet()){
                        Command command = COMMAND.parseSimulationSize(input);
                        output = marsRoverApplication.handleCommand(command, input);
                    } else {
                        Command command = COMMAND.parse(input);
                        output = marsRoverApplication.handleCommand(command, input);
                    }

                } else {
                    Command command = COMMAND.parse(input);
                    output = marsRoverApplication.handleCommand(command, input);
                }
                System.out.println(output.messageToBePrinted());

            }
            while (!isQuit(input));
        }
        System.out.println("*********END*****************");
        return input;
    }

    public boolean isSimulationSizeSet() {
        return simulation.getSimulationSize() > 0;
    }

    public Message handleCommand(Command command, String input) {

        return COMMAND_HANDLER.handleCommand(command, input, simulation);
    }
    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }
}
