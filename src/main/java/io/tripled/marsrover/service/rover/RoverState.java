package io.tripled.marsrover.service.rover;

public record RoverState (Coordinate roverCoordinate, Heading heading){

//    public void moveForward(int simulationSize){
//        if(heading() == Heading.NORTH){
//            this = new RoverState(new Coordinate(roverCoordinate.x(), (roverCoordinate.y() + 1) % (simulationSize + 1)), Heading.NORTH);
//        }
//        if(roverState.heading() == Heading.EAST){
//            roverState = new RoverState(new Coordinate((getRoverCoordinates().x() + 1) % (simulationSize + 1), getRoverCoordinates().y()), Heading.EAST);
//        }
//        if(roverState.heading() == Heading.WEST){
//            roverState = new RoverState(new Coordinate((getRoverCoordinates().x() - 1) % (simulationSize + 1), getRoverCoordinates().y()), Heading.WEST);
//        }
//        if(roverState.heading() == Heading.SOUTH){
//            roverState = new RoverState(new Coordinate(getRoverCoordinates().x(), (getRoverCoordinates().y() - 1) % (simulationSize + 1)), Heading.NORTH);
//        }
//    }
}
