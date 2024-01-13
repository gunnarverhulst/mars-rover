package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.businesslogic.message.Message;

public class LogoMessage implements Message {

    private final String message = """
                **************************
                **    Gunz Rover        **
                **************************\n""";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
