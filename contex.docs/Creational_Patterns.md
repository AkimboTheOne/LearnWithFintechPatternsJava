# Factory Method

## Business Context

A fintech platform processes various types of financial transactions (e.g., credit card, wire transfer, crypto). Based on the transaction type, a specific processor class should handle it without changing client code.

## Pattern Forces & Solution

Decouples client code from concrete classes. Enables adding new processors without modifying factory clients.

## Java Code Example (Enriched)

```java
package randomcode.patterns.creational;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory Method Pattern – Creational Design Pattern
 * Creates TransactionProcessor instances without exposing their concrete classes.
 */
public class FactoryMethodPattern {
    private static final Logger logger = Logger.getLogger(FactoryMethodPattern.class.getName());

    public enum ProcessorType { CREDIT, WIRE }

    public interface TransactionProcessor {
        void processTransaction(double amount);
    }

    public class CreditCardProcessor implements TransactionProcessor {
        @Override
        public void processTransaction(double amount) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Processing credit-card transaction: $%.2f", amount));
            }
        }
    }

    public class WireTransferProcessor implements TransactionProcessor {
        @Override
        public void processTransaction(double amount) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Processing wire transfer transaction: $%.2f", amount));
            }
        }
    }

    public class ProcessorFactory {
        public TransactionProcessor getProcessor(ProcessorType type) {
            Objects.requireNonNull(type, "ProcessorType must not be null");
            return switch (type) {
                case CREDIT -> new CreditCardProcessor();
                case WIRE   -> new WireTransferProcessor();
            };
        }
    }

    public static void main(String[] args) {
        FactoryMethodPattern pattern = new FactoryMethodPattern();
        ProcessorFactory factory = pattern.new ProcessorFactory();
        TransactionProcessor processor = factory.getProcessor(ProcessorType.CREDIT);
        processor.processTransaction(250.00);
    }
}
# Abstract Factory

## Business Context

A banking platform renders UI components for both web and mobile applications. Each platform has its own theme and components, but must ensure consistency.

## Pattern Forces & Solution

Produces families of related objects without specifying concrete classes. Ensures platform consistency and supports extensibility.

## Java Code Example (Enriched)

```java
package randomcode.patterns.creational;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract Factory Pattern – Creational Design Pattern
 * Produces families of related UI components without specifying concrete classes.
 */
public class AbstractFactoryPattern {
    private static final Logger logger = Logger.getLogger(AbstractFactoryPattern.class.getName());

    public interface Button { void render(); }
    public interface TextField { void render(); }

    public interface UIFactory {
        Button createButton();
        TextField createTextField();
    }

    public class WebUIFactory implements UIFactory {
        @Override
        public Button createButton() {
            return () -> {
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("Rendering Web Button");
                }
            };
        }
        @Override
        public TextField createTextField() {
            return () -> {
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("Rendering Web TextField");
                }
            };
        }
    }

    public class MobileUIFactory implements UIFactory {
        @Override
        public Button createButton() {
            return () -> {
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("Rendering Mobile Button");
                }
            };
        }
        @Override
        public TextField createTextField() {
            return () -> {
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("Rendering Mobile TextField");
                }
            };
        }
    }

    public static void main(String[] args) {
        AbstractFactoryPattern pattern = new AbstractFactoryPattern();
        UIFactory factory = pattern.new MobileUIFactory();
        factory.createButton().render();
        factory.createTextField().render();
    }
}
# Singleton

## Business Context

The interest rate configuration is shared across multiple modules (e.g., loans, savings, credit scoring). It must be consistent and accessed globally.

## Pattern Forces & Solution

Ensures a single instance of a shared configuration. Centralizes access, enforces consistency.

## Java Code Example (Enriched)

```java
package randomcode.patterns.creational;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton Pattern – Creational Design Pattern
 * Ensures a single instance of a shared configuration.
 */
public class SingletonPattern {
    private static final Logger logger = Logger.getLogger(SingletonPattern.class.getName());
    private static SingletonPattern instance;
    private double rate;

    private SingletonPattern() { this.rate = 0.05; }

    public static synchronized SingletonPattern getInstance() {
        if (instance == null) {
            instance = new SingletonPattern();
        }
        return instance;
    }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }

    public static void main(String[] args) {
        SingletonPattern config = SingletonPattern.getInstance();
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Current rate: " + config.getRate());
        }
    }
}
# Builder

## Business Context

Building a full mortgage application object requires collecting personal info, credit history, employment, etc., often conditionally.

## Pattern Forces & Solution

Separates complex object construction from its representation. Supports flexible and stepwise creation.

## Java Code Example (Enriched)

```java
package randomcode.patterns.creational;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Builder Pattern – Creational Design Pattern
 * Separates complex object construction from its representation.
 */
public class BuilderPattern {
    private static final Logger logger = Logger.getLogger(BuilderPattern.class.getName());

    public static class MortgageApplication {
        private String applicant;
        private double income;
        private boolean hasCreditHistory;

        private MortgageApplication() {}

        public void display() {
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Applicant: %s, Income: %.2f, CreditHistory: %s",
                    applicant, income, hasCreditHistory));
            }
        }

        public static class Builder {
            private final MortgageApplication app = new MortgageApplication();

            public Builder setApplicant(String name) {
                Objects.requireNonNull(name, "Applicant name must not be null");
                app.applicant = name;
                return this;
            }
            public Builder setIncome(double income) {
                app.income = income;
                return this;
            }
            public Builder setCreditHistory(boolean hasHistory) {
                app.hasCreditHistory = hasHistory;
                return this;
            }
            public MortgageApplication build() {
                return app;
            }
        }
    }

    public static void main(String[] args) {
        MortgageApplication app = new MortgageApplication.Builder()
            .setApplicant("John Doe")
            .setIncome(95000)
            .setCreditHistory(true)
            .build();
        app.display();
    }
}
# Prototype

## Business Context

Customer onboarding reuses validated KYC profiles with small variations (e.g., new product registration). Cloning is more efficient than recreating.

## Pattern Forces & Solution

Supports efficient object duplication without relying on constructors.

## Java Code Example (Enriched)

```java
package randomcode.patterns.creational;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Prototype Pattern – Creational Design Pattern
 * Supports efficient object duplication without relying on constructors.
 */
public class PrototypePattern {
    private static final Logger logger = Logger.getLogger(PrototypePattern.class.getName());

    public static class KYCProfile implements Cloneable {
        private String name;
        private String id;

        public KYCProfile(String name, String id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public KYCProfile clone() {
            try {
                return (KYCProfile) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

        public void display() {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("KYC: " + name + ", ID: " + id);
            }
        }
    }

    public static void main(String[] args) {
        KYCProfile original = new KYCProfile("Alice", "A1234");
        KYCProfile copy = original.clone();
        copy.display();
    }
}
# Object Pool

## Business Context

A trading engine manages connections to external market APIs. Instead of opening/closing them constantly, a pool maintains reusable connections.

## Pattern Forces & Solution

Optimizes performance and resource reuse by maintaining a pool of initialized objects.

## Java Code Example (Enriched)

```java
package randomcode.patterns.creational;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Object Pool Pattern – Creational Design Pattern
 * Optimizes performance by reusing expensive objects.
 */
public class ObjectPoolPattern {
    private static final Logger logger = Logger.getLogger(ObjectPoolPattern.class.getName());

    public static class MarketConnection {
        private final UUID id = UUID.randomUUID();
        public void connect() {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Using connection: " + id);
            }
        }
    }

    public static class ConnectionPool {
        private final Queue<MarketConnection> pool = new LinkedList<>();

        public ConnectionPool(int size) {
            for (int i = 0; i < size; i++) {
                pool.add(new MarketConnection());
            }
        }
        public MarketConnection acquire() {
            return pool.poll();
        }
        public void release(MarketConnection conn) {
            pool.offer(conn);
        }
    }

    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool(2);
        MarketConnection conn = pool.acquire();
        conn.connect();
        pool.release(conn);
    }
}