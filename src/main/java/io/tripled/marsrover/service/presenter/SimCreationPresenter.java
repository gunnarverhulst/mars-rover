package io.tripled.marsrover.service.presenter;

import io.tripled.marsrover.cli.message.messages.Message;

public interface SimCreationPresenter extends Presenter {
    Message simulationSuccesfullyCreated(int simulationSize);
}
