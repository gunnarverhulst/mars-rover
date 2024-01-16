package io.tripled.marsrover.api.message;

public class TransientMessage implements Message{

    private String message;

    public TransientMessage(String message) {
        this.message = message;
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }
}
