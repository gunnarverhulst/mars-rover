package io.tripled.marsrover.cli.message.messages;

import io.tripled.marsrover.service.message.messages.Message;

public class RoverDrivingMessage implements Message {

    private String message = "";
    @Override
    public String messageToBePrinted() {
        return message;
    }

    public void concat(String concatenationString){
        message += concatenationString;
    }
}
