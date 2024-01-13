package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.businesslogic.message.Message;

public class QuitMessage implements Message {
    private final String message = "Quitting application";


    @Override
    public String messageToBePrinted() {
        return message;
    }
}
