package io.tripled.marsrover.service.rover;

public enum Heading {
    NORTH(0),
    WEST(1),
    SOUTH(2),
    EAST(3);

    private int headingNumber;

    Heading(int headingNumber) {
        this.headingNumber = headingNumber;
    }

    private Heading getHeading(int headingNumber){
        for(Heading heading: values()){
            if(heading.headingNumber == headingNumber){
                return heading;
            }
        }
        return null;
    }

    public Heading turnLeft(){
        return getHeading(headingNumber + 1);
    }

    public Heading turnRight(){
        return getHeading(headingNumber - 1);
    }

    public Coordinate moveForward(){

        return switch (headingNumber){
            case 0 -> new Coordinate(0,1);
            case 1 -> new Coordinate(-1,0);
            case 2 -> new Coordinate(0,-1);
            case 3 -> new Coordinate(1,0);
            default -> new Coordinate(0,0);
        };
    }
}
