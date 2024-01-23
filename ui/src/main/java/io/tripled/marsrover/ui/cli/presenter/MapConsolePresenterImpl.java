package io.tripled.marsrover.ui.cli.presenter;

import io.tripled.marsrover.vocabulary.message.MapData;
import io.tripled.marsrover.api.presenter.MapPresenter;
import io.tripled.marsrover.vocabulary.rover.Heading;
import io.tripled.marsrover.vocabulary.rover.RoverState;

import java.util.ArrayList;
import java.util.List;

public class MapConsolePresenterImpl implements MapPresenter {

    private String[][] virtualMap;

    public void mapMessage(MapData mapdata) {
        List<RoverState> roverStates = new ArrayList<>();
        createVirtualMap(mapdata.simulationSize());
        if(!mapdata.roverStates().isEmpty()){
             roverStates = mapdata.roverStates();
             for(RoverState roverState : roverStates){
                 int x = roverState.roverCoordinate().x();
                 int y = roverState.roverCoordinate().y();
                 String rover = "";
                 if(roverState.heading() == Heading.NORTH){
                     rover += "^R" + roverState.id();
                 } else if(roverStates.get(0).heading() == Heading.EAST){
                     rover += "R" + roverState.id() + ">";
                 } else if(roverStates.get(0).heading() == Heading.WEST){
                     rover += "< " + roverState.id() + "R";
                 } else{
                     rover += " R" + roverState.id();
                 }
                 virtualMap[y][x] = rover;
             }

        }
        System.out.printf("%3s|%s|\n", "   ", "-".repeat((mapdata.simulationSize() + 1) * 3));
        for(int i = 0; i <= mapdata.simulationSize(); i++){
            System.out.printf("%3s|", i);
            for(int j = 0; j <= mapdata.simulationSize(); j++){
                System.out.printf("%3s",virtualMap[j][i]);
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

    private void createVirtualMap(int simulationSize) {
        virtualMap = new String[simulationSize + 1][simulationSize + 1];
        for(int i = 0; i <= simulationSize; i++){
            for(int j = 0; j <= simulationSize; j++){
                virtualMap[i][j] = " . ";
            }
        }
    }
}
