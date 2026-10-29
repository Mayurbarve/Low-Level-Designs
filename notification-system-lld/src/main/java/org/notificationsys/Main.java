package org.notificationsys;

import org.notificationsys.Decorator.SignatureDecorator;
import org.notificationsys.Decorator.TimeStampDecorator;
import org.notificationsys.Notification.INotification;
import org.notificationsys.Notification.SimpleNotification;
import org.notificationsys.Observer.Logger;
import org.notificationsys.Strategies.EmailStrategy;
import org.notificationsys.Strategies.PopUpStrategy;
import org.notificationsys.Strategies.SMSStrategy;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Create NotificationService.
        NotificationService notificationService = NotificationService.INSTANCE;

        // Create Logger Observer
        Logger logger = new Logger();

        // Create NotificationEngine observers.
        NotificationEngine notificationEngine = new NotificationEngine();

        notificationEngine.addStrategy(new EmailStrategy("mayurchike@gmail.com"));
        notificationEngine.addStrategy(new SMSStrategy("7057627532"));
        notificationEngine.addStrategy(new PopUpStrategy());

        INotification notification = new SimpleNotification(" Your order has been shipped");
        notification = new TimeStampDecorator(notification);
        notification = new SignatureDecorator(notification, "Customer care");

        notificationService.sendNotification(notification);
    }

}
