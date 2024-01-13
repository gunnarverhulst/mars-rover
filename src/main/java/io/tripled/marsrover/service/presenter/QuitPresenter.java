package io.tripled.marsrover.service.presenter;

import io.tripled.marsrover.cli.message.messages.Message;

public interface QuitPresenter extends Presenter {
    Message quitMessage();
}
