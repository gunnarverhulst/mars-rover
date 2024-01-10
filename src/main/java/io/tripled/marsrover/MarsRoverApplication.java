package io.tripled.marsrover;

import io.tripled.marsrover.cli.message.messages.LogoMessage;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.service.simulation.Simulation;

import java.util.Scanner;

import static io.tripled.marsrover.cli.command.CommandHandler.COMMAND_HANDLER;

public class MarsRoverApplication {

    public Simulation getSimulation() {
        return simulation;
    }

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
        if (!isQuit(input)){

            if(!marsRoverApplication.isSimulationSizeSet()){

                output = COMMAND_HANDLER.handlerBeforeSimulationSizeSet(input, marsRoverApplication.getSimulation());

            } else {
                output = COMMAND_HANDLER.handlerAfterSimulationSizeSet(input, marsRoverApplication.getSimulation());
            }

        } else {
            output = COMMAND_HANDLER.handlerAfterSimulationSizeSet(input, marsRoverApplication.getSimulation());
        }
        return output;
    }

    public boolean isSimulationSizeSet() {
        return simulation.getSimulationSize() > 0;
    }

//    public Message handleCommand(Command command, String input) {
//
//        return COMMAND_HANDLER.handleCommand(command, input, simulation);
//    }
    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }
}
