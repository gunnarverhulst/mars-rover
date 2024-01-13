package io.tripled.marsrover.ui.cli.presenter;

import io.tripled.marsrover.ui.cli.messages.ApiMessage;
import io.tripled.marsrover.businesslogic.presenter.ApiPresenter;

public class ApiConsolePresenterImpl implements ApiPresenter {
    @Override
    public void apiMessage() {
        System.out.println(new ApiMessage().messageToBePrinted());
    }
}
