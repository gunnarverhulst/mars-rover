package io.tripled.marsrover.cli.input;

import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.presenter.ProgramConsolePresenterImpl;
import io.tripled.marsrover.service.presenter.ProgramPresenter;
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

                handleCommand(input);

            }
            while (!isQuit(input));
        }

    }

    public void handleCommand(String input) {
        ProgramPresenter presenter = new ProgramConsolePresenterImpl();
        presenter.startupMessage();

        Message output = inputParser.determineCommand(input);

    }

    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }
}