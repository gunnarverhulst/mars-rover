package io.tripled.marsrover.service.presenter;

import io.tripled.marsrover.cli.message.messages.Message;

public interface ProgramPresenter extends Presenter{

    Message logo();

    Message startupMessage();

    Message endMessage();
}
