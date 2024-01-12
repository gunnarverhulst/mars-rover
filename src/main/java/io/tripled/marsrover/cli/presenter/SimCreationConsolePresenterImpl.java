package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.messages.SimulationSizeSetMessage;
import io.tripled.marsrover.service.businessinterface.SimCreationPresenter;

public class SimCreationConsolePresenterImpl implements SimCreationPresenter {
    @Override
    public Message simulationCreated(int simulationSize) {
        return new SimulationSizeSetMessage(simulationSize);
    }
}
