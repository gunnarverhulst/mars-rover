package io.tripled.marsrover.api.message;

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
