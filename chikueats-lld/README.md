# ChikuEats - Food Delivery LLD (Java)

## Run
    mvn compile exec:java -Dexec.mainClass=com.chikueats.Main
or without Maven:
    mkdir out && javac -d out $(find src -name "*.java") && java -cp out com.chikueats.Main

## Design patterns
- Singleton: RestaurantManager, OrderManager
- Factory: IOrderFactory -> NowOrderFactory, ScheduledOrderFactory
- Strategy: IPaymentStrategy -> CreditCard / NetBanking / UPI
- Orchestrator: ChikuEats
