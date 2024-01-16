package io.tripled.marsrover.businesslogic.presenter;

public interface SimCreationPresenter {
    void simulationCreated(int simulationSize);

    void simulationSizeError(String input);
}
