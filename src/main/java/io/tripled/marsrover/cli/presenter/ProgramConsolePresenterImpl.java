package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.messages.EndMessage;
import io.tripled.marsrover.cli.messages.LogoMessage;
import io.tripled.marsrover.cli.messages.SimulationSizeRequestMessage;
import io.tripled.marsrover.service.message.messages.Message;
import io.tripled.marsrover.service.presenter.ProgramPresenter;

public class ProgramConsolePresenterImpl implements ProgramPresenter {


    @Override
    public Message logo() {
        System.out.println(new LogoMessage().messageToBePrinted());
        return new LogoMessage();
    }

    @Override
    public Message startupMessage() {
        System.out.println(new SimulationSizeRequestMessage().messageToBePrinted());
        return new SimulationSizeRequestMessage();
    }

    @Override
    public Message endMessage() {
        System.out.println(new EndMessage().messageToBePrinted());
        return new EndMessage();
    }


}

