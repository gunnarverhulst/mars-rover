package io.tripled.marsrover.service.rover;

public enum Heading {
    WEST(1),
    SOUTH(2),
    NORTH(0),
    EAST(3);

    private final int headingNumber;

    Heading(int headingNumber) {
        this.headingNumber = headingNumber;
    }

    public int getHeadingNumber(){
        return headingNumber;
    }

    public static Heading getHeading(int headingNumber){
        for(Heading heading: values()){
            if(heading.headingNumber == headingNumber){
                return heading;
            }
        }
        return null;
    }
}
