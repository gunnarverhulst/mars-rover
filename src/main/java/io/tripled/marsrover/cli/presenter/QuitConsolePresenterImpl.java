package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.messages.QuitMessage;
import io.tripled.marsrover.service.presenter.QuitPresenter;

public class QuitConsolePresenterImpl implements QuitPresenter {
    @Override
    public void quitMessage() {
        System.out.println(new QuitMessage().messageToBePrinted());
    }
}
