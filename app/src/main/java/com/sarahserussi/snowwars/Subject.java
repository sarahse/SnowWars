package com.sarahserussi.snowwars;

/**
 * Created by FinkenLaptop on 09.04.2015.
 */
public interface Subject {

    void register(Observer o);
    void Unregister(Observer o);
    void notifyObserver();
}
