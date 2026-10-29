package org.notificationsys.Strategies;

import org.notificationsys.Notification.INotification;

public interface INotificationStrategy {
    void sendNotification(String message);
}
