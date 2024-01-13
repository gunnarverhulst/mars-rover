package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.message.messages.*;
import io.tripled.marsrover.service.presenter.ProgramPresenter;

public class ProgramPresenterImpl implements ProgramPresenter {


    @Override
    public Message printLogo() {
        System.out.println(new LogoMessage());
        return new LogoMessage();
    }

    @Override
    public Message printStartUpMessage() {
        System.out.println(new SimulationSizeRequestMessage());
        return new SimulationSizeRequestMessage();
    }

    @Override
    public Message printEndMessage() {
        System.out.println(new EndMessage());
        return new EndMessage();
    }


}

