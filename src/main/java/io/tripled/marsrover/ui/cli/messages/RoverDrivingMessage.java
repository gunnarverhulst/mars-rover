package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.businesslogic.message.Message;

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