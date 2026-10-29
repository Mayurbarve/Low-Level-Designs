package org.notificationsys.Decorator;

import org.notificationsys.Notification.INotification;

abstract class INotificationDecorator implements INotification {

    protected INotification notification;

    public INotificationDecorator(INotification notification) {
        this.notification = notification;
    }
}
