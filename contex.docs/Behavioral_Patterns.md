# Iterator

## Business Context

In a banking dashboard, different financial products (savings, loans, investments) are stored in a custom collection. The client UI should iterate over them uniformly.

## Pattern Forces & Solution

Provides a standard way to access elements of a collection without exposing its internal representation.

## Java Code Example (Enriched)

```java
package randomcode.patterns.behavioral;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Iterator Pattern – Behavioral Design Pattern
 * Enables traversal of AccountCollection without exposing its internal list.
 */
public class IteratorPattern {
    private static final Logger logger = Logger.getLogger(IteratorPattern.class.getName());

    public static class Account {
        private final String type;
        public Account(String type) { this.type = type; }
        public String getType() { return type; }
    }

    public static class AccountCollection implements Iterable<Account> {
        private final List<Account> accounts = new ArrayList<>();
        public void add(Account acc) {
            accounts.add(acc);
        }
        @Override
        public Iterator<Account> iterator() {
            return accounts.iterator();
        }
    }

    public static void main(String[] args) {
        AccountCollection coll = new AccountCollection();
        coll.add(new Account("Savings"));
        coll.add(new Account("Loan"));
        for (Account acc : coll) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Account type: " + acc.getType());
            }
        }
    }
}
# Command

## Business Context

Banking operations (transfer, deposit, withdraw) must be encapsulated as objects to support undo/redo and audit trails.

## Pattern Forces & Solution

Encapsulates requests as objects, allowing queuing, logging, and undo functionality.

## Java Code Example (Enriched)

```java
package randomcode.patterns.behavioral;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Command Pattern – Behavioral Design Pattern
 * Encapsulates financial operations as commands.
 */
public class CommandPattern {
    private static final Logger logger = Logger.getLogger(CommandPattern.class.getName());

    public interface Command {
        void execute();
    }

    public static class TransferCommand implements Command {
        @Override
        public void execute() {
            logger.info("Executing transfer command");
        }
    }

    public static class Invoker {
        private final List<Command> history = new ArrayList<>();

        public void submit(Command cmd) {
            history.add(cmd);
            cmd.execute();
        }
    }

    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        invoker.submit(new TransferCommand());
    }
}
# Observer

## Business Context

When an asset’s price updates, multiple systems (dashboard, alert service, logger) must be notified automatically.

## Pattern Forces & Solution

Defines a one-to-many dependency between Subject and Observers, updating all observers on state change.

## Java Code Example (Enriched)

```java
package randomcode.patterns.behavioral;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Observer Pattern – Behavioral Design Pattern
 * Notifies registered observers upon price updates.
 */
public class ObserverPattern {
    private static final Logger logger = Logger.getLogger(ObserverPattern.class.getName());

    public interface Observer {
        void update(String asset, double price);
    }

    public static class PriceFeed {
        private final List<Observer> observers = new ArrayList<>();

        public void add(Observer o) {
            observers.add(o);
        }

        public void setPrice(String asset, double price) {
            for (Observer o : observers) {
                o.update(asset, price);
            }
        }
    }

    public static class Dashboard implements Observer {
        @Override
        public void update(String asset, double price) {
            logger.info("Dashboard: " + asset + " -> $" + price);
        }
    }

    public static void main(String[] args) {
        PriceFeed feed = new PriceFeed();
        feed.add(new Dashboard());
        feed.setPrice("BTC", 30500.0);
    }
}
# Template Method

## Business Context

Different fund transfers (domestic, international) share common steps with variations in the transfer step.

## Pattern Forces & Solution

Defines the skeleton of an algorithm, deferring specific steps to subclasses.

## Java Code Example (Enriched)

```java
package randomcode.patterns.behavioral;

import java.util.logging.Logger;

/**
 * Template Method Pattern – Behavioral Design Pattern
 * Defines a template for fund transfers, with variant transfer logic.
 */
public class TemplateMethodPattern {
    private static final Logger logger = Logger.getLogger(TemplateMethodPattern.class.getName());

    public abstract static class FundTransfer {
        public final void executeTransfer() {
            validate();
            authenticate();
            transfer();
            notifyUser();
        }
        protected void validate() {
            logger.info("Validating account");
        }
        protected void authenticate() {
            logger.info("Authenticating user");
        }
        protected abstract void transfer();
        protected void notifyUser() {
            logger.info("Notifying user");
        }
    }

    public static class DomesticTransfer extends FundTransfer {
        @Override
        protected void transfer() {
            logger.info("Domestic transfer executed");
        }
    }

    public static void main(String[] args) {
        FundTransfer ft = new DomesticTransfer();
        ft.executeTransfer();
    }
}
# Strategy

## Business Context

Tax calculation varies by account type and customer region. The system must apply different rules dynamically at runtime.

## Pattern Forces & Solution

Defines a family of algorithms, encapsulates each one, and makes them interchangeable.

## Java Code Example (Enriched)

```java
package randomcode.patterns.behavioral;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Strategy Pattern – Behavioral Design Pattern
 * Encapsulates tax calculation algorithms for different regions.
 */
public class StrategyPattern {
    private static final Logger logger = Logger.getLogger(StrategyPattern.class.getName());

    /** Strategy interface for tax calculation. */
    public interface TaxStrategy {
        double calculate(double income);
    }

    /** Concrete strategy for US tax rules. */
    public class USStrategy implements TaxStrategy {
        @Override
        public double calculate(double income) {
            return income * 0.30;
        }
    }

    /** Concrete strategy for UK tax rules. */
    public class UKStrategy implements TaxStrategy {
        @Override
        public double calculate(double income) {
            return income * 0.25;
        }
    }

    /** Context class using a TaxStrategy. */
    public class TaxCalculator {
        private final TaxStrategy strategy;

        public TaxCalculator(TaxStrategy strategy) {
            this.strategy = Objects.requireNonNull(strategy, "Strategy must not be null");
        }

        public double apply(double amount) {
            return strategy.calculate(amount);
        }
    }

    public static void main(String[] args) {
        StrategyPattern pattern = new StrategyPattern();
        TaxCalculator calc = pattern.new TaxCalculator(pattern.new USStrategy());
        double tax = calc.apply(100_000);
        logger.info("Calculated tax: " + tax);
    }
}
# Chain of Responsibility

## Business Context

Transaction validation includes multiple steps: balance check, fraud check, and limit check. Each must pass before proceeding.

## Pattern Forces & Solution

Decouples sender and receiver by allowing multiple handlers to process a request in sequence.

## Java Code Example (Enriched)

```java
package randomcode.patterns.behavioral;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Chain of Responsibility Pattern – Behavioral Design Pattern
 * Processes transaction validation steps in a chain.
 */
public class ChainOfResponsibilityPattern {
    private static final Logger logger = Logger.getLogger(ChainOfResponsibilityPattern.class.getName());

    /** Handler base class. */
    public abstract class Handler {
        protected Handler next;

        public Handler linkWith(Handler next) {
            this.next = Objects.requireNonNull(next, "Next handler must not be null");
            return next;
        }

        public abstract boolean handle(String request);
    }

    /** Concrete handler for balance check. */
    public class BalanceHandler extends Handler {
        @Override
        public boolean handle(String req) {
            logger.info("Checking balance for: " + req);
            return next == null || next.handle(req);
        }
    }

    /** Concrete handler for fraud check. */
    public class FraudHandler extends Handler {
        @Override
        public boolean handle(String req) {
            logger.info("Checking fraud for: " + req);
            return next == null || next.handle(req);
        }
    }

    public static void main(String[] args) {
        ChainOfResponsibilityPattern pattern = new ChainOfResponsibilityPattern();
        Handler handler = pattern.new BalanceHandler();
        handler.linkWith(pattern.new FraudHandler());
        boolean result = handler.handle("TXN123");
        logger.info("Validation result: " + result);
    }
}
# Interpreter

## Business Context

A financial rule engine evaluates expressions like 'amount > 1000 AND country == US' to determine eligibility.

## Pattern Forces & Solution

Defines a grammatical representation for a language and an interpreter to evaluate sentences.

## Java Code Example (Enriched)

```java
package randomcode.patterns.behavioral;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Interpreter Pattern – Behavioral Design Pattern
 * Interprets boolean financial rules against a context.
 */
public class InterpreterPattern {
    private static final Logger logger = Logger.getLogger(InterpreterPattern.class.getName());

    /** Context with data to interpret. */
    public static class Context {
        public final int amount;
        public final String country;
        public Context(int amount, String country) {
            this.amount = amount;
            this.country = country;
        }
    }

    /** Expression interface. */
    public interface Expression {
        boolean interpret(Context context);
    }

    /** Concrete expression for amount comparison. */
    public class AmountExpression implements Expression {
        @Override
        public boolean interpret(Context ctx) {
            return ctx.amount > 1000;
        }
    }

    /** Concrete expression for country comparison. */
    public class CountryExpression implements Expression {
        @Override
        public boolean interpret(Context ctx) {
            return "US".equals(ctx.country);
        }
    }

    /** Composite expression for logical AND. */
    public class AndExpression implements Expression {
        private final Expression exp1, exp2;
        public AndExpression(Expression exp1, Expression exp2) {
            this.exp1 = Objects.requireNonNull(exp1);
            this.exp2 = Objects.requireNonNull(exp2);
        }
        @Override
        public boolean interpret(Context ctx) {
            return exp1.interpret(ctx) && exp2.interpret(ctx);
        }
    }

    public static void main(String[] args) {
        InterpreterPattern pattern = new InterpreterPattern();
        Expression rule = pattern.new AndExpression(pattern.new AmountExpression(), pattern.new CountryExpression());
        boolean result = rule.interpret(new Context(1200, "US"));
        logger.info("Interpretation result: " + result);
    }
}
# Mediator

## Business Context

Different UI components (form, summary) must coordinate updates without direct references.

## Pattern Forces & Solution

Encapsulates interaction between multiple objects, promoting loose coupling.

## Java Code Example (Enriched)

```java
package randomcode.patterns.behavioral;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mediator Pattern – Behavioral Design Pattern
 * Coordinates interaction between Form and Summary components.
 */
public class MediatorPattern {
    private static final Logger logger = Logger.getLogger(MediatorPattern.class.getName());

    /** Mediator interface. */
    public interface Mediator {
        void notify(Component sender, String event);
    }

    /** Base component. */
    public abstract class Component {
        protected Mediator mediator;
        protected Component(Mediator m) { this.mediator = m; }
    }

    /** Concrete component: Summary. */
    public class Summary extends Component {
        public Summary(Mediator m) { super(m); }
        public void update() {
            logger.info("Summary updated");
        }
    }

    /** Concrete component: Form. */
    public class Form extends Component {
        public Form(Mediator m) { super(m); }
        public void input() {
            logger.info("Form input received");
            mediator.notify(this, "input");
        }
    }

    /** Concrete mediator. */
    public class UIManager implements Mediator {
        private final Summary summary;
        public UIManager(Summary summary) {
            this.summary = summary;
        }
        @Override
        public void notify(Component sender, String event) {
            if ("input".equals(event)) {
                summary.update();
            }
        }
    }

    public static void main(String[] args) {
        MediatorPattern pattern = new MediatorPattern();
        Summary summary = pattern.new Summary(null);
        UIManager manager = pattern.new UIManager(summary);
        Form form = pattern.new Form(manager);
        summary.mediator = manager;
        form.input();
    }
}
# Memento

## Business Context

Editing a loan application must support rollback (undo) to previous states.

## Pattern Forces & Solution

Captures and externalizes an object’s internal state for later restoration.

## Java Code Example (Enriched)

```java
package randomcode.patterns.behavioral;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Memento Pattern – Behavioral Design Pattern
 * Saves and restores Loan internal state.
 */
public class MementoPattern {
    private static final Logger logger = Logger.getLogger(MementoPattern.class.getName());

    /** Memento: stores state. */
    public static class LoanState {
        private final String status;
        public LoanState(String status) { this.status = status; }
        public String getStatus() { return status; }
    }

    /** Originator: Loan object. */
    public class Loan {
        private String status;
        public void setStatus(String s) { this.status = s; }
        public LoanState save() { return new LoanState(status); }
        public void restore(LoanState m) { this.status = m.getStatus(); }
        public void print() { logger.info("Status: " + status); }
    }

    public static void main(String[] args) {
        MementoPattern pattern = new MementoPattern();
        Loan loan = pattern.new Loan();
        loan.setStatus("Submitted");
        LoanState saved = loan.save();
        loan.setStatus("Approved");
        loan.restore(saved);
        loan.print();
    }
}
# Null Object

## Business Context

Reporting system must handle missing customers without null checks by using a placeholder.

## Pattern Forces & Solution

Provides a default object in place of null to avoid conditional checks.

## Java Code Example (Enriched)

```java
package randomcode.patterns.behavioral;
import java.util.logging.Logger;

/**
 * Null Object Pattern – Behavioral Design Pattern
 * Uses a placeholder for missing Customer instances.
 */
public class NullObjectPattern {
    private static final Logger logger = Logger.getLogger(NullObjectPattern.class.getName());

    public interface Customer {
        String getName();
    }

    public class RealCustomer implements Customer {
        private final String name;
        public RealCustomer(String name) { this.name = name; }
        @Override public String getName() { return name; }
    }

    public class NullCustomer implements Customer {
        @Override public String getName() { return "Unknown"; }
    }

    public static void main(String[] args) {
        NullObjectPattern pattern = new NullObjectPattern();
        Customer cust = pattern.new NullCustomer();
        logger.info("Customer name: " + cust.getName());
    }
}
# State

## Business Context

Loan application transitions through states (draft, submitted, approved), each with distinct actions.

## Pattern Forces & Solution

Allows an object to alter its behavior when its internal state changes.

## Java Code Example (Enriched)

```java
package randomcode.patterns.behavioral;
import java.util.logging.Logger;

/**
 * State Pattern – Behavioral Design Pattern
 * Changes LoanContext behavior based on its current state.
 */
public class StatePattern {
    private static final Logger logger = Logger.getLogger(StatePattern.class.getName());

    public interface LoanState {
        void handle();
    }

    public class DraftState implements LoanState {
        @Override public void handle() { logger.info("Loan is in draft state"); }
    }

    public class ApprovedState implements LoanState {
        @Override public void handle() { logger.info("Loan has been approved"); }
    }

    public class LoanContext {
        private LoanState state;
        public void setState(LoanState s) { this.state = s; }
        public void process() { state.handle(); }
    }

    public static void main(String[] args) {
        StatePattern pattern = new StatePattern();
        LoanContext ctx = pattern.new LoanContext();
        ctx.setState(pattern.new DraftState());
        ctx.process();
        ctx.setState(pattern.new ApprovedState());
        ctx.process();
    }
}
# Visitor

## Business Context

An audit engine applies different report operations (summary, detailed) to a set of transactions.

## Pattern Forces & Solution

Separates an algorithm from the objects on which it operates, enabling addition of new operations.

## Java Code Example (Enriched)

```java
package randomcode.patterns.behavioral;
import java.util.logging.Logger;

/**
 * Visitor Pattern – Behavioral Design Pattern
 * Applies operations to Transaction objects without modifying them.
 */
public class VisitorPattern {
    private static final Logger logger = Logger.getLogger(VisitorPattern.class.getName());

    public interface Visitable {
        void accept(Visitor v);
    }

    public interface Visitor {
        void visit(Transaction tx);
    }

    public class Transaction implements Visitable {
        @Override
        public void accept(Visitor v) {
            v.visit(this);
        }
    }

    public class SummaryReport implements Visitor {
        @Override public void visit(Transaction tx) {
            logger.info("Generating summary report for transaction");
        }
    }

    public static void main(String[] args) {
        VisitorPattern pattern = new VisitorPattern();
        Transaction tx = pattern.new Transaction();
        tx.accept(pattern.new SummaryReport());
    }
}