package io.tripled.marsrover;

import io.tripled.marsrover.cli.input.InputReader;
import io.tripled.marsrover.cli.message.messages.LogoMessage;
import io.tripled.marsrover.cli.message.messages.Message;


public class MarsRoverApplication {

    private final InputReader inputReader;

    public MarsRoverApplication() {
        this.inputReader = new InputReader();
    }

    public static void main(String[] args) {
        MarsRoverApplication marsRoverApplication = new MarsRoverApplication();

        printLogo();
        marsRoverApplication.getInputReader().readInput();
    }

    public static void printLogo() {
        Message logo = new LogoMessage();
        System.out.println(logo.messageToBePrinted());
    }
    public InputReader getInputReader() {
        return inputReader;
    }

}
