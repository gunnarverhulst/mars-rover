package io.tripled.marsrover.service.presenter;

import io.tripled.marsrover.cli.message.messages.Message;

public interface SimConsolePresenter extends Presenter {
    Message simulationCreated(int simulationSize);

    Message simulationSizeError(String input);
}
