package org.notificationsys;

import org.notificationsys.Notification.INotification;
import org.notificationsys.Observer.IObservable;
import org.notificationsys.Observer.NotificationObservable;

import java.util.ArrayList;
import java.util.List;

public enum NotificationService {
    INSTANCE;

    private final NotificationObservable observable;
    private final List<INotification> notifications;

    NotificationService() {
        observable = new NotificationObservable();
        notifications = new ArrayList<>();
    }

    // Expose the observable so observers can attach.
    public NotificationObservable getObservable() {
        return observable;
    }

    // Creates a new Notification and notifies observers.
    public void sendNotification(INotification notification) {
        notifications.add(notification);
        observable.setNotification(notification);
    }

}
