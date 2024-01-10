package io.tripled.marsrover.message;

public class QuitMessage implements Message{
    private final String message = "Quitting application";


    @Override
    public String messageToBePrinted() {
        return message;
    }
}
