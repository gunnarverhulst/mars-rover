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


    private static int simulationSize = 0;
    private static Coordinate rover1Coordinate = null;
    private static RoverState rover1State = null;

    private static Rover rover1 = new Rover();

    public static void main(String[] args) {
        printLogo();
        readInput();
    }

    public static void printLogo() {
        Message logo = new LogoMessage();
        System.out.println(logo.messageToBePrinted());
    }

    public static String readInput() {
        System.out.println(MessagePrinter.requestSimulationSize().messageToBePrinted());
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                input = scanner.nextLine();

                Message output;
                if (!isQuit(input)){

                    if(!isSimulationSizeSet()){
                        Command command = COMMAND.parseSimulationSize(input);
                        output = handleCommand(command, input);
                    } else {
                        Command command = COMMAND.parse(input);
                        output = handleCommand(command, input);
                    }

                } else {
                    Command command = COMMAND.parse(input);
                    output = handleCommand(command, input);
                }
                System.out.println(output.messageToBePrinted());

            }
            while (!isQuit(input));
        }
        System.out.println("*********END*****************");
        return input;
    }

    public static boolean isSimulationSizeSet() {
        return simulationSize > 0;
    }

    public static Message handleCommand(Command command, String input) {
        return switch (command){
            case QUIT -> MessagePrinter.quitMessage();
            case SIMULATION_SIZE -> handleSimulationSize(input);
//            case SIMULATION_SIZE -> rover1.handleSimulationSize(input);
            case EMPTY_INPUT -> MessagePrinter.apiMessage();
            case LAND -> handleRoverLanding(input);
//            case LAND -> rover1.handleRoverLanding(input);
            case PRINT -> MessagePrinter.apiMessage();
            case STATE -> MessagePrinter.stateMessage(rover1State);
            default -> MessagePrinter.apiMessage();
        };
    }

    private static Message handleRoverLanding(String input) {

        if(rover1Coordinate == null) {

            Optional<Coordinate> parsedInput = InputParser.parseInputForCoordinate(input.toLowerCase(), simulationSize);
            if(parsedInput.isPresent() ){
                rover1Coordinate = parsedInput.get();
                if(rover1State == null){
                    rover1State = new RoverState(simulationSize,rover1Coordinate);
                    return MessagePrinter.landingMessage(rover1Coordinate);
                }
            }

            return MessagePrinter.landingErrorMessage(rover1Coordinate);
        }
        return MessagePrinter.landingAlreadyLandedMessage();

    }

    private static Message handleSimulationSize(String input) {
        Optional<Integer> simulationSizeOptional = InputParser.parseInputForSimulationSize(input);
        if(simulationSizeOptional.isPresent()){
            simulationSize = simulationSizeOptional.get();

            return MessagePrinter.simulationSizeSetMessage(simulationSize);
        }
        return MessagePrinter.simulationSizeErrorMessage(input);
    }

    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }

    public static void resetWorld(){
        simulationSize = 0;
        rover1State = null;
        rover1Coordinate = null;
    }
}
