package io.tripled.marsrover.cli.messages;

import io.tripled.marsrover.service.message.messages.Message;

public class ApiMessage implements Message {

    private final String message = """
                ***************************************************************************************************************************************************
                *   Print API overview            | {P}                                                       | ex: p                                             *
                *   Print state of simulation     | {state}                                                   | ex: state                                         *
                *   Land a new rover              | {land {x} {y}}                                            | ex: land 1 5                                      *
                *   Drive with rover              | {Rx {fn|bn|ln|rn}}                                        | ex R1 f5 l2 b                                     *
                *                                                                                             | Rover with id R1                                  *
                *                                                                                             | drive forward 5 steps                             *
                *                                                                                             | turn left twice                                   *
                *                                                                                             | go back one step                                  *
                *   Quit the application          | {Q}                                                       | ex: q                                             *
                ***************************************************************************************************************************************************\n\n""" +
            "[Please enter a command] : ";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
