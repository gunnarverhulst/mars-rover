package io.tripled.marsrover.message.messages;

public class StateErrorMessage implements Message{

    private final String message = "Rover has not landed yet.\n\n"+
                "[Please enter a command]";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
