package com.akimbotheone.pg.patterns.structural;

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
