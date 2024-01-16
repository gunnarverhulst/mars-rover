package io.tripled.marsrover.ui.cli.presenter;

import io.tripled.marsrover.ui.cli.messages.QuitMessage;
import io.tripled.marsrover.api.presenter.QuitPresenter;

public class QuitConsolePresenterImpl implements QuitPresenter {
    @Override
    public void quitMessage() {
        System.out.println(new QuitMessage().messageToBePrinted());
    }
}
