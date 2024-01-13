package io.tripled.marsrover.service.presenter;

import io.tripled.marsrover.service.message.messages.Message;

public interface QuitPresenter extends Presenter {
    Message quitMessage();
}
