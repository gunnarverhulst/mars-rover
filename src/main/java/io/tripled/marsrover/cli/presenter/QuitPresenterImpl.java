package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.messages.QuitMessage;
import io.tripled.marsrover.service.presenter.QuitPresenter;

public class QuitPresenterImpl implements QuitPresenter {
    @Override
    public Message printQuitMessage() {
        System.out.println(new QuitMessage().messageToBePrinted());
        return new QuitMessage();
    }
}
