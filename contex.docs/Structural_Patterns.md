# Adapter

## Business Context

An old legacy banking system provides customer records via SOAP, while the new system expects REST-based JSON responses. The systems must communicate without changing legacy code.

## Pattern Forces & Solution

Converts one interface into another the client expects. Enables reuse of legacy components without modifying them.

## Java Code Example (Enriched)

```java
package randomcode.patterns.structural;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Adapter Pattern – Structural Design Pattern
 * Adapts LegacyCustomerService (SOAP/XML) to CustomerService (JSON).
 */
public class AdapterPattern {
    private static final Logger logger = Logger.getLogger(AdapterPattern.class.getName());

    /**
     * Target interface expected by clients.
     */
    public interface CustomerService {
        String getCustomerJson(String customerId);
    }

    /**
     * Adaptee: legacy SOAP-based service.
     */
    public class LegacyCustomerService {
        public String getCustomerXML(String customerId) {
            return "<customer><id>" + customerId + "</id></customer>";
        }
    }

    /**
     * Adapter: translates XML from LegacyCustomerService into JSON.
     */
    public class CustomerServiceAdapter implements CustomerService {
        private final LegacyCustomerService legacy = new LegacyCustomerService();

        @Override
        public String getCustomerJson(String customerId) {
            Objects.requireNonNull(customerId, "CustomerId must not be null");
            String xml = legacy.getCustomerXML(customerId);
            // Simplified transformation
            String json = "{\"id\":\"" + customerId + "\"}";
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Converted XML to JSON: " + json);
            }
            return json;
        }
    }

    public static void main(String[] args) {
        AdapterPattern pattern = new AdapterPattern();
        CustomerService service = pattern.new CustomerServiceAdapter();
        System.out.println(service.getCustomerJson("123"));
    }
}
# Bridge

## Business Context

A digital banking app sends notifications (email/SMS) for different alert types. Notification channels and alert types must evolve independently.

## Pattern Forces & Solution

Decouples an abstraction from its implementation so both can vary independently.

## Java Code Example (Enriched)

```java
package randomcode.patterns.structural;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Bridge Pattern – Structural Design Pattern
 * Separates Alert hierarchy from NotificationSender implementations.
 */
public class BridgePattern {
    private static final Logger logger = Logger.getLogger(BridgePattern.class.getName());

    /**
     * Implementor interface.
     */
    public interface NotificationSender {
        void send(String message);
    }

    /**
     * Concrete Implementor for email.
     */
    public class EmailSender implements NotificationSender {
        @Override
        public void send(String message) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Email sent: " + message);
            }
        }
    }

    /**
     * Concrete Implementor for SMS.
     */
    public class SmsSender implements NotificationSender {
        @Override
        public void send(String message) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("SMS sent: " + message);
            }
        }
    }

    /**
     * Abstraction for alerts.
     */
    public abstract class Alert {
        protected final NotificationSender sender;

        protected Alert(NotificationSender sender) {
            this.sender = sender;
        }

        public abstract void notifyUser(String msg);
    }

    /**
     * Refined Abstraction for low-balance alerts.
     */
    public class LowBalanceAlert extends Alert {
        public LowBalanceAlert(NotificationSender sender) {
            super(sender);
        }

        @Override
        public void notifyUser(String msg) {
            sender.send("Low Balance Alert: " + msg);
        }
    }

    public static void main(String[] args) {
        BridgePattern pattern = new BridgePattern();
        Alert alert = pattern.new LowBalanceAlert(pattern.new SmsSender());
        alert.notifyUser("Balance below $100");
    }
}
# Composite

## Business Context

A wealth management portfolio contains individual accounts and nested portfolios. Clients should treat both uniformly.

## Pattern Forces & Solution

Composes objects into tree structures to represent part-whole hierarchies. Clients treat individual and composite uniformly.

## Java Code Example (Enriched)

```java
package randomcode.patterns.structural;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Composite Pattern – Structural Design Pattern
 * Represents portfolio and accounts uniformly.
 */
public class CompositePattern {
    private static final Logger logger = Logger.getLogger(CompositePattern.class.getName());

    /**
     * Component interface.
     */
    public interface AccountComponent {
        void display();
    }

    /**
     * Leaf class representing an account.
     */
    public class Account implements AccountComponent {
        private final String name;

        public Account(String name) {
            this.name = name;
        }

        @Override
        public void display() {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Account: " + name);
            }
        }
    }

    /**
     * Composite class representing a portfolio.
     */
    public class Portfolio implements AccountComponent {
        private final String name;
        private final List<AccountComponent> components = new ArrayList<>();

        public Portfolio(String name) {
            this.name = name;
        }

        public void add(AccountComponent component) {
            components.add(component);
        }

        @Override
        public void display() {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Portfolio: " + name);
            }
            for (AccountComponent comp : components) {
                comp.display();
            }
        }
    }

    public static void main(String[] args) {
        CompositePattern pattern = new CompositePattern();
        Portfolio main = pattern.new Portfolio("Main");
        main.add(pattern.new Account("Savings"));
        Portfolio inv = pattern.new Portfolio("Investments");
        inv.add(pattern.new Account("Stocks"));
        inv.add(pattern.new Account("Bonds"));
        main.add(inv);
        main.display();
    }
}
# Decorator

## Business Context

Core transactions need logging, encryption, and auditing. Features should be added dynamically.

## Pattern Forces & Solution

Attaches additional responsibilities to objects dynamically. Supports flexible addition/removal of behaviors.

## Java Code Example (Enriched)

```java
package randomcode.patterns.structural;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Decorator Pattern – Structural Design Pattern
 * Dynamically adds responsibilities like encryption to transactions.
 */
public class DecoratorPattern {
    private static final Logger logger = Logger.getLogger(DecoratorPattern.class.getName());

    /**
     * Component interface.
     */
    public interface Transaction {
        void execute();
    }

    /**
     * Concrete component.
     */
    public class BasicTransaction implements Transaction {
        @Override
        public void execute() {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Executing basic transaction");
            }
        }
    }

    /**
     * Decorator for encryption.
     */
    public class EncryptedTransaction implements Transaction {
        private final Transaction wrapped;

        public EncryptedTransaction(Transaction wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public void execute() {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Encrypting transaction");
            }
            wrapped.execute();
        }
    }

    public static void main(String[] args) {
        DecoratorPattern pattern = new DecoratorPattern();
        Transaction tx = pattern.new EncryptedTransaction(pattern.new BasicTransaction());
        tx.execute();
    }
}
# Facade

## Business Context

Payment processing involves validation, fraud checks, and gateway interaction. Clients need a simple interface.

## Pattern Forces & Solution

Provides a unified interface to a complex subsystem, simplifying client usage.

## Java Code Example (Enriched)

```java
package randomcode.patterns.structural;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Facade Pattern – Structural Design Pattern
 * Simplifies payment processing via a unified interface.
 */
public class FacadePattern {
    private static final Logger logger = Logger.getLogger(FacadePattern.class.getName());

    public class Validator {
        public boolean validateCard(String card) {
            return card.startsWith("4");
        }
    }

    public class FraudChecker {
        public boolean check(String card) {
            return !card.equals("4000000000000000");
        }
    }

    public class PaymentGateway {
        public void process(String card, double amount) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Processing $" + amount + " for card " + card);
            }
        }
    }

    /**
     * Facade providing a simple pay() method.
     */
    public class PaymentFacade {
        private final Validator validator = new Validator();
        private final FraudChecker checker = new FraudChecker();
        private final PaymentGateway gateway = new PaymentGateway();

        public void pay(String card, double amount) {
            if (validator.validateCard(card) && checker.check(card)) {
                gateway.process(card, amount);
            } else if (logger.isLoggable(Level.INFO)) {
                logger.info("Payment declined for card: " + card);
            }
        }
    }

    public static void main(String[] args) {
        FacadePattern pattern = new FacadePattern();
        PaymentFacade facade = pattern.new PaymentFacade();
        facade.pay("4111111111111111", 150.0);
    }
}
# Flyweight

## Business Context

Rendering thousands of account icons in a dashboard; icons share intrinsic state (type), extrinsic state varies.

## Pattern Forces & Solution

Reduces memory footprint by sharing intrinsic state between many objects.

## Java Code Example (Enriched)

```java
package randomcode.patterns.structural;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Flyweight Pattern – Structural Design Pattern
 * Shares account icon instances to minimize memory usage.
 */
public class FlyweightPattern {
    private static final Logger logger = Logger.getLogger(FlyweightPattern.class.getName());

    public interface Icon {
        void render(String accountId);
    }

    public class AccountIcon implements Icon {
        private final String type;

        public AccountIcon(String type) {
            this.type = type;
        }

        @Override
        public void render(String accountId) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Rendering " + type + " icon for account " + accountId);
            }
        }
    }

    public class IconFactory {
        private final Map<String, Icon> cache = new HashMap<>();

        public Icon getIcon(String type) {
            return cache.computeIfAbsent(type, AccountIcon::new);
        }
    }

    public static void main(String[] args) {
        FlyweightPattern pattern = new FlyweightPattern();
        IconFactory factory = pattern.new IconFactory();
        Icon icon1 = factory.getIcon("savings");
        icon1.render("AC123");
        Icon icon2 = factory.getIcon("savings");
        icon2.render("AC456");
    }
}
# Proxy

## Business Context

Access to credit score service must be controlled by user role; unauthorized roles should be blocked.

## Pattern Forces & Solution

Provides a surrogate representing the real object to control access or add behavior.

## Java Code Example (Enriched)

```java
package randomcode.patterns.structural;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Proxy Pattern – Structural Design Pattern
 * Controls access to credit service with role-based authorization.
 */
public class ProxyPattern {
    private static final Logger logger = Logger.getLogger(ProxyPattern.class.getName());

    public interface CreditService {
        int getScore(String userId);
    }

    public class RealCreditService implements CreditService {
        @Override
        public int getScore(String userId) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Accessing real credit data for " + userId);
            }
            return 750;
        }
    }

    public class CreditServiceProxy implements CreditService {
        private final RealCreditService real = new RealCreditService();
        private final Set<String> roles = Set.of("admin","audit");
        private final String role;

        public CreditServiceProxy(String role) {
            this.role = role;
        }

        @Override
        public int getScore(String userId) {
            if (!roles.contains(role)) {
                throw new SecurityException("Access denied for role: " + role);
            }
            return real.getScore(userId);
        }
    }

    public static void main(String[] args) {
        ProxyPattern pattern = new ProxyPattern();
        CreditService service = pattern.new CreditServiceProxy("admin");
        System.out.println("Score: " + service.getScore("U123"));
    }
}