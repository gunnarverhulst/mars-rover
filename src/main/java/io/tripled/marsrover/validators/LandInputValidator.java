package io.tripled.marsrover.validators;

import io.tripled.marsrover.rover.Coordinate;

public enum LandInputValidator {
    LAND_INPUT_VALIDATOR;

    public boolean isValidCoordinateInput(Coordinate coordinate, int simulationSize){

        if(coordinate.x() < 0 || coordinate.x() > simulationSize)
            return false;

        return coordinate.y() >= 0 && coordinate.y() <= simulationSize;
    }
}
