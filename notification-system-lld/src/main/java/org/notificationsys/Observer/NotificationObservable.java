package org.notificationsys.Observer;

import org.notificationsys.Notification.INotification;

import java.util.ArrayList;
import java.util.List;

public class NotificationObservable implements IObservable{
    private List<IObserver> observers = new ArrayList<>();
    private INotification currentNotification = null;

    public void addObserver(IObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for(IObserver observer : observers){
            observer.update();
        }
    }

    public void setNotification(INotification notification){
        this.currentNotification = notification;
        notifyObservers();
    }

    public INotification getNotification(){
        return currentNotification;
    }

    public String getNotificationMassage(){
        return currentNotification.getMessage();
    }

}
