package com.mjc.stage2.entity;

import com.mjc.stage2.Observable;
import com.mjc.stage2.Observer;
import com.mjc.stage2.event.RectangleEvent;
import com.mjc.stage2.impl.RectangleObserver;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class Rectangle implements Observable {
    @Getter
    private int id;
    @Getter
    private double sideA;
    @Getter
    private double sideB;

    private final Set<Observer> observers = new HashSet<>();

    public Rectangle(int id, double sideA, double sideB) {
        this.id = id;
        this.sideA = sideA;
        this.sideB = sideB;
        this.observers.add(new RectangleObserver());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
        notifyObserver();
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
        notifyObserver();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.removeIf(observer::equals);
    }

    @Override
    public void notifyObserver() {
        var event = new RectangleEvent(this);
        observers.forEach(observer -> observer.handleEvent(event));
    }
}
