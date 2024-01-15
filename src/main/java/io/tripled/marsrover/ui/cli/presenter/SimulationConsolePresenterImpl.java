package io.tripled.marsrover.ui.cli.presenter;

import io.tripled.marsrover.ui.cli.messages.SimulationSizeErrorMessage;
import io.tripled.marsrover.ui.cli.messages.SimulationSizeSetMessage;
import io.tripled.marsrover.businesslogic.presenter.SimConsolePresenter;

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
