package com.akimbotheone.pg.patterns.structural;

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
