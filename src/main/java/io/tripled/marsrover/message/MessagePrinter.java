package io.tripled.marsrover.message;

public enum MessagePrinter {
    MESSAGE_PRINTER;

    public String quit(){
        return "Quitting application";
    }

    public static String WorldInitCoordsSet(String input, String amountOfCoordinates) {

        return "Simulation with max coordinate [" + input + "] created successfully. Simulation contains [" + amountOfCoordinates + "] coordinates\n\n"+
                       "[Please enter a command]";
    }

    public static String invalidValue(String input,String showWorldInitMessage) {
        return "[" + input + "] is an invalid Simulation maxCoordinate\n" +
                worldCoordsInitMessage();
    }

    public static String worldCoordsInitMessage() {

        return "Determine the maxCoordinate of the simulation by setting the maximum coordinate [0-100]\n" +
                "[Enter max coordinate] : ";
    }

    public static String apiMessage() {
        return """
                ***************************************************************************************************************************************************
                *   Print state of simulation     | {state}                                                   | ex: state                                         *
                *   Land a new rover              | {land {x} {y}}                                            | ex: land 1 5                                      *
                *   Quit the application          | {Q}                                                                                                           *
                *   Print API overview            | {P}                                                                                                           *
                ***************************************************************************************************************************************************""";
    }


}
