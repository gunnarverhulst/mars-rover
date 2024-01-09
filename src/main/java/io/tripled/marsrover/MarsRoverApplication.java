package io.tripled.marsrover;

import io.tripled.marsrover.command.Command;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                **************************
                """;
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
            case QUIT -> "Quitting application";
            case COORDS_VALUE -> {
                maxCoords = Integer.parseInt(extractCoordValue(input));
                yield "Simulation with max coordinate [" + input + "] created successfully. Simulation contains [" + calculateTotalAmountOfCoords(input) + "] coordinates";
            }
            case INVALID_VALUE -> {
                yield "[" + input + "] is an invalid Simulation maxCoordinate\n" +
                        showWorldInitText();
            }
            case EMPTY_INPUT -> {
                maxCoords = 0;
                if(maxCoords > 0)
                    yield showHelpMessage();
                else
                    yield showWorldInitText();
            }
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
        return "help";
    }


    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }

    public static String showWorldInitText() {
        String worldInitText = "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ";

        return worldInitText;
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
}
