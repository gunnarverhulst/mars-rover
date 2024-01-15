package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.businesslogic.message.Message;

public class EndMessage implements Message {
    private final String message = "*****************END*****************";


    @Override
    public String messageToBePrinted() {
        return message;
    }
}
