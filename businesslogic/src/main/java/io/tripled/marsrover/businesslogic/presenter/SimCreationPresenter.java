package io.tripled.marsrover.businesslogic.presenter;

import io.tripled.marsrover.api.presenter.Presenter;

public interface SimCreationPresenter  extends Presenter {
    void simulationCreated(int simulationSize);

    void simulationSizeError(String input);
}
