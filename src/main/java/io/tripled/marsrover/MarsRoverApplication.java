package io.tripled.marsrover;

import io.tripled.marsrover.command.Command;
import io.tripled.marsrover.message.MessagePrinter;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.tripled.marsrover.message.MessagePrinter.MESSAGE_PRINTER;

public class MarsRoverApplication {


    private static int maxCoords = 0;

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
        System.out.println(showWorldInitText());
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                input = scanner.nextLine();

                String output = "";
                if (!isQuit(input)){
                    Command command = Command.COMMAND.parse(input);
                    output = handleCommand(command, input);
                }
                System.out.println(output);

            }
            while (!isQuit(input));
        }
        System.out.println("*********END*****************");
        return input;
    }

    public static String handleCommand(Command command, String input) {
        return switch (command){
            case QUIT -> MESSAGE_PRINTER.quit();
            case COORDS_VALUE -> {
                maxCoords = Integer.parseInt(extractCoordValue(input));
                yield MESSAGE_PRINTER.WorldInitCoordsSet(input, calculateTotalAmountOfCoords(input));
            }
            case INVALID_VALUE ->MESSAGE_PRINTER.invalidValue(input, showWorldInitText());
            case EMPTY_INPUT -> {
                if(maxCoords > 0)
                    yield MessagePrinter.apiMessage();
                else
                    yield showWorldInitText();
            }
            case PRINT -> showHelpMessage();

            default -> showHelpMessage();
        };
    }

    private static String extractCoordValue(String input) {
        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()){
            return matcher.group();
        }
        return showWorldInitText();
    }

    private static String showHelpMessage() {
        return """
                ***************************************************************************************************************************************************
                *   Print state of simulation     | {state}                                                   | ex: state                                         *
                *   Land a new rover              | {land {x} {y}}                                            | ex: land 1 5                                      *
                *   Quit the application          | {Q}                                                                                                           *
                *   Print API overview            | {P}                                                                                                           *
                ***************************************************************************************************************************************************""";
    }


    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }

    public static String showWorldInitText() {

        return "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ";
    }

    public static int parseCoordinateValue(String inputValueString) {
        return  Integer.parseInt(inputValueString);
    }

    public static String calculateTotalAmountOfCoords(String amountOfCoords) {
        return ""+ (Integer.parseInt(amountOfCoords) + 1) * (Integer.parseInt(amountOfCoords) + 1);
    }

    public static boolean maxCoordsHasValue() {
        return maxCoords > 0;
    }

    public static void resetWorld(){
        maxCoords = 0;
    }
}
