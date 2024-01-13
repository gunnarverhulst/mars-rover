package io.tripled.marsrover.businesslogic.message;

public class TransientMessage implements Message{

    private String message;

    public TransientMessage(String message) {
        this.message = message;
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }

    public void concat(String input){
        message = message.concat(input);
    }
}
