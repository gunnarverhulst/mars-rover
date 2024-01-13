package io.tripled.marsrover.service.presenter;

import io.tripled.marsrover.service.message.messages.Message;

public interface SimConsolePresenter extends Presenter {
    Message simulationCreated(int simulationSize);

    Message simulationSizeError(String input);
}
