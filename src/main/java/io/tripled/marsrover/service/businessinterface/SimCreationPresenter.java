package io.tripled.marsrover.service.businessinterface;

import io.tripled.marsrover.cli.message.messages.Message;

public interface SimCreationPresenter extends Presenter {
    Message simulationCreated(int simulationSize);
}
