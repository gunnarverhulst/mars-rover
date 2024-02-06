package io.tripled.marsrover.vocabulary.rover;

public enum Heading {
    NORTH(0),
    WEST(3),
    SOUTH(2),
    EAST(2);

    private final int headingNumber;

    Heading(int headingNumber) {
        this.headingNumber = headingNumber;
    }

    public static Heading getHeading(int headingNumber){
        for(Heading heading: values()){
            if(heading.headingNumber == headingNumber){
                return heading;
            }
        }
        return null;
    }

    public int getHeadingNumber(){
        return headingNumber;
    }

}
