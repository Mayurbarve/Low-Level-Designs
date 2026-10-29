package org.notificationsys.Notification;


public class SimpleNotification implements INotification {
    private String message;

    public SimpleNotification(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
