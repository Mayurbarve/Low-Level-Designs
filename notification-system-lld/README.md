# Notification System (Low-Level Design)

A small Java project demonstrating how a notification can be decorated with extra content and delivered through multiple channels. Email, SMS, and popup delivery are simulated by printing to the console.

## Features

- Creates notifications through the `INotification` interface.
- Adds a timestamp and signature using decorators.
- Publishes notifications to observers.
- Logs each notification and passes it to configured delivery strategies.
- Demonstrates the **Singleton**, **Observer**, **Strategy**, and **Decorator** design patterns.

## Workflow

1. `Main` creates a `SimpleNotification`.
2. `TimeStampDecorator` and `SignatureDecorator` add details to its message.
3. `NotificationService` receives the decorated notification.
4. The service updates `NotificationObservable`, which notifies its observers.
5. `LogObserver` prints the message, and `NotificationEngine` sends it to each configured strategy.
6. The email, SMS, and popup strategies print their simulated delivery output.

```text
Main
  -> NotificationService
      -> NotificationObservable
          -> LogObserver
          -> NotificationEngine
              -> EmailStrategy
              -> SMSStrategy
              -> PopUpStrategy
```

## Project structure

```text
src/main/java/org/notificationsys/
├── Main.java                       # Application entry point and example setup
├── NotificationService.java        # Singleton service that accepts notifications
├── Decorator/
│   ├── INotificationDecorator.java # Base wrapper for notification decorators
│   ├── TimeStampDecorator.java     # Adds the current time to a message
│   └── SignatureDecorator.java     # Adds a signature to a message
├── Notification/
│   ├── INotification.java          # Common notification interface
│   └── SimpleNotification.java     # Basic notification implementation
├── Observer/
│   ├── IObservable.java            # Observable contract
│   ├── IObserver.java              # Observer contract
│   ├── NotificationObservable.java # Stores current notification and notifies observers
│   ├── LogObserver.java            # Logs notifications
│   └── NotificationEngine.java     # Dispatches through configured strategies
└── Strategies/
    ├── INotificationStrategy.java # Delivery strategy contract
    ├── EmailStrategy.java          # Simulated email delivery
    ├── SMSStrategy.java            # Simulated SMS delivery
    └── PopUpStrategy.java          # Simulated popup delivery
```

## Requirements

- JDK 25, as configured in `pom.xml`
- Maven, if using the Maven commands below

## Run

From the project root, compile and package with Maven:

```bash
mvn package
```

Then run the application:

```bash
java -cp target/classes org.notificationsys.Main
```

The program prints the logged notification and the output from each delivery strategy. The timestamp is generated when the notification message is read.

## Design patterns in this example

- **Singleton:** `NotificationService` is an enum singleton (`NotificationService.INSTANCE`).
- **Observer:** `NotificationObservable` notifies `LogObserver` and `NotificationEngine` when a notification is set.
- **Strategy:** `NotificationEngine` can dispatch through any registered `INotificationStrategy` implementation.
- **Decorator:** `TimeStampDecorator` and `SignatureDecorator` wrap an `INotification` and add message content without changing the base notification class.

## Scope

This is an in-memory learning example. Delivery strategies currently print to standard output; they do not connect to real email, SMS, or popup services. `NotificationService` keeps sent notifications in memory for the lifetime of the process.
