package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.cli.message.messages.SimulationSizeErrorMessage;
import io.tripled.marsrover.cli.message.messages.SimulationSizeSetMessage;
import io.tripled.marsrover.service.presenter.SimConsolePresenter;

public class SimulationConsolePresenterImpl implements SimConsolePresenter {
    @Override
    public Message simulationCreated(int simulationSize) {
        System.out.println(new SimulationSizeSetMessage(simulationSize));
        return new SimulationSizeSetMessage(simulationSize);
    }

    @Override
    public Message simulationSizeError(String input) {
        return new SimulationSizeErrorMessage(input);
    }
}
