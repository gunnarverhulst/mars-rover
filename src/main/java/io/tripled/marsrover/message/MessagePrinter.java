package io.tripled.marsrover.message;

public enum MessagePrinter {
    MESSAGE_PRINTER;

    public String quit(){
        return "Quitting application";
    }

    public String WorldInitCoordsSet(String input, String amountOfCoordinates) {

        return "Simulation with max coordinate [" + input + "] created successfully. Simulation contains [" + amountOfCoordinates + "] coordinates\n\n"+
                       "[Please enter a command]";
    }
}
