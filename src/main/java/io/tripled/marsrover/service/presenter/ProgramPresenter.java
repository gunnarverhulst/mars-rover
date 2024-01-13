package io.tripled.marsrover.service.presenter;

import io.tripled.marsrover.cli.message.messages.Message;

public interface ProgramPresenter extends Presenter{

    public Message printLogo();

    public Message printStartUpMessage();

    public Message printEndMessage();
}
