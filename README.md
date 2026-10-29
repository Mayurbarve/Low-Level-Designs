# Low-Level Design Projects

This repository contains standalone Java projects that demonstrate low-level design concepts and design patterns.

## Projects

### [ChikuEats](chikueats-lld/README.md)

A food delivery example with order creation, payment strategies, and manager singletons.

Run it from its project directory:

```bash
cd chikueats-lld
mvn compile exec:java -Dexec.mainClass=com.chikueats.Main
```

### [Notification System](notification-system-lld/README.md)

A notification example with observable updates, logging and delivery observers, channel strategies, and message decorators.

Run it from its project directory:

```bash
cd notification-system-lld
mvn package
java -cp target/classes org.notificationsys.Main
```

Each project has its own `pom.xml` and can be built independently. See its README for details.
