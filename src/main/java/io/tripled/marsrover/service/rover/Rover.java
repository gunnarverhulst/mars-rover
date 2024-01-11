package io.tripled.marsrover.service.rover;

public class Rover {

    private RoverState roverState = null;

    public void setRoverState(RoverState roverState) {
        this.roverState = roverState;
    }

    public RoverState getRoverState() {
        return roverState;
    }

    public Coordinate getRoverCoordinates() {
        return roverState.roverCoordinate();
    }

    public String move(Move move, int simulationSize) {
        StringBuilder stringToConcatToMessage = new StringBuilder();

        for(int i = 0; i < move.steps(); i++){
            stringToConcatToMessage.append(move(move.direction(), simulationSize));
        }
        return stringToConcatToMessage.toString();
    }

    private String move(Direction direction, int simulationSize) {
        String stringToConcat = "";
        switch (direction){
            case FORWARD -> {
                Coordinate coordinateToAdd = getRoverHeading().move("forward");
                roverState = new RoverState(createNewRoverCoordinate(coordinateToAdd, simulationSize), getRoverHeading());
                stringToConcat += "Rover R1 is moving forward\n";
                stringToConcat += "Rover R1 is now located at [" + getRoverCoordinates().x() + "," + getRoverCoordinates().y() + "]\n";
            }
            case BACKWARD -> {
                Coordinate coordinateToAdd = getRoverHeading().move("backward");
                roverState = new RoverState(createNewRoverCoordinate(coordinateToAdd, simulationSize), getRoverHeading());
                stringToConcat += "Rover R1 is moving backward\n";
                stringToConcat += "Rover R1 is now located at [" + getRoverCoordinates().x() + "," + getRoverCoordinates().y() + "]\n";
            }
            case LEFT -> {
                Heading newHeading = roverState.heading().turnLeft();

                roverState = new RoverState(new Coordinate(getRoverCoordinates().x(), (getRoverCoordinates().y())), newHeading);
                stringToConcat += "Rover R1 is turning left\n";
                stringToConcat += "Rover R1 is now facing " + getRoverHeading() + "\n";
            }
            case RIGHT -> {
                Heading newHeading = roverState.heading().turnRight();
                roverState = new RoverState(new Coordinate(getRoverCoordinates().x(), (getRoverCoordinates().y())), newHeading);
                stringToConcat += "Rover R1 is turning right\n";
                stringToConcat += "Rover R1 is now facing " + getRoverHeading() + "\n";
            }
        }
        return stringToConcat;
    }

    private Coordinate createNewRoverCoordinate(Coordinate coordinateToAdd, int simulationSize) {
        return new Coordinate((getRoverCoordinates().x() + coordinateToAdd.x()) % (simulationSize + 1),
                (getRoverCoordinates().y() + coordinateToAdd.y()) % (simulationSize + 1));
    }

    private Heading getRoverHeading() {
        return roverState.heading();
    }
}
