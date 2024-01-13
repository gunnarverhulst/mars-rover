package io.tripled.marsrover.cli.message.messages;

import io.tripled.marsrover.service.message.messages.Message;

public class LogoMessage implements Message {

    private final String message = """
                **************************"
                **    Gunz Rover        **"
                **************************""";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
