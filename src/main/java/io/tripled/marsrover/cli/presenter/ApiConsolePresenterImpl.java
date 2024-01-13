package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.messages.ApiMessage;
import io.tripled.marsrover.service.presenter.ApiPresenter;

public class ApiConsolePresenterImpl implements ApiPresenter {
    @Override
    public void apiMessage() {
        System.out.println(new ApiMessage().messageToBePrinted());
    }
}
