package io.tripled.marsrover.businesslogic.rover;

import io.tripled.marsrover.api.message.Message;
import io.tripled.marsrover.api.message.TransientMessage;
import io.tripled.marsrover.vocabulary.rover.*;

public class Rover {

    private RoverState roverState = null;

    private final String id;

    private final int simulationSize;

    public Rover(int simulationSize) {
        this.simulationSize = simulationSize;
        this.id = "R1";
    }
//
//    public Rover(int simulationSize, int id) {
//        this.simulationSize = simulationSize;
//        this.id = "R"+id;

//    }

    public Rover(int simulationSize, int id, RoverState roverState) {

        this.simulationSize = simulationSize;
        this.id = "R"+id;
        this.roverState = roverState;
    }

    public Message moveRover(Move move) {
        StringBuilder stringToConcatToMessage = new StringBuilder();

        for(int i = 0; i < move.steps(); i++){
            stringToConcatToMessage.append(singleStepMoveRover(move.direction()).messageToBePrinted());
        }
        return new TransientMessage(stringToConcatToMessage.toString());
    }
    public Coordinate getRoverCoordinates() {
        return roverState.roverCoordinate();
    }

    public void setRoverState(RoverState roverState) {
        this.roverState = roverState;
    }

    public RoverState getRoverState() {
        return roverState;
    }

    private Message singleStepMoveRover(Direction direction) {
        String stringToConcat = "";
        switch (direction){
            case FORWARD, BACKWARD -> {
                Coordinate coordinateToAdd = move(direction);
                roverState = new RoverState(id,createNewRoverCoordinate(coordinateToAdd), getRoverHeading());
                stringToConcat = createRoverMoveMessage(stringToConcat, direction);
            }
            case LEFT, RIGHT -> {
                Heading newHeading;
                if(direction == Direction.LEFT)
                    newHeading = turnLeft();
                else
                    newHeading = turnRight();

                roverState = new RoverState(id,new Coordinate(getRoverCoordinates().x(), (getRoverCoordinates().y())), newHeading);
                stringToConcat = createRoverTurnMessage(stringToConcat, direction);
            }
        }
        return new TransientMessage(stringToConcat);
    }

    private String createRoverMoveMessage(String stringToConcat, Direction direction) {
        stringToConcat += "Rover R1 is moving " + direction.parseDirectionAsText()+ "\n";
        stringToConcat += "Rover R1 is now located at [" + getRoverCoordinates().x() + "," + getRoverCoordinates().y() + "]\n";
        return stringToConcat;
    }
    private Coordinate move(Direction direction){
        int sign = 1;
        if(direction == Direction.BACKWARD){
            sign *= -1;
        }

        return switch (roverState.heading().getHeadingNumber()){
            case 0 -> new Coordinate(0,-1*sign);
            case 1 -> new Coordinate(-1 * sign,0);
            case 2 -> new Coordinate(0,sign);
            case 3 -> new Coordinate(sign,0);
            default -> new Coordinate(0,0);
        };
    }

    private Heading getRoverHeading() {
        return roverState.heading();
    }

    private Coordinate createNewRoverCoordinate(Coordinate coordinateToAdd) {

        int simulationSizeWithOffset = simulationSize + 1;

        int x = (getRoverCoordinates().x() + coordinateToAdd.x() + simulationSizeWithOffset) % (simulationSizeWithOffset);
        int y = (getRoverCoordinates().y() + coordinateToAdd.y() + simulationSizeWithOffset) % (simulationSizeWithOffset);

        return new Coordinate(x, y);
    }

    private String createRoverTurnMessage(String stringToConcat, Direction direction) {
        stringToConcat += "Rover R1 is turning " + direction.parseDirectionAsText() + "\n";
        stringToConcat += "Rover R1 is now facing " + getRoverHeading() + "\n";
        return stringToConcat;
    }

    private Heading turnLeft(){
        return Heading.getHeading((roverState.heading().getHeadingNumber() + 1) % 4);
    }

    private Heading turnRight(){
        return Heading.getHeading((((roverState.heading().getHeadingNumber() - 1) % 4) + 4) % 4);
    }


    public String getId() {
        return  id;
    }
}
