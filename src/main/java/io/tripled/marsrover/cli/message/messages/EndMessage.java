package io.tripled.marsrover.cli.message.messages;

public class EndMessage implements Message{
    private String message = "*****************END*****************";


    @Override
    public String messageToBePrinted() {
        return message;
    }
}
