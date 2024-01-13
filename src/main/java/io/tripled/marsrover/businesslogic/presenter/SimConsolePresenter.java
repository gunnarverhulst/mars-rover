package io.tripled.marsrover.businesslogic.presenter;

public interface SimConsolePresenter extends Presenter {
    void simulationCreated(int simulationSize);

    void simulationSizeError(String input);
}
