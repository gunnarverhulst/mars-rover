package io.tripled.marsrover;

import io.tripled.marsrover.ui.cli.input.InputReader;
import io.tripled.marsrover.ui.cli.presenter.ProgramConsolePresenterImpl;
import io.tripled.marsrover.businesslogic.presenter.ProgramPresenter;


public class MarsRoverApplication {

    private final InputReader inputReader;


    public MarsRoverApplication() {
        this.inputReader = new InputReader();
    }

    public static void main(String[] args) {
        MarsRoverApplication marsRoverApplication = new MarsRoverApplication();

        ProgramPresenter programPresenter = new ProgramConsolePresenterImpl();
        programPresenter.logo();
        programPresenter.startupMessage();


        marsRoverApplication.getInputReader().readInput();

        programPresenter.endMessage();
    }

    public InputReader getInputReader() {
        return inputReader;
    }

}
