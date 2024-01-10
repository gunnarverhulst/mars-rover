package io.tripled.marsrover.message.messages;

public class LogoMessage implements Message{

    private final String message = """
                **************************"
                **    Gunz Rover        **"
                **************************""";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
