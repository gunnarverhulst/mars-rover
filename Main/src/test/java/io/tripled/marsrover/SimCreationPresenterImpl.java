package io.tripled.marsrover;

import io.tripled.marsrover.api.presenter.SimCreationPresenter;

public class SimCreationPresenterImpl implements SimCreationPresenter {
    @Override
    public void simulationCreated(int simulationSize) {
        System.out.println("Simulation created with simulation size: " + simulationSize);
    }

    @Override
    public void simulationSizeError(String input) {

    }
}
