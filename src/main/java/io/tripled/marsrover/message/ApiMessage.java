package io.tripled.marsrover.message;

public class ApiMessage implements Message{

    private String message = """
                ***************************************************************************************************************************************************
                *   Print state of simulation     | {state}                                                   | ex: state                                         *
                *   Land a new rover              | {land {x} {y}}                                            | ex: land 1 5                                      *
                *   Quit the application          | {Q}                                                                                                           *
                *   Print API overview            | {P}                                                                                                           *
                ***************************************************************************************************************************************************\n\n""" +
            "[Please enter a command] : ";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
