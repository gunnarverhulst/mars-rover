package io.tripled.marsrover;

import io.tripled.marsrover.command.Command;
import io.tripled.marsrover.input.InputParser;
import io.tripled.marsrover.message.LogoMessage;
import io.tripled.marsrover.message.Message;
import io.tripled.marsrover.message.MessagePrinter;
import io.tripled.marsrover.rover.Coordinate;
import io.tripled.marsrover.rover.Rover;
import io.tripled.marsrover.rover.RoverState;

import java.util.Optional;
import java.util.Scanner;

import static io.tripled.marsrover.command.Command.COMMAND;

public class MarsRoverApplication {
    private Rover rover1 = new Rover();

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
        return rover1.getSimulationSize() > 0;
    }

    public Message handleCommand(Command command, String input) {
        return switch (command){
            case QUIT -> MessagePrinter.quitMessage();
            case SIMULATION_SIZE -> rover1.handleSimulationSize(input);
            case EMPTY_INPUT -> MessagePrinter.apiMessage();
            case LAND -> rover1.handleRoverLanding(input);
            case PRINT -> MessagePrinter.apiMessage();
            case STATE -> MessagePrinter.stateMessage(rover1.getRoverState());
            default -> MessagePrinter.apiMessage();
        };
    }
    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }
}
