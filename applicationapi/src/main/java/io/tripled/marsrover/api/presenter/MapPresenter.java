package io.tripled.marsrover.api.presenter;

import io.tripled.marsrover.vocabulary.message.MapData;

public interface MapPresenter extends Presenter {

    void mapMessage(MapData mapdata);

}
