package io.tripled.marsrover.api.presenter;

public interface SimCreationPresenter extends Presenter {
    void simulationCreated(int simulationSize);

    void simulationSizeError(String input);
}
