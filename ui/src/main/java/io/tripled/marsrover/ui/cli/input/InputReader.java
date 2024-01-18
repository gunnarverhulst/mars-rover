package io.tripled.marsrover.ui.cli.input;


import io.tripled.marsrover.api.command.ApplicationService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputReader {
    private final InputParser inputParser;

    public InputReader(ApplicationService applicationService) {
        this.inputParser = new InputParser(applicationService);
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