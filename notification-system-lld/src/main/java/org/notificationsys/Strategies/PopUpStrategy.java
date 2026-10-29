package org.notificationsys.Strategies;

public class PopUpStrategy implements INotificationStrategy {
    public void sendNotification(String message) {
        // Simulate the process of sending popup notification.
        System.out.println("Sending Popup Notification: \n" + message);
    }
}
