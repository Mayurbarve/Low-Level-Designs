package org.notificationsys.Observer;

import org.notificationsys.NotificationService;

public class LogObserver implements IObserver{
    private NotificationObservable observable;

    public LogObserver() {
        this.observable = NotificationService.INSTANCE.getObservable();
        observable.addObserver(this);
    }

    public LogObserver(NotificationObservable observable) {
        observable.addObserver(this);
        this.observable = observable;
    }

    public void update() {
        System.out.println("Logging New Notification : \n" + observable.getNotificationMassage());
    }

}
