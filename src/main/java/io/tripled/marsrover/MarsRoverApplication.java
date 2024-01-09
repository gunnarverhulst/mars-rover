package io.tripled.marsrover;

import io.tripled.marsrover.command.Command;
import io.tripled.marsrover.input.InputParser;
import io.tripled.marsrover.message.MessagePrinter;
import io.tripled.marsrover.rover.RoverState;
import io.tripled.marsrover.validators.LandInputValidator;

import java.util.Optional;
import java.util.Scanner;

import static io.tripled.marsrover.command.Command.COMMAND;
import static io.tripled.marsrover.validators.LandInputValidator.LAND_INPUT_VALIDATOR;

public class MarsRoverApplication {


    private static int simulationSize = 0;
    private static Coordinate rover1Coordinate;
    private static RoverState roverState = null;

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
            case INVALID_LANDING -> handleFailedLanding(input);
            case STATE -> MessagePrinter.stateMessage(roverState);
            default -> MessagePrinter.apiMessage();
        };
    }

    private static String handleFailedLanding(String input) {
        LAND_INPUT_VALIDATOR.setSimulationSize(simulationSize);
        Optional<Coordinate> parsedInput = InputParser.parseInputForCoordinate(input);
        if(parsedInput.isPresent()){
            rover1Coordinate = parsedInput.get();
            return MessagePrinter.landingErrorMessage(rover1Coordinate);
        }

        return MessagePrinter.apiMessage();
    }

    private static String handleRoverLanding(String input) {
        LAND_INPUT_VALIDATOR.setSimulationSize(simulationSize);
        Optional<Coordinate> parsedInput = InputParser.parseInputForCoordinate(input);
        if(parsedInput.isPresent() && roverState == null){
            rover1Coordinate = parsedInput.get();
            roverState = new RoverState(simulationSize,rover1Coordinate);
            return MessagePrinter.landingMessage(rover1Coordinate);
        }

        return MessagePrinter.apiMessage();

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
