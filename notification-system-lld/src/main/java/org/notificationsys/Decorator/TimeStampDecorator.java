package org.notificationsys.Decorator;

import org.notificationsys.Notification.INotification;

import java.time.LocalTime;

public class TimeStampDecorator extends INotificationDecorator{

    public TimeStampDecorator(INotification notification){
        super(notification);
    }

    public String getMessage(){
        LocalTime currentTime = LocalTime.now();
        return currentTime + notification.getMessage();
    }
}
