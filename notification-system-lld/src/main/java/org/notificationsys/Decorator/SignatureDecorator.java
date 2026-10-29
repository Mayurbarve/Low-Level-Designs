package org.notificationsys.Decorator;

import org.notificationsys.Notification.INotification;

public class SignatureDecorator extends INotificationDecorator{
    private String signature;

    public SignatureDecorator(INotification notification, String signature) {
        super(notification);
        this.signature = signature;
    }

    public String getMessage(){
        return notification.getMessage() + "\n-- " + signature + "\n\n ";
    }
}
