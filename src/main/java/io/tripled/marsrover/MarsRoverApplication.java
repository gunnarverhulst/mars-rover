package io.tripled.marsrover;

import io.tripled.marsrover.command.Command;
import io.tripled.marsrover.input.InputParser;
import io.tripled.marsrover.message.Message;
import io.tripled.marsrover.message.MessagePrinter;
import io.tripled.marsrover.rover.Coordinate;
import io.tripled.marsrover.rover.RoverState;

import java.util.Optional;
import java.util.Scanner;

import static io.tripled.marsrover.command.Command.COMMAND;
import static io.tripled.marsrover.validators.LandInputValidator.LAND_INPUT_VALIDATOR;

public class MarsRoverApplication {


    private static int simulationSize = 0;
    private static Coordinate rover1Coordinate;
    private static RoverState rover1State = null;

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
        System.out.println(MessagePrinter.requestSimulationSize());
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
//            case EMPTY_INPUT -> MessagePrinter.apiMessage();
//            case LAND -> handleRoverLanding(input);
//            case STATE -> MessagePrinter.stateMessage(rover1State);
            default -> MessagePrinter.apiMessage();
        };
    }

    private static String handleRoverLanding(String input) {


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
    }
}
