package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.message.messages.ApiMessage;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.service.presenter.ApiPresenter;

public class ApiConsolePresenterImpl implements ApiPresenter {
    @Override
    public Message apiMessage() {
        System.out.println(new ApiMessage().messageToBePrinted());
        return new ApiMessage();
    }
}
