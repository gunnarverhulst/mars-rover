package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.message.messages.ApiMessage;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.service.presenter.ApiPresenter;

public class ApiPresenterImpl implements ApiPresenter {
    @Override
    public Message printApiMessage() {
        System.out.println(new ApiMessage().messageToBePrinted());
        return new ApiMessage();
    }
}
