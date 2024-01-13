package io.tripled.marsrover.cli.input;

import io.tripled.marsrover.service.simulation.SimulationRepository;

import java.util.Scanner;

public class InputReader {
    public final SimulationRepository simulationRepository;
    private final InputParser inputParser;

    public InputReader(SimulationRepository simulationRepository) {
        this.simulationRepository  = simulationRepository;
        this.inputParser = new InputParser(simulationRepository);
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