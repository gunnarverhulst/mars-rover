package io.tripled.marsrover;

import io.tripled.marsrover.command.Command;
import io.tripled.marsrover.input.InputParser;
import io.tripled.marsrover.message.MessagePrinter;

import java.util.Scanner;

import static io.tripled.marsrover.command.Command.COMMAND;

public class MarsRoverApplication {


    private static int simulationSize = 0;

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
                    //Vraag simulatie groote
                    //Command command = COMMAND.parse(input);
                    // command. doe uw ding
                    // print boodschappen van commanda af voor ebruiker


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

    private static boolean isSimulationSizeSet() {
        return simulationSize > 0;
    }

    public static String handleCommand(Command command, String input) {
        return switch (command){
            case QUIT -> MessagePrinter.quit();
            case SIMULATIONSIZE -> handleSimulationSize(input);
            case INVALID_VALUE -> MessagePrinter.invalidValue(input);
            case EMPTY_SIMULATION_SIZE -> MessagePrinter.simulationSizeErrorMessage();
            case EMPTY_INPUT -> MessagePrinter.apiMessage();
            default -> MessagePrinter.apiMessage();
        };
    }

    private static String handleSimulationSize(String input) {
        simulationSize = InputParser.parseInputForSimulationSize(input);
        return MessagePrinter.simulationSizeSetMessage(input, simulationSize);
    }

    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }

    public static int parseSimulationSize(String inputValueString) {
        return  Integer.parseInt(inputValueString);
    }
    public static boolean maxCoordsHasValue() {
        return simulationSize > 0;
    }

    public static void resetWorld(){
        simulationSize = 0;
    }
}
