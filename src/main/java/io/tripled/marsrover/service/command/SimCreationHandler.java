package io.tripled.marsrover.service.command;

import io.tripled.marsrover.cli.message.MessagePrinter;
import io.tripled.marsrover.cli.message.messages.Message;
import io.tripled.marsrover.service.businessinterface.SimCreationPresenter;
import io.tripled.marsrover.service.simulation.Simulation;
import io.tripled.marsrover.service.simulation.SimulationRepository;

public final class SimCreationHandler implements ActionHandler<Integer, SimCreationPresenter> {

    private final SimulationRepository simulationRepository;

    private final MessagePrinter messagePrinter;

    public SimCreationHandler(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
        messagePrinter = new MessagePrinter(simulationRepository);
    }

    public Message execute(int simulationSize){
        simulationRepository.addSimulation(new Simulation(simulationSize, simulationRepository));

        return messagePrinter.simulationSizeSetMessage(simulationRepository.getSimulation().getSimulationSize());

    }

    @Override
    public Message handle(Integer simulationSize, SimCreationPresenter simCreationPresenter) {
        simulationRepository.addSimulation(new Simulation(simulationSize, simulationRepository));
        System.out.println(simCreationPresenter.simulationCreated(simulationSize).messageToBePrinted());
        return simCreationPresenter.simulationCreated(simulationSize);
    }
}
