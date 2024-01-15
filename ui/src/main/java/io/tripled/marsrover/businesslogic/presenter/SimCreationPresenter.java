package io.tripled.marsrover.businesslogic.presenter;

public interface SimCreationPresenter extends Presenter {
    void simulationCreated(int simulationSize);

    void simulationSizeError(String input);
}
