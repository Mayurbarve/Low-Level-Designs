package org.notificationsys.Observer;

public interface IObservable {
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();

}
