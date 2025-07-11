package com.akimbotheone.pg.patterns.behavioral;
import java.util.Objects;
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
