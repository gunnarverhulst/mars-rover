package io.tripled.marsrover.ui.cli.presenter;

import io.tripled.marsrover.businesslogic.presenter.MapPresenter;
import io.tripled.marsrover.businesslogic.rover.Heading;
import io.tripled.marsrover.businesslogic.rover.RoverState;

public class MapConsolePresenterImpl implements MapPresenter {
    @Override
    public void mapMessage(int simulationSize, RoverState roverstate) {
        System.out.printf("%3s|%s|\n", "   ", "-".repeat((simulationSize + 1) * 3));
        for(int i = 0; i <= simulationSize; i++){
            System.out.printf("%3s|", i);
            for(int j = 0; j <= simulationSize; j++){
                if(i == roverstate.roverCoordinate().y() && j == roverstate.roverCoordinate().x()){
                    String rover = "";
                    if(roverstate.heading() == Heading.NORTH){
                        rover += "^R1";
                    } else if(roverstate.heading() == Heading.EAST){
                        rover += "R1>";
                    } else if(roverstate.heading() == Heading.WEST){
                        rover += "<R1";
                    } else{
                        rover += " R1";
                    }
                    System.out.print(rover);
                }
                else
                    System.out.print(" . ");
            }
            System.out.print("|\n");
        }
        System.out.printf("%3s|%s|\n", "---", "-".repeat((simulationSize + 1) * 3));
        System.out.printf("%3s|", "   ");
        for(int i = 0; i <= simulationSize; i++){
            System.out.printf("%3s", i);
        }
        System.out.print("|\n\n[Please enter a command] : ");
    }

    @Override
    public void mapMessage(int simulationSize) {
        System.out.printf("%3s|%s|\n", "   ", "-".repeat((simulationSize + 1) * 3));
        for(int i = 0; i <= simulationSize; i++){
            System.out.printf("%3s|%s|\n", i, " . ".repeat((simulationSize + 1)));
        }
        System.out.printf("%3s|%s|\n", "---", "-".repeat((simulationSize + 1) * 3));
        System.out.printf("%3s|", "   ");
        for(int i = 0; i <= simulationSize; i++){
            System.out.printf("%3s", i);
        }
        System.out.print("|\n\n[Please enter a command] : ");
    }
}
