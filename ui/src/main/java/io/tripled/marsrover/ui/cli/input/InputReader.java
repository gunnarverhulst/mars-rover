package io.tripled.marsrover.ui.cli.input;

import java.util.Scanner;

public class InputReader {
    private final InputParser inputParser;

    public InputReader() {
        this.inputParser = new InputParser();
    }

    public void readInput() {

        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                input = scanner.nextLine();
                inputParser.determineCommand(input);

            }
            while (!isQuit(input));
        }

    }

    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }
}