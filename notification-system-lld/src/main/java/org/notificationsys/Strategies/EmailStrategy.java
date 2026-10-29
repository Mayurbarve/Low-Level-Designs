package org.notificationsys.Strategies;

import org.notificationsys.Notification.INotification;

public class EmailStrategy implements INotificationStrategy {

    private String eMailId;

    public EmailStrategy(String eMailId) {
        this.eMailId = eMailId;
    }

    public void sendNotification(String message) {
        // Simulate the process of sending an email notification,
        // representing the dispatch of messages to users via email.​
        System.out.println("Sending email Notification to: " + eMailId + "\n" + message);
    }


}
