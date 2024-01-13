package io.tripled.marsrover.service.presenter;

public interface SimConsolePresenter extends Presenter {
    void simulationCreated(int simulationSize);

    void simulationSizeError(String input);
}
