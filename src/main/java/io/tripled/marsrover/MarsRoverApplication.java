package io.tripled.marsrover;

import io.tripled.marsrover.command.Command;
import io.tripled.marsrover.input.InputParser;
import io.tripled.marsrover.message.MessagePrinter;
import io.tripled.marsrover.validators.LandInputValidator;

import java.util.Scanner;

import static io.tripled.marsrover.command.Command.COMMAND;
import static io.tripled.marsrover.validators.LandInputValidator.LAND_INPUT_VALIDATOR;

public class MarsRoverApplication {


    private static int simulationSize = 0;
    private static Coordinate rover1Coordinate;

    public static void main(String[] args) {
        printLogo();
        readInput();
    }

    public static String printLogo() {
        String logo = """
                **************************"
                **    Gunz Rover        **"
                **************************""";
        System.out.println(logo);
        return logo;
    }

    public static String readInput() {
        System.out.println(MessagePrinter.simulationSizeErrorMessage());
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                input = scanner.nextLine();

                String output = "";
                if (!isQuit(input)){

                    if(!isSimulationSizeSet()){
                        Command command = COMMAND.parseSimulationSize(input);
                        output = handleCommand(command, input);
                    } else {
                        Command command = COMMAND.parse(input);
                        output = handleCommand(command, input);
                    }

                }
                System.out.println(output);

            }
            while (!isQuit(input));
        }
        System.out.println("*********END*****************");
        return input;
    }

    public static boolean isSimulationSizeSet() {
        return simulationSize > 0;
    }

    public static String handleCommand(Command command, String input) {
        return switch (command){
            case QUIT -> MessagePrinter.quit();
            case SIMULATIONSIZE -> handleSimulationSize(input);
            case INVALID_VALUE -> MessagePrinter.invalidValue(input);
            case EMPTY_SIMULATION_SIZE -> MessagePrinter.simulationSizeErrorMessage();
            case EMPTY_INPUT -> MessagePrinter.apiMessage();
            case LAND -> handleRoverLanding(input);
            default -> MessagePrinter.apiMessage();
        };
    }

    private static String handleRoverLanding(String input) {
        LAND_INPUT_VALIDATOR.setSimulationSize(simulationSize);
        rover1Coordinate = InputParser.parseInputForCoordinate(input);
        return "Rover R1 landed at (" + rover1Coordinate.x() + "," + rover1Coordinate.y() + ") and is facing North";
    }

    private static String handleSimulationSize(String input) {
        simulationSize = InputParser.parseInputForSimulationSize(input);
        LAND_INPUT_VALIDATOR.setSimulationSize(simulationSize);
        return MessagePrinter.simulationSizeSetMessage(input, simulationSize);
    }

    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }

    public static void resetWorld(){
        simulationSize = 0;
    }
}
