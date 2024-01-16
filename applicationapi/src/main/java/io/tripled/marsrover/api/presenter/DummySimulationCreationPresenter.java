package io.tripled.marsrover.api.presenter;


public class DummySimulationCreationPresenter implements SimCreationPresenter {
    private int simulationSize = 0;

    private boolean simulationSizeError = false;

    public int returnSimCreationSize(){
        return simulationSize;
    }

    public boolean returnSimulationSizeError(){
        return simulationSizeError;
    }


    @Override
    public void simulationCreated(int simulationSize) {
        this.simulationSize = simulationSize;
    }

    @Override
    public void simulationSizeError(String input) {
        simulationSizeError = true;
    }
}
