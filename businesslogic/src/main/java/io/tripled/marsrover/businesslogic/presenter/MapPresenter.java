package io.tripled.marsrover.businesslogic.presenter;

import io.tripled.marsrover.businesslogic.message.MapData;
import io.tripled.marsrover.businesslogic.rover.RoverState;

public interface MapPresenter extends Presenter{

    void mapMessage(MapData mapdata);

}
