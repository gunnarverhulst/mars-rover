package io.tripled.marsrover.service.rover;

import io.tripled.marsrover.data.simulation.InMemorySimulationRepository;
import io.tripled.marsrover.service.simulation.SimulationRepository;

public class Rover {

    private RoverState roverState = null;

    private final SimulationRepository simulationRepository;

    public Rover(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    public void setRoverState(RoverState roverState) {
        this.roverState = roverState;
    }

    public RoverState getRoverState() {
        return roverState;
    }

    public Coordinate getRoverCoordinates() {
        return roverState.roverCoordinate();
    }

    public String move(Move move) {
        StringBuilder stringToConcatToMessage = new StringBuilder();

        for(int i = 0; i < move.steps(); i++){
            stringToConcatToMessage.append(move(move.direction()));
        }
        return stringToConcatToMessage.toString();
    }

    private String move(Direction direction) {
        String stringToConcat = "";
        switch (direction){
            case FORWARD -> {
                Coordinate coordinateToAdd = getRoverHeading().move("forward");
                roverState = new RoverState(createNewRoverCoordinate(coordinateToAdd), getRoverHeading());
                stringToConcat = createRoverMoveMessage(stringToConcat, direction);
            }
            case BACKWARD -> {
                Coordinate coordinateToAdd = getRoverHeading().move("backward");
                roverState = new RoverState(createNewRoverCoordinate(coordinateToAdd), getRoverHeading());
                stringToConcat = createRoverMoveMessage(stringToConcat, direction);
            }
            case LEFT -> {
                Heading newHeading = roverState.heading().turnLeft();

                roverState = new RoverState(new Coordinate(getRoverCoordinates().x(), (getRoverCoordinates().y())), newHeading);
                stringToConcat = createRoverTurnMessage(stringToConcat, direction);
            }
            case RIGHT -> {
                Heading newHeading = roverState.heading().turnRight();
                roverState = new RoverState(new Coordinate(getRoverCoordinates().x(), (getRoverCoordinates().y())), newHeading);
                stringToConcat = createRoverTurnMessage(stringToConcat, direction);
            }
        }
        return stringToConcat;
    }


    private Heading getRoverHeading() {
        return roverState.heading();
    }

    private Coordinate createNewRoverCoordinate(Coordinate coordinateToAdd) {

        int simulationSizeWithOffset = simulationRepository.getSimulation().getSimulationSize() + 1;

        int x = (getRoverCoordinates().x() + coordinateToAdd.x()) % (simulationSizeWithOffset);
        int y = (getRoverCoordinates().y() + coordinateToAdd.y()) % (simulationSizeWithOffset);

        if(x < 0){
            x += simulationSizeWithOffset;
            x = x % simulationSizeWithOffset;
        }

        if(y < 0){
            y += simulationSizeWithOffset;
            y = y % simulationSizeWithOffset;
        }

        return new Coordinate(x, y);
    }

    private String createRoverMoveMessage(String stringToConcat, Direction direction) {
        stringToConcat += "Rover R1 is moving " + direction.parseDirectionAsText()+ "\n";
        stringToConcat += "Rover R1 is now located at [" + getRoverCoordinates().x() + "," + getRoverCoordinates().y() + "]\n";
        return stringToConcat;
    }
    private String createRoverTurnMessage(String stringToConcat, Direction direction) {
        stringToConcat += "Rover R1 is turning " + direction.parseDirectionAsText() + "\n";
        stringToConcat += "Rover R1 is now facing " + getRoverHeading() + "\n";
        return stringToConcat;
    }


}
