package io.tripled.marsrover.businesslogic.presenter;

public class DummySimulationCreationPresenter implements SimCreationPresenter {
    private int simulationSize = 0;

    public int returnSimCreationSize(){
        return simulationSize;
    }


    @Override
    public void simulationCreated(int simulationSize) {
        this.simulationSize = simulationSize;
    }

    @Override
    public void simulationSizeError(String input) {

    }
}
