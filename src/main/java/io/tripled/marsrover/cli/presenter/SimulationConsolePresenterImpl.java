package io.tripled.marsrover.cli.presenter;

import io.tripled.marsrover.cli.messages.SimulationSizeErrorMessage;
import io.tripled.marsrover.cli.messages.SimulationSizeSetMessage;
import io.tripled.marsrover.service.presenter.SimConsolePresenter;

public class SimulationConsolePresenterImpl implements SimConsolePresenter {
    @Override
    public void simulationCreated(int simulationSize) {
        System.out.println(new SimulationSizeSetMessage(simulationSize).messageToBePrinted());
    }

    @Override
    public void simulationSizeError(String input) {
        System.out.println(new SimulationSizeErrorMessage(input).messageToBePrinted());
    }
}
