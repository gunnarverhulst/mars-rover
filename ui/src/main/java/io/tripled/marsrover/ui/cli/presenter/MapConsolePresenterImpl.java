package io.tripled.marsrover.ui.cli.presenter;

import io.tripled.marsrover.businesslogic.message.MapData;
import io.tripled.marsrover.businesslogic.presenter.MapPresenter;
import io.tripled.marsrover.businesslogic.rover.Heading;

public class MapConsolePresenterImpl implements MapPresenter {

    public void mapMessage(MapData mapdata) {
        System.out.printf("%3s|%s|\n", "   ", "-".repeat((mapdata.simulationSize() + 1) * 3));
        for(int i = 0; i <= mapdata.simulationSize(); i++){
            System.out.printf("%3s|", i);
            for(int j = 0; j <= mapdata.simulationSize(); j++){
                if(mapdata.roverState() != null && i == mapdata.roverState().roverCoordinate().y() && j == mapdata.roverState().roverCoordinate().x()){
                    String rover = "";
                    if(mapdata.roverState().heading() == Heading.NORTH){
                        rover += "^R1";
                    } else if(mapdata.roverState().heading() == Heading.EAST){
                        rover += "R1>";
                    } else if(mapdata.roverState().heading() == Heading.WEST){
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
        System.out.printf("%3s|%s|\n", "---", "-".repeat((mapdata.simulationSize() + 1) * 3));
        System.out.printf("%3s|", "   ");
        for(int i = 0; i <= mapdata.simulationSize(); i++){
            System.out.printf("%3s", i);
        }
        System.out.print("|\n\n[Please enter a command] : ");
    }
}
