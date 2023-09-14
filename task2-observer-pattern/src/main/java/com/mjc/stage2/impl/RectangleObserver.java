package com.mjc.stage2.impl;

import com.mjc.stage2.Observer;
import com.mjc.stage2.event.RectangleEvent;
import com.mjc.stage2.warehouse.RectangleWarehouse;
import lombok.Synchronized;


public class RectangleObserver implements Observer {
    @Synchronized
    @Override
    public void handleEvent(RectangleEvent event) {
        var rectangle = event.getSource();
        var warehouse = RectangleWarehouse.getInstance();
        var rectangleValues = warehouse.get(rectangle.getId());
        rectangleValues.setPerimeter((rectangle.getSideA() + rectangle.getSideB()) * 2);
        rectangleValues.setSquare(rectangle.getSideA() * rectangle.getSideB());
    }
}
