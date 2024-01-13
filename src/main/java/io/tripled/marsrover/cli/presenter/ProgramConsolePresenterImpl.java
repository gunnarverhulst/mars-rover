package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.messages.EndMessage;
import io.tripled.marsrover.cli.messages.LogoMessage;
import io.tripled.marsrover.cli.messages.SimulationSizeRequestMessage;
import io.tripled.marsrover.service.presenter.ProgramPresenter;

public class ProgramConsolePresenterImpl implements ProgramPresenter {

    @Override
    public void logo() {
        System.out.println(new LogoMessage().messageToBePrinted());
    }

    @Override
    public void startupMessage() {
        System.out.println(new SimulationSizeRequestMessage().messageToBePrinted());
    }

    @Override
    public void endMessage() {
        System.out.println(new EndMessage().messageToBePrinted());
    }
}

