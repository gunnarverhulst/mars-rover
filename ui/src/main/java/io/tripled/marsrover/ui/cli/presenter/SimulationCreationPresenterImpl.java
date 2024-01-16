package io.tripled.marsrover.ui.cli.presenter;

import io.tripled.marsrover.api.presenter.SimCreationPresenter;
import io.tripled.marsrover.ui.cli.messages.SimulationSizeErrorMessage;
import io.tripled.marsrover.ui.cli.messages.SimulationSizeSetMessage;

public class SimulationCreationPresenterImpl implements SimCreationPresenter {
    @Override
    public void simulationCreated(int simulationSize) {
        System.out.println(new SimulationSizeSetMessage(simulationSize).messageToBePrinted());
    }

    @Override
    public void simulationSizeError(String input) {
        System.out.println(new SimulationSizeErrorMessage(input).messageToBePrinted());
    }
}
