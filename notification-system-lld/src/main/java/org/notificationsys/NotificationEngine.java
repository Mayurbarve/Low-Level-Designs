package org.notificationsys;

import org.notificationsys.Notification.INotification;
import org.notificationsys.Observer.IObserver;
import org.notificationsys.Observer.NotificationObservable;
import org.notificationsys.Strategies.INotificationStrategy;

import java.util.ArrayList;
import java.util.List;

public class NotificationEngine implements IObserver {
    private NotificationObservable notificationObservable;
    private List<INotificationStrategy> notificationStrategies = new ArrayList<>();

    public NotificationEngine() {
        this.notificationObservable = NotificationService.INSTANCE.getObservable();
        notificationObservable.addObserver(this);
    }

    public NotificationEngine(NotificationObservable notificationObservable) {
        this.notificationObservable = notificationObservable;
        notificationObservable.addObserver(this);
    }

    public void addStrategy(INotificationStrategy strategy) {
        notificationStrategies.add(strategy);
    }

    public void update() {
        String notificationContent = notificationObservable.getNotificationMassage();
        for (INotificationStrategy strategy : notificationStrategies) {
            strategy.sendNotification(notificationContent);
        }
    }
}
