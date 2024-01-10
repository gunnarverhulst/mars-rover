package io.tripled.marsrover.cli.validators;

import io.tripled.marsrover.service.rover.Coordinate;

public enum LandInputValidator {
    LAND_INPUT_VALIDATOR;

    public boolean isValidCoordinateInput(Coordinate coordinate, int simulationSize){

        if(coordinate.x() < 0 || coordinate.x() > simulationSize + 1)
            return false;

        return coordinate.y() >= 0 && coordinate.y() <= simulationSize + 1;
    }
}
